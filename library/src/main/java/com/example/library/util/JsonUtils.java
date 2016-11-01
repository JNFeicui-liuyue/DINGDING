package com.example.library.util;

import com.orhanobut.logger.Logger;


import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * JSON工具类
 * */
public final class JsonUtils {

	private JsonUtils(){
		throw  new Error("Do not need instantiate!");
	}

	/**
	 * 将JAVA对象序列化为JSON字符串
	 * */
	public static String SerializeJSON(Object obj) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		String _json = objectMapper.writeValueAsString(obj);
		return _json;
	}

	/**
	 * 将JSON字符串反序列化为JAVA对象
	 * */
	public static <T> T DeserializeJSON(String json, Class<T> cla)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, cla);
	}

	/**
	 * 如果json过长，请调用此方法分段输出
	 */
	public static void splitJson(String _json) {
		final int NUMBER = 1000;
		int i = 1;
		String TAG = "";
		long length = _json.length();
		if (length <= NUMBER) {
			TAG += "完整的json=====================================";
			Logger.i(TAG);
			Logger.i(_json);
		} else {
			while (_json.length() > NUMBER) {
				String logContent = _json.substring(0, NUMBER);
				_json = _json.replace(logContent, "");
				TAG = "打印被分割的第" + i + "条json==========================";
				Logger.i(TAG);
				Logger.i(logContent);
				i++;
			}
			TAG = "";
			TAG += "最后一条json=====================================";
			Logger.i(TAG);
			Logger.i(_json);
		}
	}

	/**
	 * 将map序列化称json
	 * join map
	 *
	 * @param map
	 * @return
	 */
	public static String toJson(Map<String, String> map) {
		if (map == null || map.size() == 0) {
			return null;
		}

		StringBuilder paras = new StringBuilder();
		paras.append("{");
		Iterator<Map.Entry<String, String>> ite = map.entrySet().iterator();
		while (ite.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>)ite.next();
			paras.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\"");
			if (ite.hasNext()) {
				paras.append(",");
			}
		}
		paras.append("}");
		return paras.toString();
	}
}
