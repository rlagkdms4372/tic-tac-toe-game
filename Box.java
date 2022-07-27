package finalProject;
//CS 2336.003 Haeun Kim
/*
 * Analysis:
 * This is box class which creates each boxes of board
 * I'm going to initialize the each boxes with Dash "-", and dash should be static variable.
 * What I need to solve the problem at this class is to make the useful boxes and useful methods which would be used
 * 
 * Design:
 * 1. I'll make the static String Dash --> -
 * 2. The Box.Dash becomes the placeHolder for now
 * 3. getPlaceHolder method returns the placeHolder, but setPlaceHolder place the input placeHolder in the box
 * 4. If the placeHolder is equals to dash, it means that none of mark is placed, so it is available and return true
 * 5. print method is for the printing the box
 * 
 */
public class Box {
	private int row;
	private int col;
	// My static string is Dash
	private final static String DASH = "-";
	private String placeHolder = Box.DASH;
	
	// Construct the Box
	public Box(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	// Return the placeHolder
	public String getPlaceHolder() {
		return placeHolder;		
	}
	
	// This method is to set the placeHolder
	public boolean setPlaceHolder(String placeHolder) {
		// if is is available
		if(isAvailable()) {
			// then this placeHolder becomes placeHoler
			this.placeHolder = placeHolder;
			return true;	
		}
			return false;
			
	}
	
	//if the placeHolder is same with dash, then return true
	public boolean isAvailable() {
		return this.placeHolder.equals(Box.DASH);
	}
	
	// Print out the box
	public void print() {
		System.out.print(placeHolder + "|");
	}
	
	
}
