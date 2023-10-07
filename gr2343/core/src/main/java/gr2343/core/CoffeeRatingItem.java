package gr2343.core;

public class CoffeeRatingItem {
    private String description;
    private int rating;

    @Override
    public String toString() {
        return description +
                ", " + rating +
                "/5";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
