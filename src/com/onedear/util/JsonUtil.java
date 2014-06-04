package com.onedear.util;

import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	private static ObjectMapper objectMapper = new ObjectMapper();
	static {
//		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//		objectMapper.enable(JsonGenerator.Feature.ESCAPE_NON_ASCII);
//		objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, false);
	}

	public static <T> T decode(String json, Class<T> pojoClass) {
		if (json == null)
			return null;
		try {
			return objectMapper.readValue(json, pojoClass);
		} catch (Exception e) {
			return null;
		}
	}

	public static String encode(Object o) {
		try {
			return objectMapper.writeValueAsString(o);
		} catch (Exception e) {
			return null;
		}
	}

	public static Object get(String json, String key) {
		Map map = decode(json, Map.class);
		if (map == null)
			return null;
		return map.get(key);
	}
	
	public static void main(String[] args) {
		String json = "{\"name\":\"''Bob\", \"age\":13}";
//		String json = "{\"name\":\"Bob\"}";
		
	}
	
	
}
