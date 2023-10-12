# Group gr2343 repository

# About

This code project contains coffee rating, a personal ratingsystem for coffee. It allows the user to log and review coffee locations.

![Planned](../docs/images/plan.png)

## Installation

0. If you don't have an acces token on eclipse che, you need to add one first.
1. Open project by clicking the link: (https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2343/gr2343/-/tree/master?new)
2. Change directory by running 'cd gr2343'
3. Install modules by running 'mvn install'
4. Run tests by running 'mvn test'
5. Run spotbugs and checkstyle by running 'mvn verify'
6. Run project by running 'mvn javafx:run -f ./fxui/pom.xml'

## Structure and Maven build

The project is made with JavaFX, and data is stored and read from JSON-objects.

Example of how data is stored in ratings.json:
{
"items" : [ {
"description" : "Kaffe fra Sit",
"rating" : 3
}, {
"description" : "Kaffe fra Starbucks",
"rating" : 2
} ]
}

The maven build requires Maven version 3.8.1, Java version 17 and JavaFX version 20.

## User stories

- As a user, I want to review the coffee I'm drinking.
