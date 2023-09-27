
import java.util.LinkedList;

public class ProcessData {
    Process process;
    int id;
    int faults;
    int turnarroundTime;
    int count = 0;
    
    LinkedList<Integer> faultsList = new LinkedList<Integer>();


    public ProcessData(int id, Process process){
        this.id = id;
        this.process = process;
    }

    public void fault(int time){
        faults++;
        faultsList.add(time);
    }

    public int getFaults(){
        return faults;
    }
    public LinkedList<Integer> getFaultsList(){
        return faultsList;
    }

    public void setProcess(Process process){
        this.process = process;
    }

    public Process getProcess(){
        return process;
    }

    public void setTurnarroundTime(int time){
        turnarroundTime = time;
    }

    public int getTurnarroundTime(){
        return turnarroundTime;
    }

    @Override
    public String toString(){
        return id + "\t" + process.getName() + "\t" + turnarroundTime + "\t\t" + faults + "\t\t" + faultsList;
    }

 
}
