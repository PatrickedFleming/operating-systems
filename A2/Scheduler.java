//Scheduler class for P2 assignment 2
//Author: Patrick Fleming
//Student Number: c3253586
//date: 27/08/2023

import java.util.logging.Logger;
import static java.util.logging.Level.FINEST;
import java.util.concurrent.Semaphore;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.concurrent.CountDownLatch;

public class Scheduler extends Thread{

    private static Logger status = Logger.getLogger("OutputLog");

    //countdown latch to start all threads at once
    private static CountDownLatch latch = new CountDownLatch(1);

    //holds all the info from files needed to run the program
    private HashMap<String, Integer> tasksList;
    private static ArrayList<TaskThread> taskThreads = new ArrayList<TaskThread>();
    private static HashMap<String, Semaphore> taskProcessors = new HashMap<String, Semaphore>();

    //constructor
    public Scheduler(HashMap<String, Integer> tasksList){
        this.tasksList = tasksList;
    }

    @Override
    public void run(){
        for(Entry<String, Integer> task : tasksList.entrySet()){
            getTaskProcessors(task.getKey(), task.getValue());
            getTaskThreads(task.getKey());
        }
        for(TaskThread task : taskThreads){
            task.setSem(taskProcessors.get(task.getTaskOwner()));
        }
        status.log(FINEST, "Server initialised....");
        for(TaskThread task : taskThreads){
            task.start();
        }
        latch.countDown();
        for(TaskThread task : taskThreads){
            try{
                task.join();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        status.log(FINEST, "All file processed.");
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
