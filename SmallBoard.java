package finalProject;
//CS 2336.003 Haeun Kim
/* Analysis:
 * This is the Small Board method which makes the smallBoard, and the relationship between Box and SmallBoard is composition
 * This made smallBoard would appear in the TTTSBGame
 * We will make the board by using the boxes.
 * 1 Board has 9 boxes, so I'll create 9 array for boxes, and I'll 
 * Design:
 * I'll make the construct method, which is useful to make various small board from the TTTSBGmae
 * - setSize method : this is in order to make the Box array for the board
 * - init method: this is in order to put the Box into boxes array.
 * - print method : This is in order to print the box of current index
 * 1: isFull method let us know whether the current board is full or not
 * - getMark method returns the current boxes' placeHolder and getName method returns the name
 * - makeMove method is for placing the mark. If the player can place its mark, then return true, and place the placeHolder
 */
public class SmallBoard{
		private Box[] boxes; // boxes is an array from Box class
		private String name;
		private int boardRowSize = 3;
		private int boardColSize = 3;
		
		// Construct the SmallBoard method
		SmallBoard(int rowSize, int colSize, String name){
			this.setName(name);
			this.setSize(rowSize, colSize);
		}
		
		// This method is to set the size
		public void setSize(int row, int col) {
			this.boardColSize = col;
			this.boardRowSize = row;
			// the boxes have 9 elements
			boxes = new Box[boardColSize*boardRowSize];
			init();

		}
		
		// This method is to initialize the box and put them in boxes array
		public void init() {
			for(int i = 0; i < boxes.length; i++) {
				Box b = new Box(i/boardColSize, i%boardColSize);
					boxes[i]= b;
			}
		}
		// This method is to print the boxes array
		 public void print(int i) {
				boxes[i].print();
				
		 }
		 // Set the name of board
		public void setName(String name) {
			this.name = name;
		}

		// This return whether we can place the mark or not
		public boolean makeMove(String mark, int index) {
			// if the mark is set on the box[index]
			if(boxes[index].setPlaceHolder(mark)) {			 
				 return true; // return true
			}
				return false; // if the mark can't be set, return false
		}
		
		// This method is to check whether the current board is Full or not
		public boolean isFull() {
			// for all the boxes of the board
			for(Box b : boxes)
				// if there is some box where it isn't filled, then return false
				if(b.isAvailable()) return false;
			// the other case return true, means that the board is full
			return true;
		}
		
		// This return the mark in the current index in the box
		public String getMark(int i) {
			return boxes[i].getPlaceHolder();
		}
		// This method return the name
		public String getName() {
			return this.name;
		}
	}
