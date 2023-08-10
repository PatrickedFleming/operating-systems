//First come first serve algorithm
//Author: Patrick Fleming
//Student Number: c3253586
//date: 2023-08-06

import java.util.ArrayList;

public class FCFS implements SchedularInterface{

    private ArrayList<ProcessInfo> fcfsInput;
    private String timeline = "";
    private String sum = "";
    private float avgWaitTime;
    private float avgTurnAroundTime;

    public FCFS(ArrayList<ProcessInfo> input){
        fcfsInput = input;
    }
    
    public String getSum(){
        return sum;
    }
    
    public void run(ArrayList<ProcessInfo> input, int DISP){
        int time = 0;
        int disp = DISP;
        //sort input by arrival time
        fcfsInput.sort((ProcessInfo p1, ProcessInfo p2) -> p1.getArrivalTime() - p2.getArrivalTime());
        while(!fcfsInput.isEmpty()){
            //if time is less then arrival time of next process add time up to arival time
            if(time < fcfsInput.get(0).getArrivalTime()){
                time = fcfsInput.get(0).getArrivalTime();
            }

            //is proccess ready to run
            if(fcfsInput.get(0).getArrivalTime() <= time && fcfsInput.get(0).getServiceTimeLeft() > 0){
                //disp
                time += disp;
                //add process to timeline
                timeline +="T" + time + ": "+ fcfsInput.get(0).getPidString() + "(" + fcfsInput.get(0).getPriority() + ")\n";
                //set service time left to 0
                fcfsInput.get(0).setServiceTimeLeft(0);
                //add service time to time
                time += fcfsInput.get(0).getServiceTime();
                //add process turn around time
                fcfsInput.get(0).setTurnAroundTime(time - fcfsInput.get(0).getArrivalTime());
                //add process wait time
                fcfsInput.get(0).setWaitTime(time - fcfsInput.get(0).getArrivalTime() - fcfsInput.get(0).getServiceTime());

                //move to next process
                fcfsInput.remove(0);
            }
        }

        //calculate average wait time
        avgWaitTime();
        //calculate average turn around time
        avgTurnAroundTime();
        //set sum
        setSum();

        }

    public void avgWaitTime(){
        int totalWaitTime = 0;
        for(int i = 0; i < fcfsInput.size(); i++){
            totalWaitTime += fcfsInput.get(i).getWaitTime();
        }
        avgWaitTime = (float)totalWaitTime / fcfsInput.size();
    }

    public void avgTurnAroundTime(){
        int totalTurnAroundTime = 0;
        for(int i = 0; i < fcfsInput.size(); i++){
            totalTurnAroundTime += fcfsInput.get(i).getTurnAroundTime();
        }
        avgTurnAroundTime = (float)totalTurnAroundTime / fcfsInput.size();
    }

    public void setSum(){
        sum = "FCFS\t\t" + avgTurnAroundTime + "\t\t" + avgWaitTime;
    }


    //prints output in format
    public String toString(){
        String output = "FCFS: \n";
        output += timeline;
        output += "Process\t\tTurnaround Time\t\tWait Time\n";
        for(int i = 0; i < fcfsInput.size(); i++){
            output += fcfsInput.get(i).toString();
        }
        return output;
    }
}
