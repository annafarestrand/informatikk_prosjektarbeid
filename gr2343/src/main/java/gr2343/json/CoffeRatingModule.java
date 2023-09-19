package gr2343.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import gr2343.core.CoffeRatingItem;
import gr2343.core.CoffeRatings;

public class CoffeRatingModule extends SimpleModule {
    private static final String NAME = "CoffeRatingModule";
    private static final VersionUtil VERSION_UTIL = new VersionUtil() {
    };

    public CoffeRatingModule() {
        super(NAME, VERSION_UTIL.version());
        addSerializer(CoffeRatingItem.class, new CoffeRatingItemSerializer());
        addSerializer(CoffeRatings.class, new CoffeRatingSerializer());
    }

    // test
    /*
     * public static void main(String[] args) {
     * ObjectMapper mapper = new ObjectMapper();
     * mapper.registerModule(new CoffeRatingModule());
     * CoffeRatings ratings = new CoffeRatings();
     * CoffeRatingItem item = new CoffeRatingItem();
     * item.setDescription("Kaffe på Sit Kafe");
     * item.setRating(5);
     * ratings.addCoffeRatingItem(item);
     * try {
     * System.out.println(mapper.writeValueAsString(ratings));
     * } catch (JsonProcessingException e) {
     * System.out.println("Virket ikke");
     * e.printStackTrace();
     * }
     * 
     * }
     */
}
