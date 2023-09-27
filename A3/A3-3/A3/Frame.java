public class Frame {
    private boolean free = true;
    private Page instruction;
    

    public void load(Page pg){
        this.instruction = pg;
        free = false;
    }

    public void unload(){
        free = true;
    }

    public boolean isFree(){
        return free;
    }

    public Page getInstruction(){
        return instruction;
    }

        
}
