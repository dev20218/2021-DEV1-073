package com.bnp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import be.bnp.enumeration.MessagesEnum;
import be.bnp.enumeration.PlayerEnum;
import be.bnp.service.TicTacToeRules;

@SpringBootTest(classes = TicTacToeRules.class)
public class TictacTaeRulesTest {

	@Autowired
	TicTacToeRules service;

	@Test
	void testFilledBoard() {
		String[][] board = new String[][] { { " ", " ", " " }, { " ", " ", " " }, { " ", " ", " " } };

		board[0][0] = PlayerEnum.X.getValue();
		Assertions.assertEquals(MessagesEnum.NEXT_TURN_FOR_PLAYER_O,
				this.service.checkWinnerInRowHorizontally(board, PlayerEnum.X, new int[] { 0, 0 }));
		board[0][2] = PlayerEnum.O.getValue();
		Assertions.assertEquals(MessagesEnum.NEXT_TURN_FOR_PLAYER_X,
				this.service.checkWinnerInRowHorizontally(board, PlayerEnum.O, new int[] { 0, 1 }));
		board[0][1] = PlayerEnum.X.getValue();
		board[1][1] = PlayerEnum.O.getValue();
		board[2][0] = PlayerEnum.X.getValue();
		board[2][2] = PlayerEnum.O.getValue();
		board[1][2] = PlayerEnum.X.getValue();
		board[1][0] = PlayerEnum.O.getValue();

		MessagesEnum result = this.service.gameIsADraw(board, PlayerEnum.X, new int[] { 2, 1 });
		Assertions.assertEquals(MessagesEnum.THE_GAME_IS_A_DRAW, result);

	}

	@Test
	void testPlayerXAlwaysGoesFirst() {
		String[][] board = new String[][] { { " ", " ", " " }, { " ", " ", " " }, { " ", " ", " " } };

		Assertions.assertEquals(MessagesEnum.PLAYER_X_SHOULD_BE_THE_FIRST,
				this.service.checkFirstPlayerX(board, PlayerEnum.O));

		Assertions.assertEquals(MessagesEnum.NEXT_TURN_FOR_PLAYER_O,
				this.service.checkFirstPlayerX(board, PlayerEnum.X));

		board[0][2] = PlayerEnum.X.getValue();
		Assertions.assertEquals(MessagesEnum.NEXT_TURN_FOR_PLAYER_O,
				this.service.checkFirstPlayerX(board, PlayerEnum.X));
		Assertions.assertEquals(MessagesEnum.NEXT_TURN_FOR_PLAYER_X,
				this.service.checkFirstPlayerX(board, PlayerEnum.O));

	}

	@Test
	void testPlayersCannotPlayOnAPlayedPosition() {
		String[][] board = new String[][] { { " ", " ", " " }, { " ", " ", " " }, { " ", " ", " " } };

		board[0][0] = PlayerEnum.X.getValue();

		Assertions.assertEquals(MessagesEnum.ERROR_PLAYED_POSITION,
				this.service.playerCannotplayInplayedPosition(board, PlayerEnum.X, new int[] { 0, 0 }));

		Assertions.assertEquals(MessagesEnum.NEXT_TURN_FOR_PLAYER_O,
				this.service.playerCannotplayInplayedPosition(board, PlayerEnum.X, new int[] { 0, 1 }));

		Assertions.assertEquals(MessagesEnum.NEXT_TURN_FOR_PLAYER_X,
				this.service.playerCannotplayInplayedPosition(board, PlayerEnum.O, new int[] { 0, 2 }));
	}

	@Test
	void testWinnerRowHorizontally() {
		String[][] board = new String[][] { { " ", " ", " " }, { " ", " ", " " }, { " ", " ", " " } };

		board[0][0] = PlayerEnum.X.getValue();
		Assertions.assertEquals(MessagesEnum.NEXT_TURN_FOR_PLAYER_O,
				this.service.checkWinnerInRowHorizontally(board, PlayerEnum.X, new int[] { 0, 0 }));

		board[1][0] = PlayerEnum.O.getValue();
		Assertions.assertEquals(MessagesEnum.NEXT_TURN_FOR_PLAYER_X,
				this.service.checkWinnerInRowHorizontally(board, PlayerEnum.O, new int[] { 1, 0 }));

		board[0][1] = PlayerEnum.X.getValue();
		board[1][1] = PlayerEnum.O.getValue();
		board[0][2] = PlayerEnum.X.getValue();
		Assertions.assertEquals(MessagesEnum.THE_WINNER_IS_X,
				this.service.checkWinnerInRowHorizontally(board, PlayerEnum.X, new int[] { 0, 2 }));
	}

	@Test
	void testWinnerRowVertically() {
		String[][] board = new String[][] { { " ", " ", " " }, { " ", " ", " " }, { " ", " ", " " } };

		board[0][0] = PlayerEnum.X.getValue();
		Assertions.assertEquals(MessagesEnum.NEXT_TURN_FOR_PLAYER_O,
				this.service.checkWinnerVertically(board, PlayerEnum.X, new int[] { 0, 0 }));

		board[0][1] = PlayerEnum.O.getValue();
		Assertions.assertEquals(MessagesEnum.NEXT_TURN_FOR_PLAYER_X,
				this.service.checkWinnerVertically(board, PlayerEnum.O, new int[] { 1, 0 }));

		board[1][0] = PlayerEnum.X.getValue();
		board[1][1] = PlayerEnum.O.getValue();
		board[2][0] = PlayerEnum.X.getValue();
		Assertions.assertEquals(MessagesEnum.THE_WINNER_IS_X,
				this.service.checkWinnerVertically(board, PlayerEnum.X, new int[] { 2, 0 }));
	}

	@Test
	void testWinnerRowDiagonally() {
		String[][] board = new String[][] { { " ", " ", " " }, { " ", " ", " " }, { " ", " ", " " } };

		board[0][0] = PlayerEnum.X.getValue();
		Assertions.assertEquals(MessagesEnum.NEXT_TURN_FOR_PLAYER_O,
				this.service.checkWinnerDiagonally(board, PlayerEnum.X, new int[] { 0, 0 }));

		board[0][2] = PlayerEnum.O.getValue();
		Assertions.assertEquals(MessagesEnum.NEXT_TURN_FOR_PLAYER_X,
				this.service.checkWinnerDiagonally(board, PlayerEnum.O, new int[] { 0, 1 }));

		board[2][2] = PlayerEnum.X.getValue();
		board[2][0] = PlayerEnum.O.getValue();
		board[1][0] = PlayerEnum.X.getValue();
		board[1][1] = PlayerEnum.O.getValue();
		Assertions.assertEquals(MessagesEnum.THE_WINNER_IS_O,
				this.service.checkWinnerDiagonally(board, PlayerEnum.O, new int[] { 1, 1 }));

	}

}
