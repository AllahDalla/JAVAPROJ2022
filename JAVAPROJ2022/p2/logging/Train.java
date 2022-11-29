package p2.logging;

import java.util.ArrayList;

import p2.MoveEvent;
import p2.enums.Action;
import p2.enums.TrainStatus;
import p2.event.CFOSEvent;
import p2.event.Event;

public class Train extends Logable implements Comparable<Train> {
	private int id = nextID++;
	private static int nextID = 1;
	private String name;
	private int timeRegistered = -1; /* once first set, is "immutable", and only deregister can change its value */
	private int startTime = -1; /* once first set, is "immutable" */
	private String currentStation;
	private Route route;
	private boolean isAtStart = true;
	private int waitTimeRemaining = 0; /* only changes with advance */
	private ArrayList<String> stopsAt = new ArrayList<String>();
	private TrainStatus status = TrainStatus.Initialised;

	public Train(String name, int startTime) {
		this.name = name != null ? name : "";
		this.startTime = startTime >= 0 ? startTime : this.startTime;
		System.out.println("The start time is " + this.startTime + "\n");
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getStartTime() {
		return timeRegistered > 0 && startTime >= 0 ? timeRegistered + startTime : -1;
	}

	public boolean isRegistered() {
		return timeRegistered > 0;
	}

	public int whenRegistered() {
		return timeRegistered;
	}

	public void register(int timeRegistered) {

		if (status != TrainStatus.Initialised) {
			System.out.println("Train status is NOT Initialised\n");
			return;
		}
		/* ensures immutability until deregistered */
		System.out.println("Train is INITIALISED\n");
		this.timeRegistered = isRegistered() ? this.timeRegistered
				: timeRegistered >= 0 ? timeRegistered : this.timeRegistered;
		System.out.println("Time train was registered = " + String.valueOf(this.timeRegistered) + "\n");
	}

	public void deregister() {

		if (status != TrainStatus.Initialised)
			return;

		if (!isRegistered() || currentStation != null)
			return;

		this.timeRegistered = -1;
		route = null;
		currentStation = null;
	}

	public boolean isWaiting() {
		return waitTimeRemaining > 0;
	}

	public Event start() {
		CFOSEvent start = new CFOSEvent("Start", startTime, Action.Start);
		if (status != TrainStatus.Initialised || !verify())
			return null;

		status = TrainStatus.Started; // remember this
		System.out.println("Train '" + getName() + "' has been started successfully\n");
		return start;
	}

	public Event finish() {
		CFOSEvent fin = new CFOSEvent("Finish", startTime, Action.Finish);
		if (status != TrainStatus.Started)
			return null;

		status = TrainStatus.Completed;
		currentStation = this.route.getEnd().getName();

		return fin;
	}

	public Event advance(int time) {

		if (status != TrainStatus.Started)
			return null;

		isAtStart = false;

		if (waitTimeRemaining > 0) {
			waitTimeRemaining -= 1;
			return null;
		} else {
			MoveEvent moveEvent = new MoveEvent("Advance", time, currentStation,
					this.route.getNextStation(currentStation, isAtStart));

			this.currentStation = nextStation();
			System.out.println("The current station is " + currentStation + "\n");

			return moveEvent;
		}
	}

	public String currentStation() {
		if (currentStation != null)
			return currentStation;
		return null;
	}

	public String nextStation() {

		if (!verify())
			return null;

		if (currentStation != null)
			return route.getNextStation(currentStation, isAtStart);
		else if (status != TrainStatus.Completed)
			return route.getStart().getName();
		else
			return null;
	}

	public void changeRoute(Route r) {

		if (status != TrainStatus.Initialised)
			return;

		if (currentStation == null && r != null && status == TrainStatus.Initialised)
			route = r;
	}

	public void addStop(String stop) {

		if (status != TrainStatus.Initialised)
			return;

		if (stop != null && !stop.trim().equals("") && status == TrainStatus.Initialised)
			stopsAt.add(stop);
	}

	public boolean verify() {
		boolean verified = true; // original line of code

		if (!this.route.verify()) {
			if (this.timeRegistered < 1) {
				return false;
			}
		}

		return verified; // original line of code
	}

	@Override
	public String toString() {
		return "Train [id=" + id + ", name=" + name + ", " + "timeRegistered="
				+ (timeRegistered <= 0 ? "unregistered" : timeRegistered) + ", startTime="
				+ (timeRegistered >= 0 ? getStartTime() : "unregistered") + ", currentStation="
				+ (currentStation == null ? "none" : currentStation) + ", route="
				+ (route == null ? "none" : route.getName()) + ", stopsAt="
				+ (stopsAt.size() > 0 ? stopsAt.toString() : "All") + ", status=" + status.getDescription()
				+ ", verified=" + (verify() ? "Yes" : "No") + "]";
	}

	@Override /* Can be used to check equality, and to sort */
	public int compareTo(Train train) {
		return (name).compareTo(train.getName());
	}

	@Override
	public boolean validate() {
		return super.validate();
	}
}
