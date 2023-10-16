//IO class used to handle the loading of pages into frames
//Author: Patrick Fleming
//Student Number: C3253586
//Date: 17/10/23
public class IO {
    private Frame[] frames;
    private int clock = 0;  

    public IO(int frames){
        this.frames = new Frame[frames];
        for(int i = 0; i < frames; i++){
            this.frames[i] = new Frame();
        }
    }

    /**
     * Searches the frames for the page
     * 
     * @param pg the page to be searched for
     * @return true if the page is in the frames false otherwise
     */
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

    /**
     * Searches the frames for the page
     * 
     * @param start the start of the search
     * @param end the end of the search
     * @param pg the page to be searched for
     * @return true if the page is in the frames false otherwise
     */
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

    /**
     * Loads the page into the frames
     * 
     * @param start the start of the search
     * @param end the end of the search
     * @param count the current index of the clock
     * @param pg the page to be loaded
     * @return the index of the frame the page was loaded into
     */
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


    /**
     * Loads the page into the frames
     * 
     * @param pg the page to be loaded
     */
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

    /**
     * Checks if there is a free frame
     * 
     * @return true if there is a free frame false otherwise
     */
    private boolean free(){
        for(Frame f: frames){
            if(f.isFree()){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if there is a free frame
     * 
     * @param start the start of the search
     * @param end the end of the search
     * @return true if there is a free frame false otherwise
     */
    private boolean free(int start,int end){
        for(int i = start; i < end; i++){
            if(frames[i].isFree()){
                return true;
            }
        }
        return false;
    }

    /**
     * Unloads all pages from the frames
     * 
     */
    public void reset(){
        for(Frame f: frames){
            f.unload();
        }
    } 
    
}
