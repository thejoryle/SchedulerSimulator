package scheduler_simulator;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        InputParser parser = new InputParser(args);
        Scheduler scheduler = parser.craftScheduler();
        scheduler.runScheduler();
        scheduler.printResults();
    }
}
