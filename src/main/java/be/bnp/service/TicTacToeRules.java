package be.bnp.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import be.bnp.enumeration.BoardEnum;
import be.bnp.enumeration.MessagesEnum;
import be.bnp.enumeration.PlayerEnum;

@Service
public class TicTacToeRules {
	public MessagesEnum playerCannotplayInplayedPosition(final String[][] board, final PlayerEnum currentPlayer,
			final int[] position) {
		MessagesEnum result = MessagesEnum.ERROR_PLAYED_POSITION;
		String[][] resultBoard = getResultBoard(board);
		if (BoardEnum.BLANK.getValue().equals(resultBoard[position[0]][position[1]]))
			if (PlayerEnum.X.equals(currentPlayer))
				result = MessagesEnum.NEXT_TURN_FOR_PLAYER_O;
			else
				result = MessagesEnum.NEXT_TURN_FOR_PLAYER_X;
		return result;
	}

	public MessagesEnum checkFirstPlayerX(final String[][] board, final PlayerEnum currentPlayer) {
		MessagesEnum result = MessagesEnum.NEXT_TURN_FOR_PLAYER_X;
		long count = Arrays.stream(board)
				.mapToLong(
						line -> Arrays.stream(line).filter(player -> (player.equals(PlayerEnum.X.getValue()))).count())
				.sum();

		if (count == 0 && currentPlayer.getValue().equals(PlayerEnum.O.getValue()))
			return MessagesEnum.PLAYER_X_SHOULD_BE_THE_FIRST;

		if (PlayerEnum.X.equals(currentPlayer))
			result = MessagesEnum.NEXT_TURN_FOR_PLAYER_O;

		return result;
	}

	public MessagesEnum checkWinnerInRowHorizontally(final String[][] board, final PlayerEnum currentPlayer,
			final int[] position) {
		MessagesEnum result = MessagesEnum.NEXT_TURN_FOR_PLAYER_X;
		String[][] resultBoard = getResultBoard(board);
		resultBoard[position[0]][position[1]] = currentPlayer.getValue();

		if (Arrays.stream(resultBoard).anyMatch(
				row -> Arrays.stream(row).filter(item -> item.equals(PlayerEnum.X.getValue())).count() == board.length))
			return MessagesEnum.THE_WINNER_IS_X;

		if (Arrays.stream(resultBoard).anyMatch(
				row -> Arrays.stream(row).filter(item -> item.equals(PlayerEnum.O.getValue())).count() == board.length))
			return MessagesEnum.THE_WINNER_IS_O;

		if (PlayerEnum.X.equals(currentPlayer))
			result = MessagesEnum.NEXT_TURN_FOR_PLAYER_O;
		return result;
	}

	public String[][] getResultBoard(final String[][] board) {
		String[][] result = new String[3][3];
		for (int row = 0; row < result.length; row++)
			for (int line = 0; line < result.length; line++)
				result[row][line] = board[row][line];
		return result;
	}

	public MessagesEnum checkWinnerVertically(final String[][] board, final PlayerEnum currentPlayer,
			final int[] position) {
		MessagesEnum result = MessagesEnum.NEXT_TURN_FOR_PLAYER_X;
		int row = position[0];
		int col = position[1];
		String[][] resultBoard = getResultBoard(board);
		resultBoard[row][col] = currentPlayer.getValue();

		if (isPlayerInColumn(resultBoard, currentPlayer, 0) || isPlayerInColumn(resultBoard, currentPlayer, 1)
				|| isPlayerInColumn(board, currentPlayer, 2)) {
			if (PlayerEnum.X.getValue().equals(currentPlayer.getValue()))
				return MessagesEnum.THE_WINNER_IS_X;
			else
				return MessagesEnum.THE_WINNER_IS_O;
		}

		if (PlayerEnum.X.equals(currentPlayer))
			result = MessagesEnum.NEXT_TURN_FOR_PLAYER_O;
		return result;
	}

