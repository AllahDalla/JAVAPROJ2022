package P1;

import java.util.ArrayList;
import P1.Segment;
import P1.Station;

class Route {
    private String name;
    private Boolean isRoundTrip;
    private RSStatus status;
    private ArrayList<Segment> segments = new ArrayList<Segment>(); // added attribute
    private ArrayList<Station> stations = new ArrayList<Station>(); // added attribute

    Route(String rname, Boolean isRoundTrip, RSStatus status, ArrayList<Station> stations) {
        this.name = rname;
        this.isRoundTrip = isRoundTrip;
        this.status = status;
        this.stations = stations;

    }

    /**
     * method is used to return the name of the Route Object
     * 
     * @return
     *         returns a String - name of Route Object
     */
    public String getName() {
        return this.name;
    }

    /**
     * method returns the current status of the Route Object
     * 
     * @return
     *         returns a RSStatus type Object
     * 
     * 
     *         RSSstatus can be :
     *         OPEN("Status is OPEN")
     *         CLOSEDFORMAINTENANCE("Status is CLOSED FOR MAINTENANCE");
     */
    public RSStatus geStatus() {
        return this.status;
    }

    /**
     * method returns the Route Object's segments list
     * 
     * @return
     *         An ArrayList of type 'Segment' is returned
     * 
     */
    public ArrayList<Segment> getSegments() {
        return this.segments;
    }

    /**
     * method checks if the route is a round trip.
     * 
     * @return
     *         returns a Boolean
     */
    public Boolean isRoundTrip() {
        return isRoundTrip;
    }

    /**
     * method gets the Route's starting station
     * 
     * @return
     *         returns a station object that is the start of the route
     */
    public Station getStart() {

        return this.stations.get(0);
    }

    /**
     * method gets the Route's ending station
     * 
     * @return
     *         returns a station object that is the end of the route
     */
    public Station getEnd() {

        return this.stations.get(this.stations.size() - 1);
    }

    /**
     * method checks the next station on the the current route and returns it's name
     * 
     * @param station
     *                name of the station to used to check for the next station that
     *                comes after it
     * @return
     *         The name (String) of the next station
     * 
     */
    public String getNextStation(String station) { // changed Return type to string as it seems to be the same method in
                                                   // essence to getPreviousStation
        for (int index = 0; index < this.stations.size(); index++) {
            if (this.stations.get(index).getName().equals(station)) {
                if (index == this.stations.size()) {
                    return "There is no station afterwards on route " + this.getName();
                } else {
                    return this.stations.get(index + 1).getName();
                }
            }
        }

        return "There is no station afterwards on route " + this.getName();
    }

    /**
     * method checks the previous station on the the current route and returns it's
     * name.
     * 
     * @param station
     *                name of the station to used to check for the previous station
     *                that
     *                comes before it
     * @return
     *         The name (String) of the previous station
     * 
     */
    public String getPreviousStation(String station) {
        for (int index = 0; index < this.stations.size(); index++) {
            if (this.stations.get(index).getName().equals(station)) {
                if (index == 0) {
                    return this.stations.get(index).getName();
                } else {
                    return this.stations.get(index - 1).getName();
                }
            }
        }

        return "There is no previous station on route " + this.getName();
    }

    /**
     * method checks to see if the station that is given can be accessed, meaning
     * that it is along the current route
     * 
     * @param station
     *                a string that is the name of the station to be checked if it's
     *                along the route
     * @return
     *         boolean - this tells if the station if along the route or not
     */
    public Boolean canGetTo(String station) {
        for (Station station2 : this.stations) {
            if (station2.getName().equals(station)) {
                return true;
            }
        }
        return false;
    }

    /**
     * method adds a segment to the route
     * 
     * @param segment
     *                a segment object to be added to the current route
     */
    public void addSegment(Segment segment) {
        this.segments.add(segment);
    }

    /**
     * method adds a list of segments to the route
     * 
     * @param segments
     *                 an ArrayList of type Segment to be added to the current route
     */
    public void addSegments(ArrayList<Segment> segments) {
        for (Segment segment : segments) {
            this.segments.add(segment);
        }
    }

    /**
     * method removes a segment from the route
     * 
     * @param segment
     *                name of segment (String) to be removed from the current route
     */
    public void removeSegment(String segment) {
        for (Segment x : this.segments) {
            if (x.getName().equals(segment)) {
                this.segments.remove((x));
            }
        }
    }

    /**
     * method checks if a segment is in/on a route object
     * 
     * @param segment
     *                name of segment (String) to be checked for
     * @returns
     *          boolean to say whether the segment exists in the route object
     *          true - it exists
     *          false - it does not exist
     * 
     */
    public Boolean containsSegment(String segment) { // changed return type to fit description of function. Function
                                                     // seems to be asking to check if the route contains a segment
        for (Segment x : this.segments) {
            if (x.getName().equals(segment)) {
                return true;
            }
        }
        return false;
    }

    /**
     * method changes the segment's light color attribute to the opposite color
     * 
     * @param startOfSegment
     *                       the segment that the light color attribute is needed to
     *                       be changed for.
     */
    public void changeLight(String startOfSegment) {
        for (Segment segment : this.segments) {
            if (segment.getName().equals(startOfSegment)) {
                segment.changeLight();
            }
        }

    }

    /**
     * method checks if the name of the route is null or does not contain
     * characters. It also checks the route's segments'
     * names if it is null or does not contain charaters. It also checks if the
     * route is a Round Trip and that there are no duplicated
     * segments (only if they are not Round trip routes). Finally, it also checks
     * that each segment is also verified.
     * 
     * @return
     *         a boolean to signify that it has passed the verification conditions.
     *         false - it did not pass true - it passed
     */
    public Boolean verify() {
        if (getName().equals(null) || getName().equals(" ") || getName().equals("")) {
            return false;
        }

        for (Segment segment : this.segments) {
            if (segment.getName().equals(null) || segment.getName().equals(" ") || segment.getName().equals("")) {
                return false;
            }
        }

        if (this.isRoundTrip()) {
            if (!this.stations.get(0).getName().equals(this.stations.get(this.stations.size() - 1).getName())) {
                return false;
            }
        } else {
            if (this.stations.get(0).getName().equals(this.stations.get(this.stations.size() - 1).getName())) {
                return false;
            }
        }

        if (this.segments.size() > 0) {
            Segment check = this.segments.get(0);
            if (!this.isRoundTrip()) {
                for (int x = 1; x < this.segments.size(); x++) {
                    for (int z = 1; z < this.segments.size(); z++) {
                        if (this.segments.get(z).getName().equals(check.getName())) {
                            System.out.println(
                                    "Segment " + this.segments.get(z).getName() + ", index #" + String.valueOf(z)
                                            + " is repeated");
                            return false;
                        }
                    }

                    check = this.segments.get(x);

                }
            }

        }

        for (Segment segment : this.segments) {
            if (segment.verify()) {
                continue;
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * method changes the status of the route to close - RSStatus
     * (CLOSEDFORMAINTENANCE)
     */
    public void close() {
        this.status = RSStatus.CLOSEDFORMAINTENANCE;
    }

    /**
     * method changes the status of the route to open - RSStatus (OPEN)
     */
    public void open() {
        this.status = RSStatus.OPEN;
    }

    /**
     * method returns the stations that that are along the current route
     * 
     * @return
     *         an ArrayList of type Station is returned
     */
    public ArrayList<Station> getStationList() {
        return this.stations;
    }

}