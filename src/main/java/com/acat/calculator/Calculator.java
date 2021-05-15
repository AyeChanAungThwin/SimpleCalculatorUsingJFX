package com.acat.calculator;

import java.util.List;
import java.util.Stack;

import com.acat.calculator.tranversal.Inorder;
import com.acat.calculator.tranversal.Preorder;
import com.acat.calculator.utils.DecimalPointUtils;

public class Calculator {
	
	private static String bodmas(Double num1, Double num2, String operator) {
		Double result = (double) 0;
		
		if (operator.equals(String.valueOf(Operator.ADDITION))) {
			result = num1+num2;
		}
		else if (operator.equals(String.valueOf(Operator.SUBTRACTION))) {
			result = num1-num2;
		}
		else if (operator.equals(String.valueOf(Operator.MULTIPLICATION))) {
			result = num1*num2;
		}
		else if (operator.equals(String.valueOf(Operator.DIVISION))) {
			result = num1/num2;
		}
		else if (operator.equals(String.valueOf(Operator.POWER))) {
			result = Math.pow(num1, num2);
		}
		return String.valueOf(result);
	}

	public static String calculate(String data) {
		List<String> inOrderExpression = Inorder.getData(data);
		
		int length = inOrderExpression.size();
		
		Stack<String> stack = new Stack<String>();
		for (int i=0; i<length; i++) {
			String out = inOrderExpression.get(i);
			Character operator = out.length()==1?out.charAt(0):null;
			if (!OperatorChecker.isAnyArithmeticOperators(operator)) {
				stack.push(out);
			}
			else {
				Double num2 = Double.parseDouble(stack.pop());
				Double num1 = Double.parseDouble(stack.pop());
				String result = bodmas(num1, num2, out);
				stack.push(result);
			}
		}
		Double result = Double.parseDouble(stack.pop());
		return DecimalPointUtils.removeContinuousZeros(result);
	}
	
	public static void main(String[] args) {
    	String data = "5-2×1";
    	
    	//Same priority
    	//data = "5-2+1"; //3+1 = 4
    	//data = "6/6/3"; //1/3 = 0.333
    	
    	//Not same priority
    	//data = "5*2-1"; //10-1 = 9
    	//data = "5-2*1"; //5-2 = 3
    	//data = "3.5*2"; //7
    	
    	//Brackets
    	//data = "5*(2+1*2)"; //5*(2+2) = 5*4 = 20
    	//data = "5*(2*1+2)";
    	//data = "(156-61)*(81+91)/2^3"; //95*172/8 = 16340/8 = 2042.5
    	//data = "((156-61)*(81+91))/2^3"; //Same result as above
    	//data = "(8*(0+3))"; //8*3 = 24;
    	//data = "(8-3)*4"; //5*4 = 20;
    	
    	//More Brackets
    	//data = "((8*(0+3)))"; //(8*3) = 24
    	
    	//data = "(6+0)/(6+0)+(3+0)"; //6/6+3 = 1+3 = 4
    	//data = "(0+6)/(0+6)+(0+3)"; //6/6+3 = 1+3 = 4
    	//data = "(6+0)+(6+0)/(3+0)"; //6+6/3 = 6+2 = 8
    	//data = "9*(3+4*(9-1))/10"; //(9*(3+4*8)/10) = 9*(3+32)/10 = 9*35/10 = 31.5
    	
    	//data = "(9/7*8/9)+(3/8-1/8)/10"; //1.168
    	data = "(9÷7)×(8÷9)+(3÷8)-(1÷8)÷10"; 
    	/* 1.286*0.889+0.375-0.125/10 
    	 * 1.143+0.375-0.013 = 1.505
    	 */
    	data = "1.143+0.375-0.013";
    	data = "(1.413-0.8)÷(56×178)+(75-89)-9";
    	//data = "-2×-2";
    	//data = "5-2+1";
    	//data = "6÷6÷3";
    	data = "(9÷7×8÷9)+(3÷8-1÷8)÷10";
    	data = "2+2^3";
    	data = "2((-3)2)";
    	
    	List<String> preOrder = Preorder.getData(data);
    	System.out.print("Preorder : ");
    	for (String out: preOrder) {
    		System.out.print(""+out+"");
    	}
    	System.out.println();
    	
    	List<String> inOrder = Inorder.getData(data);
    	System.out.print("Inorder  : ");
    	for (String out: inOrder) {
    		System.out.print("("+out+") ");
    	}
    	System.out.println();
    	
    	String result = Calculator.calculate(data);
    	System.out.println(result);
    }
}
