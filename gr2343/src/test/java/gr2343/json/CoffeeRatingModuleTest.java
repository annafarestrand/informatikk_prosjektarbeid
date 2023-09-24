package gr2343.json;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gr2343.core.CoffeeRatingItem;
import gr2343.core.CoffeeRatings;

public class CoffeeRatingModuleTest {

    private static ObjectMapper mapper;

    @BeforeAll
    public static void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new CoffeeRatingModule());
    }

    @Test
    public void testSerializers() {
        CoffeeRatings ratings = new CoffeeRatings();
        CoffeeRatingItem item = new CoffeeRatingItem();
        item.setDescription("Kaffe på Sit Kafe");
        item.setRating(5);
        ratings.addCoffeeRatingItem(item);
        try {
            assertEquals(
                    "{\"items\":[{\"description\":\"Kaffe på Sit Kafe\",\"rating\":5}]}",
                    mapper.writeValueAsString(ratings));
        } catch (JsonProcessingException e) {
            fail();
        }
    }

}
