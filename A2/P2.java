//Problem 2 of Assignment 2
//Author: Patrick Fleming
//Student Number: c3253586
//date: 27/08/2023

import java.io.File;
import java.util.logging.*;
import static java.util.logging.Level.*;
import java.util.Scanner;
import java.util.HashMap;

public class P2 {

    private static final Logger outputLog = Logger.getLogger("OutputLog");
    private static HashMap<String, Integer> taskList = new HashMap<String, Integer>();
    
    public static void main(String[] args) {
        //set up logger
        Handler handler = new ConsoleHandler();
        handler.setLevel(FINEST);
        handler.setFormatter(new ClusterManagementFormat());
        outputLog.addHandler(handler);
        outputLog.setLevel(FINEST);
        outputLog.setUseParentHandlers(false);

        //gets file
        File input = new File(args[0]);
        //gets tasks from file
        getTasks(input);
        //creates scheduler
        Scheduler scheduler = new Scheduler(taskList);
        //starts scheduler
        scheduler.run();
        //waits for scheduler to finish
        try{
            scheduler.join();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void getTasks(File file){
        try{
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                String[] line = sc.nextLine().split(" ");
                taskList.put(line[0], Integer.parseInt(line[2]));
            }
            sc.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
