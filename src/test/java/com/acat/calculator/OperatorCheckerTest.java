package com.acat.calculator;

import org.junit.Test;

import junit.framework.TestCase;

public class OperatorCheckerTest extends TestCase {
	
	private Character plx = Operator.ADDITION;
	private Character mns = Operator.SUBTRACTION;
	private Character mul = Operator.MULTIPLICATION;
	private Character div = Operator.DIVISION;
	private Character pow = Operator.POWER;

	@Test
	public void test() {
		//Check one arithmetic Operators
		assertTrue(OperatorChecker.isAddition(plx));
		assertTrue(OperatorChecker.isSubtraction(mns));
		assertTrue(OperatorChecker.isMultiplication(mul));
		assertTrue(OperatorChecker.isDivision(div));
		assertTrue(OperatorChecker.isPower(pow));
		
		//Check all arithmetic operators
		assertTrue(OperatorChecker.isAnyArithmeticOperators(plx));
		assertTrue(OperatorChecker.isAnyArithmeticOperators(mns));
		assertTrue(OperatorChecker.isAnyArithmeticOperators(mul));
		assertTrue(OperatorChecker.isAnyArithmeticOperators(div));
		assertTrue(OperatorChecker.isAnyArithmeticOperators(pow));
		
		//Brackets
		assertTrue(OperatorChecker.isOpenBracket('('));
		assertTrue(OperatorChecker.isCloseBracket(')'));
		assertTrue(OperatorChecker.isAnyBrackets('('));
		assertTrue(OperatorChecker.isAnyBrackets(')'));
		
		//Brackets or Arithmetic Operators
		assertTrue(OperatorChecker.isMatchedAnyOperators(plx));
		assertTrue(OperatorChecker.isMatchedAnyOperators('('));
		assertFalse(OperatorChecker.isMatchedAnyOperators('0'));
		assertFalse(OperatorChecker.isMatchedAnyOperators('s'));
		
		//plus or minus?
		assertTrue(OperatorChecker.isPlusOrMinus(plx));
		assertTrue(OperatorChecker.isPlusOrMinus(mns));
		assertFalse(OperatorChecker.isPlusOrMinus(mul));
		assertFalse(OperatorChecker.isPlusOrMinus(div));
		assertFalse(OperatorChecker.isPlusOrMinus(pow));
		
		//Multiplication or division?
		assertFalse(OperatorChecker.isMulOrDiv(plx));
		assertFalse(OperatorChecker.isMulOrDiv(mns));
		assertTrue(OperatorChecker.isMulOrDiv(mul));
		assertTrue(OperatorChecker.isMulOrDiv(div));
		assertFalse(OperatorChecker.isPlusOrMinus(pow));
		
		//Same priority (BODMAS)
		assertTrue(OperatorChecker.isSamePriority(plx, plx));
		assertTrue(OperatorChecker.isSamePriority(plx, mns));
		assertTrue(OperatorChecker.isSamePriority(mns, mns));
		assertTrue(OperatorChecker.isSamePriority(mul, mul));
		assertTrue(OperatorChecker.isSamePriority(div, mul));
		assertTrue(OperatorChecker.isSamePriority(div, div));
		assertTrue(OperatorChecker.isSamePriority(pow, pow));
		
		//Previous operator low priority? (BODMAS)
		assertTrue(OperatorChecker.isPreOptLowPriority(mns, div));
		assertTrue(OperatorChecker.isPreOptLowPriority(plx, mul));
		assertTrue(OperatorChecker.isPreOptLowPriority(plx, pow));
		assertTrue(OperatorChecker.isPreOptLowPriority(mul, pow));
		
		//Previous operator high priority? (BODMAS)
		assertTrue(OperatorChecker.isPreOptHighPriority(div, mns));
		assertTrue(OperatorChecker.isPreOptHighPriority(mul, plx));
		assertTrue(OperatorChecker.isPreOptHighPriority(pow, plx));
		assertTrue(OperatorChecker.isPreOptHighPriority(pow, mul));
		
		//Same priority (or) previous high priority are the same (BODMAS)
		assertTrue(OperatorChecker.isSamePriOptOptn(mns, plx));
		assertTrue(OperatorChecker.isSamePriOptOptn(mul, mns));
		assertFalse(OperatorChecker.isSamePriOptOptn(mns, mul));
		assertFalse(OperatorChecker.isSamePriOptOptn(mul, pow));
		
		//Previous is open bracket (and) current is any arithmetic operators
		assertTrue(OperatorChecker.isPreOpnBktAndCurArith('(', pow));
		assertTrue(OperatorChecker.isPreClsBktAndCurArith(')', mul));
	}

}
