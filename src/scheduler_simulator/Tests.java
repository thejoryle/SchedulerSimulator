package scheduler_simulator;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Tests {
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
}
