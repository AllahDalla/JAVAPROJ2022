
import p2.enums.Action;
import p2.event.Event;

public class CFOSEvent extends Event {

	private Action action;

	public CFOSEvent(String object, int time, Action action) {
		super(object, time);
		this.action = action;
	}

	@Override
	public boolean equals(Object oe) {
		if (oe instanceof CFOSEvent)
			return super.equals(oe) && action == ((CFOSEvent) oe).action;
		return false;
	}

	@Override
	public String toString() {
		return action.getDescription() + " Event [" + super.toString() + "]";
	}

}
