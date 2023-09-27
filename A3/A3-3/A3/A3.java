
import java.io.File;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class A3 {
    private static int quantum;
    private static int frames;
    private static LinkedList<Process> processes = new LinkedList<Process>();
    
    public static void main(String[] args) {
        getData(args);
        Scheduler scheduler = new Scheduler(processes, quantum, frames);
        scheduler.start();
    }


    private static void getData(String[] arguments){
        try{
            frames = Integer.parseInt(arguments[0]);
            quantum = Integer.parseInt(arguments[1]);
            for(int i = 2; i < arguments.length; i++){
               File inputFile = new File(arguments[i]);
               CreateProcesses(inputFile);
            }
        }
        catch(Exception e){
            System.out.println("Error: Invalid number of pages");
            System.exit(1);
        }
    }




    private static void CreateProcesses(File input) throws InputMismatchException{
        try(Scanner sc = new Scanner(input)){
            String name = "";
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
                    }
                    else if(s.contains("end")){  
                        pages.add(new Page("end"));
                        processes.add(new Process(name, pages));
                        break;
                    }
                }
            }   
        }
        catch(Exception e){
            System.out.println("Error: Invalid input file");
            System.exit(1);
        }
    }
}
