package gr2343.ui;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import gr2343.core.CoffeeRatingItem;
import gr2343.core.CoffeeRatings;
import gr2343.json.CoffeeRatingModule;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CoffeeRatingController {

    private final static String ratingsWithItems = "{\"items\":[]}";

    private CoffeeRatings ratings;
    private ObjectMapper mapper = new ObjectMapper();

    public CoffeeRatingController() throws IOException {
        // setter opp data
        mapper.registerModule(new CoffeeRatingModule());
        try {
            ratings = mapper.readValue(ratingsWithItems, CoffeeRatings.class);
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
    public void initialize() {
        // kobler data til view
        updateRatingsView();
    }

    protected CoffeeRatings getRatings() {
        return ratings;
    }

    protected void updateRatingsView() {
        // oppdaterer view
        List<CoffeeRatingItem> viewRatings = ratingsView.getItems();
        viewRatings.clear();
        viewRatings.addAll(ratings.getItems());
    }

    @FXML
    public void handlenewCoffeeRatingAction() {
        // legger til ny rating
        CoffeeRatingItem item = new CoffeeRatingItem();
        item.setDescription(newDescriptionText.getText());
        item.setRating(Integer.parseInt(newRatingText.getText()));
        ratings.addCoffeeRatingItem(item);
        ratingsView.getItems().add(item);
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(new File("ratings.json"), ratings);
        } catch (IOException e) {
            e.printStackTrace();
        }
        newDescriptionText.clear();
        newRatingText.clear();
    };

}
