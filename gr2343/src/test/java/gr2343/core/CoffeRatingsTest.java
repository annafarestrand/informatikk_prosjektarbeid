package gr2343.core;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.beans.Transient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import gr2343.core.CoffeRatingItem;
import gr2343.core.CoffeRatings;

public class CoffeRatingsTest {

    @Test 
    public void testSetAndGetDescription_returnsDescription() {   // tester foerst at get og set for description virker som den skal
        CoffeRatingItem newItem = new CoffeRatingItem();

        String description = "Kontoret på A3";

        newItem.setDescription(description);

        assertEquals(description, newItem.getDescription());
    }

    @Test 
    public void testSetAndGetRating_returnsRating() {            // tester saa at get og set for rating virker som den skal
        CoffeRatingItem newItem = new CoffeRatingItem();

        int rating = 4;
        
        newItem.setRating(rating);

        assertEquals(rating, newItem.getRating());
    }

    @Test
    public void testAddCoffeRatingItem_returnsListWithOneItem() { // tester at et element kan legges til i CoffeRatings
        CoffeRatings newRating = new CoffeRatings();
        CoffeRatingItem newItem = new CoffeRatingItem();

        newItem.setDescription("Kaffe på Sit Kafe");
        newItem.setRating(5);

        newRating.addCoffeRatingItem(newItem);

        Iterator<CoffeRatingItem> newRatingIterator = newRating.iterator();

        if (newRatingIterator.hasNext()) {
            assertEquals(newItem, newRatingIterator.next());
        }
    }

    @Test
    public void testRemoveCoffeRatingItem_returnsEmptyList() { // tester at et element kan fjernes fra CoffeeRating
        CoffeRatings newRating = new CoffeRatings();
        CoffeRatingItem newItem = new CoffeRatingItem();

        newItem.setDescription("Kaffe på Sit Kafe");
        newItem.setRating(5);

        newRating.addCoffeRatingItem(newItem);
        newRating.removeCoffeRatingItem(newItem);

        Iterator<CoffeRatingItem> newRatingIterator = newRating.iterator();

        assertFalse(newRatingIterator.hasNext());
    }
}