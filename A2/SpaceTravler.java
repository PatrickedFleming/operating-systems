//SpaceTravler for problem 1
//Author: Patrick Fleming
//Student Number: c3253586
//date: 27/08/2023

import java.util.concurrent.Semaphore;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;
import java.util.logging.Handler;

public class SpaceTravler extends Thread{
    private String name;
    private int travel;
    private Semaphore sem;

    private Logger spaceLog = Logger.getLogger("spaceLog");


    public SpaceTravler(Semaphore sem, String name, int travel){
        this.sem = sem;
        this.name = name;
        this.travel = travel;
        Handler spaceX = new ConsoleHandler();
        spaceX.setFormatter(new SpaceLogFormatter());
    }

    @Override
    public void run(){
        try{

            sem.acquire();
            
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    
}
