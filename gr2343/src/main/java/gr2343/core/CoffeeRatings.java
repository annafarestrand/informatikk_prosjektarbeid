package gr2343.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CoffeeRatings implements Iterable<CoffeeRatingItem> {
    private List<CoffeeRatingItem> items = new ArrayList<CoffeeRatingItem>();

    public CoffeeRatingItem createCoffeeRatingItem() {
        return new CoffeeRatingListItem(this);
    }
    
    public void addCoffeeRatingItem(CoffeeRatingItem item) {
        CoffeeRatingListItem coffeeRatingListItem = null;
        if (item instanceof CoffeeRatingItem) {
            coffeeRatingListItem = (CoffeeRatingListItem) item;
        }
        else {
            CoffeeRatingListItem newItem = new CoffeeRatingListItem(this);
            newItem.setDescription(item.getDescription());
            newItem.setRating(item.getRating());
        }
        items.add(item);
        fireCoffeeRatingChanged();
    }

    public void removeCoffeeRatingItem(CoffeeRatingItem item) {
        items.remove(item);
        fireCoffeeRatingChanged();
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

    private Collection<CoffeeRatingListener> cofferatingListeners = new ArrayList<>();

    public void removeCofferatingListener(CoffeeRatingListener listener) {
        cofferatingListeners.remove(listener);
    }

    public void addCofferatingListener(CoffeeRatingListener listener) {
        cofferatingListeners.add(listener);
    }

    protected void fireCoffeeRatingChanged() {
        for (CoffeeRatingListener listener : cofferatingListeners) {
            listener.coffeeRatingChanged(this);
        }
    }

    protected void fireCoffeeRatingChanged(CoffeeRatingItem item) {
        fireCoffeeRatingChanged();
    }


}
