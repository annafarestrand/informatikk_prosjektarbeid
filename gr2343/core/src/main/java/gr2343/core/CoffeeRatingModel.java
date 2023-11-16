package gr2343.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CoffeeRatingModel implements Iterable<CoffeeRatings> {

  private Collection<CoffeeRatings> ratings = new ArrayList<>(); // lager en ny collection som brukes til å legge inn, hente ut, iterere over og fjerne enkle CoffeeRatings fra

  public void addCoffeeRating(CoffeeRatings rating) { // legger inn en CoffeeRating med rating og beskrivelse i en collection
    ratings.add(rating);
  }

  public void removeCoffeeRating(CoffeeRatings rating) { // fjerner en gitt CoffeeRating fra en collection
    ratings.remove(rating);
  }

  public CoffeeRatings getCoffeeRating(String name) { // henter ut en CoffeeRating som ligger i en collection
    for (CoffeeRatings rating : ratings) {
      if (rating.getName().equals(name)) {
        return rating;
      }
    }
    return null; // hvis det ikke finnes en CoffeeRating med det gitte navnet i collectionen, returneres null
  }

  @Override
  public Iterator<CoffeeRatings> iterator() { // lager en iterator for å iterere gjennom collectionen ratings
    return ratings.iterator();
  }

  public CoffeeRatings putCoffeeRating(CoffeeRatings rating) {
    CoffeeRatings oldRating = getCoffeeRating(rating.getName());
    if (oldRating != null) {
      removeCoffeeRating(oldRating);
    }
    addCoffeeRating(rating);
    return oldRating;
  }

  // public boolean hasCoffeeRating(String name) {
  //   return getCoffeeRating(name) != null;
  // }

  // public boolean isValidCoffeeRatingName(String name) {
  //   return name != null && !name.isEmpty() && !hasCoffeeRating(name);
  // }
}
