package P1;

public class Train {
    private int Id;
    private int timeRegistered = 0;
    private int startTime;
    private Station currentLocation;
    private Route route; // added attribute

    Train(int id, int timeRegistered, int startTime, Station currLocation, Route route) {
        this.Id = id;
        this.timeRegistered = timeRegistered;
        this.startTime = startTime;
        this.currentLocation = currLocation;
        this.route = route;
    }

    /**
     * method returns the train object's id attribute
     * 
     * @return
     *         int
     */
    public int getID() {
        return this.Id;
    }

    /**
     * method checks if train has been registered, that is, if the registered time
     * is zero (0).
     * 
     * @return
     *         boolean
     *         true - registered
     *         false - not registered
     */
    public Boolean isRegistered() {
        if (this.timeRegistered == 0) {
            return false;
        }
        return true;
    }

    /**
     * method returns the time the train was registered
     * 
     * @return
     *         int
     */
    public int whenRegistered() {
        return timeRegistered;
    }

    /**
     * method sets the time registered attribute of the train object
     * 
     * @param time
     *             int - to be the new value of the time registered attribute of the
     *             train object
     */
    public void register(int time) {
        this.timeRegistered = time;
    }

    /**
     * method transfers the train to the next station along it route only if the
     * next station is available. The station has to have an open status
     * to accept the train.
     */
    public void start() {
        if (this.route.canGetTo(nextStation())) {
            for (int x = 0; x < this.route.getStationList().size(); x++) {
                if (this.route.getStationList().get(x).getName().equals(nextStation())) {
                    if (this.route.getStationList().get(x).getStatus() == RSStatus.OPEN) {
                        this.route.getStationList().get(x).acceptTrain(this);
                    } else {
                        System.out.println("Train cannot advance because the status of the next station is "
                                + RSStatus.CLOSEDFORMAINTENANCE);
                    }

                }
            }
        }
    }

    public void advance() {
    }

    /**
     * method returns the current location (Station) attribute of the train object
     * 
     * @return
     *         String - name of the Station that the train is currently located
     */
    public String currentStation() {
        return this.currentLocation.getName();
    }

    /**
     * method returns the next station on the train's route
     * 
     * @return
     *         String - name of the next Station along the train's route
     */
    public String nextStation() {
        return this.route.getNextStation(currentStation());
    }

    /**
     * mmethod allows for the route attribute of the train object to be changed to a
     * new value
     * 
     * @param r
     *          a Route object to assign to the train's route attribute
     */
    public void changeRoute(Route r) {
        this.route = r;
    }

    /**
     * method checks if the route of the train in verified and that the train is
     * registered, that it, the time registered is greater than zero (0)
     * 
     * @return
     *         boolean
     *         true - it has passed all conditions
     *         false - it has failed a condition
     */
    public Boolean verify() {
        if (!this.route.verify()) {
            if (this.timeRegistered < 1) {
                return false;
            }
        }
        return true;
    }
}
