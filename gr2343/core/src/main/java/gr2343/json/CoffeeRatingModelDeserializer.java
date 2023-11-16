package gr2343.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import gr2343.core.CoffeeRatingModel;
import gr2343.core.CoffeeRatings;

class CoffeeRatingModelDeserializer extends JsonDeserializer<CoffeeRatingModel> {

    private CoffeeRatingsDeserializer coffeeRatingsDeserializer = new CoffeeRatingsDeserializer();

    /*
     * format: { "ratings": [ ... ] }
     */

    @Override
    public CoffeeRatingModel deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        System.out.println("Raw JSON Content: " + treeNode.toString()); // Log the raw JSON content
        return deserialize((JsonNode) treeNode);
    }

    CoffeeRatingModel deserialize(JsonNode treeNode) {
        if (treeNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) treeNode;
            CoffeeRatingModel model = new CoffeeRatingModel();
            JsonNode ratingsNode = objectNode.get("ratings");
            if (ratingsNode instanceof ArrayNode) {
                for (JsonNode elementNode : ((ArrayNode) ratingsNode)) {
                    CoffeeRatings ratings = coffeeRatingsDeserializer.deserialize(elementNode);
                    if (ratings != null) {
                        model.addRating(ratings);
                    }
                }
            }
            return model;
        }
        return null;
    }
}
