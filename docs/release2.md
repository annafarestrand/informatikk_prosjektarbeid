# Release 2

## Contents

The code project now contains the same functionality, with the primary changes since last release revolving around infrastructure-setup.

The setup has been modularized, consisting of a code module and an FXUI module. The architecture follows a full three-layer design, with the JSON layer residing in the core module. When storing data as JSON-object, we use a spesific "save"-button, aka. a document-metafor. This is because this combined with the ListView assures the user that the data is saved and is more intuitive.

We chose desktop over an app because we wanted users to explicitly save an object. The saved object's addition to the list in the app window makes its saved status evident.

In addition to JaCoCo from the previous release, we have incorporated Spotbugs and Checkstyle to assess and ensure code quality. Tests have been written for all layers; FXUI, core, and JSON.

## JSON-objects
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

## Development method

The development method is the same as in release1, with the notable difference being our approach to collaboration, mostly working in pairs. Unlike the previous release, where tasks were assigned individually, we now exclusively work in pairs.

This is documentet with the footer "Co-Authored-By: name" in each commit. We've been using a dynamic pair programming model, periodically changing partners to gain exposure to different perspectives and leverage each other's strengths. We have had regular work sessions together as well as bi-weekly meetings with our "læringsassistent" (supervisor).

We started out with adding some new functionality to the app, but later discovered it was not a requirement in this release, and reverted the updates (to save ourselves from the work of writing tests and debugging).

As for comments in the code, we have only addded a few comments. This is because we focused on making the code simple and understandable.

## Design

The design is unchanged in this release.