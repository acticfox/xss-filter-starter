package com.github.acticfox.xss;


import java.util.Objects;

/**
 * Created by fanyong.kfy on 2018/5/23.
 * xss工具类
 */
public class XssUtil {

	public static String cleanXSS(String value) {
		if (Objects.isNull(value)) {
			return value;
		}
		// You'll need to remove the spaces from the html entities below
		value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
		value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
		value = value.replaceAll("'", "& #39;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		value = value.replaceAll("script", "");
		return value;
	}

	public static String filter(String input) {
		if (Objects.isNull(input)) {
			return input;
		}
		return new HTMLFilter().filter(input);
	}
}
