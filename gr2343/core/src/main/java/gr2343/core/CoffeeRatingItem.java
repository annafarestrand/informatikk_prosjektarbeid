package gr2343.core;

public class CoffeeRatingItem {
    private String description;  // hvor kaffen ble drukket
    private int rating;          // paa en skala fra 1 til 5

    @Override
    public String toString() {   // for aa printe coffeeRatingItem'et i listen i UI'en
        return description +
                ", " + rating +
                "/5";
    }

    public String getDescription() { // henter ut beskrivelsen til et item
        return description;
    }

    public void setDescription(String description) { // setter beskrivelsen til et item
        this.description = description;
    }

    public int getRating() { // henter ut ratingen til et item
        return rating;
    }

    public void setRating(int rating) { // setter ratingen til et item
        this.rating = rating;
    }
}
