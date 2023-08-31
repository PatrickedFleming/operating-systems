//thread to process task files
//Author: Patrick Fleming
//Student Number: c3253586
//date: 31/08/2023


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.CountDownLatch;

public class FileThreads extends Thread{

    private String taskName;

    //shared resources
    private static ArrayList<TaskThread> taskThreads;
    public static Semaphore fileSem = new Semaphore(1);
    private static CountDownLatch latch;

    public FileThreads(String fileName, ArrayList<TaskThread> tasks, CountDownLatch cdLatch, Semaphore sem){
        this.taskName = fileName;
        taskThreads = tasks;
        latch = cdLatch;
        fileSem = sem;
    }

    @Override
    public void run(){
        getTaskThreads(taskName);
    }


    private void getTaskThreads(String fileName){
        try(Scanner sc = new Scanner(new File(fileName))){
            int i = 0;
            while(sc.hasNextLine()){
                try{
                    fileSem.acquire();
                    taskThreads.add(new TaskThread(i, Integer.parseInt(sc.nextLine()), fileName, latch));
                    fileSem.release();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                i++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    
}
