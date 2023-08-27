//ArrayList for the proccesses
//Author: Patrick Fleming
//Student Number: c3253586
//date: 2023-08-22

import java.util.ArrayList;

public class ProccessList<E> extends ArrayList<ProcessInfo> {
    
    public void sortArivalTime(){
        this.sort((ProcessInfo p1, ProcessInfo p2) -> p1.getArrivalTime() - p2.getArrivalTime());
    }

    public void sortPriority(){
        this.sort((ProcessInfo p1, ProcessInfo p2) -> p1.getPriority() - p2.getPriority());
    }

    public void sortServiceTime(){
        this.sort((ProcessInfo p1, ProcessInfo p2) -> p1.getServiceTime() - p2.getServiceTime());
    }

    public void sortPid(){
        this.sort((ProcessInfo p1, ProcessInfo p2) -> p1.getPid() - p2.getPid());
    }

    public void resetProccesses(){
        for(ProcessInfo p : this){
            p.reset();
        }
    }

    public void readyProccesses(){
        this.sortArivalTime();
        this.resetProccesses();
    }

    public boolean checkIdle(int time){
        for(ProcessInfo p : this){
            if(p.getArrivalTime() <= time && !p.complete()){
                return false;
            }
        }
        return true;
    }

    public int getNextArivalTime(int time){
        for(ProcessInfo p : this){
            if(p.getArrivalTime() > time && !p.complete()){
                return p.getArrivalTime();
            }
        }
        return 0;
    }
}
