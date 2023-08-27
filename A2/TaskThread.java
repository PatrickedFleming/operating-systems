//Tasks to be run in processor
//Author: Patrick Fleming
//Student Number: c3253586
//date: 27/08/2023

import java.util.logging.*;
import static java.util.logging.Level.*;

import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.Date;

public class TaskThread extends Thread {

    private static Logger taskLog = Logger.getLogger("OutputLog");
    private Semaphore taskSem;
    private CountDownLatch latch;

    //thread info
    private int threadNumber;
    private Integer input;
    private String taskOwner;

    
    //output formatting
    private String dateTimeFormate = "EEE MMM dd HH:mm:ss zzz yyyy";
    private SimpleDateFormat formatter = new SimpleDateFormat(dateTimeFormate);
    private Date day = new Date();


    public TaskThread(int threadNumber, Integer input, String taskOwner, CountDownLatch latch){
        this.threadNumber = threadNumber;
        this.input = input;
        this.taskOwner = taskOwner;
        this.latch = latch;
    }

    @Override
    public void run(){
        try{
            latch.await();
            taskSem.acquire();
            int output = input * input;
            sleep(input*100);
            taskLog.log(FINEST, "Time: "+ formatter.format(day) + ", Task: " + taskOwner + ", Thread No: "+ threadNumber +", Input: " + input + ", Result: " + output);
            taskSem.release();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    
    }

    public String getTaskOwner(){
        return taskOwner;
    }

    public void setSem(Semaphore sem){
        taskSem = sem;
    }
}
