package com.acat.jfx.utils;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class StageUtils {
	
	public static void minimize(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.setIconified(true);
	}
	
	public static void close(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
}
