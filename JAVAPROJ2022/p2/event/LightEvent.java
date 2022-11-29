package p2.event;

import p2.enums.Light;

public class LightEvent extends Event {

	private Light fromColour, toColour; /* once set are immutable */

	public LightEvent(String objectID, int time, Light fromColour, Light toColour) {
		super(objectID, time);
		this.fromColour = fromColour;
		this.toColour = toColour;
	}

	@Override
	public String toString() {
		return "LightEvent [" + super.toString() + ", From colour=" + fromColour.getDescription() + ", To colour="
				+ toColour.getDescription() + "]";
	}

	@Override
	public boolean equals(Object le) {
		if (le instanceof LightEvent)
			return super.equals(le) && fromColour == ((LightEvent) le).fromColour
					&& toColour == ((LightEvent) le).toColour;
		return false;
	}

}
