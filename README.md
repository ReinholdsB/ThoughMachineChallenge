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

## Input file requirements 
First line has to be the size of the board that will be used in the game. 
  - Example Command: `10` - 10 - size of the board.
  - Stackable: No, one command per line.
  - Limitations: Can only be set once as the first command in the file.

Next lines in the file should be commands. Available commands:
1. Place ship(s) - place new ships on the board.
  - Example Command: `(5, 2, N)` - 5 - X x coordinate, 2 - Y coordinate, N - orientation of ship.
  - Stackable: Yes, you can have multiple commands on one line, separated by spaces.
  - Limitations: Ship cannot be placed out-of-bounds or on a location that's already taken.
2. Move ship - give instructions to a ship to move on the board.
  - Example Command: `(5, 2)` MLLRM - 5 - X x coordinate, 2 - Y coordinate, MLLRM - movement directions for selected ship.
    + M - Move Forward, R - turn right, L - turn left.
  - Stackable: No, one command per line.
  - Limitations: Ship cannot move out-of-bounds or to a location that's already taken.
3. Shoot at ships - If you hit a ship, it sinks.
  - Example Command: `(5, 2)` - xCoordinate=5, yCoordinate=2.
  - Stackable: No, one command per line.
  - Limitations: Cannot shoot out-of-bounds.

The commands can be repeated as many times as needed as long as they don't hit the limitations
  
## End of run
At the end of the run it will output the end board state to the output file (i.e. default "output.txt", can be overriden).
