package gr2343.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import gr2343.core.CoffeRatingItem;
import gr2343.core.CoffeRatings;

public class CoffeRatingSerializer extends JsonSerializer<CoffeRatings> {
    /*
     * Format:
     * {
     * "items": [...] // Array of CoffeRatingItem
     * }
     */

    @Override
    public void serialize(CoffeRatings items,
            JsonGenerator jGen,
            SerializerProvider serializerProvider)
            throws IOException {
        jGen.writeStartObject();
        jGen.writeArrayFieldStart("items");
        for (CoffeRatingItem item : items) {
            jGen.writeObject(item);
        }
        jGen.writeEndArray();
        jGen.writeEndObject();
    }
}