package org.ethanchen.uwaterloo.sudokuSolver.solver;

public class Node {
	private Node left;
	private Node right;
	private Node up;
	private Node down;
	private boolean[] data = {true, true, true, true, true, true, true, true, true};
	private int value;
	private int boxID;
	private int rowID;
	private int columnID;
	
	public Node() {
		// TODO Auto-generated constructor stub
		setValue(-1);
		left = null;
		right = null;
		up = null;
		down = null;
	}



	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	public Node getUp() {
		return up;
	}
	public void setUp(Node up) {
		this.up = up;
	}
	public Node getDown() {
		return down;
	}
	public void setDown(Node down) {
		this.down = down;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}


	public boolean[] getData() {
		return data;
	}


	public void setData(boolean[] data) {
		this.data = data;
	}


	public int getBoxID() {
		return boxID;
	}



	public void setBoxID(int boxID) {
		this.boxID = boxID;
	}



	public int getRowID() {
		return rowID;
	}



	public void setRowID(int rowID) {
		this.rowID = rowID;
	}



	public int getColumnID() {
		return columnID;
	}



	public void setColumnID(int columnID) {
		this.columnID = columnID;
	}
	
	public static int numberPossibilities(Node node){//returns #possibilities for a node
		int count = 0;
		for(int i = 0; i < 9; i++){
			if(node.getData()[i] == true){
				count++;
			}
		}
		
		return count;
	}
	
	public static boolean isSolved(Node root){//put in root of a grid, returns solved status as boolean
		Node ymarker = root;
		Node marker = root;
		
		int count = 0;
		
		while(ymarker != null){
			marker = ymarker;
			
			while(marker != null){
				
				if(marker.getValue() > -1){
					count++;
				}
				
				marker = marker.getRight();
			}
			ymarker = ymarker.getDown();
		}
		
		if(count == 81){
			return true;
		}else{
			return false;
		}
		
	}
		
	public static Node findNode(Node root, int column, int row){
		Node ymarker = root;
		Node marker = root;
		
		while(ymarker != null){
			marker = ymarker;
			
			while(marker != null){
				
				if(marker.getColumnID() == column && marker.getRowID() == row){
					return marker;
				}
				
				marker = marker.getRight();
			}
			ymarker = ymarker.getDown();
		}
		return null;
	}
	
	public static Node clone(Node root){//send in root of grid to be cloned, outputs root of cloned grid
		
		Linkedgrid clone = new Linkedgrid(9);
		
		Node marker = root;
		Node ymarker = root;
		
		Node cloneMarker = clone.getRoot();
		Node cloneYMarker = clone.getRoot();
		
		while(ymarker != null){
			marker = ymarker;
			cloneMarker = cloneYMarker;
			while(marker != null){
				
				cloneMarker.setData(marker.getData());
				cloneMarker.setValue(marker.getValue());
				
				marker = marker.getRight();
				cloneMarker = cloneMarker.getRight();
			}
			ymarker = ymarker.getDown();
			cloneYMarker = cloneYMarker.getDown();
		}
		
		return clone.getRoot();
		
	}
	
	public static void display(Node root){
		int count = 0;
		
		Node marker = root;
		Node ymarker = root;
		
		while(ymarker != null){
			marker = ymarker;
			while(marker != null){
				count++;
				System.out.print(marker.getValue() + " ");
				if(count%3 == 0){
					System.out.print("  ");
				}
				if(count%27 == 0){
					
					System.out.println();
				}
				
				marker = marker.getRight();
			}
			System.out.println();
			ymarker = ymarker.getDown();
		}
	}
	
	public static String displayTrue(Node root){//return solution board as String
		int count = 0;
		int arrayCount = 0;
		int[] boardArray = new int[81];
		Node marker = root;
		Node ymarker = root;
		
		while(ymarker != null){
			marker = ymarker;
			while(marker != null){
				count++;
				boardArray[arrayCount] = marker.getValue()+1;
				arrayCount++;
				marker = marker.getRight();
			}
			ymarker = ymarker.getDown();
		}
		String solution = "";
		
		for(int i = 0; i < 81; i++){
			solution+= boardArray[i];
		}
		
		return solution;
	}
	
	
		
	
	
	
	
	
}
