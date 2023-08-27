//threads for p3 
//Author: Patrick Fleming
//Student Number: c3253586
//date: 27/08/2023


public class MonitorThread extends Thread{

    private SyncronisedCompute proccessors;

    //thread info
    private int threadNumber;
    private Integer input;
    private String taskOwner;

    @Override
    public void run(){
        try{
            proccessors.Compute(input, taskOwner, threadNumber);
            sleep(input*100);
            proccessors.log(taskOwner, threadNumber, input);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
