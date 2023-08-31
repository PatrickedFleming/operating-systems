//Tasks to be run in processor
//Author: Patrick Fleming
//Student Number: c3253586
//date: 27/08/2023

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import static java.util.logging.Level.FINEST;


public class TaskThread extends Thread {
    //thread info
    private int threadNumber;
    private Integer input;
    private String taskOwner;

    //output formatting
    private String dateTimeFormate = "EEE MMM dd HH:mm:ss zzz yyyy";
    private SimpleDateFormat formatter = new SimpleDateFormat(dateTimeFormate);
    private Date day = new Date();

    //logger
    private static Logger taskLog = Logger.getLogger("OutputLog");

    //scheduler
    private Scheduler scheduler;



    public TaskThread(int threadNumber, Integer input, String taskOwner, Scheduler taskScheduler){
        this.threadNumber = threadNumber;
        this.input = input;
        this.taskOwner = taskOwner;
        this.scheduler = taskScheduler;
    }

    @Override
    public void run(){
        try{
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
