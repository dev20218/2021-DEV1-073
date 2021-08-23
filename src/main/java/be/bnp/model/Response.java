package be.bnp.model;

import be.bnp.enumeration.MessagesEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Response {

	private String[][] board;
	private MessagesEnum result;

	public Response(String[][] board, MessagesEnum result) {
		this.board = board;
		this.result = result;
	}
}
