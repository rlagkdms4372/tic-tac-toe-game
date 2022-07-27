package finalProject;
// CS 2336.003 Haeun Kim
/*
 * Analysis:
 * I need to make the game print very well through this class
 * The main game is working through the TTTSBGame class.
 * I'll make the ttt is from TTTSBGame
 * 
 * Design:
 * This class name is DriverMain class.
 * It contains the 'public static void main(String[] args)
 * I'll declare the ttt from TTTSBGame
 * There is three kinds of game (Human vs Human, Human vs AI, AI vs AI)
 */
public class DriverMain {

	public static void main(String[] args) {	
		TTTSBGame ttt = new TTTSBGame();
		//ttt.startAI(); // This is the game between AI vs AI
		//ttt.start(); // This is the game between Human vs Human
		ttt.startAIWithHuman(); // This is the game between AI vs Human
		
	}

}
