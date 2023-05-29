# d7c3a3c13a8cbee36de05

### This weather api application is built with Java and Spring Boot. It uses a MongoDB database. The application has no UI and is only a REST API example.
## Required tools
* Java 11


In order to launch the application open the command line in the application folder and run the gradle bootRun task.  This will start the application.

### Ensure PORT 8080 is not in use, as an embedded version of Apache Tomcat server will be deployed there.

##
##

## Functionality
### http://localhost:8080/api
## +

### "/weatherSensors"
Displays all the currently available sensors.

### "/sensor?id=<span style="color:red">v"
Display all the metrics attatched the sensor chosen by ID (long).

### "/sensorInTimePeriod?id=<span style="color:red">v</span>&days=<span style="color:red">v</span>"
Display all the metrics for the sensor chosen by ID (long) in the last chosen amount of days (long).

### "/addSensor?id=<span style="color:red">v</span>&country=<span style="color:red">v</span>&city=<span style="color:red">v</span>"
Add a new sensor by choosing the ID (long) and city (string) and country (string).

### "/addMetrics?sensorId=<span style="color:red">v</span>&id=<span style="color:red">v</span>&temperature=<span style="color:red">v</span>&humidity=<span style="color:red">v</span>"
Add a new metric by choosing the sensorId (long), id (long), temperature(double) and humidity(double)

### "/removeSensor?id=<span style="color:red">v</span>"
Remove a sensor using its id (long)

### "/average?id=<span style="color:red">v</span>
Retrieve the average temperature and humidity of a chosen sensor.

