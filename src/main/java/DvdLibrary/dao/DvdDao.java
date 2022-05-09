package DvdLibrary.dao;

import DvdLibrary.dto.Dvd;

import java.io.*;
import java.util.*;


public class DvdDao {

    public static final String Library_FILE = "library.txt";
    public static final String DELIMITER = "::";

    public Map<String, Dvd> dvds = new HashMap<>();

    public Dvd addDvd(String title, Dvd dvd) throws DvdDaoException {
        loadRoster();
        Dvd newDvd = dvds.put(title, dvd);
        writeRoster();
        return newDvd;
    }

    public List<Dvd> getAllDvds() throws DvdDaoException {
        loadRoster();
        return new ArrayList<Dvd>(dvds.values());
    }

    public Dvd getDvd(String title) throws DvdDaoException {
        loadRoster();
        return dvds.get(title);
    }

    public Dvd removeDvd(String title) throws DvdDaoException {
        loadRoster();
        Dvd removedDvd = dvds.remove(title);
        writeRoster();
        return removedDvd;
    }



        private Dvd unmarshallDvd(String dvdAsText){

        String[] dvdTokens = dvdAsText.split(DELIMITER);

        String title = dvdTokens[0];

        Dvd dvdFromFile = new Dvd(title);
        dvdFromFile.setreleaseDate(dvdTokens[1]);
        dvdFromFile.setMpaaRating(dvdTokens[2]);
        dvdFromFile.setDirectorName(dvdTokens[3]);
        dvdFromFile.setStudio(dvdTokens[4]);
        dvdFromFile.setUserNote(dvdTokens[5]);

        return dvdFromFile;
    }

    private void loadRoster() throws DvdDaoException {
        Scanner scanner;
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(Library_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdDaoException(
                    "-_- Could not load roster data into memory.", e);
        }
        String currentLine;
        Dvd currentDvd;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();

            currentDvd = unmarshallDvd(currentLine);

            dvds.put(currentDvd.getTitle(), currentDvd);
        }
        scanner.close();
    }

    private String marshallDvd(Dvd aDvd){
        String dvdAsText = aDvd.getTitle() + DELIMITER;
        dvdAsText += aDvd.getReleaseDate() + DELIMITER;
        dvdAsText += aDvd.getMpaaRating() + DELIMITER;
        dvdAsText += aDvd.getDirectorName() + DELIMITER;
        dvdAsText += aDvd.getStudio() + DELIMITER;
        dvdAsText += aDvd.getUserNote();
        return dvdAsText;
    }

    private void writeRoster() throws DvdDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(Library_FILE));
        } catch (IOException e) {
            throw new DvdDaoException(
                    "Could not save student data.", e);
        }

        String dvdAsText;
        List<Dvd> dvdList = this.getAllDvds();
        for (Dvd currentDvd : dvdList) {
            dvdAsText = marshallDvd(currentDvd);
            out.println(dvdAsText);
            out.flush();
        }
        out.close();
    }
}
