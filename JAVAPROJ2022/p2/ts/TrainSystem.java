package p2.ts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import p2.enums.ObjectType;
import p2.enums.RSStatus;
import p2.enums.SystemStatus;
import p2.event.Event;
import p2.logging.Route;
import p2.logging.Segment;
import p2.logging.Station;
import p2.logging.Train;

public class TrainSystem {

	/* default values have been set */
	private SystemStatus status = SystemStatus.Initialised;
	private int currentTime = -1;

	private ArrayList<Route> routes = new ArrayList<Route>();
	private ArrayList<Train> trains = new ArrayList<Train>();
	private ArrayList<Station> stations = new ArrayList<Station>();
	private ArrayList<Segment> segments = new ArrayList<Segment>();

	public TrainSystem() {
	}

	public void addStation(String sname) {
		Station station = new Station(sname);
		this.stations.add(station);
		System.out.println("Station '" + sname + "' has been added successfully\n");
	}

	public void removeStation(String sname) {
		for (Station station : this.stations) {
			if (station.getName().equals(sname)) {
				this.stations.remove(station);
				System.out.println("Station '" + station.getName() + "' has been removed successfully\n");
			}
		}
	}

	// needs to return proper event
	public Event openStation(String sname) {
		// ensure you collect the event and return it
		System.out.println("TrainSystem.openStation()\n");
		for (Station station : this.stations) {
			if (station.getName().equals(sname)) {
				System.out.println("Station '" + station.getName() + "' is now open\n");
				return station.open();
			}
		}

		return null;
	}

	// needs to return proper event
	public Event closeStation(String sname) {
		// ensure you collect the event and return it
		System.out.println("TrainSystem.closeStation()\n");
		for (Station station : this.stations) {
			if (station.getName().equals(sname)) {
				System.out.println("Station '" + station.getName() + "' is now closed\n");
				return station.close();
			}
		}

		return null;
	}

	public void addSegment(String sname, String start, String sEnd) {
		System.out.println("TrainSystem.addSegment()\n");
		Station startStation = null;
		Station endStation = null;
		for (Station st : this.stations) {
			if (st.getName().equalsIgnoreCase(start)) {
				startStation = st;
				System.out.println("Start Station has been assigned\n");
			}
			if (st.getName().equalsIgnoreCase(sEnd)) {
				endStation = st;
				System.out.println("End Station has been assigned\n");

			}
		}

		if (startStation == null) {
			System.out.println("A Start Station was not assigned.\n");
			System.out.println("Name searched : " + start + "\n");
		} else if (endStation == null) {
			System.out.println("An End Station was not assigned.\n");
			System.out.println("Name searched : " + sEnd + "\n");
		}
		Segment newSegment = new Segment(sname, startStation, endStation);
		this.segments.add(newSegment);
		System.out.println("The segment '" + sname + "' has been added successfully\n");
	}

	public void removeSegment(String sname) {
		for (Segment segment : this.segments) {
			if (segment.getName().equals(sname)) {
				this.segments.remove(segment);
				System.out.println("Segment '" + segment.getName() + "' has been removed successfully\n");
			}
		}
	}

	// needs to return a proper event
	public Event openSegment(String sname) {
		// ensure you collect the event and return it
		System.out.println("TrainSystem.openSegment()\n");
		for (Segment segment : this.segments) {
			if (segment.getName().equals(sname)) {
				System.out.println("Segment '" + segment.getName() + "' is now open\n");
				return segment.open();
			}
		}

		return null;
	}

	// needs to return a proper event
	public Event closeSegment(String sname) {
		// ensure you collect the event and return it
		System.out.println("TrainSystem.closeSegment()\n");
		for (Segment segment : this.segments) {
			if (segment.getName().equals(sname)) {
				System.out.println("Segment '" + segment.getName() + "' is now closed\n");
				return segment.close();
			}
		}

		return null;
	}

