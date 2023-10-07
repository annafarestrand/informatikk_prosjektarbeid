package gr2343.core;

public class CoffeeRatingListItem extends CoffeeRatingItem {

    private final CoffeeRatings ratings;

    public CoffeeRatingListItem(CoffeeRatings ratings) {
        this.ratings = ratings;
    }

    @Override
    public void setDescription(String description) {
        String oldDescription = getDescription();
        super.setDescription(description);
        if (oldDescription != description || oldDescription != null && !(oldDescription.equals(description))) {
            ratings.fireCoffeeRatingChanged();
        }
    }

    @Override
    public void setRating(int rating) {
        int oldRating = getRating();
        super.setRating(rating);
        if (oldRating != rating || oldRating != 0 && !(oldRating != rating)) {
            ratings.fireCoffeeRatingChanged();
        }
    }
}
