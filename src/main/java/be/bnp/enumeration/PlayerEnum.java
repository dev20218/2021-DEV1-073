package be.bnp.enumeration;

import java.util.Arrays;


public enum PlayerEnum {

	O("O"), X("X");

	public static PlayerEnum getPlayer(final String value) {
		return Arrays.stream(PlayerEnum.values()).filter(p -> p.getValue().equals(value)).findFirst().get();
	}

	private String value;

	PlayerEnum(final String value) {
		this.value = value;
	}

	public final String getValue() {
		return this.value;
	}
}
