package gr2343.springboot.restserver;

import com.fasterxml.jackson.databind.Module;
import java.util.EnumSet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import gr2343.json.CoffeeRatingsPersistence;
//import gr2343.json.CoffeeRatingsPersistence.CoffeeRatingModel;

/**
 * The Spring application.
 */
@SpringBootApplication
public class CoffeeRatingModelApplication {

  //@Bean
  //public Module objectMapperModule() {
    //return CoffeeRatingsPersistence.createJacksonModule(EnumSet.of(CoffeeRatingModelParts.LISTS));
  //}

  public static void main(String[] args) {
    SpringApplication.run(CoffeeRatingModelApplication.class, args);
  }
}
