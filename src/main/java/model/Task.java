package model;

public class Task implements Comparable<Task>{
    private static int of_id = 0;
    private int ID;
    private int arrivalTime;
    private int serviceTime;

    public Task(int arrivalTime, int serviceTime){
        ++of_id;
        this.ID = of_id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public int compareTo(Task o) {
        return this.arrivalTime - o.arrivalTime;
    }
    public String toString(){
        return("("+this.ID+","+this.arrivalTime+","+this.serviceTime+")");
    }
}
