package ru.nsu.fit.g18214.yakovlev;

import java.util.Objects;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("field.fxml")));
    Parent root = loader.load();
    FieldController controller = loader.getController();
    Scene scene = new Scene(root);
    scene.setOnKeyPressed(controller::keyHandler);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Snake");
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

}
