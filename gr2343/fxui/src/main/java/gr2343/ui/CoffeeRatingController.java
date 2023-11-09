package gr2343.ui;

import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import gr2343.core.CoffeeRatingItem;
import gr2343.core.CoffeeRatings;
import gr2343.json.CoffeeRatingsPersistence;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import gr2343.core.CoffeeRatingModel;

public class CoffeeRatingController {

    private final static String ratingsWithItems = "{\"lists\":[{\"name\":\"ratings\",\"items\":[]}]}";

    // private CoffeeRatings ratings;
    private CoffeeRatingModel model;
    private CoffeeRatingsPersistence coffeeRatingsPersistence = new CoffeeRatingsPersistence();

    private CoffeeRatingItem selectedItemForUpdate = null;

    public CoffeeRatingController() throws IOException {
        try {
            model = coffeeRatingsPersistence.readCoffeeRatings(ratingsWithItems);
        } catch (JsonProcessingException e) {
        }
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
        updateRatingsView();
        ratingsView.setCellFactory(ratingsView -> new CoffeeRatingListCell());
    }

    protected CoffeeRatingModel getModel() {
        return model;
    }

    protected CoffeeRatings getRatings() {
        return model.getRating("ratings");
    }

    protected void updateRatingsView() {
        // oppdaterer view
        List<CoffeeRatingItem> viewRatings = ratingsView.getItems();
        viewRatings.clear();
        CoffeeRatings ratings = model.getRating("ratings");
        if (ratings != null) {
            viewRatings.addAll(ratings.getItems());
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
            updateRatingsView();
        } else {
            // Få CoffeeRatings object for "ratings"
            CoffeeRatings ratings = model.getRating("ratings");

            if (ratings == null) {
                // If "ratings" doesn't exist in the model, create it
                ratings = new CoffeeRatings();
                ratings.setName("ratings");
                model.addRating(ratings);
            }

            // Legg til ny rating
            CoffeeRatingItem item = new CoffeeRatingItem();
            item.setDescription(newDescriptionText.getText());
            item.setRating(Integer.parseInt(newRatingText.getText()));

            // Add the new item to the ratings
            ratings.addCoffeeRatingItem(item);

            ratingsView.getItems().add(item);

            // Tøm midlertidige tekstfelt
            newDescriptionText.clear();
            newRatingText.clear();
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
            getRatings().removeCoffeeRatingItem(item);
            ratingsView.getItems().remove(item);
        }
    }

    @FXML
    public void handleUpdateRatingAction() {
        // Få det valgte elementet som skal oppdateres
        selectedItemForUpdate = ratingsView.getSelectionModel().getSelectedItem();

        if (selectedItemForUpdate != null) {
            // Fyll inn midlertidige tekstfelt med eksisterende data for redigering
            newDescriptionText.setText(selectedItemForUpdate.getDescription());
            newRatingText.setText(String.valueOf(selectedItemForUpdate.getRating()));
        }
    }


}
