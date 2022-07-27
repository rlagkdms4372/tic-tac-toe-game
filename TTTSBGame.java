package finalProject;

import java.util.Scanner;
//CS 2336.003 Haeun Kim
/*
 * Analysis:
 * The problem to solve this class is that making the game play well
 * I need to make the small board and big board(The final result) and organize the board to look like the real game
 * I need to make the class to distinguish who is winner or is this game tie
 * The game should be go on and go on until the there is Winner or tie, and I'll make the message(Who is winner) print
 * There is three kinds of game (AI VS AI, AI VS Human, Human VS Human)
 * Those three game styles are different, so I'll make three kinds of Game
 * 
 * Design:
 * 1 I'll Construct the TTTSBGame, and I'll set the players and board, by using the class SmallBoard and ComputerPlayer
 * 2 I'll make total 10 boards, (9 for game, 1 for result), So I'll need to make SmallBoard returnBoard() function to return the proper function for the index
 * 3. I'll make the board with print function from the SmallBoard class.
 *	  When I input the index of each boards, then the board's box from the boxes array will print the current box. By repeating the statements, 
 *	  I'll make the game board
 * 4. I'll make the method for the switch the players, if current player index is 0 then,
 	  it'll switch to 1,  or is the current player index is 1, then it'll switch to 0
 * 5. I'll make the xWinner method and oWineer method. In the xWinner method, the winner is player who has the X mark. 
	  I'll write all the cases when the X player wins the game. 
	  In the oWinner method, the winner is player who has the O mark. I'll write all the cases when the O player wins the game. 
	  This will be useful for checking not only small boards but also the big Board
 * 6. I'll make the method which distinguish wheather the game is over or not
 * There is total 4 cases
    1) By using oWinner method, we check whether O wins the game from the big Board
    2) By using xWinner method, we check whether X wins the game from the big Board
    3) We check all the sBoards are full, but there is no O winner or X winner
    4) The big Board is full, but there is no O winner or X winner
 * 7. I'll make the small game win method. When there is winner in the SmallBoard, then the winner's mark is placed in index of BigBoard
 * 8) Game between Human and Human
 *  - The game start by recieving the board number and box number from the user
    - if the current player can put the mark in that index, then I'll use makeMove() method from the SmallBoard I'll make place the mark
	- the lastest box index will be the board index for the next turn, and I'll switch the players, and I'll check there is winner in the small board or not
	- if the big game does not end, then the game goes on
	- In the meanwhile the game, if the board is full, then the next player have chance to change the board, So I'll write the statement for the player to input the next board
	- All the input should be between 0 to 8. When the user input out of range, then I'll use the while loop until the user input valid input
	- If the game end from the bigGameResult() method, it will print the result and the game end
	9) Game between AI and AI
 *  - The game start between AI and AI. I'll use randomNumber(int range), So there will be no error regarding to the input
 *  - if the current player can put the mark in that index, then I'll use makeMove() method from the SmallBoard I'll make place the mark
	- the lastest box index will be the board index for the next turn, and I'll swith the players, and I'll check there is winner in the small board or not
	- if the big game does not end, then the game goes on
	- In the meanwhile the game, if the board is full, then the next player have chance to change the board, So I'll write the statement for the player to choose the next board
	- If the game end from the bigGameResult() method, it will print the result and the game end
	10) Game between AI and Human
 *  - The game start with the turn of AI. AI have X mark, and Human has O mark
 *  - the player index of AI is 0 and the player index of Human is 1
    - if the current player can put the mark in that index, then I'll use makeMove() method from the SmallBoard I'll make place the mark
	- the lastest box index will be the board index for the next turn, and I'll swith the players, and I'll check there is winner in the small board or not
	- if the big game does not end, then the game goes on
	- AI input only valid index because we set the randomNumber method from the ComputerPlayer class
	- In the meanwhile the game, if the board is full, then the next player have chance to change the board, So I'll write the statement for the player to input the next board
	- All the input should be between 0 to 8. When the human input out of range, then I'll use the while loop until the user input valid input
	- If the game end from the bigGameResult() method, it will print the result and the game end
 */

