package be.bnp.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Request {
	private String[][] board;
	private String player;

	private int[] positions;
}
