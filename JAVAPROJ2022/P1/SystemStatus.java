package P1;

public enum SystemStatus {
    INITIALISED("System is Initialised"),
    OPERATIONAL("System is Operational"),
    DEADLOCKED("System is Deadlocked"),
    FINISHED("No More trains!");

    private String description;

    /**
     * Method sets the value of the class attribute description
     * 
     * @param description
     *                    A string that may describe one of teh values in the enum
     *                    class to be set as the class attribute 'description'
     */
    SystemStatus(String description) {
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
