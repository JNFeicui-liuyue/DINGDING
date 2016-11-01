package com.example.library.util;

import java.io.IOException;

public abstract class SerializableJson {
	
	/**
	 * 序列化
	 * */
	public String Serialize() {
		String json = null;
		try {
			json = JsonUtils.SerializeJSON(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}
}
