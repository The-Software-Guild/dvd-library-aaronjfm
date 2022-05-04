package src.main.java;

import java.util.Scanner;

public class dvdDisplay {

    static private dvdController controller;

    public static void showOptions(){
        System.out.println("Please select an option below by typing the number next to it: ");
        System.out.println("1: Add DVD to the collection");
        System.out.println("2: Remove DVD from the collection");
        System.out.println("3: Edit existing DVD in the collection");
        System.out.println("4: Search DVD by Title");
        System.out.println("5: List DVDs in the collection");
    }

    public static void startAgain(){
        System.out.println("Would you like to do something else? (Type Yes or No)");
        Scanner sc = new Scanner(System.in);
        String result = sc.nextLine().toLowerCase();
        if(result.charAt(0) == 'y'){
            controller.controlOptions();
        }
        else if(result.charAt(0) == 'n'){
            System.out.println("Application closing");
        }
        else{
            System.out.println("Incorrect word entered, please type Yes or No.");
            startAgain();
        }
    }

    public static void showEditOptions(){
        System.out.println("What would you like to edit? Select an option by typing the number next to it.");
        System.out.println("1: DVD Title");
        System.out.println("2: Release Date");
        System.out.println("3: MPAA Rating");
        System.out.println("4: Directors Name");
        System.out.println("5: Studio");
        System.out.println("6: Your rating/note");
    }
}
