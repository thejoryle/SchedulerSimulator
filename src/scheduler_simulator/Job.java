package scheduler_simulator;

import java.util.Comparator;

public class Job {
    private final int arrivalTime;
    private final int burstTime;
    private int jobNumber;
    private int remainingTime;
    private int timeCompleted;
    private double turnaroundTime;
    private double waitingTime;

    Job(int burstTime, int arrivalTime){
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.jobNumber = Integer.MIN_VALUE;
        this.remainingTime = this.burstTime;
        this.timeCompleted = Integer.MIN_VALUE;
        this.turnaroundTime = Integer.MIN_VALUE;
        this.waitingTime = Integer.MIN_VALUE;
    }

    public double getTurnaroundTime(){ return this.turnaroundTime; }
    public double getWaitingTime(){ return this.waitingTime; }
    public int getBurstTime(){ return this.burstTime; }
    public int getRemainingTime(){ return this.remainingTime; }
    public int getArrivalTime(){ return this.arrivalTime; }

    public void setJobNumber(int newJobNumber){
        this.jobNumber = newJobNumber;
    }

    public void decrementRemainingTime(){
        this.remainingTime--;
    }

    public void setTimeCompleted(int completedAt){
        this.timeCompleted = completedAt;
    }

    public void calculateResults(){
        this.turnaroundTime = this.timeCompleted - this.arrivalTime + 1;
        this.waitingTime = this.turnaroundTime - this.burstTime;
    }

    public String getResults(){
        return String.format("Job %3d -- Turnaround %5.2f Wait %5.2f",
                this.jobNumber, this.turnaroundTime, this.waitingTime);
    }

    static class sortByArrival implements Comparator<Job>{
        public int compare(Job a, Job b){
            return a.arrivalTime - b.arrivalTime;
        }
    }

    static class sortByRemaining implements Comparator<Job>{
        public int compare(Job a, Job b) {
            int timeDif = a.remainingTime - b.remainingTime;
            if(timeDif != 0){
                return timeDif;
            } else {
                return a.jobNumber - b.jobNumber;
            }
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Job: ");
        sb.append(this.jobNumber);
        sb.append('\n');
        sb.append("Burst: ");
        sb.append(this.burstTime);
        sb.append('\n');
        sb.append("Arrival: ");
        sb.append(this.arrivalTime);
        sb.append('\n');
        return sb.toString();
    }
}
