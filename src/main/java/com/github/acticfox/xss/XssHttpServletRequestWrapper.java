package com.github.acticfox.xss;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.web.servlet.HandlerMapping;

/**
 * Created by fanyong.kfy on 2018/5/23.
 * xss 包装
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		return XssUtil.filter(value);
	}

	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		return XssUtil.filter(value);
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if (values != null) {
			int length = values.length;
			String[] escapseValues = new String[length];
			for (int i = 0; i < length; i++) {
				escapseValues[i] = XssUtil.filter(values[i]);
			}
			return escapseValues;
		}
		return super.getParameterValues(name);
	}

	/**
	 * 主要是针对HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE 获取pathvalue的时候把原来的pathvalue经过xss过滤掉
	 */
	@Override
	public Object getAttribute(String name) {
		// 获取pathvalue的值
		if (HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE.equals(name)) {
			Map uriTemplateVars = (Map) super.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
			if (Objects.isNull(uriTemplateVars)) {
				return uriTemplateVars;
			}
			Map newMap = new LinkedHashMap<>();
			uriTemplateVars.forEach((key, value) -> {
				if (value instanceof String) {
					newMap.put(key, XssUtil.filter((String) value));
				} else {
					newMap.put(key, value);

				}
			});
			return newMap;
		} else {
			return super.getAttribute(name);
		}
	}
}