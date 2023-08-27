// Process class to hold process information
//date: 2023-08-06
//Author: Patrick Fleming
//Student Number: c3253586

public class ProcessInfo {
    private int pid;
    private String pidString;
    private int arrivalTime;
    private int serviceTime;
    private int priority;

    private int serviceTimeLeft;
    private boolean disp = false;

    //constructor
    public ProcessInfo(int inputPid, int inputArrivalTime, int inputServiceTime, int inputPriority) {
        pid = inputPid;
        pidString = "p" + pid;
        arrivalTime = inputArrivalTime;
        serviceTime = inputServiceTime;
        priority = inputPriority;
        serviceTimeLeft = inputServiceTime;
    }

    public int getPid() {
        return pid;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public int getPriority() {
        return priority;
    }

    public String getPidString() {
        return pidString;
    }

    public int getServiceTimeLeft() {
        return serviceTimeLeft;
    }

    public void setServiceTimeLeft(int serviceTimeLeft) {
        this.serviceTimeLeft = serviceTimeLeft;
    }

    public void reset(){
        serviceTimeLeft = serviceTime;
    }

    public void setDisp(boolean disp){
        this.disp = disp;
    }

    public boolean getDisp(){
        return disp;
    }
    
    public boolean complete(){
        if(serviceTimeLeft == 0){
            return true;
        } else{
            return false;
        }
    }
}
