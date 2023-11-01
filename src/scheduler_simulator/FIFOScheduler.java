package scheduler_simulator;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FIFOScheduler implements Scheduler{
    private final List<Job> jobs;
    private final Queue<Job> jobQueue;
    FIFOScheduler(List<Job> jobs){
        this.jobs = jobs;
        this.jobQueue = new LinkedList<>(jobs);
    }

    public void runScheduler(){
        Job current = jobQueue.peek();
        int cyclesDone = 0;
        while(!jobQueue.isEmpty()){
            if(current.getArrivalTime() <= cyclesDone){
                current.decrementRemainingTime();
                if(current.getRemainingTime() == 0){
                    current.setTimeCompleted(cyclesDone);
                    current.calculateResults();
                    jobQueue.remove();
                    current = jobQueue.peek();
                }
            }
            cyclesDone++;
        }
    }

    public void printResults(){
        double totTurnaround = 0;
        double totWait = 0;
        int numJobs = jobs.size();
        for(Job j : jobs){
            totTurnaround += j.getTurnaroundTime();
            totWait += j.getWaitingTime();
            System.out.println(j.getResults());
        }
        System.out.println(String.format("Average -- Turnaround %5.2f Wait %5.2f",
                totTurnaround/numJobs, totWait/numJobs));
    }
}
