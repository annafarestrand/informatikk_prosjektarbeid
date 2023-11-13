package gr2343.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import gr2343.core.CoffeeRatingItem;
import gr2343.core.CoffeeRatingModel;
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

  // starter et testvindu som skal brukes videre
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
    ratings = new CoffeeRatings();
    item1 = new CoffeeRatingItem();
    item1.setDescription("Kaffe på Sit Kafe");
    item1.setRating(5);
    item2 = new CoffeeRatingItem();
    item2.setDescription("Kaffe fra stand");
    item2.setRating(3);
  }


  @Test
  public void testController_intital() {
    assertNotNull(this.controller, "Controller is not initialized");
    assertNotNull(this.ratings, "Ratings in controller is not initialized");
    checkCoffeeRatingListItems(item1, item2);
  }

  @Test
  public void testGetModel() {
      // henter ut modellen
      CoffeeRatingModel model = controller.getModel();
      // sjekker at modellen blir hentet
      assertNotNull(model, "Cannot get model.");
  }

  @Test
  public void testRatingsView_initialItems() {
    final ListView<CoffeeRatingItem> ratingsView = lookup("#ratingsView").query();
    checkCoffeeRatingItems(ratingsView.getItems(), item1, item2);

  }

  // tester at nye objekt blir lagt inn riktig
  @Test
  public void testNewCoffeRating() {
    String newDescription = "Kantina";
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

  @Test
  private void testAlert_DescriptionNotValid() {
    // Legger inn en ugyldig beskrivelse
    String newDescription = "48902";
    String newRating = "4";

    // skriver inn description og rating i tekstfeltene
    clickOn("#newDescriptionText").write(newDescription);
    clickOn("#newRatingText").write(newRating);

    // prøver å lagre
    clickOn("#newCoffeeRatingButton");

    // sjekke at det kommer opp en alert med riktig navn 


  }

  @Test
  public void testDeleteCoffeRating() {
    // legger inn en rating som kan slettes
    String newDescription = "Nabo-kaféen";
    String newRating = "5";

    // skriver inn description og rating
    clickOn("#newDescriptionText").write(newDescription);
    clickOn("#newRatingText").write(newRating);

    // lagrer
    clickOn("#newCoffeeRatingButton");
    CoffeeRatingItem newItem = new CoffeeRatingItem();
    newItem.setDescription(newDescription);
    newItem.setRating(Integer.parseInt(newRating));

    // klikke paa det item'et som skal slettes

    // sletter itemet
    clickOn("#deleteRatingButton");

    // sjekker at riktig items ligger i listen
    checkCoffeeRatingListItems(item1, item2, newItem);
    checkCoffeeRatingListViewItems(item1, newItem, item2);
  }

  @Test
  public void testUpdateCoffeRating() {
    // legger inn et item
    String newDescription1 = "Kontoret mitt";
    String newRating1 = "5";
    clickOn("#newDescriptionText").write(newDescription1);
    clickOn("#newRatingText").write(newRating1);
    clickOn("#newCoffeeRatingButton");

    CoffeeRatingItem newItem = new CoffeeRatingItem();
    newItem.setDescription(newDescription1);
    newItem.setRating(Integer.parseInt(newRating1));

    // angrer paa det vi la inn, vil redigere item'et
    // klikker paa det item'et som vi vil endre paa

    // skriver inn ny description og rating og lagrer denne
    String newDescription2 = "Kontoret ditt";
    String newRating2 = "3";
    clickOn("#updateRatingButton");
    clickOn("#newDescriptionText").write(newDescription2);
    clickOn("#newRatingText").write(newRating2);
    newItem.setDescription(newDescription2);
    newItem.setRating(Integer.parseInt(newRating2));

    checkCoffeeRatingListItems(item1, item2, newItem);
    checkCoffeeRatingListViewItems(item1, newItem, item2);
  }


}
