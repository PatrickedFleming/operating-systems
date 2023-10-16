//Schedular class used to handle the scheduling of processes 
//Author: Patrick Fleming
//Student Number: C3253586
//Date: 17/10/23
import java.util.LinkedList;
import java.util.HashMap;

public class Scheduler {
    private LinkedList<Process> processes;
    private int quantum;
    private int frames;
    private int alocatedframes;
    private int time = 0;
    private IO io;

    private Process current;

    private LinkedList<Process> ready = new LinkedList<Process>();
    private LinkedList<Process> blocked = new LinkedList<Process>();
    private LinkedList<Process> finished = new LinkedList<Process>();

    private HashMap<Integer,ProcessData> Globaldata = new HashMap<Integer,ProcessData>();
    private HashMap<Integer,ProcessData> Localdata = new HashMap<Integer,ProcessData>();

    private String localoutput = "Clock - Fixed-Local Replacement:\nPID \tProcess-Name \tTurnaround \t# Faults \tFault-Times";
    private String globaloutput = "Clock - Variable-Global Replacement:\nPID \tProcess-Name \tTurnaround \t# Faults \tFault-Times";


    /**
     * Creates a scheduler with the processes, quantum and frames
     * 
     * @param processes the processes to be scheduled
     * @param q the quantum
     * @param p the number of frames
     */
    public Scheduler(LinkedList<Process> processes, int q, int p){
        this.processes = processes;
        this.quantum = q;
        this.frames = p;
        this.alocatedframes = p/processes.size();
        this.io = new IO(p);
    }

    /**
     * Starts the scheduler
     */
    public void start(){
        for(Process p: processes){
            p.setQuantum(quantum);
            p.setId(processes.indexOf(p));
            Localdata.put(p.getId(),new ProcessData(p.getId(), p));
            p.setCount(alocatedframes*p.getId());
            ready.add(p);
        }
        current = ready.removeFirst();
        local();
        System.out.println(localoutput);
        for(ProcessData p: Localdata.values()){
            System.out.println(p);
        }
        reset();
        global();
        System.out.println(globaloutput);
        for(ProcessData p: Globaldata.values()){
            System.out.println(p);
        }
    }

    /**
     * Resets the scheduler
     */
    public void reset(){
        io.reset();
        blocked.clear();
        ready.clear();
        finished.clear();
        for(Process p: processes){
            p.reset();
            p.setQuantum(quantum);
            ready.add(p);
            Globaldata.put(p.getId(),new ProcessData(p.getId(), p));
        }
        current = ready.removeFirst();
    }

    /**
     * Runs the local replacement scheduler
     */
    public void local(){
        for(time = 0; finished.size() != processes.size(); time++){
            unblockLocal();
            runLocal();
            
        }
    }

    /**
     * Unblocks the processes that are ready for local replacement
     */
    private void unblockLocal(){
        for(Process p: blocked){
            if(p.unblock(time)){
                p.setCount(io.load((p.getId()*(alocatedframes)), ((p.getId()*(alocatedframes))+ alocatedframes), p.getCount(),p.getPage()));
                ready.add(p);
            }
        }
        for(Process p: ready){
            if(blocked.contains(p)){
                blocked.remove(p);
            }
        }
    }

    /**
     * Runs the global replacement scheduler
     */
    public void global(){
        for(time = 0;finished.size() != processes.size() ; time++){
            unblock();
            runGlobal();
        }
    }

    /**
     * Unblocks the processes that are ready for global replacement
     */
    private void unblock(){
        for(Process p: blocked){
            if(p.unblock(time)){
                io.load(p.getPage());
                ready.add(p);
            }
        }
        for(Process p: ready){
            if(blocked.contains(p)){
                blocked.remove(p);
            }
        }
    }

    /**
     * Handles the fault for global replacement
     */
    private void faultGlobal(){
        current.setUnblockTime(time + 5);
        Globaldata.get(current.getId()).fault(time);
        current.setQuantum(quantum);
        blocked.add(current);
        if(!ready.isEmpty()){
            current = ready.removeFirst();
        }
        else{
            current = null;
        }
    }

