package ru.nsu.fit.g18214.yakovlev;

import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{


  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("field.fxml")));
    Parent root = loader.load();
    FieldController controller = loader.getController();
    controller.box.setPrefHeight(FieldController.getHeigth());
    controller.box.setPrefWidth(FieldController.getWidth());
    controller.canvas.setHeight(FieldController.getHeigth());
    controller.canvas.setWidth(FieldController.getWidth());
    Scene scene = new Scene(root, FieldController.getWidth(), FieldController.getHeigth());
    scene.setOnKeyPressed(controller::keyHandler);
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.setMaximized(true);
    primaryStage.setTitle("Snake");
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

}
