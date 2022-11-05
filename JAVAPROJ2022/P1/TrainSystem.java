package P1;

import java.util.ArrayList;

class TrainSystem {

    private SystemStatus status;
    private ArrayList<Station> stations = new ArrayList<Station>();
    private ArrayList<Segment> segments = new ArrayList<Segment>();
    private ArrayList<Route> routes = new ArrayList<Route>();
    private ArrayList<Train> trains = new ArrayList<Train>();

    TrainSystem(SystemStatus status) {
        this.status = status;
    }

    TrainSystem(SystemStatus status, ArrayList<Station> stations) {
        this.status = status;
        this.stations = stations;
    }

    /**
     * method adds a station to the train system object
     * 
     * @param sname
     *              name of station (String) to be added to the train system
     *              object
     */

    public void addStation(String sname) {
        Station station = new Station(sname, RSStatus.OPEN);
        this.stations.add(station);
        System.out.println("Station '" + sname + "' has been added successfully");
    }

    /**
     * method removes a station from the train system object
     * 
     * @param sname
     *              name of the station (String) to be removed from the train system
     *              object
     */
    public void removeStation(String sname) {
        for (Station station : this.stations) {
            if (station.getName().equals(sname)) {
                this.stations.remove(station);
                System.out.println("Station '" + station.getName() + "' has been removed successfully");
            }
        }
    }

    /**
     * method changes the status of the Station to OPEN.
     * 
     * @param sname
     *              name of station (String) to change the status of.
     */
    public void OpenStation(String sname) {
        for (Station station : this.stations) {
            if (station.getName().equals(sname)) {
                station.open();
                System.out.println("Station '" + station.getName() + "' is now open");
            }
        }
    }

    /**
     * method changes the status of the Station to CLOSED.
     * 
     * @param sname
     *              name of station (String) to change the status of.
     */
    public void closeStation(String sname) {
        for (Station station : this.stations) {
            if (station.getName().equals(sname)) {
                station.close();
                System.out.println("Station '" + station.getName() + "' is now closed");
            }
        }
    }

    /**
     * method adds a new segment to teh train system object
     * 
     * @param sname
     *              name of the segment object to be created
     * @param start
     *              name of the start of the segment (Station)
     * @param sEnd
     *              name of the end of the segment (Station)
     */
    public void addSegment(String sname, String start, String sEnd) {
        Segment newSegment = new Segment(sname, RSStatus.OPEN, start, sEnd);
        this.segments.add(newSegment);
        System.out.println("The segment '" + sname + "' has been added successfully");
    }

    /**
     * method removes a segment from the train system object
     * 
     * @param sname
     *              name of the Segment (String) to be removed from the train system
     *              object
     */
    public void removeSegment(String sname) {
        for (Segment segment : this.segments) {
            if (segment.getName().equals(sname)) {
                this.segments.remove(segment);
                System.out.println("Segment '" + segment.getName() + "' has been removed successfully");
            }
        }
    }

    /**
     * method changes the status attribute of the segment to OPEN
     * 
     * @param sname
     *              name of Segment (String) to change the status of
     */
    public void openSegment(String sname) {
        for (Segment segment : this.segments) {
            if (segment.getName().equals(sname)) {
                segment.open();
                System.out.println("Segment '" + segment.getName() + "' is now open");
            }
        }
    }

    /**
     * method changes the status attribute of the segment to CLOSED
     * 
     * @param sname
     *              name of Segment (String) to change the status of
     */
    public void closeSegment(String sname) {
        for (Segment segment : this.segments) {
            if (segment.getName().equals(sname)) {
                segment.close();
                System.out.println("Segment '" + segment.getName() + "' is now closed");
            }
        }
    }

    /**
     * method adds a route object to the train system object
     * 
     * @param rName
     *                    name of the route (String) to be added to the train system
     *                    object
     * @param isRoundTrip
     *                    boolean to indicate if the route is a Round Trip route
     * @param rStations
     *                    ArrayList of type Station which are the stations that are
     *                    along the route to be added
     */
    public void addRoute(String rName, Boolean isRoundTrip, ArrayList<Station> rStations) {
        Route r = new Route(rName, isRoundTrip, RSStatus.OPEN, rStations);
        this.routes.add(r);
        System.out.println("Route '" + rName + "' has been added successfully");
    }

    /**
     * method remove a route object from the train system object
     * 
     * @param sname
     *              name of the route (String) to be removed from the train system
     *              object
     */
    public void removeRoute(String sname) {
        for (Route route : this.routes) {
            if (route.getName().equals(sname)) {
                this.routes.remove(route);
                System.out.println("Route '" + route.getName() + "' has been removed successfully");
            }
        }
    }

