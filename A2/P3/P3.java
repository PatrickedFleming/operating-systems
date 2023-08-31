//Problem 3 of Assignment 2
//Author: Patrick Fleming
//Student Number: c3253586
//date: 31/08/2023

import java.io.File;
import java.util.logging.*;
import static java.util.logging.Level.*;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.HashMap;
import java.util.ArrayList;

public class P3 {

    private static final Logger outputLog = Logger.getLogger("OutputLog");
    private static HashMap<String, Integer> taskList = new HashMap<String, Integer>();
    private static ArrayList<Scheduler> schedulers = new ArrayList<Scheduler>();
    private static CountDownLatch startRace = new CountDownLatch(1);
    
    public static void main(String[] args) {
        //set up logger
        Handler handler = new ConsoleHandler();
        handler.setLevel(FINEST);
        handler.setFormatter(new ClusterManagementFormat());
        outputLog.addHandler(handler);
        outputLog.setLevel(FINEST);
        outputLog.setUseParentHandlers(false);

        //gets file
        File input = new File(args[0]);
        //gets tasks from file
        getTasks(input);
        //get schedulers
        getSchedulers();
        //start schedulers
        outputLog.log(FINEST, "Server Initialised...");
        startThreads(schedulers);
        //wait for schedulers to finish
        joinThreads(schedulers);
        outputLog.log(FINEST, "All Files Proccesssed.");
        
    }

    private static void getTasks(File file){
        try{
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                String[] line = sc.nextLine().split(" ");
                taskList.put(line[0], Integer.parseInt(line[2]));
            }
            sc.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void getSchedulers(){
        for(String key : taskList.keySet()){
            schedulers.add(new Scheduler(key, taskList.get(key)));
        }
    }

    public static void startThreads(ArrayList<? extends Thread> threads){
        for(Thread t : threads){
            t.start();
        }
    }

    public static void joinThreads(ArrayList<? extends Thread> threads){
        for(Thread t : threads){
            try{
                t.join();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
