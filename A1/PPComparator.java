//see whos shlong is bigger
//im sorry i couldnt resist
//Author: Patrick Fleming
//Student Number: c3253586
//date: 2023-08-07

import java.util.Comparator;

public class PPComparator implements Comparator<ProcessInfo> {

    //compare by arival time if its the same compare by priority 0 being highest priority 5 being lowest 
    public int compare(ProcessInfo p1, ProcessInfo p2){
        if(p1.getArrivalTime() == p2.getArrivalTime()){
            return p1.getPriority() - p2.getPriority();
        }
        else{
            return p1.getArrivalTime() - p2.getArrivalTime();
        }
    }
    
}
