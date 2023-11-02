package gr2343.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import gr2343.core.CoffeeRatingItem;
import gr2343.core.CoffeeRatings;

class CoffeeRatingsSerializer extends JsonSerializer<CoffeeRatings> {
    /*
     * Format: { "name": "...", "items": [...] // Array of CoffeeRatingItem }
     */

    @Override
    public void serialize(CoffeeRatings items, JsonGenerator jGen, SerializerProvider serializerProvider)
            throws IOException {
        jGen.writeStartObject();
        if (items.getName() != null) {
            jGen.writeStringField("name", items.getName());
        }
        jGen.writeArrayFieldStart("items");
        for (CoffeeRatingItem item : items) {
            jGen.writeObject(item);
        }
        jGen.writeEndArray();
        jGen.writeEndObject();
    }
}
