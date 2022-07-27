package finalProject;
//CS 2336.003 Haeun Kim
/*
 * Analysis:
 * This class is for the AI Player
 * This class will be the Super class
 * What I need to solve is making the method that the sub class can use this class as Super class
 * 
 * Design:
 * 1. I need to construct the AIPlayer with name and mark
 * 2. I'll make setName() method to set the name and getName() to return the name
 * 3. I'll make setMark() method to set the mark for the player, and getMark() to return the mark
 */

public class AIPlayer {
	private String mark;
	private String name;
	
	// Construct the AIPlayer class
	public AIPlayer(String name, String mark){
		this.setMark(mark);
		this.setName(name);
	}
	
	// return the player's name
	public String getName() {
		return name;
	}
	
	// This is to set the player's name
	public void setName(String name) {
		this.name = name;
	}
	
	// return the player's mark
	public String getMark() {
		return mark;
	}
	
	// This is to set the player's mark
	public void setMark(String mark) {
		this.mark = mark;
	}

}

