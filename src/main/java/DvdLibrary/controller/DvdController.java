package DvdLibrary.controller;

import DvdLibrary.dao.DvdDao;
import DvdLibrary.dao.DvdDaoException;
import DvdLibrary.dto.Dvd;
import DvdLibrary.ui.DvdView;
import DvdLibrary.ui.UserIOConsole;

import java.util.List;

public class DvdController {

    private DvdView view = new DvdView();
    private DvdDao dao = new DvdDao();
    private UserIOConsole io = new UserIOConsole();

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        createDvd();
                        break;
                    case 2:
                        removeDvd();
                        break;
                    case 3:
                        editDvd(); //not functioning
                        break;
                    case 4:
                        searchDvd();
                        break;
                    case 5:
                        listDvds();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();

                }

            }
            exitMessage();
        } catch (DvdDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void createDvd() throws DvdDaoException {
        view.displayCreateDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(), newDvd);
        view.displayCreateSuccessBanner();
    }

    private void listDvds() throws DvdDaoException {
        view.displayDisplayAllBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }

    private void searchDvd() throws DvdDaoException {
        view.displayDisplayDvdBanner();
        String title = view.getDvdChoice();
        Dvd dvd = dao.getDvd(title);
        view.displayDvd(dvd);
    }

    private void removeDvd() throws DvdDaoException {
        view.displayRemoveDvdBanner();
        String title = view.getDvdChoice();
        Dvd removedDvd = dao.removeDvd(title);
        view.displayRemoveResult(removedDvd);
    }

    private void editDvd(){
        view.displayEditDvdBanner();
        view.editDvdOptions();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
