package be.bnp.enumeration;

public enum BoardEnum {
	BLANK(" ");

	private final String value;

	BoardEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
