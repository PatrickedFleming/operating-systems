//syncronised compute class
//Author: Patrick Fleming
//Student Number: c3253586
//date: 27/08/2023

import java.util.logging.Logger;
import static java.util.logging.Level.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SyncronisedCompute {

    private static Logger syncLog = Logger.getLogger("P3outPut");
    private int output;
    
    //output formatting
    private String dateTimeFormate = "EEE MMM dd HH:mm:ss zzz yyyy";
    private SimpleDateFormat formatter = new SimpleDateFormat(dateTimeFormate);
    private Date day = new Date();

    public synchronized void Compute(int input, String taskOwner, int threadNumber){
        output = input * input;
    }

    public synchronized void log(String taskOwner, int threadNumber, int input){
        syncLog.log(FINEST, "Time: "+ formatter.format(day) + ", Task: " + taskOwner + ", Thread No: "+ threadNumber +", Input: " + input + ", Result: " + output);
    }
}