	// needs revision // still needs revision
	public void addRoute(String rname, boolean isRoundTrip, ArrayList<String> segs) {
		System.out.println("TrainSystem.addRoute()\n");
		Route r = new Route(rname, isRoundTrip);
		ArrayList<Segment> s = new ArrayList<Segment>();
		Station start = null;
		Station end = null;
		for (Segment sg : this.segments) {
			if (sg.getName().equals(segs.get(0))) {
				for (Station st : this.stations) {
					if (st.getName().equals(sg.getSegmentStart().getName())) {
						start = st;
					}
				}
				System.out.println("Start Station has been created for route.\n");
			}

			if (sg.getName().equals(segs.get(segs.size() - 1))) {
				for (Station st : this.stations) {
					if (st.getName().equals(sg.getSegmentEnd().getName())) {
						end = st;
					}
				}
				System.out.println("End Station has been created for route.\n");
			}
		}
		if (start == null) {
			System.out.println("No matches were found for Start Station.\n");
		} else if (end == null) {
			System.out.println("No matches were found for End Station.\n");
		} else {
			for (int i = 0; i < segs.size(); i++) {

				for (Segment sg : this.segments) {
					if (sg.getName().equals(segs.get(i))) {
						s.add(sg);
					}
				}

			}

			r.addSegments(s);
			System.out.println("Route has been added successfully\n");
			System.out.println("Name of route added : " + r.getName());
			this.routes.add(r);

		}

	}

	public void removeRoute(String rname) {
		for (Route route : this.routes) {
			if (route.getName().equals(rname)) {
				this.routes.remove(route);
				System.out.println("Route '" + route.getName() + "' has been removed successfully\n");
			}
		}
	}

	// needs to return proper event
	public Event openRoute(String rname) {
		/* for P2, renamed to OpenRoute from OpenSRoute */

		// ensure you collect the event and return it

		for (Route route : this.routes) {
			if (route.getName().equals(rname)) {
				System.out.println("Route " + route.getName() + " is now open\n");
				return route.open();
			}
		}

		return null;
	}

	// needs to return proper event
	public Event closeRoute(String rname) {
		// ensure you collect the event and return it

		for (Route route : this.routes) {
			if (route.getName().equals(rname)) {
				System.out.println("Route " + route.getName() + "is now closed\n");
				return route.close();
			}
		}

		return null;
	}

	public void addTrain(String name, int startTime) {
		Train t = new Train(name, startTime);
		this.trains.add(t);
		System.out.println("Train has been added successfully.\n");
	}

	public void removeTrain(String name) {
		for (int index = 0; index < this.trains.size(); index++) {
			if (this.trains.get(index).getName().equals(name)) {
				this.trains.remove(index);
				System.out.println("Train has been removed successfully.\n");
			}
		}
	}