	public MessagesEnum checkWinnerDiagonally(final String[][] board, final PlayerEnum currentPlayer,
			final int[] position) {
		MessagesEnum result = MessagesEnum.NEXT_TURN_FOR_PLAYER_X;
		int row = position[0];
		int col = position[1];
		String[][] resultBoard = getResultBoard(board);
		resultBoard[row][col] = currentPlayer.getValue();
		int[][] diagonal1 = { { 0, 0 }, { 1, 1 }, { 2, 2 } };
		int[][] diagonal2 = { { 0, 2 }, { 1, 1 }, { 2, 0 } };

		if (Arrays.stream(diagonal1)
				.allMatch(element -> resultBoard[element[0]][element[1]].equals(currentPlayer.getValue()))
				|| Arrays.stream(diagonal2)
						.allMatch(element -> resultBoard[element[0]][element[1]].equals(currentPlayer.getValue()))) {
			if (currentPlayer.equals(PlayerEnum.X))
				result = MessagesEnum.THE_WINNER_IS_X;
			else
				result = MessagesEnum.THE_WINNER_IS_O;
		}
		if (PlayerEnum.X.equals(currentPlayer))
			result = MessagesEnum.NEXT_TURN_FOR_PLAYER_O;
		return result;
	}

	private boolean isPlayerInColumn(final String[][] board, final PlayerEnum currentPlayer, final int column) {
		return Arrays.stream(board).allMatch(p -> p[column].equals(currentPlayer.getValue()));
	}

	public MessagesEnum gameIsADraw(final String[][] board, final PlayerEnum currentPlayer, final int[] position) {
		MessagesEnum result = MessagesEnum.THE_GAME_IS_A_DRAW;
		String[][] resultBoard = getResultBoard(board);
		resultBoard[position[0]][position[1]] = currentPlayer.getValue();
		if (Arrays.stream(resultBoard).anyMatch(line -> Arrays.stream(line)
				.filter(value -> value.equals(BoardEnum.BLANK.getValue())).findAny().isPresent()))
			if (currentPlayer.equals(PlayerEnum.X))
				result = MessagesEnum.NEXT_TURN_FOR_PLAYER_O;
			else
				result = MessagesEnum.NEXT_TURN_FOR_PLAYER_X;
		return result;
	}

	public MessagesEnum checkWinner(final String[][] board, final PlayerEnum currentPlayer, final int[] position) {
		String[][] resultBoard = getResultBoard(board);
		resultBoard[position[0]][position[1]] = currentPlayer.getValue();

		MessagesEnum result;
		result = this.checkWinnerVertically(board, currentPlayer, position);
		if (result == MessagesEnum.THE_WINNER_IS_O || result == MessagesEnum.THE_WINNER_IS_X)
			return result;
		result = this.checkWinnerInRowHorizontally(board, currentPlayer, position);
		if (result == MessagesEnum.THE_WINNER_IS_O || result == MessagesEnum.THE_WINNER_IS_X)
			return result;
		result = this.checkWinnerDiagonally(board, currentPlayer, position);
		if (result == MessagesEnum.THE_WINNER_IS_O || result == MessagesEnum.THE_WINNER_IS_X)
			return result;

		if (currentPlayer == PlayerEnum.X)
			result = MessagesEnum.NEXT_TURN_FOR_PLAYER_O;
		else
			result = MessagesEnum.NEXT_TURN_FOR_PLAYER_X;
		return result;
	}

	public static void drawBoard(final List<List<String>> board) {

		StringBuilder builder = new StringBuilder("|--------------|").append("\n");
		builder.append("|  ").append(board.get(0).get(0));
		builder.append(" | ").append(board.get(0).get(1));
		builder.append(" | ").append(board.get(0).get(2)).append(" |").append("\n");
		builder.append("|--------------|").append("\n");
		builder.append("| " + board.get(1).get(0));
		builder.append(" | " + board.get(1).get(1));
		builder.append(" | " + board.get(1).get(2) + " |").append("\n");
		builder.append("|--------------|").append("\n");
		builder.append("| " + board.get(2).get(0));
		builder.append(" | " + board.get(2).get(1));
		builder.append(" | " + board.get(2).get(2) + " |").append("\n");
		builder.append("|--------------|");
		builder.append("\n\n");
	}
}
