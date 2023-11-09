package gr2343.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CoffeeRatingModel implements Iterable<CoffeeRatings> {

  private Collection<CoffeeRatings> ratings = new ArrayList<>();

  public void addRating(CoffeeRatings rating) {
    ratings.add(rating);
  }

  public void removeRatings(CoffeeRatings rating) {
    ratings.remove(rating);
  }

  public CoffeeRatings getCoffeeRating(String name) {
    for (CoffeeRatings rating : ratings) {
      if (rating.getName().equals(name)) {
        return rating;
      }
    }
    return null;
  }

  @Override
  public Iterator<CoffeeRatings> iterator() {
    return ratings.iterator();
  }
}
