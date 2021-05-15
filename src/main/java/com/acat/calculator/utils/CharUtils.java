package com.acat.calculator.utils;

public class CharUtils {

	public static boolean isWholeNumber(char ch) {
		//can be signed or unsigned but without decimal (0-9)
		if (ch>=48&&ch<=57) {
			return true;
		}
		return false;
	}
	
	public static boolean isDecimalPoint(char ch) {
		return ch==46;
	}
	
	public static boolean isAnyNumeric(char ch) {
		return isWholeNumber(ch)||isDecimalPoint(ch);
	}
}
