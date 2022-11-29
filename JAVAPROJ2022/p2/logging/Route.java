package p2.logging;

import java.util.ArrayList;
import java.util.Iterator;

import p2.enums.Action;
import p2.enums.Light;
// import p2.Segment;
// import p2.Station;
import p2.enums.RSStatus;
import p2.event.CFOSEvent;
import p2.event.Event;
import p2.event.LightEvent;
import p2.interfaces.OpenClose;

public class Route extends Logable implements Comparable<Route>, OpenClose {

	private String name; /* once set, name is immutable */
	private boolean isRoundTrip = false; /* once set, isRoundTrip is immutable */
	private RSStatus status = RSStatus.Open;
	private ArrayList<Segment> segments = new ArrayList<Segment>();

	public Route(String name, boolean isRoundTrip) {
		setName(name);
		setRoundTrip(isRoundTrip);
	}

	public Route(String name) {
		setName(name);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		// to align with the requirements at the beginning where
		// if name is set, it should be immutable.
		// Therefore, function will be changed to fill that requirement.

		if (this.name != null) {
			System.out.println("Cannot set new value for Route's name");
		} else {
			this.name = name; // original line of code.
		}
	}

	@Override
	public boolean isOpen() {
		return status == RSStatus.Open;
	}

	public void setStatus(RSStatus status) {
		this.status = status;
	}

	private void setRoundTrip(boolean isRoundTrip) {
		// to align with the requirements at the beginning where
		// if isroundtrip is set, it should be immutable.
		// Therefore, function will be changed to fill that requirement.

		if (this.isRoundTrip == true) {
			System.out.println("Cannot set new value for Route's round trip.\n");

		} else {
			this.isRoundTrip = isRoundTrip; // original line of code
			System.out.println("Route " + this.getName() + "'s round trip status has been set. \n");
		}
	}

	public boolean isRoundTrip() {
		return isRoundTrip;
	}

	private ArrayList<Station> getStationList() {
		System.out.println("Route.getStationList()\n");
		ArrayList<Station> sss = new ArrayList<Station>();
		if (this.segments.size() > 0) {
			for (Segment sg : this.segments) {
				sss.add(sg.getSegmentStart());
				sss.add(sg.getSegmentEnd());
			}
		} else {
			System.out.println("There are no segments in " + getName() + "\n");
		}

		return sss;
	}

	public ArrayList<String> getStationStrings() {
		ArrayList<String> sss = new ArrayList<String>();
		for (Station s : getStationList())
			sss.add(s.getName());
		return sss;
	}

	public Station getStart() {
		return segments.size() > 0 ? segments.get(0).getSegmentStart() : null;
	}

	public Station getEnd() {
		return segments.size() > 0 ? segments.get(segments.size() - 1).getSegmentEnd() : null;
	}

	public String getNextStation(String station, boolean isAtStart) {

		String empty = "";
		if (segments.size() > 0 && station != null) {

			if (isRoundTrip)
				if (isAtStart)
					return segments.get(0).getSegmentEnd().getName();
				else if (station.equals(getStart().getName()))
					return empty;

			ArrayList<Station> sss = getStationList();
			for (Station x : sss)
				if (x.getName().equals(station))
					if (sss.indexOf(x) + 1 == sss.size())
						return empty;
					else
						return sss.get(sss.indexOf(x) + 1).getName();
		}
		return empty;
	}

	public String getPreviousStation(String station, boolean isAtStart) {
		// writing function to work as 'getNextStation()'
		String empty = "";
		if (segments.size() > 0 && station != null) {
			if (isRoundTrip) {
				if (isAtStart) {
					return segments.get(0).getSegmentStart().getName();
				} else if (station.equals(getStart().getName())) {
					return empty;
				}
			}
			ArrayList<Station> sss = getStationList();
			for (Station x : sss)
				if (x.getName().equals(station))
					if (sss.indexOf(x) - 1 < 0)
						return empty;
					else
						return sss.get(sss.indexOf(x) - 1).getName();
		}

		return ""; // original line of code
	}

	public boolean canGetTo(String station) {
		for (Station x : getStationList())
			if (x.getName().equals(station))
				return true;
		return false;
	}

	public boolean isInnerStation(String station) {
		// returns true if station is not a start or end station for the route

		// based on requirements, function needs to check whether the station
		// is a start or end station for the route.

		int size = this.segments.size();

		if (this.segments.get(0).getName().equals(station) || this.segments.get(size - 1).getName().equals(station)) {
			return true; // original line of code
		}
		return false;

	}

	public void addSegment(Segment segment) {

		if (segments.size() == 0) {
			segments.add(segment);
			return;
		}

		if (segments.size() > 0 && !containsSegmentName(segment.getName())
				&& segment.getSegmentStart().compareTo(segments.get(segments.size() - 1).getSegmentEnd()) == 0)
			segments.add(segment);
	}

