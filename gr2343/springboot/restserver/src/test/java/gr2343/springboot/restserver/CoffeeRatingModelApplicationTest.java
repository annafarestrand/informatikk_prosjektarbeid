package gr2343.springboot.restserver;

import gr2343.core.CoffeeRatingModel;
import gr2343.json.CoffeeRatingsPersistence;
import gr2343.core.CoffeeRatings;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

//@SpringBootTest(classes = CoffeeRatingModelApplication.class)
@ContextConfiguration(classes = CoffeeRatingModelApplication.class)
@WebMvcTest(CoffeeRatingModelController.class)
public class CoffeeRatingModelApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = CoffeeRatingsPersistence.createObjectMapper();
    }

    private String coffeeRatingUrl(String... segments) {
        String url = "/" + CoffeeRatingModelController.COFFEERATING_MODEL_SERVICE_PATH;
        for (String segment : segments) {
            url = url + "/" + segment;
        }
        System.out.println("URL IS THIS: " + url);
        return url;
    }

    // @Test
    // public void testGet_coffeerating() throws Exception {
    //     MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(coffeeRatingUrl())
    //             .accept(MediaType.APPLICATION_JSON))
    //             .andExpect(MockMvcResultMatchers.status().isOk())
    //             .andReturn();
    //     try {
    //         CoffeeRatingModel coffeeRatingModel = objectMapper.readValue(result.getResponse().getContentAsString(), CoffeeRatingModel.class);
    //         Iterator<CoffeeRatings> it = coffeeRatingModel.iterator();
    //         assertTrue(it.hasNext());
    //         CoffeeRatings coffeeRating1 = it.next();
    //         assertFalse(it.hasNext());
    //         assertEquals("ratings", coffeeRating1.getName());
    //     } catch (JsonProcessingException e) {
    //         fail(e.getMessage());
    //     }
    // }
}

