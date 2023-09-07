//Tasks to be run in processor
//Author: Patrick Fleming
//Student Number: c3253586
//date: 27/08/2023

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;
import static java.util.logging.Level.FINEST;


public class P3TaskThread extends Thread {
    //thread info
    private int threadNumber;
    private Integer input;
    private String taskOwner;
    private static CountDownLatch goButton = P3.getStartRace();

    //output formatting
    private String dateTimeFormate = "EEE MMM dd HH:mm:ss zzz yyyy";
    private SimpleDateFormat formatter = new SimpleDateFormat(dateTimeFormate);
    private Date day = new Date();

    //logger
    private static Logger taskLog = Logger.getLogger("OutputLog");

    //scheduler
    private P3Scheduler scheduler;



    public P3TaskThread(int threadNumber, Integer input, String taskOwner, P3Scheduler taskScheduler){
        this.threadNumber = threadNumber;
        this.input = input;
        this.taskOwner = taskOwner;
        this.scheduler = taskScheduler;
    }

    @Override
    public void run(){
        try{
            goButton.await();
            scheduler.waitForProcessor();
            int output = input * input;
            Thread.sleep(input*100);
            taskLog.log(FINEST, "Time: "+ formatter.format(day) + ", Task: " + taskOwner + ", Thread No: "+ threadNumber +", Input: " + input + ", Result: " + output);
            scheduler.releaseProcessor();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
