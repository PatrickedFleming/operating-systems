//p3 scheduler using monitor
//Author: Patrick Fleming
//Student Number: c3253586
//date: 27/08/2023

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

import java.util.ArrayList;
import java.util.HashMap;


public class ClusterManagementMonitor{
    private static Logger status = Logger.getLogger("P3outPut");

    //holds all the info from files needed to run the program
    private static ArrayList<TaskThread> taskThreads = new ArrayList<TaskThread>();
    private static HashMap<String, Semaphore> taskProcessors = new HashMap<String, Semaphore>();
    private HashMap<String, Integer> taskList = new HashMap<String, Integer>();
  

    //constructor
    public ClusterManagementMonitor(HashMap<String, Integer> tasks){
        this.taskList = tasks;
    }


    private void getTaskThreads(String fileName){
        try(Scanner sc = new Scanner(new File(fileName))){
            int i = 0;
            while(sc.hasNextLine()){
                taskThreads.add(new TaskThread(i, Integer.parseInt(sc.nextLine()), fileName, latch));
                i++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    private void getTaskProcessors(String taskName,int processors){
        taskProcessors.put(taskName,new Semaphore(processors));
    }


}
