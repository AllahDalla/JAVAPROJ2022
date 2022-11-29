package p2;

import p2.event.Event;

public class MoveEvent extends Event {
	private String fromStation, toStation; /* once set are immutable */

	public MoveEvent(String objectID, int time, String fromStation, String toStation) {
		super(objectID, time);
		this.fromStation = fromStation;
		this.toStation = toStation;
	}

	@Override
	public String toString() {
		return "MoveEvent [" + super.toString() + ", From Station=" + fromStation + ", To Station=" + toStation + "]";
	}

	@Override
	public boolean equals(Object me) {
		if (me instanceof MoveEvent)
			return super.equals(me) && fromStation.equals(((MoveEvent) me).fromStation)
					&& toStation.equals(((MoveEvent) me).toStation);
		return false;
	}
}
