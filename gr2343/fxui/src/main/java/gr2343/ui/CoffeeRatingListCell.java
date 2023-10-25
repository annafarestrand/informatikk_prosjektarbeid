package gr2343.ui;

import gr2343.core.CoffeeRatingItem;
import javafx.scene.control.ListCell;

public class CoffeeRatingListCell extends ListCell<CoffeeRatingItem> {

  @Override
  protected void updateItem(CoffeeRatingItem item, boolean empty) {
    super.updateItem(item, empty);
    if (empty || item == null) {
      setText(null);
      setGraphic(null);
    } else {
      setText(item.getDescription() + ", " + item.getRating() + "/5"); // bestemmer hvordan data vises i appen
      setGraphic(null);
    }
  }

}

