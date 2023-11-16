package gr2343.ui;

import gr2343.core.CoffeeRatingItem;
import gr2343.core.CoffeeRatingModel;
import gr2343.core.CoffeeRatings;
import gr2343.json.CoffeeRatingsPersistence;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.net.URI;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CoffeeRatingController {

    //private final static String ratingsWithItems = "{\"lists\":[{\"name\":\"ratings\",\"items\":[]}]}";

    private CoffeeRatingModel model;
    private CoffeeRatingsPersistence coffeeRatingsPersistence = new CoffeeRatingsPersistence();

    private CoffeeRatingItem selectedItemForUpdate = null;

    private RemoteCoffeeRatingModelAccess remoteModelAccess;

    public CoffeeRatingController() throws IOException {
        remoteModelAccess = new RemoteCoffeeRatingModelAccess(URI.create("http://localhost:8080/"));
        remoteModelAccess.getCoffeeRatingModel();
        model = remoteModelAccess.getCoffeeRatingModel();
    }


    @FXML
    TextField newDescriptionText;

    @FXML
    TextField newRatingText;

    @FXML
    ListView<CoffeeRatingItem> ratingsView;

    @FXML
    Button newCoffeeRatingButton;

    @FXML
    Button deleteRatingButton;

    @FXML
    Button updateRatingButton;


    @FXML
    public void initialize() {
        // kobler data til view
        ratingsView.setCellFactory(ratingsView -> new CoffeeRatingListCell());
        updateRatingsView();
    }

    protected CoffeeRatingModel getModel() { // henter ut model
        return model;
    }

    protected CoffeeRatings getCoffeeRatings() {
        return model.getCoffeeRating("ratings");
    }

    protected void updateRatingsView() {
        try {
            // Fetch the CoffeeRatings from the server
            CoffeeRatings ratings = remoteModelAccess.getCoffeeRating("ratings");
    
            // Clear the existing items in the ListView
            ratingsView.getItems().clear();
    
            if (ratings != null && ratings.getItems() != null) {
                // Add the items from CoffeeRatings to the ListView
                ratingsView.getItems().addAll(ratings.getItems());
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
        }
    }
    
    

    @FXML
    public void handlenewCoffeeRatingAction() throws JsonGenerationException, JsonMappingException, IOException {
            // Validering
        String description = newDescriptionText.getText();
        String ratingStr = newRatingText.getText();
        int rating;

        // Sjekk om description er gyldig (inneholder minst én bokstav)
        if (description == null || !description.matches(".*[a-zA-Z]+.*")) {
            showWarning("Ugyldig beskrivelse", "Det må være minst én bokstav i beskrivelsen");
            return; // Avbryter handlingen
        }

        // Forsøk å parse rating, og sjekk om det er et tall mellom 1 og 5
        try {
            rating = Integer.parseInt(ratingStr);
            if (rating < 1 || rating > 5) {
                showWarning("Ugyldig rating", "Rating må være i intervallet 1-5.");
                return; // Avbryter handlingen
            }
        } catch (NumberFormatException e) {
            showWarning("Invalid Rating", "Rating må være et tall.");
            return; // Avbryter handlingen
        }
        // Legg til ny rating
        if (selectedItemForUpdate != null) {
            // Oppdater den eksisterende ratingen med de nye verdiene
            selectedItemForUpdate.setDescription(newDescriptionText.getText());
            selectedItemForUpdate.setRating(Integer.parseInt(newRatingText.getText()));
            // Tøm midlertidige tekstfelt
            newDescriptionText.clear();
            newRatingText.clear();

            // Nullstill selectedItemForUpdate
            selectedItemForUpdate = null;

            // Oppdater visningen
            // Få CoffeeRatings object for "ratings"
            CoffeeRatings ratings = model.getCoffeeRating("ratings");
            remoteModelAccess.putCoffeeRating(ratings);
            updateRatingsView();
           
        } else {
            // Få CoffeeRatings object for "ratings"
            CoffeeRatings ratings = model.getCoffeeRating("ratings");

            if (ratings == null) {
                // hvis det ikke finnes en "ratings" lager vi en ny
                ratings = new CoffeeRatings();
                ratings.setName("ratings");
                model.addCoffeeRating(ratings);
            }

            // Legg til ny rating
            CoffeeRatingItem item = new CoffeeRatingItem();
            item.setDescription(newDescriptionText.getText());
            item.setRating(Integer.parseInt(newRatingText.getText()));

            /// Legg til nytt item i ratings
            ratings.addCoffeeRatingItem(item);
            ratingsView.getItems().add(item);

            // Tøm midlertidige tekstfelt
            newDescriptionText.clear();
            newRatingText.clear();
            
            remoteModelAccess.putCoffeeRating(ratings);
            updateRatingsView();
        }

        // Lagre oppdateringer til fil
        coffeeRatingsPersistence.writeCoffeeRatings(model, null);
    }

    private void showWarning(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
}

    @FXML
    public void handleDeleteRatingAction() {
        // sletter en rating
        CoffeeRatingItem item = ratingsView.getSelectionModel().getSelectedItem();
        if (item != null) {
            getCoffeeRatings().removeCoffeeRatingItem(item);
            ratingsView.getItems().remove(item);
        }
        remoteModelAccess.putCoffeeRating(getCoffeeRatings());
        updateRatingsView();
    }

    @FXML
    public void handleUpdateRatingAction() {
        // Henter det valgte itemet som skal oppdateres
        selectedItemForUpdate = ratingsView.getSelectionModel().getSelectedItem();

        if (selectedItemForUpdate != null) {
            // Fyller inn midlertidige tekstfelt med eksisterende data
            newDescriptionText.setText(selectedItemForUpdate.getDescription());
            newRatingText.setText(String.valueOf(selectedItemForUpdate.getRating()));
        }
        remoteModelAccess.putCoffeeRating(getCoffeeRatings());
        updateRatingsView();
    }
}
