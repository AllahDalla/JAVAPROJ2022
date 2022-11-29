package p2.logging;

import p2.ts.TrafficLight;
import p2.MoveEvent;
import p2.enums.Action;
import p2.enums.Light;
import p2.enums.RSStatus;
import p2.event.CFOSEvent;
import p2.event.Event;
import p2.event.LightEvent;
import p2.interfaces.OpenClose;

public class Segment extends Logable implements Comparable<Segment>, OpenClose {

	private Station segmentStart, /* once set, is immutable */
			segmentEnd; /* once set, is immutable */
	private String name; /* once set, is immutable */
	private RSStatus status = RSStatus.Open;
	private TrafficLight trafficLight = new TrafficLight();
	private String train = "-1";

	public Segment(String name, Station segmentStart, Station segmentEnd) {
		setSegmentStart(segmentStart);
		setSegmentEnd(segmentEnd);
		setName(name);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name != null && !name.equals(this.name) ? name.trim() : this.name;
	}

	public Station getSegmentStart() {
		return segmentStart;
	}

	private void setSegmentStart(Station segmentStart) {
		// to align with the requirements at the beginning where
		// if segmentstart is set, it should be immutable.
		// Therefore, function will be changed to fill that requirement.

		if (this.segmentStart != null) {
			System.out.println("Cannot change value for Segment's segmentStart as it's already been set.\n");
		} else {
			this.segmentStart = segmentStart; // original line of code.
		}
	}

	public Station getSegmentEnd() {
		return segmentEnd;
	}

	private void setSegmentEnd(Station segmentEnd) {
		// to align with the requirements at the beginning where
		// if segmentEnd is set, it should be immutable.
		// Therefore, function will be changed to fill that requirement.

		if (this.segmentEnd != null) {
			System.out.println("Cannot change value for Segment's segmentEnd as it's already been set.\n");
		} else {
			this.segmentEnd = segmentEnd; // original line of code.
		}
	}

	public boolean hasTrain() {

		return !train.equals("-1");
	}

	@Override
	public boolean isOpen() {
		return status == RSStatus.Open;
	}

	public Event acceptTrain(String train) {
		System.out.println("Segment.acceptTrain()\n");
		if ((train != null && train.trim() != "") && isOpen()) {
			changeLight();
			this.train = train;
		}
		MoveEvent moveEvent = new MoveEvent("Accept", 0, this.getSegmentStart().getName(),
				this.getSegmentEnd().getName());
		System.out.println("Needs revision for releasing train event.\n");
		System.out.println("Move Event (accept) was created.\n");
		System.out.println(moveEvent.toString());
		return moveEvent;
	}

	public Event releaseTrain() {
		System.out.println("Segment.releaseTrain()\n");
		if (isOpen()) {
			changeLight();
			train = "-1";
		}
		MoveEvent moveEvent = new MoveEvent("Release", 0, this.getSegmentStart().getName(),
				this.getSegmentEnd().getName());
		System.out.println("Move Event (release) was created.\n");
		System.out.println(moveEvent.toString());
		return moveEvent;
	}

	public Event changeLight() {
		System.out.println("Segment.changeLight()\n");
		trafficLight.changeLight();
		LightEvent lightEvent = new LightEvent("Change Light", 0, lightColour(),
				lightColour() == Light.Red ? Light.Green : Light.Red);
		System.out.println("Light event was created\n");
		System.out.println(lightEvent.toString());
		return lightEvent;
	}

	public Light lightColour() {
		return trafficLight.getColour();
	}

	public boolean verify() {
		boolean verified = true; // originnal line of code

		if (getName().equals(null) || getName().equals(" ") || getName().equals("")) {
			System.out.println("Segment's name cannot be verified\n");
			return false;
		}

		if (this.trafficLight == null) {
			System.out.println("Segment's traffic light is not set\n");
			return false;
		}

		if (this.segmentEnd == null || this.segmentStart == null) {
			System.out.println("Segment's end or segment's start has not been set\n");
			return false;
		}

		if (this.segmentEnd.getName().equals(this.segmentStart.getName())
				|| (compareTo(this) < 0 || compareTo(this) > 0)) {
			System.out.println("Segment's end  and start have the same name\n");
			return false;
		}

		if (this.isOpen()) {
			if ((this.segmentEnd.isOpen() && this.segmentStart.isOpen()) == false) {
				System.out.println("Segment is open but segment's end or start is not open.\n");
				return false;
			}
		} else {
			if ((this.segmentEnd.isOpen() || this.segmentStart.isOpen()) == true) {
				System.out.println("Segment is not open but segment's end or start is open. \n");
				return false;
			}
		}

		return verified; // original line of code

	}

	@Override
	public Event close() {
		System.out.println("Segment.close()\n");
		status = status == RSStatus.Open ? status = RSStatus.ClosedForMaintenance : status;
		CFOSEvent close = new CFOSEvent("Close", 0, Action.Close);
		return close;
	}

	@Override
	public Event open() {
		System.out.println("Segment.open()\n");
		status = status == RSStatus.ClosedForMaintenance ? status = RSStatus.Open : status;
		CFOSEvent open = new CFOSEvent("Open", 0, Action.Open);
		return open;
	}

	@Override
	public String toString() {
		return "Segment [name=" + name + ", segmentStart=" + (segmentStart == null ? "none" : segmentStart.getName())
				+ ", segmentEnd=" + (segmentEnd == null ? "none" : segmentEnd.getName()) + ", status="
				+ status.getDescription() + ", trafficLight=" + trafficLight + ", train="
				+ (train.equals("-1") ? "none" : train) + ", verified=" + (verify() ? "Yes" : "No") + "]";
	}

	@Override /* two segments with the same start and end stations are the same segment */
	public int compareTo(Segment segment) {
		String s = segmentStart.getName().concat(segmentEnd.getName()),
				e = segment.getSegmentStart().getName().concat(segment.getSegmentEnd().getName());
		return s.compareTo(e);
	}

	@Override
	public boolean validate() {
		return super.validate();
	}
}
