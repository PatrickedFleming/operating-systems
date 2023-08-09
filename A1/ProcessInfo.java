// Process class to hold process information
//date: 2023-08-06
//Author: Patrick Fleming
//Student Number: c3253586

public class ProcessInfo {
    int pid;
    int arrivalTime;
    int serviceTime;
    int priority;
    int currServiceTime;

    public ProcessInfo(int pid, int arrivalTime, int serviceTime, int priority, int currServiceTime) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.priority = priority;
        this.currServiceTime = currServiceTime;
    }

    public int getPid() {
        return this.pid;
    }

    public int getArrivalTime() {
        return this.arrivalTime;
    }

    public int getServiceTime() {
        return this.serviceTime;
    }

    public int getPriority() {
        return this.priority;
    }

    public int getCurrServiceTime() {
        return this.currServiceTime;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setCurrServiceTime(int currServiceTime) {
        this.currServiceTime = currServiceTime;
    }

    public String toString() {
        return "p" + this.pid;
    }
}
