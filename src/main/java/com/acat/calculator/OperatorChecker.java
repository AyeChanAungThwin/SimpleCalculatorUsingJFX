package com.acat.calculator;

public class OperatorChecker {
	
	public static Boolean isAddition(Character ch) {
		return Operator.ADDITION.equals(ch);
	}
	
	public static Boolean isSubtraction(Character ch) {
		return Operator.SUBTRACTION.equals(ch);
	}
	
	public static Boolean isMultiplication(Character ch) {
		return Operator.MULTIPLICATION.equals(ch);
	}
	
	public static Boolean isDivision(Character ch) {
		return Operator.DIVISION.equals(ch);
	}
	
	public static Boolean isPower(Character ch) {
		return Operator.POWER.equals(ch);
	}

	public static Boolean isAnyArithmeticOperators(Character ch) {
		boolean isArithmeticOperator = false;
		
		if (isAddition(ch)) {
			isArithmeticOperator = true;
		}
		if (isSubtraction(ch)) {
			isArithmeticOperator = true;
		}
		if (isMultiplication(ch)) {
			isArithmeticOperator = true;
		}
		if (isDivision(ch)) {
			isArithmeticOperator = true;
		}
		if (isPower(ch)) {
			isArithmeticOperator = true;
		}
		
		return isArithmeticOperator;
	}
	
	public static Boolean isOpenBracket(Character ch) {
		return Operator.OPEN_BRACKET.equals(ch);
	}
	
	public static Boolean isCloseBracket(Character ch) {
		return Operator.CLOSE_BRACKET.equals(ch);
	}
	
	public static Boolean isAnyBrackets(Character ch) {
		return isOpenBracket(ch)||isCloseBracket(ch);
	}
	
	public static Boolean isMatchedAnyOperators(Character ch) {
		return isAnyArithmeticOperators(ch)||isAnyBrackets(ch);
	}
	
	public static Boolean isPlusOrMinus(Character ch) {
		if (isAddition(ch)||isSubtraction(ch)) return true;
		return false;
	}
	
	public static Boolean isMulOrDiv(Character ch) {
		if (isMultiplication(ch)||isDivision(ch)) return true;
		return false;
	}
	
	public static Boolean isSamePriority(Character prev, Character cur) {
		if (isPlusOrMinus(prev)&&isPlusOrMinus(cur)) {
			return true;
		}
		if (isMulOrDiv(prev)&&isMulOrDiv(cur)) {
			return true;
		}
		if (isPower(prev)&&isPower(prev)) {
			return true;
		}
		return false;
	}
	
	public static Boolean isPreOptLowPriority(Character prev, Character cur) {
		if (isPlusOrMinus(prev)&&isMulOrDiv(cur)) {
			return true;
		}
		if (isPlusOrMinus(prev)&&isPower(cur)) {
			return true;
		}
		if (isMulOrDiv(prev)&&isPower(cur)) {
			return true;
		}
		return false;
	}
	
	public static Boolean isPreOptHighPriority(Character prev, Character cur) {
		if (isMulOrDiv(prev)&&isPlusOrMinus(cur)) {
			return true;
		}
		if (isPower(prev)&&isPlusOrMinus(cur)) {
			return true;
		}
		if (isPower(prev)&&isMulOrDiv(cur)) {
			return true;
		}
		return false;
	}
	
	public static Boolean isSamePriOptOptn(Character prev, Character cur) {
		if (isSamePriority(prev, cur)||isPreOptHighPriority(prev, cur)) {
			return true;
		}
		return false;
	}
	
	public static Boolean isPreOpnBktAndCurArith(Character prev, Character cur) {
		return isOpenBracket(prev)&&isAnyArithmeticOperators(cur);
	}
	
	public static Boolean isPreClsBktAndCurArith(Character prev, Character cur) {
		return isCloseBracket(prev)&&isAnyArithmeticOperators(cur);
	}
}
