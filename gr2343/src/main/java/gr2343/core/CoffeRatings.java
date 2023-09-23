package gr2343.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CoffeRatings implements Iterable<CoffeRatingItem> {
    private List<CoffeRatingItem> items = new ArrayList<CoffeRatingItem>();

    public void addCoffeRatingItem(CoffeRatingItem item) {
        items.add(item);
    }

    public void removeCoffeRatingItem(CoffeRatingItem item) {
        items.remove(item);
    }

    @Override
    public Iterator<CoffeRatingItem> iterator() {
        return items.iterator();
    }

    public Collection<CoffeRatingItem> getItems() {
        Collection<CoffeRatingItem> result = new ArrayList<>(items.size());
        for (CoffeRatingItem item : items) {
            result.add(item);
        }
        return result;
    }
}
