package gr2343.springboot.restserver;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import gr2343.core.CoffeeRatingItem;
import gr2343.core.CoffeeRatings;
import gr2343.core.CoffeeRatingModel;
import gr2343.json.CoffeeRatingsPersistence;
import org.springframework.stereotype.Service;

/**
 * Configures the coffee rating service,
 * including autowired objects.
 */

@Service
public class CoffeeRatingModelService {

  private CoffeeRatingModel coffeeRatingModel;
  private CoffeeRatingsPersistence coffeeRatingsPersistence;

  /**
   * Initializes the service with a specific coffeeRatingModel.
   *
   * @param coffeeRatingModel the coffeeRatingModel
   */
  public CoffeeRatingModelService(CoffeeRatingModel coffeeRatingModel) {
    this.coffeeRatingModel = coffeeRatingModel;
    this.coffeeRatingsPersistence = new CoffeeRatingsPersistence();
    this.coffeeRatingsPersistence.setSaveFile("springbootserver-coffeerating.json");
  }

  public CoffeeRatingModelService() {
    this(createDefaultCoffeeRatingModel());
  }

  public CoffeeRatingModel getCoffeeRatingModel() {
    return coffeeRatingModel;
  }

  public void setCoffeeRatingModel(CoffeeRatingModel coffeeRatingModel) {
    this.coffeeRatingModel = coffeeRatingModel;
  }

  private static CoffeeRatingModel createDefaultCoffeeRatingModel() {
    CoffeeRatingsPersistence coffeeRatingsPersistence = new CoffeeRatingsPersistence();
    URL url = CoffeeRatingModelService.class.getResource("default-coffeeratingmodel.json");
    if (url != null) {
      try (Reader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)) {
        return coffeeRatingsPersistence.readCoffeeRatingModel(reader);
      } catch (IOException e) {
        System.err.println("Couldn't read default CoffeeRatingModel: " + e);
      }
    }
    CoffeeRatingModel coffeeRatingModel = new CoffeeRatingModel();
    CoffeeRatings coffeeRating1 = new CoffeeRatings();
    coffeeRating1.setName("ratings");
    CoffeeRatingItem coffeeRatingItem1 = new CoffeeRatingItem();
    coffeeRatingItem1.setDescription("Kaffe fra Sit");
    coffeeRatingItem1.setRating(3);
    coffeeRating1.addCoffeeRatingItem(coffeeRatingItem1);
    coffeeRatingModel.addCoffeeRating(coffeeRating1);
    return coffeeRatingModel;
  }

  /**
   * Saves the CoffeeRatingModel to disk.
   * Should be called after each update.
   */
  public void autoSaveCoffeeRatingModel() {
    if (coffeeRatingsPersistence != null) {
      try {
        coffeeRatingsPersistence.saveCoffeeRatingModel(this.coffeeRatingModel);
      } catch (IllegalStateException | IOException e) {
        System.err.println("Couldn't auto-save CoffeeRatingModel: " + e);
      }
    }
  }
}
