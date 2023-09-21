
import java.util.LinkedList;
import java.util.HashMap;

public class Process {
    private String name;
    private int pages = 0;
    private HashMap<Integer, Page> pageList = new HashMap<Integer, Page>();


    private int turnarroundtime = 0;
    private int faults = 0;
    private LinkedList<Integer> faultsList = new LinkedList<Integer>();


    public void addPage(){
        pages++;
    }

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

    public void addFault(Integer fault){
        faultsList.add(fault);
        faults++;
    }

    public void addPage(Page page){
        pageList.put(pages,page);
    }

    public Page getPage(int index){
        return pageList.get(index);
    }

    public boolean isPageAssigned(int index){
        return pageList.get(index).isAssigned();
    }


    
}
