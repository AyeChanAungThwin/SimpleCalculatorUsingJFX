package com.acat.calculator.utils;

public class DecimalPointUtils {

	public static String removeContinuousZeros(double value) {
		String local = String.valueOf(value);
		int length = local.length();
		boolean foundDecimalPoint = false;
		int decimalPoint=0;
		int start=0;
		for (int i=0; i<length; i++) {
			char out = local.charAt(i);
			if (out=='.') {
				foundDecimalPoint = true;
				decimalPoint=i;
				continue;
			}
			if (foundDecimalPoint) {
				if (out=='0') {
					if (start==0) {
						start=i;
					}
				}
				else {
					if (start!=0) start=0;
				}
			}
		}
		if (start==0) start=length;
		local = local.substring(0, start);
		
		if (decimalPoint==start-1) {
			local = local.substring(0, start-1);
		}
		return local;
	}
	
	public static String toStandardDeviation(double value) {
		String data = String.valueOf(value);
		int length = data.length();
		int start=0;
		for (int i=0; i<length; i++) {
			char out = data.charAt(i);
			if (out=='.') {
				start=i+1;
				break;
			}
		}
		int times = length-start;
		double numerator = value;
		double denominator = Math.pow(10, times);
		double result = numerator*denominator;
		System.out.println(result);
		return null;
	}
	
	public static void main(String[] args) {
		toStandardDeviation(12.6455);
	}
}