	// needs to be figured out properly
	public void registerTrain(String train, String route, ArrayList<String> stops) {
		System.out.println("TrainSystem.registerTrain()\n");
		int x = 0;
		ArrayList<Station> routeStations = new ArrayList<Station>();
		for (Route rt : this.routes) {
			if (rt.getName().equals(route)) {
				System.out.println("Route has been matched successfully.\n");
				if (x == 0) {
					for (Train tr : this.trains) {
						if (tr.getName().equals(train)) {

							tr.changeRoute(rt);
							System.out.println(
									"Route " + rt.getName() + " has been added to train " + tr.getName() + "\n");
							x = 1;
						}
					}
				}
				System.out.println(rt.toString());
				System.out.println("\nVerifying stops . . . \n");
				for (Segment sg : this.segments) {
					if (rt.containsSegmentName(sg.getName())) {
						for (String string : stops) {
							if (sg.getSegmentStart().getName().equals(string) && !sg.getSegmentStart().isOpen()) {
								System.out.println("Train is predicted to stop at " + string + " but status is "
										+ sg.getSegmentStart().getStatus() + "\nTrain cannot be registered.\n");
								return;
							}

							if (sg.getSegmentEnd().getName().equals(string) && !sg.getSegmentEnd().isOpen()) {
								System.out.println("train is predicted to stop at " + string + " but status is "
										+ sg.getSegmentEnd().getStatus() + "\nTrain cannot be registered.\n");
								return;
							}

						}

						routeStations.add(sg.getSegmentStart());
						routeStations.add(sg.getSegmentEnd());
					}
				}

				break;
			}
		}
		ArrayList<String> matched = new ArrayList<String>();
		for (Station st : routeStations) {
			for (String string : stops) {
				if (matched.size() > 0) {
					if (st.getName().equals(string)) {
						if (matched.contains(string)) {
							continue;
						} else {
							matched.add(string);
						}
					}

				} else {
					if (st.getName().equals(string)) {
						matched.add(string);
					}
				}

			}
		}

		for (String string : stops) {
			if (!matched.contains(string)) {
				System.out.println("Train predicted to have stop at station that is not on route.\n");
				System.out.println("Stop being predicted is: " + string + "\n");
				return;
			}
		}

		for (Train tr : this.trains) {
			if (tr.getName().equalsIgnoreCase(train)) {
				tr.register(this.currentTime);
				System.out.println(getTrainInfo(tr.getName()));
				System.out.println("Current time = " + this.currentTime + "\n");
				System.out.println("Register time  = " + tr.whenRegistered() + "\n");
				System.out.println("Train has been registered successfully.\n");
				return;
			}
		}

	}

	public void deRegisterTrain(String train) {
		for (Train tr : this.trains) {
			if (tr.getName().equals(train)) {
				tr.deregister();
			}
		}
	}

	public boolean containsStation(String station) {
		for (int i = 0; i < this.stations.size(); i++) {
			if (this.stations.get(i).getName().equals(station)) {
				return true;
			}
		}

		System.out.println("The Train System could not find a train station that matched your input\n");
		return false;
	}

	public boolean containsSegment(String segment) {
		for (int i = 0; i < this.segments.size(); i++) {
			if (this.segments.get(i).getName().equals(segment)) {
				return true;
			}
		}

		System.out.println("The Train System could not find a segment that matched your input\n");
		return false;

	}

	public boolean containsRoute(String route) {
		for (int i = 0; i < this.routes.size(); i++) {
			if (this.routes.get(i).getName().equals(route)) {
				return true;
			}
		}

		System.out.println("The Train System could not find a route that matched your input\n");
		return false;

	}

	public boolean containsTrain(int train) {
		for (Train tr : this.trains) {
			if (tr.getId() == train) {
				return true;
			}
		}
		return false;
	}

	public String getStationInfo(String station) {
		for (Station st : this.stations) {
			if (st.getName().equals(station)) {
				return st.toString();
			}
		}
		return "No station found in system\n";
	}

	public String getSegmentInfo(String segment) {
		for (Segment sg : this.segments) {
			if (sg.getName().equals(segment)) {
				return sg.toString();
			}
		}
		return "No segment found in the system\n";
	}

	public String getRouteInfo(String route) {
		for (Route rt : this.routes) {
			if (rt.getName().equals(route)) {
				return rt.toString();
			}
		}
		return "No route found in the system\n";
	}

	public String getTrainInfo(String train) {
		System.out.println("TrainSystem.getTrainInfo()\n");
		for (Train tr : this.trains) {
			if (tr.getName().equals(train.trim())) {
				return tr.toString();
			}
		}
		return "No train found in the system\n";
	}

	public void openAll() {
		System.out.println("TrainSystem.openAll()\n");
		for (Station st : this.stations) {
			st.open();
			System.out.println("All stations have been opened.\n");
		}

		for (Route rt : this.routes) {
			rt.open();
			System.out.println("All routes have been opened.\n");

		}

		for (Segment sg : this.segments) {
			sg.open();
			System.out.println("All segments have been opened.\n");
		}
	}

