package com.acat.calculator.utils;

import org.junit.Test;

import junit.framework.TestCase;

public class CharUtilsTest extends TestCase {

	@Test
	public void testCharUtils() {
		assertTrue(CharUtils.isWholeNumber('0'));
		assertTrue(CharUtils.isDecimalPoint('.'));
		
		assertTrue(CharUtils.isAnyNumeric('0'));
		assertTrue(CharUtils.isAnyNumeric('.'));
	}

}
