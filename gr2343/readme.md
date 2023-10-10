# Group gr2343 repository
# About
This code project contains coffee rating, a personal ratingsystem for coffee. It allows the user to log and review coffee locations.

![Planned](../docs/images/plan.png)

## Structure and Maven build
The project is made with JavaFX, and data is stored and read from JSON-objects.

The maven build requires Maven version 3.8.1, Java version 17 and JavaFX version 20.

## SKRIV OM ECLIPSE CHE HER?

## User stories
- As a user, I want to review the coffee I'm drinking.
## Installation
1. Open project by clicking the link: (https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2343/gr2343/-/tree/master?new)
2. Install modules by running 'mvn install'
3. Run project by running 'mvn javafx:run -f ./fxui/pom.xml' 
4. Run tests by running 'mvn test'
5. Run spotbugs and checkstyle by running 'mvn verify'