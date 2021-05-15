package com.acat.jfx.utils;

import java.io.File;

public class FileUtils {

	public static String getAbsolutePath() {
		return System.getProperty("user.dir");
	}
	
	public static String getResources() {
		return StringBuilderUtils.appendStrings(getAbsolutePath(), "/src/main/resources");
	}
	
	public static boolean isFileExists(String fromResourcePath) {
		File file = new File(StringBuilderUtils.appendStrings(getResources(), fromResourcePath));
		return file.exists()?true:false;
	}
}
