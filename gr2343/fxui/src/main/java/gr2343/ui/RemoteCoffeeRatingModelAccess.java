package gr2343.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import gr2343.core.CoffeeRatingModel;
import gr2343.core.CoffeeRatings;
import gr2343.json.CoffeeRatingsPersistence;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;


/**
 * Class that centralizes access to a TodoModel. Makes it easier to support transparent use of a
 * REST API.
 */
public class RemoteCoffeeRatingModelAccess {

  private final URI endpointBaseUri;

  private static final String APPLICATION_JSON = "application/json";

  private static final String ACCEPT_HEADER = "Accept";

  private static final String CONTENT_TYPE_HEADER = "Content-Type";

  private ObjectMapper objectMapper;

  private CoffeeRatingModel coffeeRatingModel;

  public RemoteCoffeeRatingModelAccess(URI endpointBaseUri) {
    this.endpointBaseUri = endpointBaseUri;
    objectMapper = CoffeeRatingsPersistence.createObjectMapper();
  }

  public CoffeeRatingModel getCoffeeRatingModel() {
    if (coffeeRatingModel == null) {
      HttpRequest request =
          HttpRequest.newBuilder(endpointBaseUri).header(ACCEPT_HEADER, APPLICATION_JSON).GET().build();
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

  private String uriParam(String s) {
    return URLEncoder.encode(s, StandardCharsets.UTF_8);
  }

  private URI coffeeRatingUri(String name) {
    return endpointBaseUri.resolve("coffeerating/rating/").resolve(uriParam(name));
  }

  /**
   * Gets the CoffeeRating with the given name.
   *
   * @param name the CoffeeRating's name
   * @return the CoffeeRating with the given name
   */
  public CoffeeRatings getCoffeeRating(String name) {
    CoffeeRatings oldCoffeeRating = this.coffeeRatingModel.getCoffeeRating(name);
    // if existing list has no coffee rating items, try to (re)load
    if (oldCoffeeRating == null || (!(oldCoffeeRating instanceof CoffeeRatings))) {
      HttpRequest request =
          HttpRequest.newBuilder(coffeeRatingUri(name)).header(ACCEPT_HEADER, APPLICATION_JSON).GET().build();
      try {
        final HttpResponse<String> response =
            HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
        String responseString = response.body();
        CoffeeRatings coffeeRating = objectMapper.readValue(responseString, CoffeeRatings.class);
        this.coffeeRatingModel.putCoffeeRating(coffeeRating);
      } catch (IOException | InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    return coffeeRatingModel.getCoffeeRating(name);
  }

  public void putCoffeeRating(CoffeeRatings coffeeRating) {
    try {
      String json = objectMapper.writeValueAsString(coffeeRating);
      HttpRequest request =
          HttpRequest.newBuilder(coffeeRatingUri(coffeeRating.getName())).header(ACCEPT_HEADER, APPLICATION_JSON)
              .header(CONTENT_TYPE_HEADER, APPLICATION_JSON).PUT(BodyPublishers.ofString(json)).build();
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

}
