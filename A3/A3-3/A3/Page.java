public class Page {
    private String pageID;
    private int pageInstruction;
    private Process Parent;


    public Page(String page){
        String temp = page.replaceAll("[^0-9]", "");
        this.pageID = temp;
        if(page.contains("end")){
            this.pageInstruction = -1;
            this.pageID = "end";
        }
        else{
            this.pageInstruction = Integer.parseInt(temp);
        }
    }

    public Page(String page, Process parent){
        String temp = page.replaceAll("[^0-9]", "");
        this.pageID = temp;
        this.Parent = parent;
        if(page.contains("end")){
            this.pageInstruction = -1;
            this.pageID = "end";
        }
        else{
            this.pageInstruction = Integer.parseInt(temp);
        }
    }

    public String getPageID(){
        return pageID;
    }

    public void setPageID(String pageID){
        this.pageID = pageID;
    }

    public int getInstruction(){
        return pageInstruction;
    }

    public void setParent(Process p){
        Parent = p;
    }


    public Process getParent(){
        return Parent;
    }


    public boolean equals(Page p){
        if(this.pageInstruction == p.getInstruction() && this.Parent.equals(p.getParent())){
            return true;
        }
        return false;
    }



}
