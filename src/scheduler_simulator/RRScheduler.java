package scheduler_simulator;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RRScheduler implements Scheduler{
    private final int quantum;
    private final List<Job> jobs;
    private final Queue<Job> jobQueue;
    RRScheduler(List<Job> jobs, int quantum){
        this.jobs = jobs;
        this.quantum = quantum;
        this.jobQueue = new LinkedList<>();
    }
    public void runScheduler() {
        int totalJobs = jobs.size();
        int jobsCompleted = 0;
        int nextJobIndex = 0;
        int cyclesDone = 0;
        int iterations = 0;
        Job current;

        while (jobsCompleted < totalJobs) {
            // add jobs to queue when at their arrival time
            while (nextJobIndex < totalJobs && jobs.get(nextJobIndex).getArrivalTime() <= cyclesDone) {
                jobQueue.add(jobs.get(nextJobIndex));
                nextJobIndex++;
            }
            if (!jobQueue.isEmpty()) {
                current = jobQueue.peek();
                current.decrementRemainingTime();
                iterations++;
                if (current.getRemainingTime() <= 0) {
                    current.setTimeCompleted(cyclesDone);
                    current.calculateResults();
                    jobQueue.remove();
                    jobsCompleted++;
                    iterations = 0;
                } else if (iterations >= quantum) {
                    jobQueue.add(jobQueue.remove());
                    iterations = 0;
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
