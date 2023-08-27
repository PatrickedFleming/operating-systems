//Problem 1 of Assignment 2
//Author: Patrick Fleming
//Student Number: c3253586
//date: 27/08/2023

import java.io.File;
import java.util.Scanner;

import java.util.ArrayList;


public class P1 {

    private static ArrayList<SpaceTravler> travelers = new ArrayList<SpaceTravler>();

    public static void main(String[] args) {
        //gets input file
        File inputFile = new File(args[0]);
        //gets Travelers from file
        getTravelers(inputFile);

        //starts travelers
        travelers.forEach((traveler) -> {
            traveler.start();
        });

        //ends travelers
        travelers.forEach((traveler) -> {
            try{
                traveler.join();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        });
    }

    private static void getTravelers(File input) {
        try(Scanner sc = new Scanner(input)){
            if(sc.hasNextLine()){
                String[] line = sc.nextLine().split(",");
                if(line[0].contains("E=")){
                    createTraveler(Integer.parseInt(line[0].substring(2)), Integer.parseInt(line[2].substring(3)), "E");

                }
                if(line[1].contains("P=")){
                    createTraveler(Integer.parseInt(line[1].substring(3)), Integer.parseInt(line[2].substring(3)), "P");
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }

    private static void createTraveler(Integer amountOfTravelers, Integer trips, String homeworld){
        if(homeworld =="E"){
            for(int i = 1; i <= amountOfTravelers; i++){
                travelers.add(new SpaceTravler("E_H"+i, trips));
            }
        }
        else{
            for(int i = 1; i <= amountOfTravelers; i++){
                travelers.add(new SpaceTravler("P_A"+i, trips));
            }

        }
    }
}
