package com.acat.calculator;

import org.junit.Test;

import junit.framework.TestCase;

public class CalculatorTest extends TestCase {

	@Test
	public void testSamePriority() {
		System.out.println("Same Priority Test");
		//5-2+1 = 3+1 = 4
    	//6/6/3 = 1/3 = 0.333
		assertEquals("4", Calculator.calculate("5-2+1"));
		assertEquals("0.3333333333333333", Calculator.calculate("6÷6÷3"));
	}
	
	@Test
	public void testDifferentPriority() {
		System.out.println("Different Priority");
		//5×2-1 = 10-1 = 9
		assertEquals("9", Calculator.calculate("5×2-1"));
		//5-2×1 = 5-2 = 3
		assertEquals("3", Calculator.calculate("5-2×1"));
    	//3.5×2 = 7
		assertEquals("7", Calculator.calculate("3.5×2"));
	}

	@Test
	public void testBrackets() {
		System.out.println("Test Brackets");
		//5×(2+3×4) = 5×(2+12) = 5×14 = 70
		assertEquals("70", Calculator.calculate("5×(2+3×4)"));
		//5×(2×3+4) = 5×(6+4) = 5×10 = 50
		assertEquals("50", Calculator.calculate("5×(2×3+4)"));
		//(156-61)×(81+91)÷2^3 = 95×172÷8 = 16340÷8 = 2042.5
		assertEquals("2042.5", Calculator.calculate("(156-61)×(81+91)÷2^3"));
		//((156-61)×(81+91))÷2^3 = (95×172)÷8 = 16340÷8 = 2042.5
		assertEquals("2042.5", Calculator.calculate("((156-61)×(81+91))÷2^3"));
		//data = "(8×(3))"; //8×3 = 24;
		assertEquals("24", Calculator.calculate("(8×(3))"));
    	//data = "(8-3)×4"; //5×4 = 20;
		assertEquals("20", Calculator.calculate("(8-3)×4"));
	}
	
	@Test
	public void testMoreBrackets() {
		System.out.println("Test More Brackets");
    	//((8×(0+3))) = (8×3) = 24
		assertEquals("24", Calculator.calculate("((8×(3)))"));
    	//(6+0)÷(6+0)+(3+0) = 6÷6+3 = 1+3 = 4
		assertEquals("4", Calculator.calculate("(6+0)÷(6+0)+(3+0)"));
    	//(0+6)÷(0+6)+(0+3) = 6÷6+3 = 1+3 = 4
		assertEquals("4", Calculator.calculate("(0+6)÷(0+6)+(0+3)"));
		//(0+6)÷((0+6)+(0+3))"; 6÷(9) = 0.667
		assertEquals("0.6666666666666666", 
				Calculator.calculate("(0+6)÷((0+6)+(0+3))"));
    	//(6+0)+(6+0)÷(3+0) = 6+6÷3 = 6+2 = 8
		assertEquals("8", Calculator.calculate("(6+0)+(6+0)÷(3+0)"));
    	//9×(3+4×(9-1))÷10 = (9×(3+4×8)÷10) = 9×(3+32)÷10 = 9×35÷10 = 31.5
		assertEquals("31.5", Calculator.calculate("(9×(3+4×8)÷10)"));
    	//(9÷7×8÷9)+(3÷8-1÷8)÷10
    	/* 
    	 * 1.142857143+0.25÷10
    	 * 1.167857143
    	 */
		assertEquals("1.167857142857143", Calculator.calculate("(9÷7×8÷9)+(3÷8-1÷8)÷10"));
    	//(9÷7)×(8÷9)+(3÷8)-(1÷8)÷10
    	/* 
    	 * 1.286×0.889+0.375-0.125÷10 
    	 * 1.143+0.375-0.013 = 1.505
    	 */
		assertEquals("1.5053571428571428", 
				Calculator.calculate("(9÷7)×(8÷9)+(3÷8)-(1÷8)÷10"));
    	//1.143+0.375-0.013
		assertEquals("1.5050000000000001", 
				Calculator.calculate("1.143+0.375-0.013"));
    	//(1.413-0.8)÷(56×178)+(75-89)-9)";
		/*
		 * (0.613)÷(9968)-14-9
		 * 0.0000614968-14-9
		 * -13.9999385032-9
		 * -22.9999385032
		 */
		assertEquals("-22.999938503210274", 
				Calculator.calculate("(1.413-0.8)÷(56×178)+(75-89)-9"));
		
		//More Tests
		assertEquals("4", Calculator.calculate("-2(-2)"));
		assertEquals("-1.25", Calculator.calculate("-2.5÷(2)"));
		assertEquals("-323", Calculator.calculate("1-6(6(9))"));
		assertEquals("-323", Calculator.calculate("1-6×(6×(9))"));
		assertEquals("0.8888888888888888", Calculator.calculate("1-6÷(6(9))"));
		assertEquals("0.8888888888888888", Calculator.calculate("1-6÷(6×(9))"));
		assertEquals("-12", Calculator.calculate("2((-3)2)"));
	}
}
