# Though Machine Coding Challenge

## Building the application
To build the application in the root directory run:
`mvn clean install`

## Running the application
After the app has been build, in the root directory run: 
`java -jar target\thought-machine-exercise-1.0-SNAPSHOT-jar-with-dependencies.jar`

By default it will use input.txt file as input and output.txt for output.

If you wish to override these values you can supply them as parameters, when running the app:
`java -jar target\thought-machine-exercise-1.0-SNAPSHOT-jar-with-dependencies.jar input.txt output.txt`