package be.bnp.enumeration;

public enum MessagesEnum {
	NEXT_TURN_FOR_PLAYER_O("Player O to play"), NEXT_TURN_FOR_PLAYER_X("Player X to play"),
	THE_GAME_IS_A_DRAW("GAME OVER! IT'S DRAW!"), THE_WINNER_IS_O("Player 'O' is the winner"),
	THE_WINNER_IS_X("Player 'X' is the winner"), ERROR_PLAYED_POSITION("Error ! It's a played position!"),
	PLAYER_X_SHOULD_BE_THE_FIRST("Player X should move the first!");

	private String value;

	MessagesEnum(final String value) {
		this.value = value;
	}

	public final String getValue() {
		return this.value;
	}
}
