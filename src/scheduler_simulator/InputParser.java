package scheduler_simulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class InputParser {
    private final Scanner fileReader;
    private final List<Job> sortedJobList;
    private int quantum;
    private AlgoType algoType;
    private final String USAGE_MESSAGE = "usage: schedSim <job-file.txt> -p <ALGORITHM> -q <QUANTUM>";

    InputParser(String[] input) throws FileNotFoundException {
        this.sortedJobList = new ArrayList<>();

        int optionCount = input.length;
        if (optionCount == 0){
            throw new RuntimeException(USAGE_MESSAGE);
        } else {
            // convert file to a list of jobs sorted in ascending arrival time
            File jobsFile = new File(input[0]);
            this.fileReader = new Scanner(jobsFile);
            this.fileToSortedJobList();

            // parse options to determine scheduler quantum and algorithm
            this.quantum = 1;
            this.algoType = AlgoType.FIFO;
            boolean quantumParsed = false;
            boolean algoParsed = false;

            if(optionCount % 2 == 0 || optionCount > 5){
                throw new RuntimeException(USAGE_MESSAGE);
            } else if(optionCount > 1){
                switch(input[1]){
                    case("-p") -> {
                        algoType = parseAlgoOption(input[2]);
                        algoParsed = true;
                    }
                    case("-q") -> {
                        quantum = Integer.parseInt(input[2]);
                        quantumParsed = true;
                    }
                    default -> throw new RuntimeException(USAGE_MESSAGE);
                }
                if(optionCount == 5){
                    if(input[3].equals("-q") && algoParsed){
                        quantum = Integer.parseInt(input[4]);
                    } else if(input[3].equals("-p") && quantumParsed){
                        algoType = parseAlgoOption(input[4]);
                    } else {
                        throw new RuntimeException(USAGE_MESSAGE);
                    }
                }
            }
        }
    }

    public Scheduler craftScheduler(){
        if(this.algoType == AlgoType.FIFO){
            return new RRScheduler(this.sortedJobList, this.quantum);
        } else if (this.algoType == AlgoType.SRTN){
            return new SRTNScheduler(this.sortedJobList);
        } else {
            return new FIFOScheduler(this.sortedJobList);
        }
    }

    private List<Job> fileToSortedJobList(){
        int burst;
        int arrival;
        while(this.fileReader.hasNext()){
            burst = this.fileReader.nextInt();
            arrival = this.fileReader.nextInt();
            this.sortedJobList.add(new Job(burst, arrival));
        }

        this.sortedJobList.sort(new Job.sortByArrival());
        for(int i = 0; i < this.sortedJobList.size(); i++){
            this.sortedJobList.get(i).setJobNumber(i);
        }

        return this.sortedJobList;
    }

    private AlgoType parseAlgoOption(String option){
        return switch(option){
            case("SRTN") -> AlgoType.SRTN;
            case("FIFO") -> AlgoType.FIFO;
            case("RR") -> AlgoType.RR;
            default -> throw new RuntimeException("Invalid algorithm type. Must be FIFO, SRTN, or RR.");
        };
    }

}
