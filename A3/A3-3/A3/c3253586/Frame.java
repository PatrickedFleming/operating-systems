//Frame class used to hold the page and whether it is free or not
//Author: Patrick Fleming
//Student Number: C3253586
//Date: 17/10/23
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
