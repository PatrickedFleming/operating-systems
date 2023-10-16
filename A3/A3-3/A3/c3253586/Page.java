//Page class used to hold the instruction and the process it belongs to
//Author: Patrick Fleming
//Student Number: C3253586
//Date: 17/10/23
public class Page {
    private String pageID;
    private int pageInstruction;
    private Process Parent;

    /**
     * Creates a page with the instruction
     * 
     * @param page the instruction
     */
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

    /**
     * Creates a page with the instruction and the process it belongs to
     * 
     * @param page the instruction and the process it belongs to
     * @param parent the process the page belongs to
     */
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

    /**
     * returns the page id
     * 
     * @return the page id as a string
     */
    public String getPageID(){
        return pageID;
    }

    /**
     * Sets the page id
     * 
     * @param pageID the page id
     */
    public void setPageID(String pageID){
        this.pageID = pageID;
    }

    /**
     * returns the instruction
     * 
     * @return the instruction
     */
    public int getInstruction(){
        return pageInstruction;
    }

    /**
     * Sets the parent process
     * 
     * @param p the process that owns this page
     */
    public void setParent(Process p){
        Parent = p;
    }

    /**
     * returns the parent process
     * 
     * @return the parent process
     */
    public Process getParent(){
        return Parent;
    }

    /**
     * Compares the instruction of the page
     * 
     * @param p the page to compare to
     */
    public boolean equals(Page p){
        if(this.pageInstruction == p.getInstruction() && this.Parent.equals(p.getParent())){
            return true;
        }
        return false;
    }



}
