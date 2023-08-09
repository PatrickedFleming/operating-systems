//Holds output of scheduling algorithm
//Author: Patrick Fleming
//Student Number: c3253586
//date: 2023-08-07


import java.util.ArrayList;

public class Output {
    private ArrayList<String> processOrder = new ArrayList<String>();
    private ArrayList<Integer> processPriority = new ArrayList<Integer>();
    private ArrayList<Integer> processTimes = new ArrayList<Integer>();
    private ArrayList<Integer> processWaitTimes = new ArrayList<Integer>();
    private ArrayList<Integer> processTurnAroundTimes = new ArrayList<Integer>();
    private ArrayList<Integer> processStartTimes = new ArrayList<Integer>();
    private int processAverageWaitTimes;
    private int processAverageTurnAroundTimes;

    public void addProcessOrder(String process){
        processOrder.add(process);
    }

    public void addProcessPriority(int priority){
        processPriority.add(priority);
    }

    public void addProcessTimes(int time){
        processTimes.add(time);
    }

    public void addProcessWaitTimes(int time){
        processWaitTimes.add(time);
    }

    public void addProcessTurnAroundTimes(int time){
        processTurnAroundTimes.add(time);
    }

    public void addProcessStartTimes(int time){
        processStartTimes.add(time);
    }

    public void setProcessAverageWaitTimes(int time){
        processAverageWaitTimes = time;
    }

    public void setProcessAverageTurnAroundTimes(int time){
        processAverageTurnAroundTimes = time;
    }

    public ArrayList<String> getProcessOrder(){
        return processOrder;
    }

    public ArrayList<Integer> getProcessPriority(){
        return processPriority;
    }

    public ArrayList<Integer> getProcessTimes(){
        return processTimes;
    }

    public ArrayList<Integer> getProcessWaitTimes(){
        return processWaitTimes;
    }

    public ArrayList<Integer> getProcessTurnAroundTimes(){
        return processTurnAroundTimes;
    }
    
    public ArrayList<Integer> getProcessStartTimes(){
        return processStartTimes;
    }

    public int getProcessAverageWaitTimes(){
        return processAverageWaitTimes;
    }

    public int getProcessAverageTurnAroundTimes(){
        return processAverageTurnAroundTimes;
    }

    //sort all the processes by process id
    public void sort(){
        for(int i = 0; i < processOrder.size(); i++){
            for(int j = 0; j < processOrder.size(); j++){
                if(Integer.parseInt(processOrder.get(i).substring(1)) < Integer.parseInt(processOrder.get(j).substring(1))){
                    String temp = processOrder.get(i);
                    processOrder.set(i, processOrder.get(j));
                    processOrder.set(j, temp);

                    int temp2 = processPriority.get(i);
                    processPriority.set(i, processPriority.get(j));
                    processPriority.set(j, temp2);

                    temp2 = processTimes.get(i);
                    processTimes.set(i, processTimes.get(j));
                    processTimes.set(j, temp2);

                    temp2 = processWaitTimes.get(i);
                    processWaitTimes.set(i, processWaitTimes.get(j));
                    processWaitTimes.set(j, temp2);

                    temp2 = processTurnAroundTimes.get(i);
                    processTurnAroundTimes.set(i, processTurnAroundTimes.get(j));
                    processTurnAroundTimes.set(j, temp2);

                    temp2 = processStartTimes.get(i);
                    processStartTimes.set(i, processStartTimes.get(j));
                    processStartTimes.set(j, temp2);
                }
            }
        }
    }

    //average wait time
    public int averageWaitTime(){
        int averageWaitTime = 0;
        for(int i = 0; i < processWaitTimes.size(); i++){
            averageWaitTime += processWaitTimes.get(i);
        }
        averageWaitTime = averageWaitTime/processWaitTimes.size();
        return averageWaitTime;
    }

    //average turn around time
    public int averageTurnAroundTime(){
        int averageTurnAroundTime = 0;
        for(int i = 0; i < processTurnAroundTimes.size(); i++){
            averageTurnAroundTime += processTurnAroundTimes.get(i);
        }
        averageTurnAroundTime = averageTurnAroundTime/processTurnAroundTimes.size();
        return averageTurnAroundTime;
    }


    
    //to string
    String toString(String algorithm){
        String outputString = "";
        outputString += algorithm+":\n";
        //formatt for order and time of proccess loading
        for(int i = 0; i < this.getProcessOrder().size(); i++){
            outputString += "T"+ this.getProcessStartTimes().get(i) +": " + this.getProcessOrder().get(i) +"("+this.getProcessPriority().get(i)+")"+ "\n";
        }
        outputString += "\n";

        //sort by id
        sort();
        //formate for process turnarround times and waiting times
        outputString += "Process Turnarround Time Waiting Time\n";
        for(int i = 0; i < this.getProcessOrder().size(); i++){
            outputString += this.getProcessOrder().get(i) +"\t"+ this.getProcessTurnAroundTimes().get(i) + "\t\t " + this.getProcessWaitTimes().get(i) + "\n";
        }
        return outputString;
    }

}
