package P1;

public class TrafficLight {
    private int Id;
    private Light colour = Light.GREEN;

    TrafficLight(int id, Light colour) {
        this.Id = id;
        this.colour = colour;
    }

    /**
     * method changes the color attribute to the opposite color. If light is green
     * then it will become red and vice versa
     */
    public void change() {
        if (this.colour == Light.RED) {
            this.colour = Light.GREEN;
        } else {
            this.colour = Light.RED;
        }
    }

    /**
     * mthod checks if color attribute is null
     * 
     * @returns
     *          a boolean
     *          true - passed condition
     *          false - failed condition
     */
    public Boolean verify() { // added function based on project's requirements
        if (this.colour == null) {
            return false;
        }
        return true;
    }
}
