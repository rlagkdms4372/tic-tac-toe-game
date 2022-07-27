package finalProject;
//CS 2336.003 Haeun Kim
/* Analysis:
 * ComputerPlayer is extends from AIPlayer
 * This class is for the AI vs Human, and AI vs AI game
 * I have to solve the problem by extending from the AIPlayer class
 * AI automatically works, so I need to code for automatic returning number
 * 
 * Design:
 * When the name and mark are constructed, it'll be overridden from AIPlayer by using super(name, mark)
 * the randomNumber method returns the random number with the range. // This is main function for the Computer play
 * - I'll use Math.random() function, and (int) Math.random() * range return the integer any number between 0 to range
 * 
 */
public class ComputerPlayer extends AIPlayer{
	
	private String mark;
	private String name;
	
	public ComputerPlayer(String name, String mark){
		super(name, mark); // Overriding
	}
	
	// This method return the random number in the 0 to range
	public int randomNumber(int range) {
		return (int) (Math.random() * range);
	}

}
