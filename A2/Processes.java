//processes alocated to tasks
//Author: Patrick Fleming
//Student Number: c3253586
//date: 27/08/2023

public class Processes extends Thread {

    private String taskName;
    private SyncronisedCompute[] proccessors;

    public Processes(String task, Integer processors){
        this.taskName = task;
        proccessors = new SyncronisedCompute[processors];
        for(int i = 0; i < processors; i++){
            proccessors[i] = new SyncronisedCompute();
        }
    }
    
    @Override
    public void run(){
        
    }
}
