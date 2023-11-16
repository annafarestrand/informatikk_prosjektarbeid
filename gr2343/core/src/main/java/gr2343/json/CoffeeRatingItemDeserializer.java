package gr2343.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import gr2343.core.CoffeeRatingItem;

// denne klassen brukes til å hente ut en JSON-streng og oversette det til et javaobjekt (et CoffeeRatingItem)

class CoffeeRatingItemDeserializer extends JsonDeserializer<CoffeeRatingItem> {

  @Override
  public CoffeeRatingItem deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    TreeNode treeNode = parser.getCodec().readTree(parser);
    return deserialize((JsonNode) treeNode);
  }

  CoffeeRatingItem deserialize(JsonNode jsonNode) {
    if (jsonNode.isObject()) {
      CoffeeRatingItem item = new CoffeeRatingItem();
      JsonNode descriptionNode = jsonNode.get("description");
      if (descriptionNode != null && descriptionNode.isTextual()) {
        item.setDescription(descriptionNode.asText());
      }
      JsonNode ratingNode = jsonNode.get("rating");
      if (ratingNode != null && ratingNode.isInt()) {
        item.setRating(ratingNode.asInt());
      }
      return item;
    }
    return null;
  }
}
