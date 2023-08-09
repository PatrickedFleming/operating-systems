//priority round robin
//Author: Patrick Fleming
//Student Number: c3253586
//date: 2023-08-07

import java.util.ArrayList;

public class PRR {
    
    private Output prrOutput = new Output();

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
                //if process high priority run by 4 time units else run by 2 time units
                if(input.get(0).getPriority() <= 2){
                    //add process to output
                    prrOutput.addProcessOrder("p" + input.get(0).getPid());
                    //add process priority to output
                    prrOutput.addProcessPriority(input.get(0).getPriority());
                    //add process start time to output
                    prrOutput.addProcessStartTimes(time);
                    //add process wait time to output
                    prrOutput.addProcessWaitTimes(time - input.get(0).getArrivalTime());
                    //add process time to output
                    prrOutput.addProcessTimes(input.get(0).getServiceTime());
                    //if process is finished
                    if(input.get(0).getServiceTime() <= 4){
                        //add service time to time
                        time += input.get(0).getServiceTime();
                    }
                    else{
                        //add 4 service time to time
                        time += 4;
                    }
                    //add process turn around time to output
                    prrOutput.addProcessTurnAroundTimes(time - input.get(0).getArrivalTime());
                    //if process is not finished
                    if(input.get(0).getServiceTime() > 4){
                        //add process to end of input
                        input.add(new ProcessInfo(input.get(0).getPid(), input.get(0).getArrivalTime(), input.get(0).getServiceTime() - 4, input.get(0).getPriority()));
                    } 
                    //remove process from input
                    input.remove(0);
                    

                }else{
                    //add process to output
                    prrOutput.addProcessOrder("p" + input.get(0).getPid());
                    //add process priority to output
                    prrOutput.addProcessPriority(input.get(0).getPriority());
                    //add process start time to output
                    prrOutput.addProcessStartTimes(time);
                    //add process wait time to output
                    prrOutput.addProcessWaitTimes(time - input.get(0).getArrivalTime());
                    //add process time to output
                    prrOutput.addProcessTimes(input.get(0).getServiceTime());
                    //if process is finished
                    if(input.get(0).getServiceTime() <= 2){
                        //add service time to time
                        time += input.get(0).getServiceTime();
                    }
                    else{
                        //add 2 service time to time
                        time += 2;
                    }
                    //add process turn around time to output
                    prrOutput.addProcessTurnAroundTimes(time - input.get(0).getArrivalTime());
                    //if process is not finished
                    if(input.get(0).getServiceTime() > 2){
                        //add process to end of input
                        input.add(new ProcessInfo(input.get(0).getPid(), input.get(0).getArrivalTime(), input.get(0).getServiceTime() - 2, input.get(0).getPriority()));
                    } 
                    //remove process from input
                    input.remove(0);
                }
            }
            
        }

        //calcs average
        prrOutput.setProcessAverageWaitTimes(prrOutput.averageWaitTime());
        prrOutput.setProcessAverageTurnAroundTimes(prrOutput.averageTurnAroundTime());
    }



    //prints output in format
    public String toString(){
        return prrOutput.toString("PRR");
    }
}