public class TTTSBGame {
	private SmallBoard sBoard0, sBoard1, sBoard2, sBoard3, sBoard4, sBoard5, sBoard6, sBoard7, sBoard8;
	private SmallBoard bBoard;
	private ComputerPlayer[] players = new ComputerPlayer[2];
	
	private String[] marks = {"X", "O"};
	private String name = "TicTacToe";
	
	private int gameRowSize = 3;
	private int gameColSize = 3;
	private int currentPlayerIndex = 0;
	
		public TTTSBGame() {
			setplayers();
			setBigBoard();
		}
		
		// This private void method is to set the board
		private void setBigBoard() {
			this.sBoard0 = new SmallBoard(3, 3, "Board 0"); //set the sBoard0 by construct SmallBoard class
			this.sBoard1 = new SmallBoard(3, 3, "Board 1"); //set the sBoard1 by construct SmallBoard class
			this.sBoard2 = new SmallBoard(3, 3, "Board 2"); //set the sBoard2 by construct SmallBoard class
			this.sBoard3 = new SmallBoard(3, 3, "Board 3"); //set the sBoard3 by construct SmallBoard class
			this.sBoard4 = new SmallBoard(3, 3, "Board 4"); //set the sBoard4 by construct SmallBoard class
			this.sBoard5 = new SmallBoard(3, 3, "Board 5"); //set the sBoard5 by construct SmallBoard class
			this.sBoard6 = new SmallBoard(3, 3, "Board 6"); //set the sBoard6 by construct SmallBoard class
			this.sBoard7 = new SmallBoard(3, 3, "Board 7"); //set the sBoard7 by construct SmallBoard class
			this.sBoard8 = new SmallBoard(3, 3, "Board 8"); //set the sBoard8 by construct SmallBoard class
			this.bBoard = new SmallBoard(3, 3, "BigBoard"); //set the bBoard by construct SmallBoard class
			
		}
		
		// This private SmallBoard method is return the board when inputting the index
		private SmallBoard returnBoard(int index) {
			if(index == 0) return sBoard0;
			else if(index == 1) return sBoard1;
			else if(index == 2) return sBoard2;
			else if(index == 3) return sBoard3;
			else if(index == 4) return sBoard4;
			else if(index == 5) return sBoard5;
			else if(index == 6) return sBoard6;
			else if(index == 7) return sBoard7;
			else if(index == 8) return sBoard8;
			else return bBoard;
		}
		
