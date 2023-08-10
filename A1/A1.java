//Assignment 1
//Author: Patrick Fleming
//Student Number: c3253586
//date: 2023-08-06

import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class A1 {

    private static int DISP = -1;
    private static ArrayList<ProcessInfo> processes = new ArrayList<ProcessInfo>();

    public static void main(String[] args) throws Exception {
        //gets input file
        File inputFile = new File(args[0]);
        //inputs processes from file
        inputProcesses(inputFile);
        
        //creates new arraylist of processes for algorithms to use
        ArrayList<ProcessInfo> fcfsProcesses = copyArray(processes);
        ArrayList<ProcessInfo> spnProcesses = copyArray(processes);
        ArrayList<ProcessInfo> ppProcesses = copyArray(processes);
        ArrayList<ProcessInfo> prrProcesses = copyArray(processes);

        //creates sheduling algorithm classes
        FCFS fcfs = new FCFS(processes);
        SPN spn = new SPN(processes);
        PP pp = new PP(processes);
        PRR prr = new PRR(processes);

        //runs scheduling algorithms
        //orders processes by arrival time
        fcfsProcesses.sort((ProcessInfo p1, ProcessInfo p2) -> p1.getArrivalTime() - p2.getArrivalTime());
        fcfs.run(fcfsProcesses, DISP);

        //orders processes by custom comparator
        spnProcesses.sort(new SPNComparator());
        spn.run(spnProcesses, DISP);

        ppProcesses.sort(new PPComparator());
        pp.run(ppProcesses, DISP);

        //order by arival time
        prrProcesses.sort((ProcessInfo p1, ProcessInfo p2) -> p1.getArrivalTime() - p2.getArrivalTime());
        prr.run(prrProcesses, DISP);

        SummaryOutput sum = new SummaryOutput(fcfs.getOutput(), spn.getOutput(), pp.getOutput(), prr.getOutput());

        //prints output
        System.out.println(fcfs);
        System.out.println(spn);
        System.out.println(pp);
        System.out.println(prr);
        System.out.println(sum);
    }

    private static void inputProcesses(File inputFile){
        try(Scanner sc = new Scanner(inputFile);){
        ArrayList<Integer> tempList = new ArrayList<Integer>();
        //while it has next line
        while(sc.hasNextLine()){
            //if it contains a number add it to the list
            if(sc.hasNextInt()){
                tempList.add(sc.nextInt());
            }
            //if it contains a string skip it
            else{
                sc.next();
            }

        }
        DISP = tempList.get(0);
        //create process objects
        int pid = 1;
        for(int i = 1; i < tempList.size(); i+=3){
           ProcessInfo tempProcess = new ProcessInfo(pid, tempList.get(i), tempList.get(i+1), tempList.get(i+2), tempList.get(i+1));
           pid++;
           processes.add(tempProcess);
        }
        sc.close();
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
    }

    //copys arraylist
    public static ArrayList<ProcessInfo> copyArray(ArrayList<ProcessInfo> input){
        ArrayList<ProcessInfo> output = new ArrayList<ProcessInfo>();
        for(int i = 0; i < input.size(); i++){
            output.add(new ProcessInfo(input.get(i).getPid(), input.get(i).getArrivalTime(), input.get(i).getServiceTime(), input.get(i).getPriority(), input.get(i).getCurrServiceTime()));
        }
        return output;
    }
}