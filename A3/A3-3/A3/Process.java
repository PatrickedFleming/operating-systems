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

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }


   public boolean unblock(int time){
        if(time > unblockTime){
            return true;
        }
        return false;
    }
    
    public Page getPage(){
        return current;
    }

    public void setUnblockTime(int time){
        unblockTime = time;
    }

    public void setQuantum(int quantum){
        this.quantum = quantum;
    }  

    public int getQuantum(){
        return quantum;
    }

    public void getNextGlobal(){
        pageListGlobal.removeFirst();
        if(!pageListGlobal.isEmpty()){
            current = pageListGlobal.getFirst();
        }
        else{
            current = null;
        }
    }

    public void getNextLocal(){
        pageListLocal.removeFirst();
        if(!pageListLocal.isEmpty()){
            current = pageListLocal.getFirst();
        }
        else{
            current = null;
        }
    }
    public int getCount(){
        return count;
    }

    public void setCount(int count){
        this.count = count;
    }

    public boolean isFinish(){
        return finish;
    }

    public boolean equals(Process p){
        if(this.name.equals(p.getName()) && this.id == p.getId()){
            return true;
        }
        return false;
    }

    public void reset(){
        current = pageListGlobal.getFirst();
        count = 0;
        finish = false;
    }

    public void setfinish(boolean finish){
        this.finish = finish;
    }
}
