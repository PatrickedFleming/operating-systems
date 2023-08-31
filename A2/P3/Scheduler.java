//Scheduler class for P2 assignment 2
//Author: Patrick Fleming
//Student Number: c3253586
//date: 27/08/2023

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Scheduler extends Thread{

    private String fileName;
    private ArrayList<TaskThread> taskThreads = new ArrayList<TaskThread>(); //list of task threads
    private int numberProcessors; //number of processors available


    //constructor
    public Scheduler(String fileName, int processors){
        this.fileName = fileName;
        numberProcessors = processors;
    }

    @Override
    public void run(){
        getTaskThreads(fileName);
        P3.startThreads(taskThreads);
        P3.joinThreads(taskThreads);
    }

    private void getTaskThreads(String fileName){
        try(Scanner sc = new Scanner(new File(fileName))){
            int i = 0;
            while(sc.hasNextLine()){
                taskThreads.add(new TaskThread(i, Integer.parseInt(sc.nextLine()), fileName, this));
                i++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public synchronized void waitForProcessor(){
        if(numberProcessors > 0){
            numberProcessors--;

        }
        else{
            try{
                wait();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public synchronized void releaseProcessor(){
        numberProcessors++;
        notify();
    }
}
