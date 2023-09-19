package gr2343;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CoffeRating implements Iterable<CoffeRatingItem> {
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
}
