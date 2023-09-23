package gr2343.ui;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gr2343.core.CoffeRatingItem;
import gr2343.core.CoffeRatings;
import gr2343.json.CoffeRatingModule;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class CoffeRatingController {

    private final static String ratingsWithOneItem = "{\"items\":[{\"description\":\"Kaffe på Sit Kafe\",\"rating\":5}]}";

    private CoffeRatings ratings;
    private ObjectMapper mapper = new ObjectMapper();

    public CoffeRatingController() throws IOException {
        // setter opp data

        mapper.registerModule(new CoffeRatingModule());
        try {
            ratings = mapper.readValue(ratingsWithOneItem, CoffeRatings.class);
        } catch (JsonProcessingException e) {
        }
    }

    @FXML
    ListView<CoffeRatingItem> ratingsView;

    @FXML
    public void initialize() {
        // kobler data til view
        List<CoffeRatingItem> viewRatings = (List<CoffeRatingItem>) ratingsView.getItems();
    }

}