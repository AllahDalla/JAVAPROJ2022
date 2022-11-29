package p2.logging;

import p2.enums.RSStatus;
import p2.event.Event;
import p2.interfaces.OpenClose;

public class Station extends Logable implements Comparable<Station>, OpenClose {

	private String name; /* once set, is immutable */
	private RSStatus status = RSStatus.Open;

	public Station(String name) {
		setName(name);
	}

	public String getName() {
		return name;
	}

	// added getter to class
	public RSStatus getStatus() {
		return status;
	}

	private void setName(String name) {
		this.name = name != null && !name.equals("") ? name : this.name;
	}

	public boolean verify() {
		boolean verified = true; // original line of code

		if (getName().equals(null) || getName().equals(" ") || getName().equals("")) {
			return false;
		}

		return verified; // original line of code
	}

	@Override
	public boolean isOpen() {
		return status == RSStatus.Open;
	}

	@Override
	public Event close() {
		status = status == RSStatus.Open ? status = RSStatus.ClosedForMaintenance : status;
		return null;
	}

	@Override
	public Event open() {
		status = status == RSStatus.ClosedForMaintenance ? status = RSStatus.Open : status;
		return null;
	}

	@Override
	public int compareTo(Station station) {
		return name.compareTo(station.getName());
	}

	@Override
	public String toString() {
		return "Station [name=" + name + ", status=" + status.getDescription() + "]";
	}

	@Override
	public boolean validate() {
		return super.validate();
	}

}