		// This method is in order to indicate the game Board
		public void print() {
			System.out.println(sBoard0.getName() + "  "+ sBoard1.getName()  +"  "+ sBoard2.getName());
			System.out.print("|");
			sBoard0.print(0);
			sBoard0.print(1);
			sBoard0.print(2);
			System.out.print("  |");
			sBoard1.print(0);
			sBoard1.print(1);
			sBoard1.print(2);
			System.out.print("  |");
			sBoard2.print(0);
			sBoard2.print(1);
			sBoard2.print(2);
			System.out.println();
			System.out.print("|");
			sBoard0.print(3);
			sBoard0.print(4);
			sBoard0.print(5);
			System.out.print("  |");
			sBoard1.print(3);
			sBoard1.print(4);
			sBoard1.print(5);
			System.out.print("  |");
			sBoard2.print(3);
			sBoard2.print(4);
			sBoard2.print(5);
			System.out.println();
			System.out.print("|");
			sBoard0.print(6);
			sBoard0.print(7);
			sBoard0.print(8);
			System.out.print("  |");
			sBoard1.print(6);
			sBoard1.print(7);
			sBoard1.print(8);
			System.out.print("  |");
			sBoard2.print(6);
			sBoard2.print(7);
			sBoard2.print(8);
			
			System.out.println();
			System.out.println(sBoard3.getName() + "  "+ sBoard4.getName()  +"  "+ sBoard5.getName());
			System.out.print("|");
			sBoard3.print(0);
			sBoard3.print(1);
			sBoard3.print(2);
			System.out.print("  |");
			sBoard4.print(0);
			sBoard4.print(1);
			sBoard4.print(2);
			System.out.print("  |");
			sBoard5.print(0);
			sBoard5.print(1);
			sBoard5.print(2);
			System.out.println();
			System.out.print("|");
			sBoard3.print(3);
			sBoard3.print(4);
			sBoard3.print(5);
			System.out.print("  |");
			sBoard4.print(3);
			sBoard4.print(4);
			sBoard4.print(5);
			System.out.print("  |");
			sBoard5.print(3);
			sBoard5.print(4);
			sBoard5.print(5);
			System.out.println();
			System.out.print("|");
			sBoard3.print(6);
			sBoard3.print(7);
			sBoard3.print(8);
			System.out.print("  |");
			sBoard4.print(6);
			sBoard4.print(7);
			sBoard4.print(8);
			System.out.print("  |");
			sBoard5.print(6);
			sBoard5.print(7);
			sBoard5.print(8);
			
			System.out.println();
			System.out.println(sBoard6.getName() + "  "+ sBoard7.getName()  +"  "+ sBoard8.getName());
			System.out.print("|");
			sBoard6.print(0);
			sBoard6.print(1);
			sBoard6.print(2);
			System.out.print("  |");
			sBoard7.print(0);
			sBoard7.print(1);
			sBoard7.print(2);
			System.out.print("  |");
			sBoard8.print(0);
			sBoard8.print(1);
			sBoard8.print(2);
			System.out.println();
			System.out.print("|");
			sBoard6.print(3);
			sBoard6.print(4);
			sBoard6.print(5);
			System.out.print("  |");
			sBoard7.print(3);
			sBoard7.print(4);
			sBoard7.print(5);
			System.out.print("  |");
			sBoard8.print(3);
			sBoard8.print(4);
			sBoard8.print(5);
			System.out.println();
			System.out.print("|");
			sBoard6.print(6);
			sBoard6.print(7);
			sBoard6.print(8);
			System.out.print("  |");
			sBoard7.print(6);
			sBoard7.print(7);
			sBoard7.print(8);
			System.out.print("  |");
			sBoard8.print(6);
			sBoard8.print(7);
			sBoard8.print(8);
			System.out.println();
			
			System.out.println(bBoard.getName());
			System.out.print("|");
			bBoard.print(0);
			bBoard.print(1);
			bBoard.print(2);
			System.out.println();
			System.out.print("|");
			bBoard.print(3);
			bBoard.print(4);
			bBoard.print(5);
			System.out.println();
			System.out.print("|");
			bBoard.print(6);
			bBoard.print(7);
			bBoard.print(8);
			System.out.println();
			
		}
		
		// This method is in order to set the players
		// Player 1 might get "X" mark
		// Player 2 might get "O" mark.
		private void setplayers() {
			for(int i = 0; i < players.length; i++) {
				ComputerPlayer p = new ComputerPlayer("player" + i+1, marks[i]);
				players[i] = p;
			}
		}
		
		
		// This method is in order to switch the players
		private void switchPlayer() {
			// when the current player index is 1
			if(currentPlayerIndex == 1)
				// I'll make the current player index changes
				currentPlayerIndex = 0;
			// When the current player index is not 1 --> which means that the current player index is 0
			else
			// When the current player index will become 1
				currentPlayerIndex = 1;
		}
		
		
		// This method is in order to check whether the game is Over or Not
		private boolean bigGameOver() {
			// When "O" mark player wins in the Big Board
			 if(oWinner(9)) {
				 // the game is Over
				return true;
			// When "X" mark player wins in the Big Board
			}else if(xWinner(9)) {
				// the game is over
				return true;
			// When the small board from 0 to 8 are all full but there is not winner
			}else if((sBoard0.isFull()&&sBoard1.isFull()&&sBoard2.isFull()&&sBoard3.isFull()&&sBoard4.isFull()&&sBoard5.isFull()
					&&sBoard6.isFull()&&sBoard7.isFull()&&sBoard8.isFull())&& !oWinner(9) && !xWinner(9)) { 
				// the game is Over
			 	return true;
			 // When there is no winner, this game is tie 
			}else if(bBoard.isFull()&&!oWinner(9) && !xWinner(9)) {
				// the game is Over
				return true;
			// In any other cases,,,
			}else
				// the game should be gone on
				return false;
		}
		
		
		// This method is in order to check whether the game is Over or Not
		// This is super similar to the previous method, but when I combine with this method the previous method and run it, the result message is printed twice.
		// To prevent from printing the result twice, I made another method to print the result message once.
		private boolean bigGameOverResult() {
			 if(oWinner(9)) {
				System.out.println("player 2 'O' Won!!");
				return true;
			}else if(xWinner(9)) {
				System.out.println("player 1 'X' Won!!");
				return true;
			}else if((sBoard0.isFull()&&sBoard1.isFull()&&sBoard2.isFull()&&sBoard3.isFull()&&sBoard4.isFull()&&sBoard5.isFull()&&sBoard6.isFull()&&sBoard7.isFull()&&sBoard8.isFull())&&
					!oWinner(9) && !xWinner(9)) { 
				System.out.println("There is no Winner, game is Over");
			 	return true;
			}else if(bBoard.isFull()&&!oWinner(9) && !xWinner(9)) {
				System.out.println("There is no Winner... This game is tie");
				return true;
			}else
				return false;
		}
		
		
		// This method is in order to place mark in the bigBoard when the x or o win in the smallBoard
		private void smallGameOver(int index) {
			if(xWinner(index)) {
				bBoard.makeMove("X", index);
			}
			else if(oWinner(index)) {
				bBoard.makeMove("O", index);
			}
		}
		
