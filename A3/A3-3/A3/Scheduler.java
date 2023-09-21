
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;

public class Scheduler {
    private int Quantum;
    private int Time;
    private int FrameNumber;
    private int allocatedFrames = 0;
    private int dispatchTime = 6;

    private LinkedList<Process> readyQueue = new LinkedList<Process>();
    private LinkedList<Process> blockedQueue = new LinkedList<Process>();

    private boolean finished = false;
    private HashMap<Integer, PageFrame> frames = new HashMap<Integer, PageFrame>();

    private LinkedList<Process> processes;

    private Process currentProcess;


    public Scheduler(int quantum, int frames, LinkedList<Process> processes){
        this.Quantum = quantum;
        this.FrameNumber = frames;
        this.processes = processes;
        start();
    }


    public void start(){
        SetupFrames();
        LoadProcesses();
        for(Time = 0; Time <40; Time++){
            unBlock();
            getCurrentProcess();
            run();
            checkFinished();
        }
    }

    private void getCurrentProcess(){
        if(currentProcess == null){
            if(!readyQueue.isEmpty()){
                currentProcess = readyQueue.getFirst();
                currentProcess.setNextTask();
            }
        }
        else{
            if(currentProcess.isFinished()){
                currentProcess = null;
                readyQueue.remove(currentProcess);
            }
        }
    }

    private void doTask(){
        if(currentProcess == null){
            return;
        }
        else{
            currentProcess.setTaskDone();
        }
    }

    private void run(){
        if(!isReady()){
            block();
        }
        else{
            doTask();
        }
    }

    private void block(){
        if(currentProcess != null){
            blockedQueue.add(currentProcess);
            currentProcess.setTimeUnblocked(Time + dispatchTime);
            currentProcess.addFault(Time);
            if(!readyQueue.isEmpty()){
                currentProcess = readyQueue.poll();
                run();
            }
            else{
                currentProcess = null;
            }
        }
    }

    private void unBlock(){
        if(!blockedQueue.isEmpty()){
            for(int i = 0; i < blockedQueue.size(); i++){
                Process p = blockedQueue.get(i);
                if(p.getTimeUnblocked() == Time && !p.isFinished()){
                    p.loadPage(p.getId() * allocatedFrames, (p.getId() + 1) * allocatedFrames);
                    readyQueue.add(p);
                    blockedQueue.remove(p);
                }
            }
        }
    }



    private void checkFinished(){
        boolean check = true;
        for(Process p: processes){
            if(!p.isFinished()){
                check = false;
            }
        }
        if(check){
            finished = true;
        } 
    }


    private void LoadProcesses(){
        int count = 0;
        for(Process p: processes){
            readyQueue.add(p);
            p.setId(count);
            p.setFrameMap(frames);
            count++;
        }
    }

     private void SetupFrames(){
        for(int i = 0; i < FrameNumber; i++){
            frames.put(i, new PageFrame());
        }  
        allocatedFrames = 0;
        allocatedFrames = (int)Math.floor(FrameNumber/processes.size());
        for(Process p: processes){
            p.setFrames(allocatedFrames);
        }
    }   
    

    public void printResults(){
        System.out.println("Clock - Fixed-Local Replacement:");
        System.out.println("PID  \tProcess-Name  \tTurnaround \t# Faults \tFault Times");
        for(Process p: processes){
            System.out.println(p.getId() + "\t" + p.getName() + "\t\t" + p.getTurnaroundTime() + "\t\t" + p.getFaults() + "\t\t" + p.getFaultTimes());
        }
    }

    private boolean isReady(){
        if(currentProcess == null){
            return true;
        }
        else{
            if(currentProcess.isLoaded()){
                return true;
            }
            else{
                return false;
            }
        }
        
    }
    
}
