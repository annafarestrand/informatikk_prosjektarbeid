package gr2343.json;

import java.io.IOException;

import gr2343.core.CoffeeRatingItem;
import gr2343.core.CoffeeRatings;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

// denne klassen oversetter en CoffeeRatings fra java-objekt til en JSON-streng

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
