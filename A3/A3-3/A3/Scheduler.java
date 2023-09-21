
import java.util.LinkedList;
import java.util.Stack;

public class Scheduler {
    private int quantum;
    private int time;
    private int pages;

    private Stack<Process> readyQueue = new Stack<Process>();
    private Stack<Process> blockedQueue = new Stack<Process>();
    private Stack<Process> finishedQueue = new Stack<Process>();

    private LinkedList<Process> processes;


    public Scheduler(int quantum, int pages, LinkedList<Process> processes){
        this.quantum = quantum;
        this.pages = pages;
        this.processes = processes;
    }


    public void run(){

    }

    private void dispatch(){

    }




    
    
}
