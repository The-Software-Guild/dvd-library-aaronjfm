package src.main.java;

import java.util.*;

public class dvdModel {

    static private dvdDisplay display;
    static private dvdController controller;

    static private String[] dvdFields = {"Title", "Release Date", "MPAA Rating", "Directors Name", "Studio", "User Rating"};

    static private HashMap<String, HashMap> dvdLibrary = new HashMap<String, HashMap>();

    public static void processInitialRequest(int request){
        switch(request){
            case 1: addDvd();
            case 2: removeDvd();
            case 3: editDvd();
            case 4: searchDvd();
            case 5: listDvds();
        }
    }

    public static void addDvd(){
        HashMap<String, String> dvdData = new HashMap<String, String>();
        String[] customerChoice = controller.requestDvdOptions();

        for (int i = 1; i < customerChoice.length; i++){
            dvdData.put(dvdFields[i], customerChoice[i]);
        }

        if (dvdLibrary.containsKey(customerChoice[0])){
            System.out.println("DVD already exists, if you would like to edit the submission, please select edit existing DVD in the options.");
        }
        else{
            dvdLibrary.put(customerChoice[0],dvdData);
            System.out.println("DVD: "+ customerChoice[0] + " has been added");
        }


        display.startAgain();
    }

    public static void removeDvd(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the DVD title to remove");
        String removeTitle = sc.nextLine();

        if (dvdLibrary.containsKey(removeTitle)){
            dvdLibrary.remove(removeTitle);
        }
        else{
            System.out.println("DVD: "+ removeTitle + " does not exist in the library.");
        }

        display.startAgain();
    }

    public static void editDvd(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the DVD you would like to edit");
        String editTitle = sc.nextLine();

        if (dvdLibrary.containsKey(editTitle)){
            display.showEditOptions();
            int selectedOption = sc.nextInt();
            sc.nextLine(); // throw away the \n not consumed by nextInt
            switch(selectedOption){
                case 1:
                    System.out.println("Please enter the corrected DVD Title");
                    String newTitle = sc.nextLine();
                    dvdLibrary.put(newTitle, dvdLibrary.get(editTitle));
                    dvdLibrary.remove(editTitle);
                    System.out.println("Title Updated");
                    break;

                case 2:
                    editDvdData(editTitle,"Release Date");
                    break;

                case 3:
                    editDvdData(editTitle,"MPAA Rating");
                    break;

                case 4:
                    editDvdData(editTitle,"Directors Name");
                    break;

                case 5:
                    editDvdData(editTitle,"Studio");
                    break;

                case 6:
                    editDvdData(editTitle,"User Rating");
                    break;
            }

        }
        else {
            System.out.println("DVD does not exist.");
        }
        display.startAgain();
    }

    public static void searchDvd(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the DVD title to search");
        String searchTitle = sc.nextLine();

        if (dvdLibrary.containsKey(searchTitle)){
            System.out.println(dvdLibrary.get(searchTitle));
        }
        else {
            System.out.println("DVD: "+ searchTitle + " does not exist in the library.");
        }

        display.startAgain();
    }

    public static void listDvds(){
        for (String i : dvdLibrary.keySet()) {
            System.out.println(i);
        }

        display.startAgain();
    }

    public static void editDvdData(String Title, String dvdData){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the corrected "+ dvdData);
        String newDvdData = sc.nextLine();

        dvdLibrary.get(Title).put(dvdData, newDvdData);
        System.out.println(Title + ": " + dvdLibrary.get(Title));
    }

}