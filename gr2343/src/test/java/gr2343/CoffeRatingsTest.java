package gr2343.core;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.beans.Transient;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.junit.jupiter.api.Test;


public class CoffeeRatingsTest {

@Test 
public void testAddCoffeRatingItem_returnsListWithOneItem() {     // sjekker at et element kan legges til i Coffeerating
    CoffeRatings newRating = new CoffeRatings();                 // er noe feil her tror jeg, men jeg skjønner ikke helt hva jeg skal
    CoffeRatingItem newItem = new CoffeRatingItem();

    System.out.println(newRating.getClass().getName());

    newItem.setDescription("Kaffe på Sit Kafe");
    newItem.setRating(5);

    newRating.addCoffeRatingItem(newItem);

    //assertEquals(1, newRating.size());
    if (iterator.hasNext()) {
        assertEquals(newItem, iterator.next());
    }
}

/**
// test for input i tekstfelt
    @Test
    public void inputIsText(){
        boolean containsLetter = false;

        for (int i = 0; i < inputFraTekstfelt.length(); i ++) {
            if (inputFraTekstfelt.at(i).isAlphabetic()) {            // isAlphabetic vs. isLetter 
                containsLetter = true;                               // hvis det finnes en bokstav blir denne true og man hopper ut av løkken
                break;
            }
        }
        assertTrue(containsLetter);                                  // ok hvis strengen inneholder letter, ikke ok hvis ikke
    }

    // test for input i rangeringsfeltet
    @Test
    public void numberIsInInterval() {
        assertTrue(number >= 1 && number <= 10);
    }
*/
}