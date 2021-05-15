package com.acat.calculator.tranversal;

import java.util.ArrayList;
import java.util.List;

import com.acat.calculator.Operator;
import com.acat.calculator.OperatorChecker;
import com.acat.calculator.SyntaxConverter;
import com.acat.calculator.utils.CharUtils;

public class Preorder {
	
	private static StringBuilder createStringBuilder() {
		return new StringBuilder();
	}
	
	private static <T> List<T> createArrayList(Class<T> className) {
		return new ArrayList<T>();
	}
	
	public static String getStructure(String data) {
		String value = data;
		Character previous = null;
		StringBuilder sb = createStringBuilder();
		
		for (int i=0; i<value.length(); i++) {
			Character current = value.charAt(i);
			Character prev = null;
			Boolean gotException = false;
			try {
				prev = value.charAt(i-1);
			}
			catch (Exception e) {
				gotException = true;
			}	
			
			if (OperatorChecker.isPlusOrMinus(current)) {
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
				previous = value.charAt(i-1);
			}
			catch (StringIndexOutOfBoundsException e) {
				sb.append(current);
				continue;
			}
			
			if (CharUtils.isWholeNumber(previous)
					&&OperatorChecker.isOpenBracket(current)) {
				sb.append(Operator.MULTIPLICATION);
			}
			if (OperatorChecker.isCloseBracket(previous)
					&&CharUtils.isAnyNumeric(current)) {
				sb.append(Operator.MULTIPLICATION);
			}
			if (OperatorChecker.isCloseBracket(previous)
					&&OperatorChecker.isOpenBracket(current)) {
				sb.append(Operator.MULTIPLICATION);
			}
			sb.append(current);
		}
		return sb.toString();
	}

	public static List<String> getData(String data) {
		String value = SyntaxConverter.convert(data);
		
		List<String> preOrder = createArrayList(String.class);
		StringBuilder numbers = createStringBuilder();
		
		for (int i=0; i<value.length(); i++) {
			Character out = value.charAt(i);
			numbers.append(out);
			if (OperatorChecker.isMatchedAnyOperators(out)) {
				numbers.setLength(numbers.length()-1);
				if (numbers.length()>0) preOrder.add(numbers.toString());
				preOrder.add(String.valueOf(out));
				numbers.setLength(0);
			}
 		}
		
		if (numbers.length()>0) {
			preOrder.add(numbers.toString());
		}
		return preOrder;
	}
}
