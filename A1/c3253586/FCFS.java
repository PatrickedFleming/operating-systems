//First come first serve algorithm
//Author: Patrick Fleming
//Student Number: c3253586
//date: 2023-08-06

import java.util.ArrayList;
import java.util.LinkedList;

public class FCFS implements SchedularInterface{

    private ArrayList<ProcessInfo> fcfsInput;
    private String timeline = "";
    private String sum = "";
    private int[] waitTime;
    private int[] turnAroundTime;
    private float avgWaitTime;
    private float avgTurnAroundTime;
    private int disp;

    public FCFS(ArrayList<ProcessInfo> input, int DISP){
        fcfsInput = input;
        disp = DISP;
        waitTime = new int[fcfsInput.size()];
        turnAroundTime = new int[fcfsInput.size()];
    }
    
    public String getSum(){
        return sum;
    }
    
    public void run(){
        int time = 0;
        //sort input by arrival time
        fcfsInput.sort((ProcessInfo p1, ProcessInfo p2) -> p1.getArrivalTime() - p2.getArrivalTime());
        LinkedList<ProcessInfo> queue = new LinkedList<ProcessInfo>();
        //add all process to que in arival order
        for(int i = 0; i < fcfsInput.size(); i++){
            queue.add(fcfsInput.get(i));
        }
        //while there are still processes to run
        while(!queue.isEmpty()){
            if(time < queue.getFirst().getArrivalTime()){
                time = queue.getFirst().getArrivalTime();
            }
            time += disp;
            //get next process
            ProcessInfo currentProcess = queue.getFirst();
            //add to timeline
            timeline += "T" + time + ": "+ currentProcess.getPidString() + "(" + currentProcess.getPriority() + ")\n";
            //add to time
            time += currentProcess.getServiceTime();
            
            //add process turn arround time
            turnAroundTime[fcfsInput.indexOf(currentProcess)] = time - currentProcess.getArrivalTime();
            //add process wait time
            waitTime[fcfsInput.indexOf(currentProcess)] = time - currentProcess.getArrivalTime() - currentProcess.getServiceTime();
            //remove process from queue
            queue.removeFirst();
        }

        //calculate average wait time
        avgWaitTime();
        //calculate average turn around time
        avgTurnAroundTime();
        //set sum
        setSum();
        }

    public void avgWaitTime(){
        float totalWaitTime = 0;
        for(int i = 0; i < fcfsInput.size(); i++){
            totalWaitTime += waitTime[i];
        }
        avgWaitTime = totalWaitTime / fcfsInput.size();
    }

    public void avgTurnAroundTime(){
        float totalTurnAroundTime = 0;
        for(int i = 0; i < fcfsInput.size(); i++){
            totalTurnAroundTime += turnAroundTime[i];
        }
        avgTurnAroundTime = totalTurnAroundTime / fcfsInput.size();
    }

    public void setSum(){
        sum = "FCFS\t\t" + avgTurnAroundTime + "\t\t\t" + avgWaitTime;
    }


    //prints output in format
    public String toString(){
        String output = "FCFS: \n";
        output += timeline;
        output += "\nProcess\t\tTurnaround Time\t\tWait Time\n";
        for(int i = 0; i < fcfsInput.size(); i++){
            output += fcfsInput.get(i).getPidString() + "\t\t" + turnAroundTime[i] + "\t\t\t" + waitTime[i] + "\n";
        }
        return output;
    }
}
