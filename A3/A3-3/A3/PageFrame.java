public class PageFrame {
    private boolean free = true;
    private Page page;
    

    public void load(Page pg){
        this.page = pg;
        this.page.setLoaded(true);
        free = false;
    }

    public void unload(){
        this.page.setLoaded(false);
        free = true;
    }

    public boolean isFree(){
        return free;
    }


    
}
