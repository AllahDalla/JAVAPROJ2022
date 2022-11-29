package p2.enums;

public enum SimulatorStatus {

	Uninitialised("Simulator is Uninitialised."),
	Initialised("Simulator is Initialised."),
	Paused("Simulator is Paused."),
	Working("Simulator is Working."),
	Finished("Simulator has Stopped.");

	private String description;

	SimulatorStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
