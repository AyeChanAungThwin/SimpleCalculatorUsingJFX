package com.acat.calculator.tranversal;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

import com.acat.calculator.OperatorChecker;

public class Inorder {

	public static List<String> getData(String data) {
		List<String> preOrder = Preorder.getData(data);
		
		Stack<Character> stack = new Stack<>();
		List<String> inOrder = new ArrayList<>();
		
		for (int i=0; i<preOrder.size(); i++) {
			String out = preOrder.get(i);
			Character cur = out.charAt(0);
			
			boolean matchOperatorAndStackNotEmpty = false;
			
			/* If it's not operator, 
			 * it can be numeric (or) 
			 * other operations; sin(?), etc...
			 */
			if (!OperatorChecker.isMatchedAnyOperators(cur)) {
				/*boolean isDataAdded = false;
				if (!stack.isEmpty()) {
					if (MathOperator.isSubtraction(stack.peek())) {
						inOrder.add(stack.pop()+out);
						stack.push('+');
						isDataAdded = true;
					}
				}
				if (!isDataAdded) */
					inOrder.add(out);
			}
			
			//Push all operators on stack!
			else if (OperatorChecker.isMatchedAnyOperators(cur)) {
				if (stack.isEmpty()) {
					stack.push(cur);
				}
				else {
					matchOperatorAndStackNotEmpty = true;
				}
			}
			
			//Stack contains at least one operator
			if (matchOperatorAndStackNotEmpty) {
				Character prev = stack.peek();
				
				//Brackets
				if (OperatorChecker.isAnyBrackets(cur)) {
					stack.push(cur);
				}
				if (OperatorChecker.isPreOpnBktAndCurArith(prev, cur)) {
					stack.push(cur);
				}
				if (OperatorChecker.isPreClsBktAndCurArith(prev, cur)) {
					stack.pop(); //remove ')';
					while (!OperatorChecker.isOpenBracket(stack.peek())) {
						inOrder.add(String.valueOf(stack.pop()));
					}
					stack.pop(); //remove ')';
					
					boolean hasException = false;
					Character prevOpt = null;
					try {
						prevOpt = stack.pop(); //()prevOpt()nextOpt
					}
					catch (EmptyStackException e) {
						hasException = true;
					}
					
					if (!hasException) {
						if (OperatorChecker.isSamePriOptOptn(prevOpt, cur)) {
							inOrder.add(String.valueOf(prevOpt));
						}
						else if (OperatorChecker.isPreOptLowPriority(prevOpt, cur)) {
							stack.push(prevOpt);
						}
					}
					
					stack.push(cur);
				}
				if (OperatorChecker.isCloseBracket(cur)) {
					stack.pop(); //remove ')';
					while (!OperatorChecker.isOpenBracket(stack.peek())) {
						inOrder.add(String.valueOf(stack.pop()));
					}
					stack.pop(); //remove ')';
				}
				
				//Priority of operations
				if (OperatorChecker.isPreOptLowPriority(prev, cur)) {
					stack.push(cur);
				}
				if (OperatorChecker.isSamePriOptOptn(prev, cur)) {
					inOrder.add(String.valueOf(stack.pop()));
					stack.push(cur);
				}
			}
		}
		
		//Pop all operators from stack and add it to inOrder
		while (!stack.isEmpty()){
			Character ch = stack.pop();
			if (!OperatorChecker.isAnyBrackets(ch))
			inOrder.add(String.valueOf(ch));
		}
		
		return inOrder;
	}
}
