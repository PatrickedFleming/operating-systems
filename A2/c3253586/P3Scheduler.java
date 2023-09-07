//Scheduler class for P2 assignment 2
//Author: Patrick Fleming
//Student Number: c3253586
//date: 27/08/2023

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class P3Scheduler extends Thread{

    private String fileName;
    private ArrayList<P3TaskThread> taskThreads = new ArrayList<P3TaskThread>(); //list of task threads
    private int numberProcessors; //number of processors available


    //constructor
    public P3Scheduler(String fileName, int processors){
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
                taskThreads.add(new P3TaskThread(i, Integer.parseInt(sc.nextLine()), fileName, this));
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
