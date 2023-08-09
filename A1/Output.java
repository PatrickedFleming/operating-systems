//Holds output of scheduling algorithm
//Author: Patrick Fleming
//Student Number: c3253586
//date: 2023-08-07

import java.util.ArrayList;
import java.util.HashMap;

public class Output {
    private static ArrayList<ProcessInfo> processes;
    
    private ArrayList<Integer> processOrder = new ArrayList<Integer>();
    private ArrayList<Integer> processPriority = new ArrayList<Integer>();
    private ArrayList<Integer> processStartTimes = new ArrayList<Integer>();

    private HashMap<Integer, Integer> processWaitTimes = new HashMap<Integer, Integer>();
    private HashMap<Integer, Integer> processTurnAroundTimes = new HashMap<Integer, Integer>();

    //constructor
    public Output(ArrayList<ProcessInfo> input){
        processes =  input;
    }

    //gets
    public ArrayList<ProcessInfo> getProcesses(){
        return processes;
    }

    public ArrayList<Integer> getProcessOrder(){
        return processOrder;
    }

    public ArrayList<Integer> getProcessPriority(){
        return processPriority;
    }

    public ArrayList<Integer> getProcessStartTimes(){
        return processStartTimes;
    }

    public HashMap<Integer,Integer> getProcessWaitTimes(){
        return processWaitTimes;
    }

    public HashMap<Integer, Integer> getProcessTurnAroundTimes(){
        return processTurnAroundTimes;
    }

    //adds
    public void addProcessOrder(Integer input){
        processOrder.add(input);
    }

    public void addProcessPriority(int input){
        processPriority.add(input);
    }

    public void addProcessStartTimes(int input){
        processStartTimes.add(input);
    }

    public void addProcessWaitTimes(int pid, int pwt){
        processWaitTimes.put(pid, pwt);
    }

    public void addProcessTurnAroundTimes(int pid, int ptt){
        processTurnAroundTimes.put(pid, ptt);
    }

    //finds process in processes with same id
    public int findProcess(int pid){
        int index = -1;
        for(int i = 0; i < processes.size(); i++){
            if(processes.get(i).getPid() == pid){
                index = i;
            }
        }
        return index;
    }

    //average wait time
    public double averageWaitTime(){
        double total = 0;
        for(Integer i : processWaitTimes.values()){
            total += i;
        }
        return total / processWaitTimes.size();
    }

    //average turn around time
    public double averageTurnAroundTime(){
        double total = 0;
        for(Integer i : processTurnAroundTimes.values()){
            total += i;
        }
        return total / processTurnAroundTimes.size();
    }

    //sumarise
    public String sum(String input){
        String output = input + "\t\t" + averageTurnAroundTime() + "\t\t\t" + averageWaitTime() + "\n";
        return output;
    }

    //prints output in format
    public String toString(String algorithm){
        String output = algorithm + ":\n";
        for(int i = 0; i < processOrder.size(); i++){
            output += "T"+ processStartTimes.get(i) + ": p" + processOrder.get(i) + "(" + processPriority.get(i) + ")\n";
        }
        output += "\n";
        output += "Process Turnaround Time Waiting Time\n";
        for(int i = 0; i < processes.size(); i++){
            output += processes.get(i) + "\t" + processTurnAroundTimes.get(processes.get(i).getPid()) + "\t\t" + processWaitTimes.get(processes.get(i).getPid()) + "\n";
        }
        return output;
    }

}