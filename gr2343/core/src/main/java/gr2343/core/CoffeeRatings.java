package gr2343.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CoffeeRatings implements Iterable<CoffeeRatingItem> {
    private List<CoffeeRatingItem> items = new ArrayList<CoffeeRatingItem>();

    public void addCoffeeRatingItem(CoffeeRatingItem item) {
        items.add(item);
    }

    public void removeCoffeeRatingItem(CoffeeRatingItem item) {
        items.remove(item);
    }

    @Override
    public Iterator<CoffeeRatingItem> iterator() {
        return items.iterator();
    }

    public List<CoffeeRatingItem> getItems() {
        List<CoffeeRatingItem> result = new ArrayList<>(items.size());
        for (CoffeeRatingItem item : items) {
            result.add(item);
        }
        return result;
    }
}
