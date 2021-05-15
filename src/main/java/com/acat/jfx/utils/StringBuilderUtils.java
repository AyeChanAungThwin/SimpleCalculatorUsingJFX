package com.acat.jfx.utils;

public class StringBuilderUtils {
	
	public static StringBuilder createObject() {
		return new StringBuilder();
	}

	public static String appendStrings(String...strings) {
		StringBuilder sb = new StringBuilder();
		for (String out: strings) {
			sb.append(out);
		}
		return sb.toString();
	}
}
