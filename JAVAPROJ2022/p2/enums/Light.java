package p2.enums;

public enum Light {

	Red("Light is Red"),
	Green("Light is Green!");

	private String description;

	Light(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
