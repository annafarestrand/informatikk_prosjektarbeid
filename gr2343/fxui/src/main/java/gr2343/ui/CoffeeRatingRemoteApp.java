package gr2343.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Variant of the app that connects to a REST service.
 */
public class CoffeeRatingRemoteApp extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    Parent parent = FXMLLoader.load(getClass().getResource("CoffeeRatingRemoteApp.fxml"));
    stage.setScene(new Scene(parent));
    stage.show();
  }

  public static void main(String[] args) {
    launch(CoffeeRatingRemoteApp.class, args);
  }
}