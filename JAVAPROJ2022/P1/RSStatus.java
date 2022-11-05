package P1;

public enum RSStatus {
    OPEN("Status is OPEN"),
    CLOSEDFORMAINTENANCE("Status is CLOSED FOR MAINTENANCE");

    private String description;

    /**
     * Method sets the value of the class attribute description
     * 
     * @param description
     *                    A string that may describe one of teh values in the enum
     *                    class to be set as the class attribute 'description'
     */
    RSStatus(String description) {
        this.description = description;
    }

    /**
     * method returns the class attribute 'description'
     * 
     * @return
     *         returns a String - class attribute 'description'
     */
    public String getDescription() {
        return description;
    }
}
