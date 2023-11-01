package gr2343.json;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import gr2343.core.CoffeeRatings;

public class CoffeeRatingsPersistenceTest {

    
    private CoffeeRatingsPersistence persistence;
    private ObjectMapper mockMapper; // simulerer oppførsel til objectMapper
    private ObjectWriter mockWriter; // simulerer oppførsel til objectWriter
    private CoffeeRatings mockRatings; // simulerer oppførsel til coffeeRatings

    @BeforeEach
    public void setUp() {
        mockMapper = mock(ObjectMapper.class);
        mockWriter = mock(ObjectWriter.class);
        mockRatings = mock(CoffeeRatings.class);
        
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

    // Sjekker at resultatet av å kjøre readCoffeRatings() matcher mockRatings. Da finner vi ut om readCoffeeRatings deserialiserer JSON riktig og returnerer riktig CoffeeRating-objekt
    
    @Test
    public void testReadCoffeeRatings() throws IOException {
        String sampleJson = "{\"ratings\":[]}"; //sampleJson representerer et coffeeRating-objekt
        when(mockMapper.readValue(sampleJson, CoffeeRatings.class)).thenReturn(mockRatings);

        CoffeeRatings result = persistence.readCoffeeRatings(sampleJson);
        assertEquals(mockRatings, result);
    }

}
