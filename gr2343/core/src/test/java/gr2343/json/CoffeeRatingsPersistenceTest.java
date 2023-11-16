package gr2343.json;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import gr2343.core.CoffeeRatingModel;

public class CoffeeRatingsPersistenceTest {

    private CoffeeRatingsPersistence persistence;
    private ObjectMapper mockMapper;
    private ObjectWriter mockWriter;
    private CoffeeRatingModel mockModel;

    @BeforeEach
    public void setUp() {
        // Oppretter mock-objekter
        mockMapper = mock(ObjectMapper.class);
        mockWriter = mock(ObjectWriter.class);
        mockModel = mock(CoffeeRatingModel.class);

        // Simulerer oppførselen til mock-objektene
        when(mockMapper.writerWithDefaultPrettyPrinter()).thenReturn(mockWriter);

        // Oppretter testobjektet
        persistence = new CoffeeRatingsPersistence();
        // Setter det private feltet mapper ved hjelp av refleksjon
        setPrivateField(persistence, "mapper", mockMapper);
    }

    @Test
    public void testReadCoffeeRatings() throws IOException {
        // Simulerer JSON-data og forventet modell fra mock-objektet
        String sampleJson = "{\"lists\":[{\"name\":\"ratings\",\"items\":[]}]}";
        when(mockMapper.readValue(sampleJson, CoffeeRatingModel.class)).thenReturn(mockModel);

        // Utfører testen
        CoffeeRatingModel result = persistence.readCoffeeRatings(sampleJson);
        // Sjekker om resultatet er som forventet
        assertEquals(mockModel, result);
    }

    @Test
    public void testWriteCoffeeRatingModel() throws IOException {
        // Oppretter en mock Writer
        Writer mockWriter = mock(Writer.class);
        // Utfører skriving til modellen
        persistence.writeCoffeeRatingModel(mockModel, mockWriter);
        // Verifiserer at writeValue-metoden på mockWriter ble kalt med riktige argumenter
        verify(mockMapper.writerWithDefaultPrettyPrinter()).writeValue(mockWriter, mockModel);
    }

    @Test
    public void testReadCoffeeRatingModel() throws IOException {
        // Oppretter en mock Reader
        Reader mockReader = mock(Reader.class);
        when(mockMapper.readValue(mockReader, CoffeeRatingModel.class)).thenReturn(mockModel);

        // Utfører lesing fra modellen
        CoffeeRatingModel result = persistence.readCoffeeRatingModel(mockReader);
        // Sjekker om resultatet er som forventet
        assertEquals(mockModel, result);
    }

    @Test
    public void testSetSaveFile() {
        // Definerer en testfil
        String saveFile = "testFile.json";
        // Setter filstien ved hjelp av setSaveFile-metoden
        persistence.setSaveFile(saveFile);
        // Forventer at filstien er satt korrekt
        Path saveFilePath = Paths.get(System.getProperty("user.home"), saveFile);
        assertEquals(saveFilePath, persistence.getSaveFilePath());
    }

    @Test
    public void testLoadCoffeeRatingModel() throws IOException {
        // Setter en testfil for lasting
        String saveFile = "testFile.json";
        persistence.setSaveFile(saveFile);

        // Simulerer JSON-innhold og en forventet modell fra mock-objektet
        String jsonContent = "{\"lists\":[{\"name\":\"ratings\",\"items\":[]}]}";
        StringReader stringReader = new StringReader(jsonContent);
        when(mockMapper.readValue(stringReader, CoffeeRatingModel.class)).thenReturn(mockModel);

        // Utfører lasting av modellen
        CoffeeRatingModel result = persistence.loadCoffeeRatingModel();
        // Sjekker om resultatet er som forventet
        assertEquals(mockModel, result);
    }

    @Test
    public void testSaveCoffeeRatingModel() throws IOException {
        // Setter en testfil for lagring
        String saveFile = "testFile.json";
        persistence.setSaveFile(saveFile);


        // Simulerer oppførselen til mock-objektene
        when(mockMapper.writerWithDefaultPrettyPrinter()).thenReturn(mockWriter);
        when(mockWriter.writeValueAsString(mockModel)).thenReturn("mockJson");

        // Utfører lagring av modellen
        persistence.saveCoffeeRatingModel(mockModel);

        // Verifiserer at writeValue-metoden på mockWriter ble kalt med riktige argumenter
        verify(mockWriter).writeValue(any(FileWriter.class), eq(mockModel));
    }

    // Hjelpemetode for å sette private felt ved hjelp av refleksjon
    private static void setPrivateField(Object object, String fieldName, Object value) {
        try {
            // Henter det private feltet ved hjelp av refleksjon
            java.lang.reflect.Field field = object.getClass().getDeclaredField(fieldName);
            // Gjør det private feltet tilgjengelig for endringer
            field.setAccessible(true);
            // Setter verdien til det private feltet
            field.set(object, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Feilet å sette mock-objektet: " + e.getMessage());
        }
    }
}
