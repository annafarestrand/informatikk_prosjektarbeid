package gr2343;

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
    public void testAddCoffeRatingItem_returnsListWithOneItem() { // sjekker at et element kan legges til i Coffeerating
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
    public void testRemoveCoffeRatingItem_returnsEmptyList() { // sjekker at et element kan fjernes fra Coffeerating
        CoffeRatings newRating = new CoffeRatings();
        CoffeRatingItem newItem = new CoffeRatingItem();

        newItem.setDescription("Kaffe på Sit Kafe");
        newItem.setRating(5);

        newRating.addCoffeRatingItem(newItem);
        newRating.removeCoffeRatingItem(newItem);

        Iterator<CoffeRatingItem> newRatingIterator = newRating.iterator();

        assertFalse(newRatingIterator.hasNext());
    }

    /**
     * // test for input i tekstfelt
     * 
     * @Test
     *       public void inputIsText(){
     *       boolean containsLetter = false;
     * 
     *       for (int i = 0; i < inputFraTekstfelt.length(); i ++) {
     *       if (inputFraTekstfelt.at(i).isAlphabetic()) { // isAlphabetic vs.
     *       isLetter
     *       containsLetter = true; // hvis det finnes en bokstav blir denne true og
     *       man hopper ut av løkken
     *       break;
     *       }
     *       }
     *       assertTrue(containsLetter); // ok hvis strengen inneholder letter, ikke
     *       ok hvis ikke
     *       }
     * 
     *       // test for input i rangeringsfeltet
     * @Test
     *       public void numberIsInInterval() {
     *       assertTrue(number >= 1 && number <= 10);
     *       }
     */
}