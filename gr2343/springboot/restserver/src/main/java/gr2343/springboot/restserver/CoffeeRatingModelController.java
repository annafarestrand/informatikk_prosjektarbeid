package gr2343.springboot.restserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import gr2343.core.CoffeeRatings;
import gr2343.core.CoffeeRatingModel;

/**
 * The service implementation.
 */

@RestController
@RequestMapping(CoffeeRatingModelController.COFFEERATING_MODEL_SERVICE_PATH)
public class CoffeeRatingModelController {

  public static final String COFFEERATING_MODEL_SERVICE_PATH = "coffeerating";

  @Autowired
  private CoffeeRatingModelService coffeeRatingModelService;

  @GetMapping
  public CoffeeRatingModel getCoffeeRatingModel() {
    return coffeeRatingModelService.getCoffeeRatingModel();
  }

  private void autoSaveCoffeeRatingModel() {
    coffeeRatingModelService.autoSaveCoffeeRatingModel();
  }

  /**
   * Gets the corresponding Coffee rating.
   *
   * @param name the name of the coffee rating
   * @return the corresponding coffee rating
   */
  @GetMapping(path = "/rating/{name}")
  public CoffeeRatings getCoffeeRating(@PathVariable("name") String name) {
    CoffeeRatings rating = getCoffeeRatingModel().getCoffeeRating(name);
    //checkCoffeeRating(rating, name);
    return rating;
  }

  /**
   * Replaces or adds a coffee rating.
   *
   * @param name the name of the coffee rating
   * @param rating the coffee rating to add
   * @return true if it was added, false if it replaced
   */
  @PutMapping(path = "/rating/{name}")
  public boolean putRating(@PathVariable("name") String name,
      @RequestBody CoffeeRatings rating) {
    // boolean added = getCoffeeRatingModel().putCoffeeRating(rating) == null;
    // autoSaveCoffeeRatingModel();
    // return added;
    return true;
  }

  /**
   * Renames the CoffeeRatings.
   *
   * @param name the name of the CoffeeRatings
   * @param newName the new name
   */
  @PostMapping(path = "/rating/{name}/rename")
  public boolean renameCoffeeRating(@PathVariable("name") String name,
      @RequestParam("newName") String newName) {
    CoffeeRatings coffeeRating = getCoffeeRatingModel().getCoffeeRating(name);
    if (getCoffeeRatingModel().getCoffeeRating(newName) != null) {
      throw new IllegalArgumentException("A CoffeeRatings named \"" + newName + "\" already exists");
    }
    coffeeRating.setName(newName);
    autoSaveCoffeeRatingModel();
    return true;
  }

  /**
   * Removes the CoffeeRatings.
   *
   * @param name the name of the CoffeeRatings
   */
  @DeleteMapping(path = "/rating/{name}")
  public boolean removeCoffeeRating(@PathVariable("name") String name) {
    CoffeeRatings coffeeRating = getCoffeeRatingModel().getCoffeeRating(name);
    //getCoffeeRatingModel().removeCoffeeRating(coffeeRating);
    autoSaveCoffeeRatingModel();
    return true;
  }
}

