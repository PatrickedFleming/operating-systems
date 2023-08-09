//Shortess process next
//Author: Patrick Fleming
//Student Number: c3253586
//date: 2023-08-07

import java.util.ArrayList;

public class SPN {

    private static Output spnOutput;

    public SPN(ArrayList<ProcessInfo> input){
       spnOutput = new Output(input);
    }
    
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
                spnOutput.addProcessOrder(input.get(0).getPid());
                //add process priority to output
                spnOutput.addProcessPriority(input.get(0).getPriority());
                //add process start time to output
                spnOutput.addProcessStartTimes(time);
                //add process wait time to output
                spnOutput.addProcessWaitTimes(input.get(0).getPid(),time - input.get(0).getArrivalTime());
                //add service time to time
                time += input.get(0).getServiceTime();
                //add process turn around time to output
                spnOutput.addProcessTurnAroundTimes(input.get(0).getPid(),time - input.get(0).getArrivalTime());
                //remove process from input
                input.remove(0);
            }
        }

    }

    //get output
    public Output getOutput(){
        return spnOutput;
    }

    //prints output in format
    public String toString(){
        return spnOutput.toString("SPN");
    }
}
