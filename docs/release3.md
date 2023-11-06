# Release 3

In this release, we had to decide whether we wanted to improve the JavaFX app with more functionality or create a new client using different technology. Since we didn't have much experience with other relevant technologies, we chose to add new functionality and continue with the current technology. We did this by adding the capability to update and delete coffee ratings.

## Development method
The development method is the same as in release1 and release2, the only difference is that we focused on writing good comments in the code, both for our own benefit to make it easier to maintain the code and write tests etc., and to improve readability for those who haven't seen the code before.

## REST-API

## The modules
### core

### ui
- hvorfor vi valgte denne funksjonaliteten og hvorfor vi løste det slik

### JSON
- hvordan ting lagres i json
- definere/dokumentere filformat for brukerdata og evt. innstillinger
- reflektere over og velge mellom dokumentmetafor (desktop) og implisitt lagring (app)


## Tests
We have written tests for a significant portion of the code, including the functions in the UI controller, most of the core logic, and the serializers and deserializers in the JSON module. The test coverage for the core is **86% (CHANGE)**, and for the FXUI, it is **65% (CHANGE)** test coverage. You can find the test coverage in the JaCoCo reports generated after running 'mvn test.' These reports are located at gr2343/core/target/site/index.html and gr2343/fxui/target/site/index.html, respectively.

One of the reasons we don't have 100% test coverage in FXUI is that we don't have direct tests for CoffeeRatingApp. This is because we have implicitly tested that the app runs by testing its functionality. If the app didn't run, its functionality wouldn't work correctly either.

Another reason is that we have 68% test coverage on the controller (CoffeeRatingController). This is because we haven't been able to test whether the pointer clicks on an item in our list, and then updates or deletes the associated item. However, we know that this works when the app is running, as we have tested it manually. Therefore, we believe it wasn't worthwhile to spend a lot of time on this and instead focus on other parts of the code.

We have also used SpotBugs and CheckStyle. Their task is to improve code quality, identify potential errors, and ensure that best coding practices are followed. SpotBugs' main task is to find errors and potential bugs, while CheckStyle helps maintain a consistent style and code format, which can enhance the readability and maintainability of the project. These tools work well. By running 'mvn verify,' we get that we have **0 CheckStyle violations (CHANGE)**, and by running 'mvn spotbugs:spotbugs,' we only get **two warnings (CHANGE)**.

We have usually written the tests after writing the code, but sometimes we have written tests that we realize are missing, only to find out that there was something wrong in the code. So, one could say that we have been somewhat into test-driven development, or at least test-driven debugging.

## Diagrams
- pakkediagram
- klassediagram
- sekvensdiagram