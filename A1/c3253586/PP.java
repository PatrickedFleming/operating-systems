//Premptive priority
//Author: Patrick Fleming
//Student Number: c3253586
//date: 2023-08-07

import java.util.LinkedList;

public class PP implements SchedularInterface {

    private ProccessList<ProcessInfo> ppInput;
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
    public PP(ProccessList<ProcessInfo> input, int DISP){
        ppInput = input;
        disp = DISP;
        waitTime = new int[ppInput.size()];
        turnAroundTime = new int[ppInput.size()];
    }
    
    public void run(){

        //add processes to queue if ariving at time 0
        for(int i = 0; i < ppInput.size(); i++){
            if(ppInput.get(i).getArrivalTime() == 0){
                queue.add(ppInput.get(i));
            }
        }

        //sort queue by priority
        queue.sort(new PPComparator());

        //disp
        time += disp;
        //add first process to timeline
        timeline += "T" + time + ": "+ queue.getFirst().getPidString() + "(" + queue.getFirst().getPriority() + ")\n";
        //set current process
        currentProcess = queue.getFirst();

        //while there are still processes to run
        while(!isComplete()){
            //update queue
            updateQueue(queue);
            //sort queue by priority
            queue.sort(new PPComparator());
            //if current process is not the highest priority
            if(currentProcess.getPriority() > queue.getFirst().getPriority()){
                //remove current process from queue
                queue.remove(currentProcess);
                //disp
                time += disp;
                //add to timeline
                timeline += "T" + time + ": "+ queue.getFirst().getPidString() + "(" + queue.getFirst().getPriority() + ")\n";
            }
            else{
                //remove service time from current process
                currentProcess.setServiceTimeLeft(currentProcess.getServiceTimeLeft() - 1);
                time++;
                if(currentProcess.getServiceTimeLeft() == 0){
                    //wait time
                    waitTime[ppInput.indexOf(currentProcess)] = time - currentProcess.getArrivalTime() - currentProcess.getServiceTime();
                    //turn around time
                    turnAroundTime[ppInput.indexOf(currentProcess)] = time - currentProcess.getArrivalTime();
                    //remove current process from queue
                    queue.remove(currentProcess);
                    if(!queue.isEmpty()){
                        //disp
                        time += disp;
                        //add to timeline
                        timeline += "T" + time + ": "+ queue.getFirst().getPidString() + "(" + queue.getFirst().getPriority() + ")\n";
                        //set current process
                        currentProcess = queue.getFirst();
                    }
                }
            }   
        }

        //calculate averages
        avgWaitTime();
        avgTurnAroundTime();

        //set sum
        setSum();
    } 

    //updates queue with processes that have arrived
    public void updateQueue(LinkedList<ProcessInfo> queue){
        //checks if first time process has arrived
        boolean temp = false;
        //if processes idle
        if(ppInput.checkIdle(time)){ 
            time +=ppInput.getNextArivalTime(time) - time;
            temp = true;
        }
        for(int i = 0; i < ppInput.size(); i++){
            if(ppInput.get(i).getArrivalTime() <= time && !queue.contains(ppInput.get(i)) && ppInput.get(i).getServiceTimeLeft() != 0){
                queue.add(ppInput.get(i));
            }
        }
        if(!queue.isEmpty()){
            currentProcess = queue.getFirst();
            if(temp){
                //disp
                time += disp;
                //add to timeline
                timeline += "T" + time + ": "+ queue.getFirst().getPidString() + "(" + queue.getFirst().getPriority() + ")\n";
            }
        }
    }


    //checks if all processes are complete
    private boolean isComplete(){
        for(int i = 0; i < ppInput.size(); i++){
            if(ppInput.get(i).getServiceTimeLeft() > 0){
                return false;
            }
        }
        return true;
    }


    public void avgWaitTime(){
        int totalWaitTime = 0;
        for(int i = 0; i < ppInput.size(); i++){
            totalWaitTime += waitTime[i];
        }
        avgWaitTime = (float)totalWaitTime / ppInput.size();
    }

    public void avgTurnAroundTime(){
        int totalTurnAroundTime = 0;
        for(int i = 0; i < ppInput.size(); i++){
            totalTurnAroundTime += turnAroundTime[i];
        }
        avgTurnAroundTime = (float)totalTurnAroundTime / ppInput.size();
    }

    public void setSum(){
        sum = "PP\t\t" + avgTurnAroundTime + "\t\t\t" + avgWaitTime;
    }

    public String getSum(){
        return sum;
    }

    //prints output in format
    public String toString(){
        String output = "PP: \n";
        output += timeline;
        output += "\nProcess\t\tTurnaround Time\t\tWait Time\n";
        for(int i = 0; i < ppInput.size(); i++){
            output += ppInput.get(i).getPidString() + "\t\t" + turnAroundTime[i] + "\t\t\t" + waitTime[i] + "\n";
        }
        return output;
    }
}