	public void closeAll(String name) {
		System.out.println("TrainSystem.closeAll()\n");
		for (Station st : this.stations) {
			if (st.getName().equals(name)) {
				st.open();
				System.out.println("All " + name + " stations have been closed.\n");
			}
		}

		for (Route rt : this.routes) {
			if (rt.getName().equals(name)) {
				rt.open();
				System.out.println("All " + name + " routes have been closed.\n");

			}
		}

		for (Segment sg : this.segments) {
			if (sg.getName().equals(name)) {
				sg.open();
				System.out.println("All " + name + " segments have been closed.\n");

			}
		}
	}

	public void setToWorking() {
		// ensure you set the status to Operational
		// ensure you set the current time to 0
		System.out.println("TrainSystem.setToWorking()\n");
		this.status = SystemStatus.Operational;
		if (this.currentTime < 0) {
			this.currentTime = 0;
		}
		System.out.println("System has been set to working.\n");
	}

	public void setStopped() {
		/*
		 * ensure you set the status to Finished or Deadlocked as appropriate
		 * 
		 * if this method is called and there are closures hindering movement,
		 * we know that the system is deadlocked
		 */
		System.out.println("TrainSystem.setStopped()\n");
		this.status = SystemStatus.Finished;
		System.out.println("System has been stopped.\n");
	}

	public SystemStatus currentStatus() {
		return status;
	}

	public boolean verify() {
		System.out.println("TrainSystem.verify()\n");
		Station checkStation = this.stations.get(0);
		for (int i = 1; i < this.stations.size(); i++) {
			for (int j = 1; j < this.stations.size(); j++) {
				if (!this.stations.get(j).verify() && this.stations.get(j).getName().equals(checkStation.getName())) {
					System.out
							.println(
									"Station " + this.stations.get(j).getName() + " is not verified by Train System\n");
				}
			}

			checkStation = this.stations.get(i);

		}

		Route checkRoute = this.routes.get(0);
		for (int i = 1; i < this.routes.size(); i++) {
			for (int j = 1; j < this.routes.size(); j++) {
				if (!this.routes.get(j).verify() && this.routes.get(j).getName().equals(checkRoute.getName())) {
					System.out
							.println("Route " + this.routes.get(j).getName() + " is not verified by Train System\n");
				}
			}

			checkRoute = this.routes.get(i);

		}

		Segment checkSegment = this.segments.get(0);
		for (int i = 1; i < this.segments.size(); i++) {
			for (int j = 1; j < this.segments.size(); j++) {
				if (!this.segments.get(j).verify() && this.segments.get(j).getName().equals(checkSegment.getName())) {
					System.out
							.println(
									"Station " + this.segments.get(j).getName() + " is not verified by Train System\n");
				}
			}
			checkSegment = this.segments.get(i);
		}
		return true;
	}

	public boolean closuresHinderingMovement() {
		/*
		 * call this method if advance returns no events and you want to determine if
		 * the system is deadlocked, i.e. there are trains to move but none of them can
		 * move because of closures.
		 * 
		 * this method should not return true if trains have stopped at a station and
		 * are waiting on a future time instant to move.
		 * 
		 */
		System.out.println("Checking for closures . . .\n");
		for (Route rt : this.routes) {
			if (!rt.isOpen()) {
				System.out.println("TrainSystem.closuresHinderingMovement()\n");
				System.out.println("Route " + rt.toString() + " is closed\n");
				return true;
			}
		}

		return false;
	}

	public ArrayList<Event> advance() {
		ArrayList<Event> events = new ArrayList<Event>();
		if (status == SystemStatus.Operational) {
			currentTime += 1;

			/*
			 * tell the trains that can move to advance
			 * 
			 * ensure that you change the lights in appropriate segments
			 * 
			 * ensure you collect the events and return them
			 */

			for (Train train : this.trains) {
				System.out.println(train.getName());
				Event event1 = train.start();
				if (event1 == null) {
					System.out.println("Train cannot be started.\n");
				}
				train.addToLog(event1);
				if (this.currentTime == train.getStartTime() || (this.currentTime == 1 && train.getStartTime() == 0)) {
					Event event2 = train.advance(currentTime);
					train.addToLog(event2);
					if (event2 == null) {
					}
				} else {
					System.out.println(getTrainInfo(train.getName()));
					continue;
				}
				System.out.println(getTrainInfo(train.getName()));
			}

		}
		System.out.println("Train System has been advanced.");
		return events;
	}