		// This is for the game Human Human
		public void start() {
			
			Scanner input = new Scanner(System.in);
			
			System.out.println("=====WELCOME TO THE ULTIMATE TIC-TAC-TOE GAME!! =====");
			print(); // print current board
			
			System.out.println("Current Player is : " + marks[this.currentPlayerIndex]);
			System.out.println("Please select a valid board:");
			System.out.print("Selectd Board : ");	
			
			// This is for the selection of Board
			int start = input.nextInt();
			// When the input is bigger than 8 or less than 0, it's invalid input
			while(start > 8 || start < 0) {
				System.out.println("This input is invalid, so please input the number from 0 to 9");
				System.out.print("Selectd Board : ");
				start = input.nextInt();
			}
			
			System.out.println("Please select a valid square on the selected board: ");
			System.out.print("Selected Square : ");
			
			// This is for the selection of box
			int index = input.nextInt();
			//When the input is bigger than 8 or less than 0, it's invalid input
			while(index > 8 || index < 0) {
				System.out.println("This input is invalid, so please input the number from 0 to 9");
				System.out.print("Selected Square : ");
				index = input.nextInt();
			}
			
			// This game plays over and over until the game is over
			while(!bigGameOver()){
				//This is the code that the players put their marks on the selected board and box
				if(!returnBoard(start).makeMove(players[this.currentPlayerIndex].getMark(), index)) {
					// if the player success to put the mark, when switch the player
					switchPlayer();
					// if the current board wins the game, then input the winner mark to big board
					smallGameOver(start);
					// print the current board
					print();
					// According to the game rule, the last box index becomes the current board index
					start = index;
					
					// When the game plays before the bigGame is not over
					if(!bigGameOverResult()) {
						// Indicating the current player
						System.out.println("Current Player is : " + marks[this.currentPlayerIndex]);
						// Indicating the current board
						System.out.println("Selected Board: " + start);
						
						// if the current board is fill
						if(returnBoard(start).isFull()) {
							System.out.println("Board " + start + " is Full. So, please choose other Board");
							// make the player input the other board
							System.out.print("Selectd Board : ");
							start = input.nextInt();
							// the input should be between 0 and 8
							while(start > 8 || start < 0) {
								System.out.println("This input is invalid, so please input the number from 0 to 9");
								start = input.nextInt();
							}
							
							// After board select, it's time to select box in the board
							System.out.println("Please select a valid square on the selected board: ");
							System.out.print("Selectd Square : ");
							index = input.nextInt();
							// the input should be between 0 and 8
							while(index > 8 || index < 0) {
								System.out.println("This input is invalid, so please input the number from 0 to 9");
								index = input.nextInt();
							}
							// else case, that is the current board is not full
						} else{	
							System.out.println("Please select a valid square on the selected board: ");
							System.out.print("Selectd Square : ");
							index = input.nextInt();
							// the input should be between 0 and 8
							while(index > 8 || index < 0) {
								System.out.println("This input is invalid, so please input the number from 0 to 9");
								index = input.nextInt();
							}
						}
					}
				}
			} 
		}
		
			
		// This method is in order to play AI vs AI
		public void startAI() {
			
			System.out.println("=====WELCOME TO THE ULTIMATE TIC-TAC-TOE GAME!! =====");
			print(); // Print the board
			
			System.out.println("Current Player is : " + (currentPlayerIndex+1) +" "+ marks[this.currentPlayerIndex]);
			System.out.println("Please select a valid board:");	
			this.currentPlayerIndex = 0;
			
			// the start will be random number from the computerPlayer from the class 
			int start = players[this.currentPlayerIndex].randomNumber(9);
			System.out.println("Selectd Board : " + start);
			System.out.println("Please select a valid square on the selected board: ");
			
			// the index will be random number from the computerPlayer from the class 
			int index = players[this.currentPlayerIndex].randomNumber(9);
			System.out.println("Selected Square : " + index);
			
			// Until the game is not end
			while(!bigGameOver()) {
				// In the currentBoard, the AI players make place in the board
				if(!returnBoard(start).makeMove(players[this.currentPlayerIndex].getMark(),
						index)) {
					//After the current player places the mark, and then the player is switched
					switchPlayer();
					// To check the small board game is over or not
					smallGameOver(start);
					print();
					
					// For the next turn, the current index becomes start
					start = index;
					
					// Until the game is over
					if(!bigGameOverResult()) {
						// If the current board is Full
						if(returnBoard(start).isFull()) {
							// The current player has chance to pick the board(it'll be random value)
							System.out.println("Current Player is : " + marks[this.currentPlayerIndex]);
							System.out.println("Board " + start + " is Full. So, please choose other Board");
							start = players[this.currentPlayerIndex].randomNumber(9);
							System.out.println("Selectd Board : " + start);
							System.out.println("Please select a valid square on the selected board: ");
							index = players[this.currentPlayerIndex].randomNumber(9);
							System.out.println("Selected Square : " + index);
						
							// The game is continued on.
						}else {
							System.out.println("Current Player is : " + marks[this.currentPlayerIndex]);
							System.out.println("Selected Board: " + start);
							System.out.println("Please select a valid square on the selected board: ");
							index = players[this.currentPlayerIndex].randomNumber(9);
							System.out.println("Selected Square : " + index);
						}
					}
				}
			} 
		}

