package gr2343.ui;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gr2343.core.CoffeRatingItem;
import gr2343.core.CoffeRatings;
import gr2343.json.CoffeRatingModule;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CoffeRatingController {

    private final static String ratingsWithItems = "{\"items\":[{\"description\":\"Kaffe på Sit Kafe\",\"rating\":5}, {\"description\":\"Kaffe fra stand\",\"rating\":3}]}";

    private CoffeRatings ratings;
    private ObjectMapper mapper = new ObjectMapper();

    public CoffeRatingController() throws IOException {
        // setter opp data
        mapper.registerModule(new CoffeRatingModule());
        try {
            ratings = mapper.readValue(ratingsWithItems, CoffeRatings.class);
        } catch (JsonProcessingException e) {
        }
    }

    @FXML
    TextField newDescriptionText;

    @FXML
    TextField newRatingText;

    @FXML
    ListView<CoffeRatingItem> ratingsView;

    @FXML
    public void initialize() {
        // kobler data til view
        updateRatingsView();
    }

    protected void updateRatingsView() {
        // oppdaterer view
        List<CoffeRatingItem> viewRatings = ratingsView.getItems();
        viewRatings.clear();
        viewRatings.addAll(ratings.getItems());
    }

    @FXML
    public void handlenewCoffeRatingAction() {
        // legger til ny rating
        CoffeRatingItem item = new CoffeRatingItem();
        item.setDescription(newDescriptionText.getText());
        item.setRating(Integer.parseInt(newRatingText.getText()));
        ratings.addCoffeRatingItem(item);
        ratingsView.getItems().add(item);
    };

}