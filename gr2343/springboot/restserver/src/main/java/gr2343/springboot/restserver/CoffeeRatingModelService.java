package gr2343.springboot.restserver;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.springframework.stereotype.Service;
import gr2343.core.CoffeeRatingItem;
import gr2343.core.CoffeeRating;
import gr2343.core.CoffeeRatingModel;
import gr2343.json.CoffeeRatingPersistence;

/**
 * Configures the coffee rating service,
 * including autowired objects.
 */

@Service
public class CoffeeRatingModelService {

  private CoffeeRatingModel coffeeRatingModel;
  private CoffeeRatingPersistence coffeeRatingPersistence;

  /**
   * Initializes the service with a specific coffeeRatingModel.
   *
   * @param coffeeRatingModel the coffeeRatingModel
   */
  public CoffeeRatingModelService(CoffeeRatingModel coffeeRatingModel) {
    this.coffeeRatingModel = coffeeRatingModel;
    this.coffeeRatingPersistence = new CoffeeRatingPersistence();
    coffeeRatingPersistence.setSaveFile("springbootserver-coffeerating.json");
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
    CoffeeRatingPersistence coffeeRatingPersistence = new CoffeeRatingPersistence();
    URL url = CoffeeRatingModelService.class.getResource("default-coffeeratingmodel.json");
    if (url != null) {
      try (Reader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)) {
        return coffeeRatingPersistence.readCoffeeRatingModel(reader);
      } catch (IOException e) {
        System.out.println("Couldn't read default-coffeemodel.json, so rigging CoffeeModel manually ("
            + e + ")");
      }
    }
    CoffeeRatingModel coffeeRatingModel = new CoffeeRatingModel();
    CoffeeRating coffeeRating1 = new CoffeeRating("coffee1");
    coffeeRating1.addCoffeeRatingItem(new CoffeeRatingItem());
    coffeeRatingModel.addCoffeeRating(coffeeRating1);
    coffeeRatingModel.addCoffeeRating(new CoffeeRating("coffee2"));
    return coffeeRatingModel;
  }

  /**
   * Saves the CoffeeRatingModel to disk.
   * Should be called after each update.
   */
  public void autoSaveCoffeeRatingModel() {
    if (coffeeRatingPersistence != null) {
      try {
        coffeeRatingPersistence.saveCoffeeRatingModel(this.coffeRatingModel);
      } catch (IllegalStateException | IOException e) {
        System.err.println("Couldn't auto-save CoffeeRatingModel: " + e);
      }
    }
  }
}
