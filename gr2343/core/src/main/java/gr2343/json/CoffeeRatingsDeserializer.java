package gr2343.json;

import gr2343.core.CoffeeRatingItem;
import gr2343.core.CoffeeRatings;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;

public class CoffeeRatingsDeserializer extends JsonDeserializer<CoffeeRatings> {

  private CoffeeRatingItemDeserializer itemDeserializer = new CoffeeRatingItemDeserializer();
  /*
   * Format: { "items": [...] // Array of CoffeeRatingItem }
   */

  @Override
  public CoffeeRatings deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    TreeNode treeNode = parser.getCodec().readTree(parser);
    if (treeNode instanceof ObjectNode) {
      ObjectNode objectNode = (ObjectNode) treeNode;
      CoffeeRatings ratings = new CoffeeRatings();
      JsonNode itemsNode = objectNode.get("items");
      if (itemsNode instanceof ArrayNode) {
        for (JsonNode elementNode : ((ArrayNode) itemsNode)) {
          CoffeeRatingItem item = itemDeserializer.deserialize(elementNode);
          if (item != null) {
            ratings.addCoffeeRatingItem(item);
          }
        }
      }
      return ratings;
    }
    return null;
  }
}

