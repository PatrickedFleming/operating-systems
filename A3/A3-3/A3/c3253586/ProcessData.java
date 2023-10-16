//Data Class used to hold the process and its output data
//Author: Patrick Fleming
//Student Number: C3253586
//Date: 17/10/23
import java.util.LinkedList;

public class ProcessData {
    Process process;
    int id;
    int faults;
    int turnarroundTime;
    int count = 0;
    
    LinkedList<Integer> faultsList = new LinkedList<Integer>();

    /**
     * Creates a process data with the id and the process
     * 
     * @param id the id of the process
     * @param process the process
     */
    public ProcessData(int id, Process process){
        this.id = id;
        this.process = process;
    }

    /**
     * records a fault at the time
     * 
     * @param time the time of the fault
     */
    public void fault(int time){
        faults++;
        faultsList.add(time);
    }

    /**
     * Returns total number of faults
     * 
     * @return the total number of faults
     */
    public int getFaults(){
        return faults;
    }

    /**
     * Returns the list of faults
     * 
     * @return the list of faults
     */
    public LinkedList<Integer> getFaultsList(){
        return faultsList;
    }

    /**
     * Sets the process of the output data
     * 
     * @param id the process
     */
    public void setProcess(Process process){
        this.process = process;
    }

    /**
     * Returns the process
     * 
     * @return the process
     */
    public Process getProcess(){
        return process;
    }

    /**
     * Sets the turn around time of the process
     * 
     * @param time the turn around time
     */
    public void setTurnarroundTime(int time){
        turnarroundTime = time;
    }

    /**
     * Returns the turn around time
     * 
     * @return the turn around time
     */
    public int getTurnarroundTime(){
        return turnarroundTime;
    }

    /**
     * Returns string of faults list for output
     * 
     * @return string of faults list for output
     */
    private String getFaultsString(){
        String faultsString = "{";

        for(int i: faultsList){
            faultsString += i + ", ";
        }
        faultsString = faultsString.substring(0, faultsString.length()-2);
        faultsString += "}";
        return faultsString;
    }

    /**
     * Returns the string of the process output data
     * 
     * @return the string of the process output data
     */
    @Override
    public String toString(){
        return id + "\t" + process.getName() + "\t" + turnarroundTime + "\t\t" + faults + "\t\t" + getFaultsString();
    }

 
}
