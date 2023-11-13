package gr2343.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CoffeeRatingsTest {

    @Test
    public void testSetAndGetDescription_returnsDescription() { // tester foerst at get og set for description virker
                                                                // som den skal
        CoffeeRatingItem newItem = new CoffeeRatingItem();

        String description = "Kontoret på A3";

        newItem.setDescription(description);

        assertEquals(description, newItem.getDescription());
    }

    @Test
    public void testSetAndGetRating_returnsRating() { // tester saa at get og set for rating virker som den skal
        CoffeeRatingItem newItem = new CoffeeRatingItem();

        int rating = 4;

        newItem.setRating(rating);

        assertEquals(rating, newItem.getRating());
    }

    @Test
    public void testAddCoffeeRatingItem_returnsListWithOneItem() { // tester at et element kan legges til i
                                                                   // CoffeeRatings
        CoffeeRatings newRating = new CoffeeRatings();
        CoffeeRatingItem newItem = new CoffeeRatingItem();

        newItem.setDescription("Kaffe på Sit Kafe");
        newItem.setRating(5);

        newRating.addCoffeeRatingItem(newItem);

        Iterator<CoffeeRatingItem> newRatingIterator = newRating.iterator();

        if (newRatingIterator.hasNext()) {
            assertEquals(newItem, newRatingIterator.next());
        }
    }

    @Test
    public void testRemoveCoffeeRatingItem_returnsEmptyList() { // tester at et element kan fjernes fra CoffeeRatings
        CoffeeRatings newRating = new CoffeeRatings();
        CoffeeRatingItem newItem = new CoffeeRatingItem();

        newItem.setDescription("Kaffe på Sit Kafe");
        newItem.setRating(5);

        newRating.addCoffeeRatingItem(newItem);
        newRating.removeCoffeeRatingItem(newItem);

        Iterator<CoffeeRatingItem> newRatingIterator = newRating.iterator();

        assertFalse(newRatingIterator.hasNext());
    }

    @Test
    public void testGetItems_returnsItems() { // tester at itemsene som returneres har riktig description og rating
        CoffeeRatings newRating = new CoffeeRatings();
        CoffeeRatingItem newItem1 = new CoffeeRatingItem();
        CoffeeRatingItem newItem2 = new CoffeeRatingItem();

        newItem1.setDescription("Kontoret");
        newItem1.setRating(4);

        newItem2.setDescription("Stand på Stripa");
        newItem2.setRating(3);


        newRating.addCoffeeRatingItem(newItem1);
        newRating.addCoffeeRatingItem(newItem2);

        List<CoffeeRatingItem> items = newRating.getItems();
        assertEquals(newItem1, items.get(0));
        assertEquals(newItem2, items.get(1));
    }

    @Test
    public void testToString_returnsString() { // tester at toString returnerer riktig description og rating
        CoffeeRatingItem newItem = new CoffeeRatingItem();

        String description = "Starbucks";
        int rating = 4;

        newItem.setDescription(description);
        newItem.setRating(rating);

        assertEquals("Starbucks, 4/5", newItem.toString());
    }
}
