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
import gr2343.core.CoffeeRatingModel;

public class CoffeeRatingModuleTest {

    // setter opp en mapper
    private static ObjectMapper mapper;

    // setter inn en simulering av en json-streng
    private String json =
            "{\"ratings\":[{\"name\":\"rating\",\"items\":[{\"description\":\"Kaffe på Sit Kafe\",\"rating\":5}]}]}";

    // setter opp en mapper og module før hver test
    @BeforeAll
    public static void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new CoffeeRatingModule());
    }

    // tester at man kan oversette fra javaobjekt til JSON-streng
    @Test
    public void testSerializers() {
        CoffeeRatingModel model = new CoffeeRatingModel();
        CoffeeRatings ratings = new CoffeeRatings();
        ratings.setName("rating");
        model.addCoffeeRating(ratings);
        CoffeeRatingItem item = new CoffeeRatingItem();
        item.setDescription("Kaffe på Sit Kafe");
        item.setRating(5);
        ratings.addCoffeeRatingItem(item);
        try {
            assertEquals(json, mapper.writeValueAsString(model));
        } catch (JsonProcessingException e) {
            fail();
        }
    }

    // tester at man kan oversette fra JSON-streng til javaobjekt
    @Test
    public void testDeserializers() {
        try {
            CoffeeRatingModel model = mapper.readValue(json, CoffeeRatingModel.class);
            assertTrue(model.iterator().hasNext());
            CoffeeRatings ratings = model.iterator().next();
            assertEquals("rating", ratings.getName());
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
