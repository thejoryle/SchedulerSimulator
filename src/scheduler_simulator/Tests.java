package scheduler_simulator;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Tests {
    private final String TEST_A_FILE = "tests/test_a.txt";
    private final String TEST_B_FILE = "tests/test_b.txt";
    private final String TEST_C_FILE = "tests/test_c.txt";

    // Parser Tests
    //
    @Test
    public void testListParseAndSort() throws FileNotFoundException {
        String[] testInput = {"tests/test_c.txt"};
        InputParser parser = new InputParser(testInput);
        for(Job j : parser.getSortedJobList()){
            System.out.println(j);
        }
    }
    @Test
    public void testParserOptionReading1arg() throws FileNotFoundException {
        String[] input = {"tests/test_a.txt"};
        InputParser parser = new InputParser(input);
        assertEquals(AlgoType.FIFO, parser.getAlgoType());
    }
    @Test
    public void testParserOptionReadingQOption() throws FileNotFoundException {
        String[] input = {"tests/test_a.txt", "-q", "5"};
        InputParser parser = new InputParser(input);
        assertEquals(5, parser.getQuantum());
    }
    @Test
    public void testParserOptionReadingPOption() throws FileNotFoundException {
        String[] input = {"tests/test_a.txt", "-p", "SRTN"};
        InputParser parser = new InputParser(input);
        assertEquals(AlgoType.SRTN, parser.getAlgoType());
    }
    @Test
    public void testParserOptionReadingPAndQ() throws FileNotFoundException {
        String[] input = {"tests/test_a.txt", "-p", "RR", "-q", "10"};
        InputParser parser = new InputParser(input);
        assertEquals(AlgoType.RR, parser.getAlgoType());
        assertEquals(10, parser.getQuantum());
    }
    @Test
    public void testCraftSchedulerNoOption() throws FileNotFoundException {
        String[] input = {"tests/test_a.txt"};
        InputParser parser = new InputParser(input);
        boolean flag = false;
        if(parser.craftScheduler() instanceof FIFOScheduler){
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void testCraftSchedulerWithOption() throws FileNotFoundException {
        String[] input = {"tests/test_a.txt", "-p", "SRTN"};
        InputParser parser = new InputParser(input);
        boolean flag = false;
        if(parser.craftScheduler() instanceof SRTNScheduler){
            flag = true;
        }
        assertTrue(flag);
    }
    //
    // End Parser tests

    //FIFO tests
    //
    @Test
    public void testFIFOa() throws FileNotFoundException {
        String[] input = {TEST_A_FILE};
        InputParser parser = new InputParser(input);
        Scheduler sched = parser.craftScheduler();
        sched.runScheduler();
        sched.printResults();
    }

    @Test
    public void testFIFOb() throws FileNotFoundException {
        String[] input = {TEST_B_FILE};
        InputParser parser = new InputParser(input);
        Scheduler sched = parser.craftScheduler();
        sched.runScheduler();
        sched.printResults();
    }

    @Test
    public void testFIFOc() throws FileNotFoundException {
        String[] input = {TEST_C_FILE};
        InputParser parser = new InputParser(input);
        Scheduler sched = parser.craftScheduler();
        sched.runScheduler();
        sched.printResults();
    }
    //
    // End FIFO tests

    // SRTN tests
    //
    @Test
    public void testSRTNa() throws FileNotFoundException {
        String[] input = {TEST_A_FILE, "-p", "SRTN"};
        InputParser parser = new InputParser(input);
        Scheduler sched = parser.craftScheduler();
        sched.runScheduler();
        sched.printResults();
    }

    @Test
    public void testSRTNb() throws FileNotFoundException {
        String[] input = {TEST_B_FILE, "-p", "SRTN"};
        InputParser parser = new InputParser(input);
        Scheduler sched = parser.craftScheduler();
        sched.runScheduler();
        sched.printResults();
    }

    @Test
    public void testSRTNc() throws FileNotFoundException {
        String[] input = {TEST_C_FILE, "-p", "SRTN"};
        InputParser parser = new InputParser(input);
        Scheduler sched = parser.craftScheduler();
        sched.runScheduler();
        sched.printResults();
    }
    //
    // End SRTN tests

    // RR Tests
    //
    @Test
    public void testRRaQ2() throws FileNotFoundException {
        String[] input = {TEST_A_FILE, "-p", "RR", "-q", "2"};
        InputParser parser = new InputParser(input);
        Scheduler sched = parser.craftScheduler();
        sched.runScheduler();
        sched.printResults();
    }
    @Test
    public void testRRbQ2() throws FileNotFoundException {
        String[] input = {TEST_B_FILE, "-p", "RR", "-q", "2"};
        InputParser parser = new InputParser(input);
        Scheduler sched = parser.craftScheduler();
        sched.runScheduler();
        sched.printResults();
    }
    @Test
    public void testRRcQDefault() throws FileNotFoundException {
        String[] input = {TEST_C_FILE, "-p", "RR"};
        InputParser parser = new InputParser(input);
        Scheduler sched = parser.craftScheduler();
        sched.runScheduler();
        sched.printResults();
    }

    @Test
    public void testRRProfEx() throws FileNotFoundException {
        String[] input = {"tests/rr.txt", "-p", "RR", "-q", "4"};
        InputParser parser = new InputParser(input);
        Scheduler sched = parser.craftScheduler();
        sched.runScheduler();
        sched.printResults();
    }
}
