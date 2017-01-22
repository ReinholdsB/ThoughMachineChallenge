package uk.co.reinholds.challenges;


import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class TaskRunnerTest {

    @Test
    public void validInputTextFile_shouldOutputCorrectOutputText() throws Exception {
        TaskRunner taskRunner = new TaskRunner();
        taskRunner.runGame("input.txt", "target/testOutput.txt");

        assertEquals("(1, 3, N)\n(9, 2, E) SUNK", taskRunner.getTask().getCurrentStateOfPlay());
    }

    @Test
    public void instructionsAreReadCorrectlyFromFile() throws Exception {
        TaskRunner taskRunner = new TaskRunner();
        List<String> instructions = taskRunner.readInstructionFromFile("./input.txt");

        assertEquals(instructions.get(0), "10");
        assertEquals(instructions.get(1), "(0, 0, N) (9, 2, E)");
        assertEquals(instructions.get(2), "(0, 0) MRMLMM");
        assertEquals(instructions.get(3), "(9, 2)");
    }

    @Test
    public void resultsAreWrittenToFileCorrectly() throws Exception {
        TaskRunner taskRunner = new TaskRunner();
        taskRunner.runGame("input.txt", "target/testOutput.txt");

        List<String> results = new LinkedList<>();
        try (BufferedReader input = new BufferedReader(new FileReader("target/testOutput.txt"))) {
            String result;
            while ((result = input.readLine()) != null) {
                results.add(result);
            }
        }
        assertEquals("(1, 3, N)", results.get(0));
        assertEquals("(9, 2, E) SUNK", results.get(1));
    }
}
