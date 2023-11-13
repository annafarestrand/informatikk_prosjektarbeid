package gr2343.json;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import gr2343.core.CoffeeRatingModel;

public class CoffeeRatingsPersistenceTest {

    private CoffeeRatingsPersistence persistence;
    private ObjectMapper mockMapper; // simulerer en ObjectMapper
    private ObjectWriter mockWriter; // simulerer en ObjectWriter
    private CoffeeRatingModel mockModel; // simulerer en CoffeeRatingModel

    // setter opp mapper, writer og model før hver test
    @BeforeEach
    public void setUp() {
        mockMapper = mock(ObjectMapper.class);
        mockWriter = mock(ObjectWriter.class);
        mockModel = mock(CoffeeRatingModel.class);

        when(mockMapper.writerWithDefaultPrettyPrinter()).thenReturn(mockWriter);

        persistence = new CoffeeRatingsPersistence();

        try {
            java.lang.reflect.Field field = CoffeeRatingsPersistence.class.getDeclaredField("mapper");
            field.setAccessible(true);
            field.set(persistence, mockMapper);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Failed to set mock ObjectMapper: " + e.getMessage());
        }
    }

    // tester om man kan lese en CoffeeRating fra model
    @Test
    public void testReadCoffeeRatings() throws IOException {
        String sampleJson = "{\"lists\":[{\"name\":\"ratings\",\"items\":[]}]}";
        when(mockMapper.readValue(sampleJson, CoffeeRatingModel.class)).thenReturn(mockModel);

        CoffeeRatingModel result = persistence.readCoffeeRatings(sampleJson);
        assertEquals(mockModel, result);
    }
}