//Assignment 1 Operating Systems
//Author: Patrick Fleming
//Student Number: c3253586
//date: 2023-08-23

import java.io.File;
import java.util.Scanner;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.logging.ConsoleHandler;


import static java.util.logging.Level.*;

public class A1 {


    private static Logger logger = Logger.getLogger(A1.class.getName());
    {
        logger.setLevel(ALL);
        Handler hand = new ConsoleHandler();
        hand.setLevel(ALL);
        logger.addHandler(hand);
        logger.setUseParentHandlers(false);
        
    }
    private static int DISP = -1;
    private static final ProccessList<ProcessInfo> processes = new ProccessList<ProcessInfo>();

    public static void main(String[] args) {
        //gets input file
        File inputFile = new File(args[0]);
        //inputs processes from file
        inputProcesses(inputFile);

        //create schedulars
        FCFS fcfs = new FCFS(processes, DISP);
        SPN spn = new SPN(processes, DISP);
        PP pp = new PP(processes, DISP);
        PRR ppr = new PRR(processes, DISP);

        //run schedulars
        processes.readyProccesses();
        fcfs.run();
        
        processes.readyProccesses();
        spn.run();
        processes.readyProccesses();
        pp.run();
        processes.readyProccesses();
        ppr.run();
        processes.readyProccesses();
        //print results
        System.out.println(fcfs);
        System.out.println(spn);
        System.out.println(pp);
        System.out.println(ppr);

        System.out.println("Summary\nAlgorithm\tAverage Waiting Time\tAverage Turnaround Time");
        System.out.println(fcfs.getSum());
        System.out.println(spn.getSum());
        System.out.println(pp.getSum());
        System.out.println(ppr.getSum());

    }

    //gets process from file and adds them to processes
     private static void inputProcesses(File inputFile){
        try(Scanner sc = new Scanner(inputFile);){
            //create patterns
            Pattern dispPattern = Pattern.compile("DISP: ");
            Pattern pidPattern = Pattern.compile("PID: p");
            //while it has next line
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                //if line is disp
                if(dispPattern.matcher(line).find()){
                    //set disp
                    DISP = Integer.parseInt(line.substring(6));
                    checkDisp(DISP);
                }
                //if line is pid
                else if(pidPattern.matcher(line).find()){
                    //create new process
                    int pid = Integer.parseInt(line.substring(6));
                    int arivalTime = Integer.parseInt(sc.nextLine().substring(9));
                    int serviceTime = Integer.parseInt(sc.nextLine().substring(9));
                    int priority = Integer.parseInt(sc.nextLine().substring(10));
                    //add process to processes
                    processes.add(new ProcessInfo(pid, arivalTime, serviceTime, priority));
                }
            }
            sc.close();
            checkProccesses(processes);
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
    }

    private static void checkDisp(Integer disp){
        if(disp < 0){
            logger.log(WARNING, "DISP is less than 0");
        }
    }

    private static void checkProccesses(ProccessList<ProcessInfo> processes){
        if(processes.size() == 0){
            logger.log(WARNING, "No processes");
        }
        ProcessInfo curr = null;
        for(ProcessInfo entry: processes){
            
            if(entry.getArrivalTime() < 0){
                logger.log(WARNING, "Process " + entry.getPidString() + " has an arrival time less than 0");
            }
            if(entry.getServiceTime() < 0){
                logger.log(WARNING, "Process " + entry.getPidString() + " has a service time less than 0");
            }
            //priority must be 0-5
            if(entry.getPriority() > 5 || entry.getPriority() < 0){
                logger.log(WARNING, "Process " + entry.getPidString() + " priority is not between 0-5");
            }
            if(curr != null){
                if(entry.getArrivalTime() < curr.getArrivalTime()){
                    logger.log(WARNING, "Process " + entry.getPidString() + " has an arrival time less than the previous process");
                }
                if(entry.getPid() < curr.getPid()){
                    logger.log(WARNING, "Process " + curr.getPidString() + " has a pid greater than the next process "+ entry.getPidString());
                }
            }
            curr = entry;
        }
    }
}