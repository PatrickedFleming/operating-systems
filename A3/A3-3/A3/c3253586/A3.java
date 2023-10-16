//Assignment 3 Operating Systems
//Author: Patrick Fleming
//Student Number: C3253586
//Date: 17/10/23
import java.io.File;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class A3 {
    //holds the data for the scheduler
    private static int quantum;
    private static int frames;
    private static LinkedList<Process> processes = new LinkedList<Process>();
    
    /**
     * Takes in the arguments from the command line and creates the processes
     * 
     * @param args the arguments from the command line
     */
    public static void main(String[] args) {
        getData(args);      //gets the data needed for the scheduler
        Scheduler scheduler = new Scheduler(processes, quantum, frames);
        scheduler.start();
    }

    /**
     * Takes in the arguments from the command line and creates the processes
     * 
     * @param arguments the arguments from the command line first is the number of frames
     * second is the quantum and the rest are the files to be read
     */
    private static void getData(String[] arguments){
        try{
            frames = Integer.parseInt(arguments[0]);
            quantum = Integer.parseInt(arguments[1]);
            if(frames < 1 || quantum < 1){  //checks if the input is valid
                throw new Exception("Error: Invalid input");
            }
            if(frames < arguments.length - 2){  //checks if there are enough frames for the processes
                throw new Exception("Error: Not enough frames for processes");
            }
            for(int i = 2; i < arguments.length; i++){
               File inputFile = new File(arguments[i]);
               CreateProcesses(inputFile);
            }
        }
        catch(Exception e){
            System.out.println(e);
            System.exit(1);
        }
    }



    /**
     * Creates the processes from the input file
     * 
     * @param input the file to be read
     */
    private static void CreateProcesses(File input) throws InputMismatchException{
        try(Scanner sc = new Scanner(input)){
            String name = "";
            int count = 0;
            LinkedList<Page> pages = new LinkedList<Page>();
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] data = line.split(";");
                for(String s: data){
                    if(s.contains("name:")){
                        name = s.substring(5);
                    }
                    else if(s.contains("page:")){
                        Page temp = new Page(s.substring(5));
                        pages.add(temp);  
                        count++;
                    }
                    else if(s.contains("end")){  
                        pages.add(new Page("end"));
                        processes.add(new Process(name, pages));
                        break;
                    }
                }
                if(count > 50){ //checks if there are too many pages
                    throw new Exception("Error: Too many pages");
                }
            }   
        }
        catch(Exception e){
            System.out.println(e);
            System.exit(1);
        }
    }
}
