# d7c3a3c13a8cbee36de05

### This weather api application is built with Java and Spring Boot. It uses a MongoDB database. The application has no UI and is only a REST API.

In order to launch the application open the command line in the application folder and run the gradle bootRun task.  This will start the application.

### Ensure PORT 8080 is not in use, as an embedded version of Apache Tomcat server will be deployed there.

##
##

## Functionality
### http://localhost:8080
## +

### "/weatherSensors"
Displays all the currently available sensors.

### "/weatherSensors/<span style="color:red">ID</span>/metrics"
Display all the metrics attatched the sensor chosen by ID (long).

### "/weatherSensors/<span style="color:red">ID</span>/metrics/<span style="color:red">DAYS</span>"
Display all the metrics for the sensor chosen by ID (long) in the last chosen amount of days (long).

### "/addSensor/<span style="color:red">ID</span>/<span style="color:red">COUNTRY</span>/<span style="color:red">CITY</span>"
Add a new sensor by choosing the ID (long) and city (string) and country (string).

### "/addMetrics/<span style="color:red">sensorId</span>/<span style="color:red">metricId</span>/<span style="color:red">temperature</span>/<span style="color:red">humidity</span>"
Add a new metric by choosing the sensorId (long), id (long), temperature(double) and humidity(double)

### "/removeSensor/<span style="color:red">id</span>"
Remove a sensor using its id (long)

### "/weatherSensors/<span style="color:red">sensorId</span>/metrics/average"
Retrieve the average of a chosen sensor.

