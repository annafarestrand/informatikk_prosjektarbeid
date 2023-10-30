package gr2343.json;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gr2343.core.CoffeeRatingItem;
import gr2343.core.CoffeeRatings;

public class CoffeeRatingModuleTest {

    private static ObjectMapper mapper;

    private String json = "{\"items\":[{\"description\":\"Kaffe på Sit Kafe\",\"rating\":5}]}";

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
            assertEquals(json, mapper.writeValueAsString(ratings));
        } catch (JsonProcessingException e) {
            fail();
        }
    }

    @Test
    public void testDeserializers() {
        try {
            CoffeeRatings ratings = mapper.readValue(json, CoffeeRatings.class);
            Iterator<CoffeeRatingItem> it = ratings.iterator();
            assertTrue(it.hasNext());
            CoffeeRatingItem item = it.next();
            assertEquals("Kaffe på Sit Kafe", item.getDescription());
            assertEquals(5, item.getRating());
        } catch (JsonProcessingException e) {
            fail();
        } catch (IOException e) {
            fail();
        }
    }
}
