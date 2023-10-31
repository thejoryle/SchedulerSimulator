package scheduler_simulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class InputParser {
    private final Scanner scanner;
    private final List<Job> sortedJobList;

    InputParser(File input) throws FileNotFoundException {
        this.scanner = new Scanner(input);
        this.sortedJobList = new ArrayList<>();
    }

    public List<Job> parse(){
        int burst;
        int arrival;
        while(this.scanner.hasNext()){
            burst = this.scanner.nextInt();
            arrival = this.scanner.nextInt();
            this.sortedJobList.add(new Job(burst, arrival));
        }
        this.sortedJobList.sort(new Job.sortByArrival());
        for(int i = 0; i < this.sortedJobList.size(); i++){
            this.sortedJobList.get(i).setJobNumber(i);
        }

        return this.sortedJobList;
    }

}
