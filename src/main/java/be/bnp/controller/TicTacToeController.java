package be.bnp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.bnp.model.Request;
import be.bnp.model.Response;
import be.bnp.service.TicTacToeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tictactoe")
@Api(tags = { "Tictactoe" }, description = "Service for tic tac toe.")
public class TicTacToeController {
	@Autowired
	TicTacToeService service;

	@PostMapping(path = "/play")
	@ApiOperation(value = "Fill the board, the player number, and the position array to play.")
	ResponseEntity<Response> play(@RequestBody final Request request) {
		return ResponseEntity.ok(this.service.play(request));
	}
}
