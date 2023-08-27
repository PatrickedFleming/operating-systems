//comparator for spn algorithm
//Author: Patrick Fleming
//Student Number: c3253586
//date: 2023-08-07

import java.util.Comparator;

public class SPNComparator implements Comparator<ProcessInfo> {

    public int compare(ProcessInfo p1, ProcessInfo p2){
        //oder by service time only
        if(p1.getServiceTime() < p2.getServiceTime()){
            return -1;
        }
        else if(p1.getServiceTime() > p2.getServiceTime()){
            return 1;
        }
        else{
            return 0;
        }
    }
    
}