		// This method is for the game AI versus Human
		public void startAIWithHuman() {
			
			System.out.println("=====WELCOME TO THE ULTIMATE TIC-TAC-TOE GAME!! =====");
			print();
			
			// The first player will be AI
			Scanner input = new Scanner(System.in);
			System.out.println("Current Player is : " + marks[this.currentPlayerIndex]);
			
			System.out.println("Please select a valid board:");			
			int start = players[this.currentPlayerIndex].randomNumber(9);
			System.out.println("Selectd Board : " + start);
			
			System.out.println("Please select a valid square on the selected board: ");
			int index = players[this.currentPlayerIndex].randomNumber(9);
			System.out.println("Selected Square : " + index);
			
			
			// Until the game is over
			while(!bigGameOver()) {
				// the current player might place the mark
				if(!returnBoard(start).makeMove(players[this.currentPlayerIndex].getMark(),
						index)) {
					switchPlayer();
					smallGameOver(start);
					print();
					start = index;
					
					// Start should be between 0 and 8
					while(start > 8 || start < 0) {
						System.out.println("This input is invalid, so please input the number from 0 to 9");
						start = input.nextInt();
						System.out.print("Selectd Board : " + start);
						
					}
					// if the game is not end
					if(!bigGameOverResult()) {
						// check that the current board is full
						if(returnBoard(start).isFull()) {
							System.out.println("Current Player is : " + marks[this.currentPlayerIndex]);
							System.out.println("Board " + start + " is Full. So, please choose other Board");
							
							// This means that the current player is AI
							if(this.currentPlayerIndex == 0) {
								start = players[this.currentPlayerIndex].randomNumber(9);
							// This means that the curretn player is human
							}else {
								start = input.nextInt();
								
								// the start should be between 0 to 8
								while(start > 8 || start < 0) {
									System.out.println("This input is invalid, so please input the number from 0 to 9");
									start = input.nextInt();
								}
							}

							System.out.print("Selectd Board : " + start);
							System.out.println("Please select a valid square on the selected board: ");

							// This means that the current player is AI
							if(this.currentPlayerIndex == 0) {
								index = players[this.currentPlayerIndex].randomNumber(9);
								
							// This means that the current player is Human
							}else {
								index = input.nextInt();
								// The input should be 0 to 8
								while(index > 8 || index < 0) {
									System.out.println("This input is invalid, so please input the number from 0 to 9");
									index = input.nextInt();									
								}
							}
							System.out.println("Selected Square : " + index);
							
						} // When the board is not full
						else {
							System.out.println("Current Player is : " + marks[this.currentPlayerIndex]);
							System.out.println("Selected Board: " + start);
							System.out.println("Please select a valid square on the selected board: ");
							
							// the current player is AI
							if(this.currentPlayerIndex == 0) {
								index = players[this.currentPlayerIndex].randomNumber(9);
								
							// The current player is Human
							}else {
								index = input.nextInt();
								// When the input should be between 0 to 8
								while(index > 8 || index < 0) {
									System.out.println("This input is invalid, so please input the number from 0 to 9");
									System.out.print("Selected Square : ");
									index = input.nextInt();
								}
							}
							System.out.println("Selected Square : " + index);
						}
					}
				}
			}	
		}

		
		
