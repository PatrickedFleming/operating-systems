// Process class to hold process information
//date: 2023-08-06
//Author: Patrick Fleming
//Student Number: c3253586

public class ProcessInfo {
    private static int pid;
    private static String pidString;
    private static int arrivalTime;
    private static int serviceTime;
    private static int priority;

    private int serviceTimeLeft;
    private int TurnaroundTime;
    private int WaitTime;


    public ProcessInfo(int inputPid, int inputArrivalTime, int inputServiceTime, int inputPriority) {
        pid = inputPid;
        pidString = "p" + inputPid;
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

    public void setServiceTimeLeft(int inputServiceTimeLeft) {
        serviceTimeLeft = inputServiceTimeLeft;
    }

    public int getServiceTimeLeft() {
        return serviceTimeLeft;
    }

    public void setTurnAroundTime(int inputTurnaroundTime) {
        TurnaroundTime = inputTurnaroundTime;
    }

    public int getTurnAroundTime() {
        return TurnaroundTime;
    }

    public void setWaitTime(int inputWaitTime) {
        WaitTime = inputWaitTime;
    }

    public int getWaitTime() {
        return WaitTime;
    }

    public void reset(){
        serviceTimeLeft = serviceTime;
        TurnaroundTime = 0;
        WaitTime = 0;
    }

    public String toString(){
        String output = pidString + "\t\t" + TurnaroundTime + "\t\t" + WaitTime + "\n";
        return output;
    }
}
