package gr2343.json;

import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;

import gr2343.core.CoffeeRatingItem;
import gr2343.core.CoffeeRatingModel;
import gr2343.core.CoffeeRatings;

class CoffeeRatingModule extends SimpleModule {
    private static final String NAME = "CoffeeRatingModule";

    public CoffeeRatingModule() {
        super(NAME, VersionUtil.packageVersionFor(CoffeeRatingModule.class));
        addSerializer(CoffeeRatingItem.class, new CoffeeRatingItemSerializer());
        addSerializer(CoffeeRatings.class, new CoffeeRatingsSerializer());
        addSerializer(CoffeeRatingModel.class, new CoffeeRatingModelSerializer());
        addDeserializer(CoffeeRatingItem.class, new CoffeeRatingItemDeserializer());
        addDeserializer(CoffeeRatings.class, new CoffeeRatingsDeserializer());
        addDeserializer(CoffeeRatingModel.class, new CoffeeRatingModelDeserializer());
    }

    // test
    /*
     * public static void main(String[] args) { ObjectMapper mapper = new ObjectMapper();
     * mapper.registerModule(new CoffeeRatingModule()); CoffeeRatings ratings = new CoffeeRatings();
     * CoffeeRatingItem item = new CoffeeRatingItem(); item.setDescription("Kaffe på Sit Kafe");
     * item.setRating(5); ratings.addCoffeeRatingItem(item); try {
     * System.out.println(mapper.writeValueAsString(ratings)); } catch (JsonProcessingException e) {
     * System.out.println("Virket ikke"); e.printStackTrace(); }
     * 
     * }
     */
}
