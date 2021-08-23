package be.bnp.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.bnp.enumeration.MessagesEnum;
import be.bnp.enumeration.PlayerEnum;
import be.bnp.model.Request;
import be.bnp.model.Response;

@Service
public class TicTacToeService {
	@Autowired
	TicTacToeRules tictacTaeRules;

	public Response play(final Request request) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
		final PlayerEnum player = PlayerEnum.getPlayer(request.getPlayer());
		int[] position = request.getPositions();
		if (player == null || position == null)
			throw new IllegalArgumentException("Player and position are required!");

		String[][] board = request.getBoard();
		if (Arrays.stream(board).anyMatch(line -> line.length != 3))
			throw new ArrayIndexOutOfBoundsException("The board matrix should be 3x3!");

		MessagesEnum result;

		// check first player
		result = this.tictacTaeRules.checkFirstPlayerX(board, player);
		if (MessagesEnum.PLAYER_X_SHOULD_BE_THE_FIRST == result)
			return new Response(board, result);
		// game is draw
		result = this.tictacTaeRules.gameIsADraw(board, player, position);
		if (MessagesEnum.THE_GAME_IS_A_DRAW == result)
			return new Response(board, result);
		// play in played position
		result = this.tictacTaeRules.playerCannotplayInplayedPosition(board, player, position);
		if (MessagesEnum.ERROR_PLAYED_POSITION == result)
			return new Response(board, result);
		// check winner
		result = this.tictacTaeRules.checkWinner(board, player, position);
		board[position[0]][position[1]] = player.getValue();

		return new Response(board, result);
	}

}
