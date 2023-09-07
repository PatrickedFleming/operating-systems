//Scheduler class for P2 assignment 2
//Author: Patrick Fleming
//Student Number: c3253586
//date: 27/08/2023

import java.util.logging.Logger;
import static java.util.logging.Level.FINEST;
import java.util.concurrent.Semaphore;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.concurrent.CountDownLatch;

public class Scheduler extends Thread{


    //countdown latch to start all threads at once
    private static CountDownLatch latch;

    private String fileName;
    private ArrayList<TaskThread> taskThreads = new ArrayList<TaskThread>(); //list of task threads
    private Semaphore processorSem;

    //constructor
    public Scheduler(String fileName, int processors, CountDownLatch cd){
        this.fileName = fileName;
        this.processorSem = new Semaphore(processors);
        latch = cd;
    }

    @Override
    public void run(){
        getTaskThreads(fileName);
        //start all task threads
        P2.startThreads(taskThreads);
        //wait for all task threads to finish
        P2.joinThreads(taskThreads);
    }

    private void getTaskThreads(String fileName){
        try(Scanner sc = new Scanner(new File(fileName))){
            int i = 0;
            while(sc.hasNextLine()){
                taskThreads.add(new TaskThread(i, Integer.parseInt(sc.nextLine()), fileName, latch, processorSem));
                i++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
