package uk.co.reinholds.challenges;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * Hello world!
 */
public class TaskRunner {
    private Task task;

    private static final String DEFAULT_INPUT_FILENAME = "./input.txt";
    private static final String DEFAULT_OUTPUT_FILENAME = "./output.txt";


    public static void main(String[] args) {
        String inputFileLocation = DEFAULT_INPUT_FILENAME;
        if (args.length > 0 && args[0] != null) {
            inputFileLocation = args[0];
        }
        String outputFileLocation = DEFAULT_OUTPUT_FILENAME;
        if (args.length > 1 && args[1] != null) {
            outputFileLocation = args[1];
        }

        // Instantiating a class instance for easier testing
        TaskRunner taskRunner = new TaskRunner();
        taskRunner.runGame(inputFileLocation, outputFileLocation);
    }

    void runGame(String inputFileLocation, String outputFileLocation) {
        List<String> instructions = readInstructionFromFile(inputFileLocation);

        task = new Task(instructions);
        task.start();
        writeResultsToFile(outputFileLocation, task.getCurrentStateOfPlay());
    }


    List<String> readInstructionFromFile(String fileLocation) {

        List<String> instructions = new LinkedList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(fileLocation))) {
            String instruction;
            System.out.println("Reading instructions from input file=" + fileLocation);

            while ((instruction = input.readLine()) != null) {
                instructions.add(instruction);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to read input file!", e);
        }
        System.out.println("Finished reading instructions=" + instructions);
        return instructions;
    }


    public Task getTask() {
        return task;
    }

    public void writeResultsToFile(String fileLocation, String results) {
        try {
            Files.write(Paths.get(fileLocation), results.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
