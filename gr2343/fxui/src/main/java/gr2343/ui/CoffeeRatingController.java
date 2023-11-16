package gr2343.ui;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import gr2343.core.CoffeeRatingItem;
import gr2343.core.CoffeeRatings;
import gr2343.json.CoffeeRatingsPersistence;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import gr2343.core.CoffeeRatingModel;
import gr2343.ui.RemoteCoffeeRatingModelAccess;

public class CoffeeRatingController {

    //private final static String ratingsWithItems = "{\"lists\":[{\"name\":\"ratings\",\"items\":[]}]}";

    // private CoffeeRatings ratings;
    private CoffeeRatingModel model;
    private CoffeeRatingsPersistence coffeeRatingsPersistence = new CoffeeRatingsPersistence();

    private CoffeeRatingItem selectedItemForUpdate = null;

    private RemoteCoffeeRatingModelAccess remoteModelAccess;

    public CoffeeRatingController() throws IOException {
        //model = coffeeRatingsPersistence.readCoffeeRatings(ratingsWithItems);
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

    protected CoffeeRatingModel getModel() {
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
            System.out.println("ratings.getItems() = " + ratings.getItems());
    
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
            CoffeeRatings ratings = model.getCoffeeRating("ratings");

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
            
            remoteModelAccess.putCoffeeRating(ratings);
            updateRatingsView();
        }

        // Lagre oppdateringer til fil
        coffeeRatingsPersistence.writeCoffeeRatings(model, null);
    }


    @FXML
    public void handleDeleteRatingAction() {
        // sletter en rating
        CoffeeRatingItem item = ratingsView.getSelectionModel().getSelectedItem();
        if (item != null) {
            getCoffeeRatings().removeCoffeeRatingItem(item);
            ratingsView.getItems().remove(item);
        }
        updateRatingsView();
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
        updateRatingsView();
    }


}
