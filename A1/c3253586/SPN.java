//Shortess process next
//Author: Patrick Fleming
//Student Number: c3253586
//date: 2023-08-07

import java.util.LinkedList;

public class SPN implements SchedularInterface{

    private ProccessList<ProcessInfo> spnInput;
    private int[] waitTime;
    private int[] turnAroundTime;
    private String timeline = "";
    private String sum = "";
    private float avgWaitTime;
    private float avgTurnAroundTime;
    private int disp;

    //constructor
    public SPN(ProccessList<ProcessInfo> input, int DISP){
        spnInput = input;
        disp = DISP;
        waitTime = new int[spnInput.size()];
        turnAroundTime = new int[spnInput.size()];
    }
    
    public void run(){
        int time = 0;
        //queue to store processes
        LinkedList<ProcessInfo> queue = new LinkedList<ProcessInfo>();

        //add all process that arrive at time 0
        for(int i = 0; i < spnInput.size(); i++){
            if(spnInput.get(i).getArrivalTime() == 0){
                queue.add(spnInput.get(i));
            }
        }
        //while there are still processes to run
        while(!isComplete()){
            
            
            //sort queue by service time
            queue.sort(new SPNComparator());
            //disp
            time += disp;
            //get next process
            if(queue.isEmpty()){
                //if idle add time to next jobs arival time
                if(spnInput.checkIdle(time)){
                    if(spnInput.getNextArivalTime(time) != 0){
                        time = spnInput.getNextArivalTime(time);
                    } else{
                        System.out.println("Error: no more processes to run");
                        break;
                    }
                }
            } else{
                ProcessInfo currentProcess = queue.getFirst();
                //add to timeline
                timeline += "T" + time + ": "+ currentProcess.getPidString() + "(" + currentProcess.getPriority() + ")\n";
                //add to time
                time += currentProcess.getServiceTime();
                //add process turn arround time
                turnAroundTime[spnInput.indexOf(currentProcess)] = time - currentProcess.getArrivalTime();
                //add process wait time
                waitTime[spnInput.indexOf(currentProcess)] = time - currentProcess.getArrivalTime() - currentProcess.getServiceTime();
                //set service time left
                currentProcess.setServiceTimeLeft(0);    
                //if process has arrived add to queue
                for(int i = 0; i < spnInput.size() && spnInput.get(i).getArrivalTime() <= time; i++){
                    if(!queue.contains(spnInput.get(i)) && spnInput.get(i).getServiceTimeLeft() != 0){
                        queue.add(spnInput.get(i));
                    }
                }
                //remove process from queue
                queue.removeFirst();
            }
            for(int i = 0; i < spnInput.size() && spnInput.get(i).getArrivalTime() <= time; i++){
                if(!queue.contains(spnInput.get(i)) && spnInput.get(i).getServiceTimeLeft() != 0){
                    queue.add(spnInput.get(i));
                }
            }
        }

        //calculate averages
        avgWaitTime();
        avgTurnAroundTime();

        //set sum
        setSum();
    }

    //checks if all processes are complete
    private boolean isComplete(){
        for(int i = 0; i < spnInput.size(); i++){
            if(spnInput.get(i).getServiceTimeLeft() > 0){
                return false;
            }
        }
        return true;
    }

    public void avgWaitTime(){
        float totalWaitTime = 0;
        for(int i = 0; i < waitTime.length; i++){
            totalWaitTime += waitTime[i];
        }
        avgWaitTime = totalWaitTime / waitTime.length;
    }

    public void avgTurnAroundTime(){
        float totalTurnAroundTime = 0;
        for(int i = 0; i < turnAroundTime.length; i++){
            totalTurnAroundTime += turnAroundTime[i];
        }
        avgTurnAroundTime = totalTurnAroundTime / turnAroundTime.length;
    }

    public void setSum(){
        sum = "SPN\t\t" + avgTurnAroundTime + "\t\t\t" + avgWaitTime;
    }

    public String getSum(){
        return sum;
    }



    //prints output in format
    public String toString(){
        String output = "SPN: \n";
        output += timeline;
        output += "\nProcess\t\tTurnaround Time\t\tWait Time\n";
        for(int i = 0; i < spnInput.size(); i++){
            output += spnInput.get(i).getPidString() + "\t\t" + turnAroundTime[i] + "\t\t\t" + waitTime[i] + "\n";
        }
        return output;
    }
}
