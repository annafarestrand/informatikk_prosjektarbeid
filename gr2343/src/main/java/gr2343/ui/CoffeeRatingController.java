package gr2343.ui;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
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

public class CoffeeRatingController {

    private final static String ratingsWithItems = "{\"items\":[{\"description\":\"Kaffe på Sit Kafe\",\"rating\":5}, {\"description\":\"Kaffe fra stand\",\"rating\":3}]}";

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
    Button deleteButton;

    @FXML
    Button newCoffeeRatingButton;

    private Collection<Button> selectionsButtons;

    @FXML
    public void initialize() {
        selectionsButtons = List.of(deleteButton);
        // kobler data til view
        updateRatingsView();
        updatCoffeeRatingButtons();
        ratings.addCofferatingListener(ratings -> updateRatingsView());
        ratingsView.setCellFactory(ratingsView -> new CoffeeRatingItemListCell());
        ratingsView.getSelectionModel().selectedItemProperty().addListener((prop, oldValue, newValue) -> {
            updatCoffeeRatingButtons();
        });
    }

    protected void updateRatingsView() {
        // oppdaterer view
        List<CoffeeRatingItem> viewRatings = ratingsView.getItems();
        viewRatings.clear();
        viewRatings.addAll(ratings.getItems());
    }

    protected void updatCoffeeRatingButtons() {
        // oppdaterer knapper
        boolean disable = ratingsView.getSelectionModel().getSelectedItem() == null;
        for (Button button : selectionsButtons) {
            button.setDisable(disable);
        }
    }

    @FXML
    public void handleDeleteCoffeRatingItem() {
        CoffeeRatingItem item = ratingsView.getSelectionModel().getSelectedItem();
        if (item != null) {
            ratings.removeCoffeeRatingItem(item);
            ratingsView.getItems().remove(item);
        }
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
    };

}