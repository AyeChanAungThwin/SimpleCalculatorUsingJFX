package com.acat.jfx.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class InputStreamUtils {

	public static InputStream fromResource(String folder, String fileNameWithExtension) {
		File file = new File(StringBuilderUtils.appendStrings(FileUtils.getResources(), File.separator, folder, File.separator, fileNameWithExtension));
		InputStream in = null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return in;
	}
}
