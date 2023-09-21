
import java.util.LinkedList;
import java.util.HashMap;


public class Process {
    private String name;
    private int id;
    private int pages = 0;
    private HashMap<Integer, Page> pageList = new HashMap<Integer, Page>();
    private HashMap<Integer, PageFrame> frameList;
    private int pageIterator = 0;

    private Page currentPage;
    private int count = 0;
    private boolean finished = false;



    private int timeUnblocked = 0;
    private int frames = 0;
    private int turnarroundtime = 0;
    private int faults = 0;
    private LinkedList<Integer> faultsList = new LinkedList<Integer>();

    public String getName(){
        return name;
    }

    public int getPages(){
        return pages;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPages(int pages){
        this.pages = pages;
    }
    

    public void addFault(Integer time){
        faultsList.add(time);
        faults++;
    }

    public void addPage(Page page){
        pageList.put(pages,page);
        pages++;
    }

    public Page getPage(int index){
        return pageList.get(index);
    }


    public void setFrames(int frames){
        this.frames = frames;
    }

    public void setCount(int count){
        this.count = count;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setFrameMap(HashMap<Integer, PageFrame> frameMap){
        this.frameList = frameMap;
    }

    public void setTimeUnblocked(int time){
        this.timeUnblocked = time;
    }

    public int getTimeUnblocked(){
        return timeUnblocked;
    }

    public int getTurnaroundTime(){
        return turnarroundtime;
    }

    public void setTurnaroundTime(int time){
        this.turnarroundtime = time;
    }

    public int getFaults(){
        return faults;
    }
    


    public boolean isLoaded(){
        if(frameList.containsValue(currentPage)){
            return true;
        }
        else{
            return false;
        }
    }

    public void loadPage(int x, int y){
        if(currentPage != null){
            for(int i = x; i < y; i++){
                if(frameList.get(i).isFree()){
                    frameList.get(i).load(currentPage);
                    break;
                }
            }
            frameList.get(count).unload();
            frameList.get(count).load(currentPage);
            if(count == y){
                count = x;
            }
            else{
                count++;
            }
        }
        
    }

    public void setTaskDone(){
        if(currentPage != null){
            currentPage.setFinished(true);
        }
    }


    public void setNextTask(){
        if(pageIterator < pages){
            currentPage = pageList.get(pageIterator);
            pageIterator++;
        }
        else{
            finished = true;
        }
    }

    public boolean isFinished(){
        return finished;
    }

    public String getFaultTimes(){
        String faultTimes = "{";
        for(Integer i: faultsList){
            faultTimes += i + ", ";
        }
        if(faultsList.size() > 0){
            faultTimes = faultTimes.substring(0, faultTimes.length() - 2);
        }
        faultTimes += "}";
        return faultTimes;
    }

    @Override
    public String toString(){
        return name;
    }
   
}