    /**
     * method to change the status of the route object to OPEN
     * 
     * @param sname
     *              name of the route (String) to change the status attribute of
     */
    public void openRoute(String sname) {
        for (Route route : this.routes) {
            if (route.getName().equals(sname)) {
                route.open();
                System.out.println("Route " + route.getName() + " is now open");
            }
        }
    }

    /**
     * method to change the status of the route object to CLOSED
     * 
     * @param sname
     *              name of the route (String) to change the status attribute of
     */
    public void closeRoute(String sname) {
        for (Route route : this.routes) {
            if (route.getName().equals(sname)) {
                route.close();
                System.out.println("Route " + route.getName() + "is now closed");
            }
        }
    }

    /**
     * method to add a train object to the train system object
     * 
     * @param train
     *              train object to be added to the train system object
     */
    public void addTrain(Train train) {
        this.trains.add(train);
        System.out.println("Train #" + train.getID() + " has been added successfully");
    }

    /**
     * method to remove a train object from the train system object
     * 
     * @param train
     *              train object to be removed from the train system object
     */
    public void removeTrain(int Id) {
        for (Train train : this.trains) {
            if (train.getID() == Id) {
                this.trains.remove(train);
                System.out.println("Tain #" + train.getID() + " has been removed");
            }
        }
    }

    /**
     * method checks if a train's route's status is open along with its segments.
     * it also initializes the time regitered attribute to 1.
     * 
     * @param train
     *              int - train to be registered
     * @param route
     *              String - name of route to be verified
     */
    public void registerTrain(int train, String route) {
        for (Route route2 : this.routes) {
            if (route2.getName().equals(route)) {
                RSStatus status = route2.geStatus();
                if (status == RSStatus.OPEN) {
                    for (Segment segment : route2.getSegments()) {
                        if (segment.getStatus() == RSStatus.OPEN) {
                            continue;
                        } else {
                            System.out.println(
                                    "The train could not be registered. Segment " + segment.getName() + "is closed");
                            return;
                        }
                    }
                } else {
                    System.out.println("The train could not be registered. Route " + route2.getName() + " is closed");
                    return;
                }
            }

        }
        for (Train train2 : this.trains) {
            if (train2.getID() == train) {
                train2.register(1);
            }
        }
        System.out.println("The train was registered successfully");

    }

    /**
     * this method deregisters a train by setting its time registered attribute to
     * zero (0)
     * 
     * @param train
     *              - id of train to be deregistered.
     */
    public void deRegisterTrain(int train) {
        for (Train train2 : this.trains) {
            if (train2.getID() == train) {
                train2.register(0);
                System.out.println("train #" + train2.getID() + " has been deregistered succcessfully");
            }
        }
        System.out.println("The train was deregistered successfully");
    }

    /**
     * method checks if the train system object has the specified station
     * 
     * @param station
     *                name of station to be checked for
     * @return
     *         boolean
     *         true - the station exists
     *         false - the station does not exist
     */
    public Boolean containsStation(String station) {
        for (int i = 0; i < this.stations.size(); i++) {
            if (this.stations.get(i).getName().equals(station)) {
                return true;
            }
        }

        System.out.println("The Train System could not find a train station that matched your input");
        return false;
    }

    /**
     * method checks if the train system object has the specified segment
     * 
     * @param segment
     *                name of segment to be checked for
     * @return
     *         boolean
     *         true - the segment exists
     *         false - the segment does not exist
     */
    public Boolean containsSegment(String segment) {
        for (int i = 0; i < this.segments.size(); i++) {
            if (this.segments.get(i).getName().equals(segment)) {
                return true;
            }
        }

        System.out.println("The Train System could not find a segment that matched your input");
        return false;

    }

    /**
     * method checks if the train system object has the specified route
     * 
     * @param route
     *              name of route to be checked for
     * @return
     *         boolean
     *         true - the route exists
     *         false - the route does not exist
     */
    public Boolean containsRoute(String route) {
        for (int i = 0; i < this.routes.size(); i++) {
            if (this.routes.get(i).getName().equals(route)) {
                return true;
            }
        }

        System.out.println("The Train System could not find a route that matched your input");
        return false;

    }

