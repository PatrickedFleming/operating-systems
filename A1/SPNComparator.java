//comparator for spn algorithm
//Author: Patrick Fleming
//Student Number: c3253586
//date: 2023-08-07

import java.util.Comparator;

public class SPNComparator implements Comparator<ProcessInfo> {

    public int compare(ProcessInfo p1, ProcessInfo p2){
        //compare by arival time if arival time is the same use service time
        if(p1.getArrivalTime() == p2.getArrivalTime()){
            return p1.getServiceTime() - p2.getServiceTime();
        }
        else{
            return p1.getArrivalTime() - p2.getArrivalTime();
        }
    }
    
}
