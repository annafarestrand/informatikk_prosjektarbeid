package gr2343.json;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import gr2343.core.CoffeeRatingModel;

public class CoffeeRatingsPersistence {

  private ObjectMapper mapper;
  private CoffeeRatingModel model;

  public CoffeeRatingsPersistence() {
    mapper = new ObjectMapper();
    mapper.registerModule(new CoffeeRatingModule());
  }

  public CoffeeRatingModel readCoffeeRatings(String ratingsWithItems) throws IOException { // her har han ikke String...
                                                                                           // men Reader reader (fordi
                                                                                           // han hadde reader i
                                                                                           // controlleren)
    return mapper.readValue(ratingsWithItems, CoffeeRatingModel.class);
  }

  public void writeCoffeeRatings(CoffeeRatingModel coffeeRatings, ObjectWriter writer) throws IOException {
    mapper.writerWithDefaultPrettyPrinter().writeValue(new File("ratings.json"), model);
  }

}