    /**
     * method checks if the train system object has the specified train
     * 
     * @param train
     *              name of train to be checked for
     * @return
     *         boolean
     *         true - the train exists
     *         false - the train does not exist
     */
    public Boolean containstrain(int train) {
        for (Station station : this.stations) {
            for (Train train2 : station.getTrains()) {
                if (train2.getID() == train) {
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * method returns the name of the Station along with its status
     * 
     * @param station
     *                name of Station to get information for
     * @return
     *         Station name and status
     */
    public String getStationInfo(String station) {
        Station result;
        for (Station station2 : this.stations) {
            if (station2.getName().equals(station)) {
                result = station2;
                return "Station Name : " + result.getName() + "\nStation Status : " + (result.getStatus().toString())
                        + "\nStation Has Train: " + result.hasTrain() + "\nTrain List : " + result.getTrains();
            }
        }
        return "Station Name '" + station + "' could not be found";

    }

    /**
     * method returns the name of the Station's Segment along with its status
     * 
     * @param station
     *                name of Station to get information for
     * @return
     *         Segment's name and status
     */
    public String getSegmentInfo(String station) {
        Station result;
        for (Station station2 : this.stations) {
            if (station2.getName().equals(station)) {
                result = station2;
                return "Segment Name : " + result.getRoute().getSegments().get(0).getName() + "\nSegment Status : "
                        + (result.getRoute().getSegments().get(0).getStatus().toString())
                        + "\nSegment Has Train : " + result.getRoute().getSegments().get(0).hasTrain();
            }
        }
        return "Segment Name of '" + station + "' could not be found";

    }

    /**
     * method returns the name of the Station's Route along with its status
     * 
     * @param station
     *                name of Station to get information for
     * @return
     *         Route's name and status
     */
    public String getRouteInfo(String station) {
        Station result;
        for (Station station2 : this.stations) {
            if (station2.getName().equals(station)) {
                result = station2;
                return "Route Name : " + result.getRoute().getName() + "\nRoute Status : "
                        + (result.getRoute().geStatus().toString())
                        + "\n";
            }
        }
        return "Route Name of '" + station + "' could not be found";

    }

    /**
     * method returns the name of the Train along with other important attributes
     * 
     * @param train
     *              ID of train to get information for.
     * @return
     *         train's ID, train's register time and train's current location
     */
    public String getTrainInfo(int train) {

        for (Train train2 : this.trains) {
            if (train2.getID() == train) {
                return "Train ID : " + train2.getID() + "\n Register Time : " + train2.whenRegistered()
                        + "\n Current Location : " + train2.currentStation() + "\n";
            }
        }

        return "Train ID '" + train + "' information could not be found";

    }

    /**
     * method to set the train system object's attribute SystemStatus to OPERATIONAL
     */
    public void setToWorking() {
        this.status = SystemStatus.OPERATIONAL;
        System.out.println("The system has been set to 'WORKING'");
    }

    /**
     * method to set the train system object's attribute SystemStatus to DEADLOCKED
     */
    public void setPaused() {
        this.status = SystemStatus.DEADLOCKED;
        System.out.println("The system has been set to 'PAUSED'");

    }

    /**
     * method to set the train system object's attribute SystemStatus to FINISHED
     */
    public void setStopped() {
        this.status = SystemStatus.FINISHED;
        System.out.println("The system has been set to 'STOPPED'");
    }

    /**
     * method to output the current value of the train system object's system status
     * attribute as a string
     */
    public void currentStatus() {
        System.out.println("Current status of Train System : \n " + this.status + " description : "
                + this.status.getDescription());
    }

    /**
     * method to check if there are duplicate stations, routes or segments and if
     * they are verified
     * 
     * @return
     *         boolean
     *         true - all conditions have been passed
     *         false - failed a condition
     */
    public Boolean verify() {

        Station checkStation = this.stations.get(0);
        for (int i = 1; i < this.stations.size(); i++) {
            for (int j = 1; j < this.stations.size(); j++) {
                if (!this.stations.get(j).verify() && this.stations.get(j).getName().equals(checkStation.getName())) {
                    System.out
                            .println("Station " + this.stations.get(j).getName() + " is not verified by Train System");
                }
            }

            checkStation = this.stations.get(i);

        }

        Route checkRoute = this.routes.get(0);
        for (int i = 1; i < this.routes.size(); i++) {
            for (int j = 1; j < this.routes.size(); j++) {
                if (!this.routes.get(j).verify() && this.routes.get(j).getName().equals(checkRoute.getName())) {
                    System.out
                            .println("Route " + this.routes.get(j).getName() + " is not verified by Train System");
                }
            }

            checkRoute = this.routes.get(i);

        }

        Segment checkSegment = this.segments.get(0);
        for (int i = 1; i < this.segments.size(); i++) {
            for (int j = 1; j < this.segments.size(); j++) {
                if (!this.segments.get(j).verify() && this.segments.get(j).getName().equals(checkSegment.getName())) {
                    System.out
                            .println("Station " + this.segments.get(j).getName() + " is not verified by Train System");
                }
            }
            checkSegment = this.segments.get(i);
        }
        return true;

    }

    public void advance() {
    }
}