	public void addSegments(ArrayList<Segment> sgmnts) {
		if (sgmnts.size() > 0)
			sgmnts.forEach(s -> addSegment(s));
	}

	public void removeSegment(String segment) {

		if (segments.size() == 0)
			return;

		if (segments.size() > 0 && containsSegmentName(segment.trim())) {
			int i = 0;
			do {
				if (segments.get(i).getName().equals(segment.trim()))
					break;
				i++;
			} while (i < segments.size());

			segments.remove(i);
		}
	}

	public boolean containsSegmentName(String segment) {
		Iterator<Segment> sgs = segments.iterator();
		while (sgs.hasNext()) {
			if (sgs.next().getName().equals(segment))
				return true;
		}
		return false;
	}

	public Event changeLight(String startOfSegment) {
		LightEvent lightEvent = null;
		for (Segment s : segments) {
			if (s.getName().equals(startOfSegment)) {
				Light currentLight = s.lightColour();
				Light nextLight = currentLight == Light.Red ? Light.Green : Light.Red;
				lightEvent = new LightEvent("Change Light", 0, currentLight, nextLight);
				s.changeLight();
				break;
			}
		}

		return lightEvent;
	}

	public boolean verify() {
		boolean verified = true; // original line of code

		if (getName().equals("") || getName().equals(null) || getName().equals(" ")) {
			System.out.println("Route name could not be verified.\nRoute " + getName() + " cannot ve verified.\n");
			return false;
		}

		for (Segment sg : this.segments) {
			if (sg == null) {
				System.out.println(
						"Segment " + sg.getName() + " is empty.\nRoute " + getName() + " cannot be verified.\n");
				return false;
			}
		}

		if (isRoundTrip()) {
			Station start = this.segments.get(0).getSegmentStart();
			Station end = this.segments.get(segments.size() - 1).getSegmentEnd();

			if (!start.getName().equals(end.getName())) {
				System.out.println("Route is a round trip but start and end are not the same.\n");
				System.out.println("Start Station for " + getName() + " is " + start.getName() + "\n");
				System.out.println("End Station for " + getName() + " is " + end.getName() + "\n");
				System.out.println("Route could not be verified.\n");
				return false;
			}
		} else {
			Station start = this.segments.get(0).getSegmentStart();
			Station end = this.segments.get(segments.size() - 1).getSegmentEnd();
			if (start.getName().equals(end.getName())) {
				System.out.println("Route is not a round trip but start and end are the same.\n");
				System.out.println("Start Station for " + getName() + " is " + start.getName() + "\n");
				System.out.println("End Station for " + getName() + " is " + end.getName() + "\n");
				System.out.println("Route could not be verified.\n");
				return false;
			}

		}

		// segments should also be sequenced here ->

		for (int i = 0; i < this.segments.size(); i++) {
			Segment checker = this.segments.get(i);
			for (int j = 0; j < this.segments.size(); j++) {
				if (i == j) {
					continue;
				} else {
					if (checker.getName().equals(this.segments.get(j).getName())) {
						System.out.println("Duplicate segment found in " + getName() + "\n");
						System.out.println(this.toString() + "\n");
						System.out.println("Duplicate : " + checker.getName() + "\n");
						return false;
					}
				}
			}
		}

		for (Segment sg : this.segments) {
			if (!sg.verify()) {
				System.out.println(sg.getName() + " for " + getName() + " is not verified.\n");
				System.out.println("Route could not be verified.\n");
				return false;
			}
		}

		return verified; // original line of code
	}

	@Override
	public Event close() {
		System.out.println("Route.close()\n");
		status = status == RSStatus.Open ? status = RSStatus.ClosedForMaintenance : status;
		CFOSEvent close = new CFOSEvent("Close", 0, Action.Finish);
		return close;
	}

	@Override
	public Event open() {
		System.out.println("Route.open()\n");
		status = status == RSStatus.ClosedForMaintenance ? status = RSStatus.Open : status;
		CFOSEvent open = new CFOSEvent("Open", 0, Action.Open);
		return open;
	}

	@Override
	public String toString() {
		String str = "[";
		for (Segment s : segments)
			str += s.getName() + (segments.get(segments.size() - 1) == s ? "]" : ", ");

		return "Route [name=" + name + ", isRoundTrip=" + isRoundTrip + ", status=" + status + ", segments="
				+ (segments == null || segments.size() == 0 ? "none" : str) + ", verified=" + (verify() ? "Yes" : "No")
				+ "]";
	}

	@Override /* mostly used to sort a collection of Route Objects */
	public int compareTo(Route route) {
		return name.compareTo(route.getName());
	}

	@Override
	public boolean validate() {
		return super.validate();
	}
}
