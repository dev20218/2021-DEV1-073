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

The technologies used are :

- JDK 8
- Spring boot
- Lombok
- JUnit
- Maven
- Swagger

## Running the application locally

Start the application spring boot with: 
mvn spring-boot:run


## Test End Point with Swagger
- Open swagger page on http://localhost:8080/swagger-ui.html
- Open tictactoe service by clicking on POST /tictactoe/play
![swagger1](https://user-images.githubusercontent.com/61793664/130404048-804283b6-0be1-44ab-9226-5425333e4c6c.PNG)

## Parameters:
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
- Board : array of string 3*3  initialized with blanck
- The X represents the player's X movement in the board,
- The O represents the player's O movement in the board,
- Player can be "X" or "O"
- Positions is an array for the position where to move
- First test: X should be the first player

![firstTest](https://user-images.githubusercontent.com/61793664/130404366-4e2486c0-abbb-4a1a-8a0e-e45619e51443.PNG)

And the result is:

![response1](https://user-images.githubusercontent.com/61793664/130404491-8e240019-43c9-40fe-9c3a-427f4f3944f4.PNG)

- second test


![r2](https://user-images.githubusercontent.com/61793664/130405728-bb80e5e4-624c-4d1c-9c8a-377202ceff67.PNG)

![t2](https://user-images.githubusercontent.com/61793664/130405780-95bb7539-c434-4a0c-b4da-87cd0eed62f9.PNG)

-third test

![t3](https://user-images.githubusercontent.com/61793664/130406284-a3912772-6f07-40a4-bcff-df9bb651c407.PNG)


![r3](https://user-images.githubusercontent.com/61793664/130406300-94db7e44-895e-4f63-b0b3-043b0d10b14f.PNG)

- fill X and O until Winner X

![winnerX](https://user-images.githubusercontent.com/61793664/130406757-b7c34ad0-7bbe-4875-a74d-621ec4c6c726.PNG)

![winnerRes](https://user-images.githubusercontent.com/61793664/130406763-1c664eb2-3e4c-42f5-9e58-55d6068f5f70.PNG)


## Display of the result:
the result will be displayed in the response of swagger as json 

## Run unit Tests

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
