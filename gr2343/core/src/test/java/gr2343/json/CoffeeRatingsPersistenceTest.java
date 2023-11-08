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
    private ObjectMapper mockMapper; // Simulates the behavior of the ObjectMapper
    private ObjectWriter mockWriter; // Simulates the behavior of the ObjectWriter
    private CoffeeRatingModel mockModel; // Simulates the behavior of the CoffeeRatingModel

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

    @Test
    public void testReadCoffeeRatings() throws IOException {
        String sampleJson = "{\"lists\":[{\"name\":\"ratings\",\"items\":[]}]}"; // Sample JSON representing a
                                                                                 // CoffeeRatingModel
        when(mockMapper.readValue(sampleJson, CoffeeRatingModel.class)).thenReturn(mockModel);

        CoffeeRatingModel result = persistence.readCoffeeRatings(sampleJson);
        assertEquals(mockModel, result);
    }
}