     private void faultlocal(){
        current.setUnblockTime(time + 5);
        Localdata.get(current.getId()).fault(time);
        current.setQuantum(quantum);
        blocked.add(current);
        if(!ready.isEmpty()){
            current = ready.removeFirst();
        }
        else{
            current = null;
        }
    }

    /**
     * Runs the local process
     */
    private void runLocal(){
        if(current != null){
            if(current.getPage().getPageID().equalsIgnoreCase("end")){
                current.setfinish(true);
                finishLocal(0);
                if(!ready.isEmpty()){
                    current = ready.removeFirst();
                }
                else{
                    current = null;
                }
                runLocal();
            }
            else{
                    if(current.getQuantum() > 0){
                        if(LoadedLocal()){
                            current.setQuantum(current.getQuantum() - 1);
                            current.getNextLocal();
                            
                        }
                        else{
                            faultlocal();
                            runLocal();
                        }
                    }
                    else{
                        if(current.getQuantum() == 0){
                            if(LoadedLocal()){
                                current.setQuantum(quantum);
                                ready.add(current);
                                current = null;
                                runLocal(); 
                            }
                            else{
                                faultlocal();
                                runLocal();
                            }
                        }
                    }
                }
            }
            else{
                if(!ready.isEmpty()){
                    current = ready.removeFirst();
                    runLocal();
                }
                else{
                    current = null;
                }
            }
       
    }

    /**
     * Runs the global process
     */
    private void runGlobal(){
        if(current != null){
            if(current.getPage().getPageID().equalsIgnoreCase("end")){
                current.setfinish(true);
                finishGlobal(0);
                if(!ready.isEmpty()){
                    current = ready.removeFirst();
                }
                else{
                    current = null;
                }
                runGlobal();
            }
            else{
                if(current.getQuantum() > 0){
                    if(LoadedGlobal()){
                        current.setQuantum(current.getQuantum() - 1);
                        current.getNextGlobal();
                    }
                    else{
                        faultGlobal();
                        runGlobal();
                    }
                }
                else{
                    if(current.getQuantum() == 0){
                            if(LoadedGlobal()){
                                current.setQuantum(quantum);
                                ready.add(current);
                                current = null;
                                runGlobal();
                            }
                            else{
                                faultGlobal();
                                runGlobal();
                            }
                        }
                }
            }
        }
        else{
            if(!ready.isEmpty()){
                current = ready.removeFirst();
                runGlobal();
            }
            else{
                current = null;
            }
        }
       
    }

    /**
     * Finishes the process for local replacement
     * 
     * @param ofset the ofset for the turnaround time
     */
    private void finishLocal(int ofset){
        if(current != null){
            if(current.getPage().getPageID().equalsIgnoreCase("end")){
                finished.add(current);
                Localdata.get(current.getId()).setTurnarroundTime(time + ofset);
                current = null;
            }
        }

    }

    /**
     * Finishes the process for global replacement
     * 
     * @param ofset the ofset for the turnaround time
     */
    private void finishGlobal(int ofset){
        if(current != null){
            if(current.getPage().getPageID().equalsIgnoreCase("end")){
                finished.add(current);
                Globaldata.get(current.getId()).setTurnarroundTime(time + ofset);
                current = null;
            }
        }
    }

    /**
     * Checks if the page is loaded for global replacement
     * 
     * @return true if the page is loaded
     */
    private boolean LoadedGlobal(){
        if(current != null){
            if(io.search(current.getPage())){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
        
    }

    /**
     * Checks if the page is loaded for local replacement
     * 
     * @return true if the page is loaded
     */
    private boolean LoadedLocal(){
        if(current != null){
            if(io.search((current.getId()*(alocatedframes)), ((current.getId()*(alocatedframes))+ (alocatedframes)), current.getPage())){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
        
    }
  
}
