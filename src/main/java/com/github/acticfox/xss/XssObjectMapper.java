package com.github.acticfox.xss;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Created by  fanyong.kfy on 2018/5/23.
 * 创建xss的json转换器
 */
public class XssObjectMapper extends ObjectMapper {

	public XssObjectMapper() {
		SimpleModule module = new SimpleModule("XSS JsonDeserializer");
		module.addDeserializer(String.class, new XssStringJsonDeserializer());
		this.registerModule(module);
	}
}
