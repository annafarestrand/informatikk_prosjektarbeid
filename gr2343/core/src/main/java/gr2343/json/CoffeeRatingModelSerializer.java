package gr2343.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import gr2343.core.CoffeeRatingModel;
import gr2343.core.CoffeeRatings;

class CoffeeRatingModelSerializer extends JsonSerializer<CoffeeRatingModel> {

  /*
   * format: { "ratings": [ ... ] }
   */

  @Override
  public void serialize(CoffeeRatingModel model, JsonGenerator jsonGen, SerializerProvider serializerProvider)
      throws IOException {
    jsonGen.writeStartObject();
    jsonGen.writeArrayFieldStart("ratings");
    for (CoffeeRatings ratings : model) {
      jsonGen.writeObject(ratings);
    }
    jsonGen.writeEndArray();
    jsonGen.writeEndObject();
  }
}
