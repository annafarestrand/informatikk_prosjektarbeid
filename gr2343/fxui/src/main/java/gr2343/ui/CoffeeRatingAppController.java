package gr2343.ui;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javafx.fxml.FXML;
import gr2343.core.CoffeeRatingItem;
import gr2343.core.CoffeeRatings;
import gr2343.core.CoffeeRatingModel;
import gr2343.json.CoffeeRatingsPersistence;

/**
 * Top-level controller.
 */
public class CoffeeRatingAppController {

  private static final String COFFEERATING_WITH_2_ITEMS = """
      {
        "ratings": [
          {
            "name": "Vurderinger",
            "items": [
              {
                "description": "Kaffe hos naboen",
                "rating": 1
              },
              {
                "description": "Sito kaffebar",
                "rating": 4
              }
            ]
          }
        ]
      }
  """;

  @FXML
  String userCoffeeRatingModelPath;

  @FXML
  String endpointUri;

  @FXML
  String sampleCoffeeRatingModelResource;

  @FXML
  CoffeeRatingModelController coffeeRatingModelViewController;

  private CoffeeRatingsPersistence coffeeRatingsPersistence;

  private CoffeeRatingModel getInitialCoffeeRatingModel() {
    CoffeeRatingModel coffeeRatingModel = null;
    // try to read file from home folder first
    if (coffeeRatingsPersistence != null) {
      try {
        coffeeRatingModel = coffeeRatingsPersistence.loadCoffeeRatingModel();
      } catch (Exception ioex) {
        System.err.println("Fikk ikke lest inn lagret coffeeRatingModel");
      }
    }
    if (coffeeRatingModel == null) {
      // setter opp data
      Reader reader = null;
      if (sampleCoffeeRatingModelResource != null) {
        // try sample todo list from resources instead
        URL url = getClass().getResource(sampleCoffeeRatingModelResource);
        if (url != null) {
          try {
            reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8);
          } catch (IOException e) {
            System.err.println("Kunne ikke lese innebygget " + sampleCoffeeRatingModelResource);
          }
        } else {
          System.err.println("Fant ikke innebygget " + sampleCoffeeRatingModelResource);
        }
      }
      if (reader == null) {
        reader = new StringReader(COFFEERATING_WITH_2_ITEMS);
      }
      try {
        if (coffeeRatingsPersistence != null) {
          coffeeRatingModel = coffeeRatingsPersistence.readCoffeeRatingModel(reader);
        }   
      } catch (IOException e) {
        // ignore
        System.err.println(e.toString());
      } finally {
        try {
          if (reader != null) {
            reader.close();
          }
        } catch (IOException e) {
          // ignore
          System.err.println(e.toString());
        }
      }
    }
    if (coffeeRatingModel == null) {
      coffeeRatingModel = new CoffeeRatingModel();
      CoffeeRatings coffeeRatings = new CoffeeRatings("Helgehandling", new TodoItem().text("Øl"),
          new TodoItem().text("Pizza"));
      coffeeRatings.setName("Vurderinger");
      CoffeeRatingItem coffeeRatingItem = new CoffeeRatingItem();
      coffeeRatingItem.setDescription("Kaffemaskina på jobben");
      coffeeRatingItem.setRating(3);
      coffeeRatings.addCoffeeRatingItem(coffeeRatingItem);
      coffeeRatingModel.addRating(coffeeRatings);
    }
    return coffeeRatingModel;
  }

  @FXML
  void initialize() {
    CoffeeRatingModelAccess coffeeRatingModelAccess = null;
    if (endpointUri != null) {
      RemoteCoffeeRatingModelAccess remoteAccess;
      try {
        System.out.println("Using remote endpoint @ " + endpointUri);
        remoteAccess = new RemoteCoffeeRatingModelAccess(new URI(endpointUri));
        coffeeRatingModelAccess = remoteAccess;
      } catch (URISyntaxException e) {
        System.err.println(e);
      }
    }
    if (coffeeRatingModelAccess == null) {
      this.coffeeRatingsPersistence = new CoffeeRatingsPersistence();
      coffeeRatingsPersistence.setSaveFile(userCoffeeRatingModelPath);
      DirectCoffeeRatingModelAccess directAccess = new DirectTodoModelAccess(getInitialTodoModel());
      directAccess.setTodoPersistence(coffeeRatingsPersistence);
      coffeeRatingModelAccess = directAccess;
    }
    coffeeRatingModelViewController.setCoffeeRatingModelAccess(coffeeRatingModelAccess);
  }
}