package gr2343.springboot.restserver;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.springframework.stereotype.Service;
import gr2343.core.CoffeeRatingItem;
import gr2343.core.CoffeeRatings;
import gr2343.core.CoffeeRatingModel;
import gr2343.json.CoffeeRatingsPersistence;

/**
 * Configures the coffee rating service,
 * including autowired objects.
 */

@Service
public class CoffeeRatingModelService {

  private CoffeeRatingModel coffeeRatingModel;
  private CoffeeRatingsPersistence CoffeeRatingsPersistence;

  /**
   * Initializes the service with a specific coffeeRatingModel.
   *
   * @param coffeeRatingModel the coffeeRatingModel
   */
  public CoffeeRatingModelService(CoffeeRatingModel coffeeRatingModel) {
    this.coffeeRatingModel = coffeeRatingModel;
    this.CoffeeRatingsPersistence = new CoffeeRatingsPersistence();
    // CoffeeRatingsPersistence.setSaveFile("springbootserver-coffeerating.json");
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
    CoffeeRatingsPersistence CoffeeRatingsPersistence = new CoffeeRatingsPersistence();
    // URL url = CoffeeRatingModelService.class.getResource("default-coffeeratingmodel.json");
    // if (url != null) {
    //   try (Reader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)) {
    //     return CoffeeRatingsPersistence.readCoffeeRatingModel(reader);
    //   } catch (IOException e) {
    //     System.out.println("Couldn't read default-coffeemodel.json, so rigging CoffeeModel manually ("
    //         + e + ")");
    //   }
    // }
    CoffeeRatingModel coffeeRatingModel = new CoffeeRatingModel();
    // CoffeeRatings coffeeRating1 = new CoffeeRatings("coffee1");
     CoffeeRatings coffeeRating1 = new CoffeeRatings();
     coffeeRating1.setName("coffee1");
    coffeeRating1.addCoffeeRatingItem(new CoffeeRatingItem());
    // coffeeRatingModel.addCoffeeRating(coffeeRating1);
    // coffeeRatingModel.addCoffeeRating(new CoffeeRatings("coffee2"));
    CoffeeRatings coffeeRating2 = new  CoffeeRatings();
    coffeeRating2.setName("coffee2");
    // coffeeRatingModel.addCoffeeRating(coffeeRating2);
    return coffeeRatingModel;
  }

  /**
   * Saves the CoffeeRatingModel to disk.
   * Should be called after each update.
   */
  public void autoSaveCoffeeRatingModel() {
    if (CoffeeRatingsPersistence != null) {
      // try {
      //   CoffeeRatingsPersistence.saveCoffeeRatingModel(this.coffeeRatingModel);
      // } catch (IllegalStateException | IOException e) {
      //   System.err.println("Couldn't auto-save CoffeeRatingModel: " + e);
      // }
    }
  }
}