	public boolean validateObjectLog(ObjectType object, String name, ArrayList<String> events) {
		return true;
	}

	@Override
	public String toString() {
		String sts = "[";
		if (stations == null || stations.size() == 0)
			sts += "none]";
		else { // extract helper method for this
			Station[] acc1 = stations.toArray(new Station[0]);
			Arrays.sort(acc1);
			List<Station> ss = Arrays.asList(acc1);
			for (Station s : ss)
				sts += (ss.indexOf(s) == 0 ? "\n\t" : "\t") + s + (ss.indexOf(s) != ss.size() - 1 ? "\n" : "\n\t]");
		}

		String sgs = "[";
		if (segments == null || segments.size() == 0)
			sgs += "none]";
		else {
			Segment[] acc1 = segments.toArray(new Segment[0]);
			Arrays.sort(acc1);
			List<Segment> ss = Arrays.asList(acc1);
			for (Segment s : ss)
				sgs += (ss.indexOf(s) == 0 ? "\n\t" : "\t") + s + (ss.indexOf(s) != ss.size() - 1 ? "\n" : "\n\t]");
		}

		String rts = "[";
		if (routes == null || routes.size() == 0)
			rts += "none]";
		else {
			Route[] acc1 = routes.toArray(new Route[0]);
			Arrays.sort(acc1);
			List<Route> ss = Arrays.asList(acc1);
			for (Route s : ss)
				rts += (ss.indexOf(s) == 0 ? "\n\t" : "\t") + s + (ss.indexOf(s) != ss.size() - 1 ? "\n" : "\n\t]");
		}

		String tns = "[";

		if (trains == null || trains.size() == 0)
			tns += "none]";
		else {
			Train[] acc1 = trains.toArray(new Train[0]);
			Arrays.sort(acc1);
			List<Train> ss = Arrays.asList(acc1);
			for (Train s : trains)
				tns += (ss.indexOf(s) == 0 ? "\n\t" : "\t") + s + (ss.indexOf(s) != ss.size() - 1 ? "\n" : "\n\t]");
		}
		return "---------- ---------- ---------- ---------- ---------- ----------\nTrainSystem [\n\nstatus="
				+ status.getDescription() + "\nverified=" + (verify() ? "Yes" : "No") + "\n\ntrains=" + tns
				+ "\n\nroutes=" + rts + "\n\nsegments=" + sgs + "\n\nstations=" + sts
				+ "\n]\n---------- ---------- ---------- ---------- ---------- ----------";
	}

	public static void main(String[] args) {
		TrainSystem ts = new TrainSystem();

		ts.addStation("Stationx");
		ts.addStation("Alberb");
		ts.addStation("Trusty");
		ts.addStation("1tationx");

		ts.addSegment("Seg1", "Stationx", "Alberb");
		ts.addSegment("Seg2", "Alberb", "Trusty");
		ts.addSegment("Seg3", "Trusty", "1tationx");

		ArrayList<String> rs = new ArrayList<String>();
		rs.add("Seg1");
		rs.add("Seg2");
		rs.add("Seg3");
		ts.addRoute("R1", false, rs);

		ts.addTrain("train_1", 2);
		ts.addTrain("train_2", 1);

		ArrayList<String> stops = new ArrayList<String>();
		stops.add("Alberb");
		ts.registerTrain("train_1", "R1", stops);
		stops.remove(0);
		stops.add("Trusty");
		ts.registerTrain("train_2", "R1", stops);

		System.out.println(ts);
	}
}
