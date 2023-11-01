package scheduler_simulator;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class SRTNScheduler implements Scheduler{
    private final List<Job> jobs;
    private final Queue<Job> jobQueue;
    SRTNScheduler(List<Job> jobs){
        this.jobs = jobs;
        this.jobQueue = new PriorityQueue<>(new Job.sortByRemaining());
    }
    public void runScheduler(){
        int totalJobs = jobs.size();
        int jobsCompleted = 0;
        int nextJobIndex = 0;

        jobQueue.add(jobs.get(nextJobIndex));
        nextJobIndex++;
        Job current;
        int cyclesDone = 0;
        while(jobsCompleted < totalJobs){
            if(jobQueue.isEmpty()){
                // do nothing
            } else {
                // add job to queue if at or before current quantum
                while(nextJobIndex < totalJobs && jobs.get(nextJobIndex).getArrivalTime() <= cyclesDone){
                    jobQueue.add(jobs.get(nextJobIndex));
                    nextJobIndex++;
                }
                current = jobQueue.peek();
                if (current.getArrivalTime() <= cyclesDone) {
                    current.decrementRemainingTime();
                    if (current.getRemainingTime() == 0) {
                        current.setTimeCompleted(cyclesDone);
                        current.calculateResults();
                        jobQueue.remove();
                        jobsCompleted++;
                    }
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
