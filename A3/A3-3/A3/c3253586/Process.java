//Process class used to hold the process infomation
//Author: Patrick Fleming
//Student Number: C3253586
//Date: 17/10/23

import java.util.LinkedList;

public class Process {
    private String name;
    private int id;
    private int unblockTime = 0;
    private LinkedList<Page> pageListGlobal = new LinkedList<Page>();
    private LinkedList<Page> pageListLocal;
    private Page current;
    private int count = 0;
    private int quantum = 0;
    private boolean finish = false;

    /**
     * Creates a process with the name and the pages
     * 
     * @param name the name of the process
     * @param pagelist the pages of the process
     */
    public Process(String name, LinkedList<Page> pagelist){
        this.pageListLocal = pagelist;
        name = name.replaceAll(" ", "");
        this.name = name;
        current = pageListLocal.getFirst();
        for(Page p: pageListLocal){
            this.pageListGlobal.add(new Page(p.getPageID(), this));
            p.setParent(this);
        }
    }

    /**
     * Sets the id of the process
     * 
     * @param id
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * Returns the id of the process
     * 
     * @return the id of the process
     */
    public int getId(){
        return id;
    }

    /**
     * Sets the name of the process
     * 
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Returns the name of the process
     * 
     * @return the name of the process
     */
    public String getName(){
        return name;
    }

    /**
     * Returns is blocked or not
     * 
     * @param time
     * @return true if the process is not blocked false otherwise
     * 
     */
   public boolean unblock(int time){
        if(time > unblockTime){
            return true;
        }
        return false;
    }

    /**
     * Returns the current page
     * 
     * @return the current page
     */
    public Page getPage(){
        return current;
    }

    /**
     * sets the time the processes unblocks
     * 
     * @param time the time the process unblocks
     */
    public void setUnblockTime(int time){
        unblockTime = time;
    }

    /**
     * Sets the quantum of the process
     * 
     * @param quantum the quantum of the process
     */
    public void setQuantum(int quantum){
        this.quantum = quantum;
    }  

    /**
     * Returns the quantum of the process
     * 
     * @return the quantum of the process
     */
    public int getQuantum(){
        return quantum;
    }

    /**
     * sets the current page to the next page for global replacement
     */
    public void getNextGlobal(){
        pageListGlobal.removeFirst();
        if(!pageListGlobal.isEmpty()){
            current = pageListGlobal.getFirst();
        }
        else{
            current = null;
        }
    }

    /**
     * sets the current page to the next page for local replacement
     */
    public void getNextLocal(){
        pageListLocal.removeFirst();
        if(!pageListLocal.isEmpty()){
            current = pageListLocal.getFirst();
        }
        else{
            current = null;
        }
    }

    /**
     * returns the clock count
     * 
     * @return the clock count
     */
    public int getCount(){
        return count;
    }

    /**
     * sets the clock count
     * 
     * @param count the clock count
     */
    public void setCount(int count){
        this.count = count;
    }

    /**
     * Checks if process is finished
     * 
     * @return true if the process is finished false otherwise
     */
    public boolean isFinish(){
        return finish;
    }

    /**
     * Compares the process
     * 
     * @param p the process to compare to
     */
    public boolean equals(Process p){
        if(this.name.equals(p.getName()) && this.id == p.getId()){
            return true;
        }
        return false;
    }

    /**
     * Resets the process
     */
    public void reset(){
        current = pageListGlobal.getFirst();
        count = 0;
        finish = false;
    }

    /**
     * Sets the finish of the process
     * 
     * @param finish true if the process is finished false otherwise
     */
    public void setfinish(boolean finish){
        this.finish = finish;
    }
}
