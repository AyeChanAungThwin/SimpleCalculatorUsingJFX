package com.acat.jfx;

import com.acat.jfx.base.SceneCreator;

import javafx.application.Application;
import javafx.stage.Stage;

public class JfxStarter extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneCreator sc = new SceneCreator.SceneBuilder()
				.title("Calculator by Aye Chan AT")
				.logoImg("calculator_logo.png")
				.fxml("Calculator.fxml")
				.css("calculator.css")
				.removeFrame()
				.build();
		sc.show();
	}
	
	public static void execute(String[] args) {
		launch(args);
	}
}
