//priority round robin
//Author: Patrick Fleming
//Student Number: c3253586
//date: 2023-08-07

import java.util.ArrayList;
import java.util.LinkedList;

public class PRR  implements SchedularInterface{
    
    private ProccessList<ProcessInfo> prrInput;
    private String timeline = "";
    private int[] waitTime;
    private int[] turnAroundTime;
    private String sum = "";
    private float avgWaitTime;
    private float avgTurnAroundTime;
    private int disp;

    private int time = 0;
    private ProcessInfo currentProcess;
    private LinkedList<ProcessInfo> queue = new LinkedList<ProcessInfo>();

    //constructor
    public PRR(ProccessList<ProcessInfo> input, int DISP){
        prrInput = input;
        disp = DISP;
        waitTime = new int[prrInput.size()];
        turnAroundTime = new int[prrInput.size()];
    }

    public void run(){
        //add processes to queue if ariving at time 0
        for(int i = 0; i < prrInput.size(); i++){
            if(prrInput.get(i).getArrivalTime() == 0){
                queue.add(prrInput.get(i));
            }
        }

        while(!isComplete()){
            updateQueue(queue);

            if(!queue.isEmpty()){
                if(queue.size() == 1){
                    if(!currentProcess.getDisp()){
                        //disp
                        time += disp;
                        //add to timeline
                        timeline += "T" + time + ": "+ currentProcess.getPidString() + "(" + currentProcess.getPriority() + ")\n";
                        //set disp
                        currentProcess.setDisp(true);
                    }
                    priortyRun();
                    updateQueue(queue);
                }
                else{
                    //disp
                    time += disp;
                    //add to timeline
                    timeline += "T" + time + ": "+ currentProcess.getPidString() + "(" + currentProcess.getPriority() + ")\n";
                    currentProcess.setDisp(false);
                    priortyRun();
                    updateQueue(queue);
                }
                queue.remove(currentProcess);
            }
        }

        //calculate averages
        avgWaitTime();
        avgTurnAroundTime();

        //set sum
        setSum();
        
    }

    private void priortyRun(){
        if(currentProcess.getPriority() < 3 ){
            if(currentProcess.getServiceTimeLeft() <= 4){
                time += currentProcess.getServiceTimeLeft();
                currentProcess.setServiceTimeLeft(0);
                waitTime[prrInput.indexOf(currentProcess)] = time - currentProcess.getArrivalTime() - currentProcess.getServiceTime();
                turnAroundTime[prrInput.indexOf(currentProcess)] = time - currentProcess.getArrivalTime();
            }
            else{
                time += 4;
                currentProcess.setServiceTimeLeft(currentProcess.getServiceTimeLeft() - 4);
            }
        } else{
            if(currentProcess.getServiceTimeLeft() <= 2){
                time += currentProcess.getServiceTimeLeft();
                currentProcess.setServiceTimeLeft(0);
                waitTime[prrInput.indexOf(currentProcess)] = time - currentProcess.getArrivalTime() - currentProcess.getServiceTime();
                turnAroundTime[prrInput.indexOf(currentProcess)] = time - currentProcess.getArrivalTime();
            }
            else{
                time += 2;
                currentProcess.setServiceTimeLeft(currentProcess.getServiceTimeLeft() - 2);
                
            }
        }
    }

    public void updateQueue(LinkedList<ProcessInfo> queue){
        //checks if first time process has arrived
        boolean temp = false;
        //if processes idle
        if(prrInput.checkIdle(time)){ 
            time +=prrInput.getNextArivalTime(time) - time;
            temp = true;
        }
        for(int i = 0; i < prrInput.size(); i++){
            if(prrInput.get(i).getArrivalTime() <= time && !queue.contains(prrInput.get(i)) && prrInput.get(i).getServiceTimeLeft() != 0){
                queue.add(prrInput.get(i));
            }
        }
        if(!queue.isEmpty()){
            currentProcess = queue.getFirst();
            if(temp && queue.size() == 1 && currentProcess.getServiceTimeLeft() != 0){
                //disp
                time += disp;
                //add to timeline
                timeline += "T" + time + ": "+ currentProcess.getPidString() + "(" + currentProcess.getPriority() + ")\n";
                //set disp
                currentProcess.setDisp(true);
            }
        }
    }

    public void setSum(){
        sum = "PRR\t\t" + avgTurnAroundTime + "\t\t\t" + avgWaitTime;
    }

    public String getSum(){
        return sum;
    }

    public void avgWaitTime(){
        int totalWaitTime = 0;
        for(int i = 0; i < prrInput.size(); i++){
            totalWaitTime += waitTime[i];
        }
        avgWaitTime = (float)totalWaitTime / prrInput.size();
    }

    public void avgTurnAroundTime(){
        int totalTurnAroundTime = 0;
        for(int i = 0; i < prrInput.size(); i++){
            totalTurnAroundTime += turnAroundTime[i];
        }
        avgTurnAroundTime = (float)totalTurnAroundTime / prrInput.size();
        
    }

    private boolean isComplete(){
        for(int i = 0; i < prrInput.size(); i++){
            if(prrInput.get(i).getServiceTimeLeft() > 0){
                return false;
            }
        }
        return true;
    }

    //prints output in format
    public String toString(){
        String output = "PRR: \n";
        output += timeline;
        output += "\nProcess\t\tTurnaround Time\t\tWait Time\n";
        for(int i = 0; i < prrInput.size(); i++){
            output += prrInput.get(i).getPidString() + "\t\t" + turnAroundTime[i] + "\t\t\t" + waitTime[i] + "\n";
        }
        return output;
    }

}
