package gr2343.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CoffeeRatingModel implements Iterable<CoffeeRatings> {

  private Collection<CoffeeRatings> ratings = new ArrayList<>();

  public void addRating(CoffeeRatings rating) {
    ratings.add(rating);
  }

  public void removeRating(CoffeeRatings rating) {
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

  public CoffeeRatings putCoffeeRating(CoffeeRatings rating) {
    CoffeeRatings oldRating = getCoffeeRating(rating.getName());
    if (oldRating != null) {
      removeRating(oldRating);
    }
    addRating(rating);
    return oldRating;
  }
}
