package gr2343.json;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import gr2343.core.CoffeeRatingModel;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class CoffeeRatingsPersistence {

  private ObjectMapper mapper;
  private CoffeeRatingModel model;

  public CoffeeRatingsPersistence() {
    mapper = new ObjectMapper();
    mapper.registerModule(new CoffeeRatingModule());
  }

  public CoffeeRatingModel readCoffeeRatings(String ratingsWithItems) throws IOException {
    return mapper.readValue(ratingsWithItems, CoffeeRatingModel.class);
  }

  public void writeCoffeeRatings(CoffeeRatingModel coffeeRatings, ObjectWriter writer) throws IOException {
    mapper.writerWithDefaultPrettyPrinter().writeValue(new File("ratings.json"), model);
  }

  public static SimpleModule createJacksonModule() {
    return new CoffeeRatingModule();
  }

  public static ObjectMapper createObjectMapper() {
    return new ObjectMapper().registerModule(createJacksonModule());
  }


  public CoffeeRatingModel readCoffeeRatingModel(Reader reader) throws IOException {
    return mapper.readValue(reader, CoffeeRatingModel.class);
  }

  public void writeCoffeeRatingModel(CoffeeRatingModel coffeeRatingModel, Writer writer) throws IOException {
    mapper.writerWithDefaultPrettyPrinter().writeValue(writer, coffeeRatingModel);
  }


  private Path saveFilePath = null;

  public void setSaveFile(String saveFile) {
    this.saveFilePath = Paths.get(System.getProperty("user.home"), saveFile);
  }

  public Path getSaveFilePath() {
    return this.saveFilePath;
  }

  /**
   * Loads a CoffeeRatingModel from the saved file (saveFilePath) in the user.home folder.
   *
   * @return the loaded TodoModel
   */
  public CoffeeRatingModel loadCoffeeRatingModel() throws IOException, IllegalStateException {
    if (saveFilePath == null) {
      throw new IllegalStateException("Save file path is not set, yet");
    }
    try (Reader reader = new FileReader(saveFilePath.toFile(), StandardCharsets.UTF_8)) {
      return readCoffeeRatingModel(reader);
    }
  }

  /**
   * Saves a CoffeeRatingModel to the saveFilePath in the user.home folder.
   *
   * @param coffeeRatingModel the CoffeeRatingModel to save
   */
  public void saveCoffeeRatingModel(CoffeeRatingModel coffeeRatingModel) throws IOException, IllegalStateException {
    if (saveFilePath == null) {
      throw new IllegalStateException("Save file path is not set, yet");
    }
    try (Writer writer = new FileWriter(saveFilePath.toFile(), StandardCharsets.UTF_8)) {
      writeCoffeeRatingModel(coffeeRatingModel, writer);
    }
  }

}
