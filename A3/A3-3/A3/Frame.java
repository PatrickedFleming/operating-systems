public class Frame {
    private boolean free = true;
    private Page page;
    

    public void load(Page pg){
        this.page = pg;
        free = false;
    }

    public void unload(){
        free = true;
    }

    public boolean isFree(){
        return free;
    }


    
}
