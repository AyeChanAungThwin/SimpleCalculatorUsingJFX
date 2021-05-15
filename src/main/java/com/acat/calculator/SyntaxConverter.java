package com.acat.calculator;

import com.acat.calculator.utils.CharUtils;

public class SyntaxConverter {

	public static String convert(String data) {
		String value = data;
		Character prev = null;
		StringBuilder sb = new StringBuilder();
		
		boolean isOpenBktAdded = false;
		for (int i=0; i<value.length(); i++) {
			Character cur = value.charAt(i);
			Boolean gotException = false;
			try {
				prev = value.charAt(i-1);
			}
			catch (Exception e) {
				gotException = true;
			}	
			
			if (OperatorChecker.isPlusOrMinus(cur)) {
				if (i==0) {
					sb.append('0');
				}
				
				if (!gotException) {
					if (OperatorChecker.isOpenBracket(prev)) {
						sb.append('0');
					}
				}
			}
			
			try {
				prev = value.charAt(i-1);
			}
			catch (StringIndexOutOfBoundsException e) {
				sb.append(cur);
				continue;
			}
			
			if (OperatorChecker.isMulOrDiv(prev)
					&&OperatorChecker.isPlusOrMinus(cur)) {
				sb.append(Operator.OPEN_BRACKET);
				sb.append("0");
				sb.append(cur);
				isOpenBktAdded = true;
				continue;
			}
			if (OperatorChecker.isAnyArithmeticOperators(cur)) {
				if (isOpenBktAdded) {
					isOpenBktAdded = false;
					sb.append(cur);
					sb.append(")");
				}
			}
			if (CharUtils.isWholeNumber(prev)
					&&OperatorChecker.isOpenBracket(cur)) {
				sb.append(Operator.MULTIPLICATION);
			}
			if (OperatorChecker.isCloseBracket(prev)
					&&CharUtils.isAnyNumeric(cur)) {
				sb.append(Operator.MULTIPLICATION);
			}
			if (OperatorChecker.isCloseBracket(prev)
					&&OperatorChecker.isOpenBracket(cur)) {
				sb.append(Operator.MULTIPLICATION);
			}
			
			sb.append(cur);
		}
		if (isOpenBktAdded) {
			isOpenBktAdded = false;
			sb.append(")");
		}
		return sb.toString();
	}
}
