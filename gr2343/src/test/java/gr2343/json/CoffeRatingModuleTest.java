package gr2343.json;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gr2343.core.CoffeRatingItem;
import gr2343.core.CoffeRatings;

public class CoffeRatingModuleTest {

    private static ObjectMapper mapper;

    @BeforeAll
    public static void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new CoffeRatingModule());
    }

    @Test
    public void testSerializers() {
        CoffeRatings ratings = new CoffeRatings();
        CoffeRatingItem item = new CoffeRatingItem();
        item.setDescription("Kaffe på Sit Kafe");
        item.setRating(5);
        ratings.addCoffeRatingItem(item);
        try {
            assertEquals(
                    "{\"items\":[{\"description\":\"Kaffe på Sit Kafe\",\"rating\":5}]}",
                    mapper.writeValueAsString(ratings));
        } catch (JsonProcessingException e) {
            fail();
        }
    }

}
