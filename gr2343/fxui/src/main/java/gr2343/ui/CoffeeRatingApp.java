package gr2343.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CoffeeRatingApp extends Application {

    // starter applikasjonen, aapner vindu som presisert i fxml-filen
    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("CoffeeRating.fxml"));
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public static void main(String[] args) {
        launch(CoffeeRatingApp.class, args);
    }

}
