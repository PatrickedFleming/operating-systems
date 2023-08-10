//Shortess process next
//Author: Patrick Fleming
//Student Number: c3253586
//date: 2023-08-07

import java.util.ArrayList;

public class SPN implements SchedularInterface{

    private static ArrayList<ProcessInfo> spnInput;
    private String timeline = "";
    private String sum = "";
    private float avgWaitTime;
    private float avgTurnAroundTime;

    public SPN(ArrayList<ProcessInfo> input){
       spnInput =input;
    }
    
    public void run(ArrayList<ProcessInfo> input, int disp){
        int time = 0;
        int DISP = disp;


        }

    }

    //get output
    public Output getOutput(){
        return spnOutput;
    }

    //prints output in format
    public String toString(){
        return spnOutput.toString("SPN");
    }
}
