package DvdLibrary.ui;

import DvdLibrary.dto.Dvd;
import DvdLibrary.dao.DvdDao;

import java.util.List;

public class DvdView {
    private UserIOConsole io = new UserIOConsole();
    private DvdDao dao = new DvdDao();

    public int printMenuAndGetSelection(){
        io.print("Please select an option below by typing the number next to it: ");
        io.print("1: Add DVD to the collection");
        io.print("2: Remove DVD from the collection");
        io.print("3: Edit existing DVD in the collection");
        io.print("4: Search DVD by Title");
        io.print("5: List DVDs in the collection");

        return io.readInt("Please select from the above choices.", 1, 5);
    }

    public Dvd getNewDvdInfo() {
        String title = io.readString("Please enter the DVD title");
        String releaseDate = io.readString("Please enter the release date");
        String mpaaRating = io.readString("Please enter the MPAA Rating");
        String director = io.readString("Please enter the directors name");
        String studio = io.readString("Please enter the studio");
        String userNote = io.readString("Please enter your rating or note");

        Dvd currentDvd = new Dvd(title);
        currentDvd.setreleaseDate(releaseDate);
        currentDvd.setMpaaRating(mpaaRating);
        currentDvd.setDirectorName(director);
        currentDvd.setStudio(studio);
        currentDvd.setUserNote(userNote);
        return currentDvd;
    }

    public void displayCreateDvdBanner() {
        io.print("=== Create Dvd ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "Dvd successfully created.  Please hit enter to continue");
    }

    public void displayDvdList(List<Dvd> dvdList) {
        for (Dvd currentDvd : dvdList) {
            io.print(currentDvd.getTitle());
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All Dvds ===");
    }

    public void displayDisplayDvdBanner () {
        io.print("=== Display Dvd ===");
    }

    public String getDvdChoice() {
        return io.readString("Please enter the Dvd Title.");
    }

    public void displayDvd(Dvd dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle());
            io.print("Release Date: " + dvd.getReleaseDate());
            io.print("MPAA Rating: " + dvd.getMpaaRating());
            io.print("Director: " + dvd.getDirectorName());
            io.print("Studio: " + dvd.getStudio());
            io.print("User Note: " + dvd.getUserNote());
            io.print("");
        } else {
            io.print("No such Dvd.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveDvdBanner () {
        io.print("=== Remove Dvd ===");
    }

    public void displayRemoveResult(Dvd dvdRecord) {
        if(dvdRecord != null){
            io.print("Dvd successfully removed.");
        }else{
            io.print("No such Dvd.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayEditDvdBanner() {
        io.print("=== Edit Dvd ===");
    }

    public void editDvdOptions(){
        String[] options = {"Release Date", "MPAA Rating", "Directors Name", "Studio", "User Note"};
        String title = io.readString("Please enter the DVD title");
        io.print("1: Edit Release Date");
        io.print("2: Edit MPAA Rating");
        io.print("3: Edit Directors Name");
        io.print("4: Edit Studio");
        io.print("5: Edit User Note");
        int result = io.readInt("Please select from the above choices.", 1, 5);

        io.print("You have chosen to edit the " + options[result-1]);

        String change = io.readString("Please enter the corrected " + options[result-1]);


        switch (result){
            case 1:
                dao.dvds.get(title).setreleaseDate(change);
        }

    }


    public void displayEditResult(Dvd dvd) {
        if(dvd != null){
            io.print("Dvd successfully edited.");
        }else{
            io.print("No such Dvd.");
        }
        io.readString("Please hit enter to continue.");
    }


    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

}
