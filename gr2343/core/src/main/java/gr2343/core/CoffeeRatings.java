package gr2343.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CoffeeRatings implements Iterable<CoffeeRatingItem> {

    private String name; 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private List<CoffeeRatingItem> items = new ArrayList<CoffeeRatingItem>();

    // videre brukes "kaffeopplevelse" om et CoffeeRatingItem med en rating og description

    // legger til en "kaffeopplevelse" i listen
    public void addCoffeeRatingItem(CoffeeRatingItem item) {
        items.add(item);
    }

    // fjerner en "kaffeopplevelse" fra listen
    public void removeCoffeeRatingItem(CoffeeRatingItem item) {
        items.remove(item);
    }

    @Override
    public Iterator<CoffeeRatingItem> iterator() {
        return items.iterator();
    }

    // returnerer en liste med "kaffeopplevelser"
    public List<CoffeeRatingItem> getItems() {                         
        List<CoffeeRatingItem> result = new ArrayList<>(items.size());
        for (CoffeeRatingItem item : items) {
            result.add(item);
        }
        return result;
    }
}
