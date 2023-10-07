package gr2343.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import gr2343.core.CoffeeRatingItem;

public class CoffeeRatingItemSerializer extends JsonSerializer<CoffeeRatingItem> {
    /*
     * Format:
     * {
     * "description": "A very good Coffeee",
     * "rating": 5
     * }
     */

    @Override
    public void serialize(CoffeeRatingItem item,
            JsonGenerator jGen,
            SerializerProvider serializerProvider)
            throws IOException {
        jGen.writeStartObject();
        jGen.writeStringField("description", item.getDescription());
        jGen.writeNumberField("rating", item.getRating());
        jGen.writeEndObject();
    }
}