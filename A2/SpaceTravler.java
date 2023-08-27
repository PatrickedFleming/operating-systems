//SpaceTravler for problem 1
//Author: Patrick Fleming
//Student Number: c3253586
//date: 27/08/2023

import java.util.concurrent.Semaphore;
import java.util.logging.Logger;
import static java.util.logging.Level.FINEST;

public class SpaceTravler extends Thread{
    private String name;
    private String destination;
    private int trips;
    private static final Semaphore Wormhole = new Semaphore(1);
    private static int count = 0;

    private static Logger SpaceLog = Logger.getLogger("SpaceLog");


    public SpaceTravler(String name, int trips){
        this.name = name;
        this.trips = trips;
        if(name.contains("E")){
            destination = "Proxima-b";
        }
        else{
            destination = "Earth";
        }
    }

    @Override
    public void run(){
        try{
            SpaceLog.log(FINEST,name + ": Waiting for wormhole. Travelling towards "+ destination);
            Wormhole.acquire();
            for(int i = 0; i < 4; i++){
                sleep(50);
                SpaceLog.log(FINEST,name + ": Crossing wormhole Loading "+ i*25+"%.");
            }
            SpaceLog.log(FINEST,name + ": Across the wormhole.");
            trips--;
            destination = destination.equals("Proxima-b") ? "Earth" : "Proxima-b";
            if(trips == 0){
                SpaceLog.log(FINEST,name + " Finished.");
            }
            SpaceLog.log(FINEST,"Count = " + ++count);
            Wormhole.release();
            sleep(50);
            if(trips > 0){
                run();
            }
            
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return name + " " + trips;
    }    
}
