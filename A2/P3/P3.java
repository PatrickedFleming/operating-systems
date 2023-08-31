//P3 clustermanagement in Monitor
//Author: Patrick Fleming
//Student Number: c3253586
//date: 27/08/2023

import java.util.HashMap;
import java.util.logging.*;
import static java.util.logging.Level.*;

import java.util.Scanner;
import java.io.File;

public class P3 {

    private static final Logger outputLog = Logger.getLogger("P3outPut");
    private static HashMap<String, Integer> taskList = new HashMap<String, Integer>();
    
    public static void main(String[] args) {
        //set up logger
        Handler handler = new ConsoleHandler();
        handler.setLevel(FINEST);
        handler.setFormatter(new ClusterManagementFormat());
        outputLog.addHandler(handler);
        outputLog.setLevel(FINEST);
        outputLog.setUseParentHandlers(false);

        //gets tasks from file
        File input = new File(args[0]);
        getTasks(input);
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
