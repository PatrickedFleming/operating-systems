//see whos shlong is bigger
//im sorry i couldnt resist
//Author: Patrick Fleming
//Student Number: c3253586
//date: 2023-08-07

import java.util.Comparator;

public class PPComparator implements Comparator<ProcessInfo> {

    //sort by priority lower number means higher priority
    public int compare(ProcessInfo p1, ProcessInfo p2){
        if(p1.getPriority() < p2.getPriority()){
            return -1;
        }
        else if(p1.getPriority() > p2.getPriority()){
            return 1;
        }
        else{
            return 0;
        }
    }
}
