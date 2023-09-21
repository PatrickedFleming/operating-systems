
import java.io.File;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class A3 {
    private static int quantum;
    private static int pages;
    private static LinkedList<Process> processes = new LinkedList<Process>();
    
    public static void main(String[] args) {
        getData(args);
        Scheduler scheduler = new Scheduler(quantum, pages, processes);
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
            while(sc.hasNext()){
                Process process = new Process();
                if(sc.hasNext("name:")){
                    process.setName((sc.next().substring(6, sc.next().indexOf(";"))));
                }
                if(sc.hasNext("page:")){
                    process.addPage();
                }
                if(process.getPages() > 50){
                    throw new InputMismatchException("Invalid number of pages");
                }
                if(sc.hasNext("end;")){
                    processes.add(process);
                    break;
                }
            }
        }
        catch(Exception e){
            System.out.println("Error: Invalid input file");
            System.exit(1);
        }
    }
}
