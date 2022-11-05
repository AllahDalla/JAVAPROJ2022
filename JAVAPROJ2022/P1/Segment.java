package P1;

import java.util.ArrayList;

public class Segment {
    private String name;
    private RSStatus status;
    private ArrayList<Train> trains = new ArrayList<Train>(); // added attributes
    private Light light = Light.GREEN;

    Segment(String segName, RSStatus status, String start, String end) {
        this.name = segName;
        this.status = status;
    }

    /**
     * method is used to return the name of the Segment Object
     * 
     * @return
     *         returns a String - name of Segment Object
     */
    public String getName() {
        return this.name;
    }

    /**
     * method returns the current status of the Segment Object
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
     * method checks if the Segment currently has a train
     * 
     * @return
     *         a boolean true - has a train
     *         false - does not have a train
     */
    public Boolean hasTrain() {
        int size = this.trains.size();
        if (size == 0) {
            return false;
        }
        return true;
    }

    /**
     * method checks if teh Segment's status is 'open'.
     * 
     * @return
     *         a boolean
     *         true - Segment is open
     *         false - Segment is not open
     */
    public Boolean isOpen() {
        if (this.status == RSStatus.OPEN) {
            return true;
        }
        return false;
    }

    /**
     * method adds train to the Segment's attribute and changes the light color to
     * red
     * 
     * @param train
     *              train object to be added to the Segment
     */
    public void acceptTrain(Train train) {
        this.trains.add(train);
        this.light = Light.RED;
    }

    /**
     * method removes train from the Segment's attribute and changes the light to
     * green
     */
    public void releaseTrain() {
        this.trains.clear();
        this.light = Light.GREEN;
    }

    /**
     * method changes the Segment's attribute of light to the opposite color it was
     * previously. If light is
     * green, it will change to red and vice versa
     */
    public void changeLight() {
        if (this.light == Light.GREEN) {
            this.light = Light.RED;
        } else {
            this.light = Light.GREEN;
        }

    }

    /**
     * method checks if the light color is green
     * 
     * @return
     *         a boolean
     *         true - the light color is green
     *         false - the light color is red
     */
    public Boolean lightColor() {
        if (this.light == Light.GREEN) {
            return true;
        }
        return false;
    }

    /**
     * method checks if the Segment's name is null or if it has any characters. It
     * also checks if the light attribute of the Segment is null
     * 
     * @return
     *         a boolean that signifies that it has passed all verifications. true -
     *         it passed
     *         false - it failed
     */
    public Boolean verify() {
        if (getName().equals(null) || getName().equals(" ") || getName().equals("")) {
            return false;
        }

        if (this.light == null) { // needs to check segment's start and end, however, project does not include
                                  // thses attributes
            return false;
        }

        // this function should also check that the segment's start and end are not the
        // same and that if they are open then the traffic light should be green and
        // vice versa. Traffic light also was given as an attribute in teh UML diagram.
        // It was added later
        return true;
    }

    /**
     * method changes the Segment's status attribute to closed
     * (RSStatus.CLOSEDFORMAINTENANCE)
     */
    public void close() {
        this.status = RSStatus.CLOSEDFORMAINTENANCE;
    }

    /**
     * method changes the Segment's status attribute to open (RSStatus.OPEN)
     */
    public void open() {
        this.status = RSStatus.OPEN;
    }
}