		// This private method is in order to return true if "X" wins in the board
		// In the if-statement, I wrote all the cases when the "X" wins
		// Therefore, other cases are false
		private boolean xWinner(int i) {
				if((returnBoard(i).getMark(0).equals("X") && returnBoard(i).getMark(1).equals("X") && returnBoard(i).getMark(2).equals("X"))||
						(returnBoard(i).getMark(3).equals("X") && returnBoard(i).getMark(4).equals("X") && returnBoard(i).getMark(5).equals("X"))||
						(returnBoard(i).getMark(6).equals("X") && returnBoard(i).getMark(7).equals("X") && returnBoard(i).getMark(8).equals("X"))||
						(returnBoard(i).getMark(0).equals("X") && returnBoard(i).getMark(3).equals("X") && returnBoard(i).getMark(6).equals("X"))||
						(returnBoard(i).getMark(1).equals("X") && returnBoard(i).getMark(4).equals("X") && returnBoard(i).getMark(7).equals("X"))||
						(returnBoard(i).getMark(2).equals("X") && returnBoard(i).getMark(5).equals("X") && returnBoard(i).getMark(8).equals("X"))||
						(returnBoard(i).getMark(0).equals("X") && returnBoard(i).getMark(4).equals("X") && returnBoard(i).getMark(8).equals("X"))||
						(returnBoard(i).getMark(2).equals("X") && returnBoard(i).getMark(4).equals("X") && returnBoard(i).getMark(6).equals("X"))) {
					return true;
				} else
					return false;
			}
		// This private method is in order to return true if "O" wins in the board
		// In the if-statement, I wrote all the cases when the "O" win.
		// Therefore, other cases are false
		private boolean oWinner(int i) {
				if((returnBoard(i).getMark(0).equals("O") && returnBoard(i).getMark(1).equals("O") && returnBoard(i).getMark(2).equals("O"))||
						(returnBoard(i).getMark(3).equals("O") && returnBoard(i).getMark(4).equals("O") && returnBoard(i).getMark(5).equals("O"))||
						(returnBoard(i).getMark(6).equals("O") && returnBoard(i).getMark(7).equals("O") && returnBoard(i).getMark(8).equals("O"))||
						(returnBoard(i).getMark(0).equals("O") && returnBoard(i).getMark(3).equals("O") && returnBoard(i).getMark(6).equals("O"))||
						(returnBoard(i).getMark(1).equals("O") && returnBoard(i).getMark(4).equals("O") && returnBoard(i).getMark(7).equals("O"))||
						(returnBoard(i).getMark(2).equals("O") && returnBoard(i).getMark(5).equals("O") && returnBoard(i).getMark(8).equals("O"))||
						(returnBoard(i).getMark(0).equals("O") && returnBoard(i).getMark(4).equals("O") && returnBoard(i).getMark(8).equals("O"))||
						(returnBoard(i).getMark(2).equals("O") && returnBoard(i).getMark(4).equals("O") && returnBoard(i).getMark(6).equals("O"))) {
					return true;
				}
				 else
					return false;
			}
}

