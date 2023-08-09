//First come first serve algorithm
//Author: Patrick Fleming
//Student Number: c3253586
//date: 2023-08-06

import java.util.ArrayList;

public class FCFS {

    private static Output fcfsOutput;

    public FCFS(ArrayList<ProcessInfo> input){
        fcfsOutput = new Output(input);
    }
    
    public void run(ArrayList<ProcessInfo> input, int DISP){
        int time = 0;

        //while there are still processes to run
        while(!input.isEmpty()){
            //if time is less then arrival time of next process add time up to arival time
            if(time < input.get(0).getArrivalTime()){
                time = input.get(0).getArrivalTime();
            }

            //is proccess ready to run
            if(input.get(0).getArrivalTime() <= time){
                //add disp to time
                time += DISP;
                //add process to output
                fcfsOutput.addProcessOrder(input.get(0).getPid());
                //add process priority to output
                fcfsOutput.addProcessPriority(input.get(0).getPriority());
                //add process start time to output
                fcfsOutput.addProcessStartTimes(time);
                //add process wait time to output
                fcfsOutput.addProcessWaitTimes(input.get(0).getPid(),time - input.get(0).getArrivalTime());
                //add service time to time
                time += input.get(0).getServiceTime();
                //add process turn around time to output
                fcfsOutput.addProcessTurnAroundTimes(input.get(0).getPid(),time - input.get(0).getArrivalTime());
                //remove process from input
                input.remove(0);
            }
        }
    }

    //get output
    public Output getOutput(){
        return fcfsOutput;
    }


    //prints output in required format
    public String toString(){
        return fcfsOutput.toString("FCFS");
    }
}
