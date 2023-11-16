# Group gr2343 repository

# About

This code project contains coffee rating, a personal ratingsystem for coffee. It allows the user to log and review coffee locations.

![Planned](../docs/images/plan.png)

## Installation

0. If you don't have an acces token on eclipse che, you need to add one first.
1. Open project by clicking the link: (https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2343/gr2343/-/tree/master?new)
2. In Eclipse Che go to Endpoints->Public and copy the link for 6080-tcp-desktop-ui and open the link in a new tab.
3. Change directory by running 'cd gr2343'
4. Install modules by running 'mvn clean install -DskipTests' (no need to run tests twice)
5. Run tests by running 'mvn test'
6. Run spotbugs and checkstyle by running 'mvn verify'
7. Run project by running 'mvn javafx:run -f ./fxui/pom.xml'
8. Use the shippable product by running ‘mvn clean install -DskipTests’, then ‘mvn javafx:jlink -f ./fxui/pom.xml’ and then ‘mvn jpackage:jpackage -f ./fxui/pom.xml’.

## Structure and Maven build

The project is made with JavaFX, and data is stored and read from JSON-objects.

The maven build requires Maven version 3.8.1, Java version 17, JavaFX version 20 and Jackson version 2.13.4.

## User stories

- As a user, I want to review the coffee I'm drinking.
- As a user, I want to edit my ratings; to delete or update them.
