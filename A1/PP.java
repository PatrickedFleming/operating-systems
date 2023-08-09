//Premptive priority
//Author: Patrick Fleming
//Student Number: c3253586
//date: 2023-08-07

import java.util.ArrayList;

public class PP {

    private Output ppOutput = new Output();
    
    public void run(ArrayList<ProcessInfo> input, int disp){
        int time = 0;

        //while process
        while(!input.isEmpty()){
            //if time is less then arrival time of next process add time up to arival time
            if(time < input.get(0).getArrivalTime()){
                time = input.get(0).getArrivalTime();
            }

            //is proccess ready to run
            if(input.get(0).getArrivalTime() <= time){
                //disp
                time += disp;
                //add process to output
                ppOutput.addProcessOrder("p" + input.get(0).getPid());
                //add process priority to output
                ppOutput.addProcessPriority(input.get(0).getPriority());
                //add process start time to output
                ppOutput.addProcessStartTimes(time);
                //add process wait time to output
                ppOutput.addProcessWaitTimes(time - input.get(0).getArrivalTime());
                //add process time to output
                ppOutput.addProcessTimes(input.get(0).getServiceTime());
                //add service time to time
                time += input.get(0).getServiceTime();
                //add process turn around time to output
                ppOutput.addProcessTurnAroundTimes(time - input.get(0).getArrivalTime());
                //remove process from input
                input.remove(0);
            }
            
            //calcs average
            ppOutput.setProcessAverageWaitTimes(ppOutput.averageWaitTime());
            ppOutput.setProcessAverageTurnAroundTimes(ppOutput.averageTurnAroundTime());

        }
    }

    //prints output in format
    public String toString(){
        return ppOutput.toString("PP");
    }
}
