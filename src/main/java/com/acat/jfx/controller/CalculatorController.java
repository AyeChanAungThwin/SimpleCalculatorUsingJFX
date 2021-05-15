package com.acat.jfx.controller;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

import com.acat.calculator.Calculator;
import com.acat.calculator.OperatorChecker;
import com.acat.calculator.utils.CharUtils;
import com.acat.jfx.utils.InputStreamUtils;
import com.acat.jfx.utils.StageUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class CalculatorController implements Initializable {

	private String finalAnswer = null;
	private boolean isPressedEqualOperator = false;
	private Stack<String> stack = new Stack<>();
	
    @FXML
    private Label input, answer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		InputStream in = InputStreamUtils.fromResource("fonts", "Calculator.ttf");
		Font font = Font.loadFont(in, 30);
		input.setFont(font); //set Calculator font to input!
		answer.setFont(font);
		
		input.setText("0");
		answer.setText("");
	}
	
	//Custom Minimize Title Bar
	@FXML
	public void onMinimizeBtnClicked(ActionEvent event) {
		StageUtils.minimize(event);
	}
	
	//Custom Close Title Bar
	@FXML
	public void onCloseBtnClicked(ActionEvent event) {
		StageUtils.close(event);
	}
    
    private String stackOperation() {
    	StringBuilder sb = new StringBuilder();
    	for (String out: stack) {
    		sb.append(out);
    	}
    	return sb.toString();
    }
    
    @FXML
    public void processNumbers(ActionEvent event) {
    	char prev = '0';
    	String previous = null;
    	if (!stack.isEmpty()) {
    		previous = stack.peek();
    		prev = previous.charAt(0);
    	}
    	if (isPressedEqualOperator
    			&&OperatorChecker.isAnyArithmeticOperators(prev)) {
    		stack.clear();
    		isPressedEqualOperator = false;
    	}
    	String value = ((Button) event.getSource()).getText();
    	char cur = value.charAt(0);
    	if (OperatorChecker.isSamePriority(prev, cur)) return;
    	if (OperatorChecker.isMulOrDiv(prev)
    			&&OperatorChecker.isPlusOrMinus(cur)) {
    		stack.add("(");
    	}
    	/*if (MathOperator.isCloseBracket(prev)) {
    		stack.add("×");
    	}*/
    	if (CharUtils.isAnyNumeric(prev)
    			&&OperatorChecker.isOpenBracket(cur)) {
    		stack.add("×");
    	}
    	if ("Ans".equals(previous)
    			&&OperatorChecker.isOpenBracket(cur)) {
    		stack.add("×");
    	}
    	stack.add(value);
    	input.setText(stackOperation());
    }
    
    @FXML
    public void processOperators(ActionEvent event) {
    	if (isPressedEqualOperator) {
    		answer.setText("");
    		stack.clear();
    		stack.add("Ans");
    		isPressedEqualOperator = false;
    	}
    	processNumbers(event);
    }
    
    @FXML
    public void onACBtnClicked(ActionEvent event) {
    	//reset all
    	stack.clear(); 
    	finalAnswer = null; 
    	isPressedEqualOperator = false; 
    	input.setText("0");
    	answer.setText("");
    }
    
    @FXML
    public void onDelBtnClicked() {
    	answer.setText("");
    	isPressedEqualOperator = false;
    	if (!stack.isEmpty()) {
    		stack.pop(); //delete that last data
    		input.setText(stackOperation());
    	}
    	if (stack.isEmpty()) {
    		input.setText("0"); //reset screen
    	}
    }
    
    @FXML
    public void onAnsBtnClicked(ActionEvent event) {
    	answer.setText("");
    	if (finalAnswer==null) return;
    	if (isPressedEqualOperator) {
    		stack.clear();
    		isPressedEqualOperator = false;
    	}
    	stack.add("Ans");
    	input.setText(stackOperation());
    }
    
    private void replaceAnswerWithValueOnStack() {
    	int length = stack.size();
    	for (int i=0; i<length; i++) {
    		String out = stack.get(i);
    		if (out.equals("Ans")) {
    			stack.set(i, this.finalAnswer);
    		}
    	}
    }
    
    @FXML
    public void onEqualBtnClicked() {
    	if (stack.isEmpty()) return;
    	replaceAnswerWithValueOnStack();
    	String data = stackOperation();
    	this.finalAnswer = null;
    	try {
    		this.finalAnswer = Calculator.calculate(data);
    	}
    	catch (Exception e) {
    		this.finalAnswer = "Syntax Error!";
    	}
    	
    	isPressedEqualOperator = true;
    	answer.setText(finalAnswer);
    }
}
