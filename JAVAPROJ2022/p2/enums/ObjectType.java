package p2.enums;

public enum ObjectType {
	Route_("Route"), Segment_("Segment"), Station_("Station"), Train_("Train");

	private String description;

	ObjectType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
