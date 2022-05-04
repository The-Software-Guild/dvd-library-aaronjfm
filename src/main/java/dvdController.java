package src.main.java;

import java.util.*;
import java.io.*;

public class dvdController {

    static private dvdModel library;
    static private dvdDisplay display;

    public static void main(String[] args){
        controlOptions();
    }

    public static void controlOptions(){
        display.showOptions();
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        library.processInitialRequest(option);

    }

    public static String[] requestDvdOptions(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the DVD title");
        String dvdTitle = sc.nextLine();

        System.out.println("Please enter the release date");
        String dvdReleaseDate = sc.nextLine();

        System.out.println("Please enter the MPAA rating");
        String dvdRating = sc.nextLine();

        System.out.println("Please enter the directors name");
        String dvdDirector = sc.nextLine();

        System.out.println("Please enter the studio");
        String dvdStudio = sc.nextLine();

        System.out.println("Please enter your rating or note");
        String dvdUserNote = sc.nextLine();

        return new String[] {dvdTitle,dvdReleaseDate,dvdRating,dvdDirector,dvdStudio,dvdUserNote};
    }
}

