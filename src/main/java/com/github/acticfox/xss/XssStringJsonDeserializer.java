package com.github.acticfox.xss;

import java.io.IOException;

import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Created by fanyong.kfy on 2018/5/23.
 * 基于xss的JsonDeserializer
 */
public class XssStringJsonDeserializer extends JsonDeserializer<String> {


	@Override
	public Class<String> handledType() {
		return String.class;
	}

	@Override
	public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
		return HtmlUtils.htmlEscape(jsonParser.getValueAsString());
	}
}
