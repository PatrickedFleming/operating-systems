public class IO {
    private Frame[] frames;
    private int clock = 0;  

    public IO(int frames){
        this.frames = new Frame[frames];
        for(int i = 0; i < frames; i++){
            this.frames[i] = new Frame();
        }
    }

    public boolean search(Page pg){
        for(Frame f: frames){
            if(!f.isFree()){
                if(f.getInstruction().equals(pg)){
                    return true;
                }
            }
            
        }
        return false;
    }

    public boolean search(int start, int end, Page pg){
        for(int i = start; i < end; i++){
            if(!frames[i].isFree()){
                if(frames[i].getInstruction().equals(pg)){
                    return true;
                }
            }
            
        }
        return false;
    }

    public int load(int start, int end, int count, Page pg){
        if(!search(start, end,  pg)){
            if(free(start, end)){
                for(int i = start; i < end; i++){
                    if(frames[i].isFree()){
                        frames[i].load(pg);
                        return count;
                    }
                }
            }
            else{
                frames[count].load(pg);
                if(count + 1 == end){
                    return start;
                }
                else{
                    return count + 1;
                }
            }
            return count;
        } else {
            return count;
        }
    }


    public void load(Page pg){
        if(!search(pg)){
            if(free()){
                for(Frame f: frames){
                    if(f.isFree()){
                        f.load(pg);
                        break;
                    }
                }
            }
            else{
                frames[clock].load(pg);
                if(clock == frames.length - 1){
                    clock = 0;
                }
                else{
                    clock++;
                }
            }
        }
        
    }

    private boolean free(){
        for(Frame f: frames){
            if(f.isFree()){
                return true;
            }
        }
        return false;
    }

    private boolean free(int start,int end){
        for(int i = start; i < end; i++){
            if(frames[i].isFree()){
                return true;
            }
        }
        return false;
    }

    public void reset(){
        for(Frame f: frames){
            f.unload();
        }
    } 
    
}
