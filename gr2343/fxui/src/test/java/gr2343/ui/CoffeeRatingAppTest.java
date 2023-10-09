package gr2343.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import gr2343.core.CoffeeRatingItem;
import gr2343.core.CoffeeRatings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class CoffeeRatingAppTest extends ApplicationTest {

  private CoffeeRatingController controller;
  private CoffeeRatings ratings;
  private CoffeeRatingItem item1, item2;

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("CoffeeRatingTest.fxml"));
    final Parent root = loader.load();
    this.controller = loader.getController();
    this.ratings = this.controller.getRatings();
    stage.setScene(new Scene(root));
    stage.show();
  }

  @BeforeEach
  public void setupItems() {
    item1 = new CoffeeRatingItem();
    item1.setDescription("Kaffe på Sit Kafe");
    item1.setRating(5);
    item2 = new CoffeeRatingItem();
    item2.setDescription("Kaffe fra stand");
    item2.setRating(3);
  }

  @Test
  public void testController_intital() {
    assertNotNull(this.controller);
    assertNotNull(this.ratings);

    checkCoffeeRatingItems(this.ratings, item1, item2);
  }

  @Test
  public void testRatingsView_initialItems() {
    final ListView<CoffeeRatingItem> ratingsView = lookup("#ratingsView").query();
    checkCoffeeRatingItems(ratingsView.getItems(), item1, item2);

  }

  @Test
  public void testNewCoffeRating() {
    String newDescription = "Kaffe fra kantina";
    String newRating = "4";
    clickOn("#newDescriptionText").write(newDescription);
    clickOn("#newRatingText").write(newRating);
    clickOn("#newCoffeeRatingButton");
    CoffeeRatingItem newItem = new CoffeeRatingItem();
    newItem.setDescription(newDescription);
    newItem.setRating(Integer.parseInt(newRating));

    checkCoffeeRatingListItems(item1, item2, newItem);

    checkCoffeeRatingListViewItems(item1, newItem, item2);
  }

  @Test


  private void checkCoffeeRatingItem(CoffeeRatingItem CoffeeRating, int rating, String text) {
    if (rating != 0) {
      assertEquals(rating, CoffeeRating.getRating());
    }
    if (text != null) {
      assertEquals(text, CoffeeRating.getDescription());
    }
  }

  private void checkCoffeeRatingListItems(CoffeeRatingItem... items) {
    checkCoffeeRatingItems(this.ratings, items);
  }


  private void checkCoffeeRatingListViewItems(CoffeeRatingItem... items) {
    final ListView<CoffeeRatingItem> ratingsView = lookup("#ratingsView").query();
    checkCoffeeRatingItems(ratingsView.getItems(), items);
  }

  private void checkCoffeeRatingItems(Iterable<CoffeeRatingItem> it, CoffeeRatingItem... items) {
    int i = 0;
    for (CoffeeRatingItem item : items) {
      assertTrue(i < items.length);
      checkCoffeeRatingItem(item, items[i].getRating(), items[i].getDescription());
      i++;
    }
    assertTrue(i == items.length);
  }
}
