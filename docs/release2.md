# Release 2
## Contents
The code project now contains the same functionality, with the primary changes since last release revolving around infrastructure-setup.

The setup has been modularized, consisting of a code module and an FXUI module. The architecture follows a full three-layer design, with the JSON layer residing in the core module.

In addition to Jacoco from the previous release, we have incorporated Spotbugs and Checkstyle to assess and ensure code quality. Tests have been written for all layers, including FXUI, core, and JSON.

## Development method
The development method is the same as in release1, with the notable difference being our approach to collaboration, mostly working in pairs. Unlike the previous release, where tasks were assigned individually, we now exclusively work in pairs. 


This is documentet with the footer "Co-Authored-By: name" in each commit. We've been using a dynamic pair programming model, periodically changing partners to gain exposure to different perspectives and leverage each other's strengths. We have had regular work sessions together as well as bi-weekly meetings with our "læringsassistent" (supervisor). 

We started out with adding some new functionality to the app, but later discovered it was not a requirement in this release, and reverted the updates (to save ourselves from the work of writing tests and debugging).

# Legge inn diagram fra PlantUML