
import java.io.File;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class A3 {
    private static int quantum;
    private static int pages;
    private static LinkedList<Process> processes = new LinkedList<Process>();
    private static int count = 0;
    
    public static void main(String[] args) {
        getData(args);
        Scheduler scheduler = new Scheduler(quantum, pages, processes);
        scheduler.printResults();
    }


    private static void getData(String[] arguments){
        try{
            pages = Integer.parseInt(arguments[0]);
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
            Process process = new Process();
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] data = line.split(";");
                for(String s: data){
                    if(s.contains("name:")){
                        process.setName(s.substring(5));
                    }
                    else if(s.contains("page:")){
                        process.addPage(new Page(s.substring(5)));
                    }
                    else if(s.contains("end")){
                        processes.add(process);
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
