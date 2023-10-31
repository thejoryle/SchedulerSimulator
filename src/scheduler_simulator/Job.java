package scheduler_simulator;

import java.util.Comparator;

public class Job {
    private final int arrivalTime;
    private final int burstTime;
    private int jobNumber;
    private int remainingTime;
    private int timeCompleted;
    private int turnaroundTime;
    private int waitingTime;

    Job(int burstTime, int arrivalTime){
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.jobNumber = Integer.MIN_VALUE;
        this.remainingTime = Integer.MIN_VALUE;
        this.timeCompleted = Integer.MIN_VALUE;
        this.turnaroundTime = Integer.MIN_VALUE;
        this.waitingTime = Integer.MIN_VALUE;
    }

    public void setJobNumber(int newJobNumber){
        this.jobNumber = newJobNumber;
    }

    public void decreaseRemainingTime(int decreaseBy){
        this.remainingTime -= decreaseBy;
    }

    public void setTimeCompleted(int completedAt){
        this.timeCompleted = completedAt;
    }

    public void calculateResults(){
        this.turnaroundTime = this.timeCompleted - this.arrivalTime;
        this.waitingTime = this.turnaroundTime - this.burstTime;
    }

    static class sortByArrival implements Comparator<Job>{
        public int compare(Job a, Job b){
            return a.arrivalTime - b.arrivalTime;
        }
    }
}
