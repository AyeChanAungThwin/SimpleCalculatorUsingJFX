package com.acat.jfx.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.acat.jfx.utils.FileUtils;
import com.acat.jfx.utils.StringBuilderUtils;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SceneCreator {
	
	private static Double xOffset = 0.0;
	private static Double yOffset = 0.0;
	private Stage stage;
	
	private SceneCreator(SceneBuilder scnBld) {
		stage = scnBld.stage;
	}
	
	public void show() {
		stage.show();
	}
	
	public static class SceneBuilder {
		private String title;
		private String logo;
		private String fxml;
		private String css;
		
		private boolean removeFrame = false;
		
		private Parent root;
		private Scene scene;
		private Stage stage;
		
		public SceneBuilder() {
			title = "";
			logo = null;
			fxml = null;
			css = null;
			
			root = null;
			scene = null;
			stage = null;
		}
		
		private Stage createStage() {
			return new Stage();
		}
		
		private Scene createScene(Parent root) {
			return new Scene(root);
		}
		
		public SceneBuilder title(String title) {
			this.title = title;
			return this;
		}
		
		public SceneBuilder logoImg(String logo) {
			this.logo = logo;
			return this;
		}
		
		public SceneBuilder fxml(String fxml) {
			this.fxml = fxml;
			return this;
		}
		
		public SceneBuilder css(String css) {
			this.css = css;
			return this;
		}
		
		public SceneBuilder removeFrame() {
			this.removeFrame = true;
			return this;
		}
		
		public SceneCreator build() {
			//Create Stage.
			this.stage = createStage();
			
			//Load file-name.fxml file.
			try {
				this.root = FXMLLoader.load(getClass().getResource(StringBuilderUtils.appendStrings("/views/", fxml)));
			} catch (IOException e) {
				System.err.println("FXML file: NOT found!\nFXML Location: src/main/resources/views/*.fxml");
			} catch (NullPointerException e) {
				System.err.println("FXML file: NOT found!\nFXML Location: src/main/resources/views/*.fxml");
				this.root = new StackPane();
			}
			
			this.scene = createScene(this.root);
			
			//Remove frame.
			if (removeFrame) {
				scene.setFill(Color.TRANSPARENT);
				stage.initStyle(StageStyle.TRANSPARENT);
				
				root.setOnMousePressed(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						// TODO Auto-generated method stub
						xOffset = event.getSceneX();
		                yOffset = event.getSceneY();
					}
				});
				
				root.setOnMouseDragged(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						// TODO Auto-generated method stub
						stage.setX(event.getScreenX() - xOffset);
		                stage.setY(event.getScreenY() - yOffset);
					}
				});
			}
			
			//Load css.
			if (this.css!=null) {
				scene.getStylesheets().add(getClass().getResource(StringBuilderUtils.appendStrings("/styles/", css)).toExternalForm());
			}
			
			//Set title text.
			if (title!=null||!title.isEmpty()) {
				this.stage.setTitle(this.title);
			}
			
			//Load image file.
			if (logo!=null||!logo.isEmpty()) {
				try {
					String path = StringBuilderUtils.appendStrings(FileUtils.getResources(), "/imgs/", this.logo);
					File file = new File(path);
					InputStream is = new FileInputStream(file);
					this.stage.getIcons().add(new Image(is));
				} catch (FileNotFoundException e) {
					System.err.println("Image file: NOT found!");
				}
			}
			
			//Prepare Stage.
			this.stage.setScene(scene);
			
			return new SceneCreator(this);
		}
	}
}
