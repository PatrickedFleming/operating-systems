public class Page {
    private int id;
    private boolean assigned = false;
    private Process parent;
    private Frame frame;

    public Page(Process parent, int id){
        this.parent = parent;
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }   

    public boolean isAssigned(){
        return assigned;
    }

    public void assign(Frame frame){
        this.frame = frame;
        assigned = true;
    }

    public Frame getFrame(){
        return frame;
    }

    public void unassign(){
        this.frame = null;
        assigned = false;
    }

    public Process getParent(){
        return parent;
    }
    
}
