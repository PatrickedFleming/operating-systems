public class Page {
    private String pageID;
    private boolean loaded = false;
    private boolean finished = false;


    public Page(String page){
        this.pageID = page;
    }

    public String getPageID(){
        return pageID;
    }

    public void setPageID(String pageID){
        this.pageID = pageID;
    }

    public boolean isLoaded(){
        return loaded;
    }

    public void setLoaded(boolean loaded){
        this.loaded = loaded;
    }

    public boolean isFinished(){
        return finished;
    }

    public void setFinished(boolean finished){
        this.finished = finished;
    }

}
