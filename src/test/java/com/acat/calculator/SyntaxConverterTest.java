package com.acat.calculator;

import org.junit.Test;

import junit.framework.TestCase;

public class SyntaxConverterTest extends TestCase {
	
	@Test
	public void test1() {
		assertEquals("0-2×2", SyntaxConverter.convert("-2×2"));
		assertEquals("2", SyntaxConverter.convert("2"));
		assertEquals("0-2", SyntaxConverter.convert("-2"));
		assertEquals("2×(2)", SyntaxConverter.convert("2(2)"));
		assertEquals("0-2×(0-2)", SyntaxConverter.convert("-2(-2)"));
		assertEquals("2-(0-2)", SyntaxConverter.convert("2-(-2)"));
		assertEquals("0-2×(0-2)", SyntaxConverter.convert("-2×-2"));
		assertEquals("0-2×(0-2)", SyntaxConverter.convert("-2×(-2)"));
		assertEquals("0-2", SyntaxConverter.convert("-2"));
	}
}
