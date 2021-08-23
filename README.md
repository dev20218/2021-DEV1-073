# 2021-DEV1-073
Tic Tac Toe API

# Tic Tac Toe Game
Proposed solution of Tic Tac Toe Game with Spring Boot &amp; TDD


## Rules

- X always goes first.

- Players cannot play on a played position.

- Players alternate placing X’s and O’s on the board until either:

    - One player has three in a row, horizontally, vertically or diagonally

    - All nine squares are filled.

- If a player is able to draw three X’s or three O’s in a row, that player wins.

- If all nine squares are filled and neither player has three in a row, the game is a draw.

## Stack used

The technologies used in Example are :

- JDK 8
- Spring boot
- Lombok
- JUnit
- Maven
- Swagger

## Running the application locally

You can execute the `main` method in the `be.bnp.TicTacToeApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

shell
mvn spring-boot:run


## Test End Point with Swagger

- Play : [localhost:8080/tictactoe/play](http://localhost:8080/tictactoe/play), With POST Method
{
  "board": [
    [
      "string"
    ]
  ],
  "player": "string",
  "positions": [
    0
  ]
}

## Parameters:
- Board : array of string 3*3  initialized with blanck
- The X represents the player's X movement in the board,
- The O represents the player's O movement in the board,
- Player can be "X" or "O"
- Positions is an array for the position where to move
## Display of the result:
the result will be displayed in the response of swagger as json 

## Run Tests

You have several methods to run the test,
From IDE :
bash
- Right click on the project: "Run All Tests"
- Right click on the class (test): "Run"
- Right click on the method (test): "Run"


or
You can use the mvn command :
shell
mvn test
