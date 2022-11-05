package P1;

import java.util.ArrayList;

public class Station {
    private String name;
    private RSStatus status;
    private ArrayList<Train> trains = new ArrayList<Train>();
    private Route route; // added attribute as method in train system asks for routes of stations

    Station(String sname, RSStatus status) {
        this.name = sname;
        this.status = status;
    }

    /**
     * method is used to return the name of the Station Object
     * 
     * @return
     *         returns a String - name of Station Object
     */
    public String getName() {
        return this.name;
    }

    /**
     * method returns the current status of the Station Object
     * 
     * @return
     *         returns a RSStatus type Object
     * 
     * 
     *         RSSstatus can be :
     *         OPEN("Status is OPEN")
     *         CLOSEDFORMAINTENANCE("Status is CLOSED FOR MAINTENANCE");
     */
    public RSStatus getStatus() {

        return this.status;

    }

    /**
     * method returns all the trains that are currently at the Station
     * 
     * @return
     *         an ArrayList of type Train
     */
    public ArrayList<Train> getTrains() {
        return this.trains;
    }

    /**
     * method returns the route for the Sttaion
     * 
     * @return
     *         a Route Object
     */
    public Route getRoute() {
        return this.route;
    }

    /**
     * method checks if the Station currently has any trains
     * 
     * @return
     *         a boolean
     *         true - Station has a train
     *         false - Sttaion does not have a train
     */
    public Boolean hasTrain() {
        int size = this.trains.size();
        if (size == 0) {
            return false;
        }
        return true;
    }

    /**
     * method checks if the status of the Station is OPEN
     * 
     * @return
     *         a boolean
     *         true - Station's status is open
     *         false - Station's status is closed
     */
    public Boolean isOpen() {
        if (this.status == RSStatus.OPEN) {
            return true;
        }
        return false;
    }

    /**
     * method checks if the name of the Station is null or has any charaters
     * 
     * @return
     *         a boolean
     *         true - passes all conditions
     *         false - failed a condition
     */
    public Boolean verify() {
        if (getName().equals(null) || getName().equals(" ") || getName().equals("")) {
            return false;
        }
        return true;
    }

    /**
     * method changes the Station's status to closed (RSStatus.CLOSEDFORMAINTENANCE)
     */
    public void close() {
        this.status = RSStatus.CLOSEDFORMAINTENANCE;
    }

    /**
     * method changes the Station's status to open (RSStatus.OPEN)
     */
    public void open() {
        this.status = RSStatus.OPEN;

    }

    /**
     * method adds a train to the Station
     * 
     * @param train
     *              train object to be added to the station
     */
    public void acceptTrain(Train train) {
        this.trains.add(train);
    }

    /**
     * method removes a train from the Station
     * 
     * @param train
     *              train object to be removed from teh Station
     */
    public void releaseTrain(Train train) {
        this.trains.remove(train);
    }

}
