package gr2343.ui;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr2343.core.CoffeeRatingModel;
import gr2343.core.CoffeeRatings;
import gr2343.core.CoffeeRatingItem;
import gr2343.json.CoffeeRatingsPersistence;


/**
 * Class that centralizes access to a TodoModel. Makes it easier to support transparent use of a
 * REST API.
 */
public class RemoteCoffeeRatingModelAccess  {

  private final URI endpointBaseUri;

  private static final String APPLICATION_JSON = "application/json";

  private static final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded";

  private static final String ACCEPT_HEADER = "Accept";

  private static final String CONTENT_TYPE_HEADER = "Content-Type";

  private ObjectMapper objectMapper;

  private CoffeeRatingModel coffeeRatingModel;

  public RemoteCoffeeRatingModelAccess(URI endpointBaseUri) {
    this.endpointBaseUri = endpointBaseUri;
    objectMapper = CoffeeRatingsPersistence.createObjectMapper();
  }

  private CoffeeRatingModel getCoffeeRatingModel() {
    if (coffeeRatingModel == null) {
      HttpRequest request = HttpRequest.newBuilder(endpointBaseUri)
          .header(ACCEPT_HEADER, APPLICATION_JSON)
          .GET()
          .build();
      try {
        final HttpResponse<String> response =
            HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
        this.coffeeRatingModel = objectMapper.readValue(response.body(), CoffeeRatingModel.class);
      } catch (IOException | InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    return coffeeRatingModel;
  }

  private boolean isDefaultSettings(CoffeeRatingSettings coffeeRatingSettings) {
    return coffeeRatingSettings == null;
  }

  
  @Override
  public CoffeeRatingSettings getCoffeeRatingSettings() {
    CoffeeRatingModel coffeeRatingModel = getCoffeeRatingModel();
    CoffeeRatingSettings settings = coffeeRatingModel.getSettings();
    if (isDefaultSettings(settings)) {
      HttpRequest request = HttpRequest.newBuilder(endpointBaseUri.resolve("settings"))
          .header(ACCEPT_HEADER, APPLICATION_JSON)
          .GET()
          .build();
      try {
        final HttpResponse<String> response =
            HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
        final String responseString = response.body();
        settings = objectMapper.readValue(responseString, CoffeeRatingSettings.class);
        coffeeRatingModel.setSettings(settings);
      } catch (IOException | InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    return settings;
  }

  /**
   * Checks that name is valid for a (new) CoffeeRating.
   *
   * @param name the (new) name
   * @return true if the name is value, false otherwise
   */
  @Override
  public boolean isValidCoffeeRatingName(String name) {
    return getCoffeeRatingModel().isValidCoffeeRatingName(name);
  }

  /**
   * Checks if there (already) exists a CoffeeRating with the provided name.
   *
   * @param name the (new) name
   * @return true if there exists a CoffeeRating with the provided name, false otherwise
   */
  @Override
  public boolean hasCoffeeRating(String name) {
    return getCoffeeRatingModel().hasCoffeeRating(name);
  }

  /**
   * Gets the names of the CoffeeRatings.
   *
   * @return the names of the CoffeeRatings.
   */
  @Override
  public Collection<String> getCoffeeRatingNames() {
    Collection<String> allNames = new ArrayList<>();
    getCoffeeRatingModel().forEach(coffeeRating -> allNames.add(coffeeRating.getName()));
    return allNames;
  }

  private String uriParam(String s) {
    return URLEncoder.encode(s, StandardCharsets.UTF_8);
  }

  private URI coffeeRatingUri(String name) {
    return endpointBaseUri.resolve("list/").resolve(uriParam(name));
  }

  /**
   * Gets the CoffeeRating with the given name.
   *
   * @param name the CoffeeRating's name
   * @return the CoffeeRating with the given name
   */
  @Override
  public CoffeeRatings getCoffeeRating(String name) {
    System.out.println("getCoffeeRating(String name) :" + coffeeRatingUri(name));
    CoffeeRatings oldCoffeeRating = this.coffeeRatingModel.getCoffeeRating(name);
    // if existing list has no coffee rating items, try to (re)load
    if (oldCoffeeRating == null || (! (oldCoffeeRating instanceof CoffeeRating))) {
      HttpRequest request =
          HttpRequest.newBuilder(coffeeRatingUri(name))
              .header(ACCEPT_HEADER, APPLICATION_JSON).GET().build();
      try {
        final HttpResponse<String> response =
            HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
        String responseString = response.body();
        System.out.println("getCoffeeRating(" + name + ") response: " + responseString);
        CoffeeRatings coffeeRating = objectMapper.readValue(responseString, CoffeeRatings.class);
        if (! (coffeeRating instanceof CoffeeRatings)) {
          CoffeeRatings newCoffeeRating = new CoffeeRatings();
          newCoffeeRating.setName(coffeeRating.getName());
          coffeeRating = newCoffeeRating;
        }
        this.coffeeRatingModel.putCoffeeRating(coffeeRating);
      } catch (IOException | InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    return oldCoffeeRating;
  }

  private void putCoffeeRating(CoffeeRatings coffeeRating) {
    try {
      String json = objectMapper.writeValueAsString(coffeeRating);
      HttpRequest request = HttpRequest.newBuilder(coffeeRatingUri(coffeeRating.getName()))
          .header(ACCEPT_HEADER, APPLICATION_JSON)
          .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
          .PUT(BodyPublishers.ofString(json))
          .build();
      final HttpResponse<String> response =
          HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      String responseString = response.body();
      Boolean added = objectMapper.readValue(responseString, Boolean.class);
      if (added != null) {
        coffeeRatingModel.putCoffeeRating(coffeeRating);
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Adds a CoffeeRating to the underlying CoffeeRatingModel.
   *
   * @param coffeeRating the coffeeRating
   */
  @Override
  public void addCoffeeRating(CoffeeRatings coffeeRating) {
    putCoffeeRating(coffeeRating);
  }

  /**
   * Removes the coffee rating with the given name from the underlying coffeRatingModel.
   *
   * @param name the name of the CoffeeRating to remove
   */
  @Override
  public void removeCoffeeRating(String name) {
    try {
      HttpRequest request = HttpRequest.newBuilder(coffeeRatingUri(name))
          .header(ACCEPT_HEADER, APPLICATION_JSON)
          .DELETE()
          .build();
      final HttpResponse<String> response =
          HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      String responseString = response.body();
      Boolean removed = objectMapper.readValue(responseString, Boolean.class);
      if (removed != null) {
        coffeeRatingModel.removeCoffeeRating(coffeeRatingModel.getCoffeeRating(name));
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Renames a coffee rating to a new name.
   *
   * @param oldName the name of the coffee rating to change
   * @param newName the new name
   */
  @Override
  public void renameCoffeeRating(String oldName, String newName) {
    try {
      HttpRequest request = HttpRequest.newBuilder(
            coffeeRatingUri(oldName).resolve(uriParam(oldName) + "/rename"))
          .header(ACCEPT_HEADER, APPLICATION_JSON)
          .header(CONTENT_TYPE_HEADER, APPLICATION_FORM_URLENCODED)
          .POST(BodyPublishers.ofString("newName=" + uriParam(newName)))
          .build();
      final HttpResponse<String> response =
          HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      String responseString = response.body();
      Boolean renamed = objectMapper.readValue(responseString, Boolean.class);
      if (renamed != null) {
        coffeeRatingModel.getCoffeeRating(oldName).setName(newName);
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Notifies that the coffee rating has changed, e.g. coffee rating items
   * have been mutated, added or removed.
   *
   * @param coffeeRating the coffee rating that has changed
   */
  @Override
  public void notifyCoffeeRatingChanged(CoffeeRatings coffeeRating) {
    putCoffeeRating(coffeeRating);
  }
}