package org.ethanchen.uwaterloo.sudokuSolver.solver;

public class Linkedgrid {
	private Node root;
	private int size;
	
	public Linkedgrid(int size){//create grid and ID all cells
		//create first layer
		this.size = size;
		root = new Node();
		Node xmarker = root;
		Node ymarker = root;
		Node temp;
		
		for(int x = 0; x < this.size-1; x++){//make the first row
			
			temp = new Node();
			temp.setLeft(xmarker);
			xmarker.setRight(temp);
			xmarker = temp;
			
		}
		for(int y = 0; y < this.size-1; y++){//repeat to create the rest of the rows
			//make the first node of the row
			temp = new Node();
			temp.setUp(ymarker);
			ymarker.setDown(temp);
			ymarker = temp;
			xmarker = ymarker;
			for(int x = 0; x < size-1; x++){//make a row and connect each node to the above one
				temp = new Node();
				temp.setLeft(xmarker);
				xmarker.setRight(temp);
				temp.setUp(temp.getLeft().getUp().getRight());
				temp.getUp().setDown(temp);
				xmarker = temp;
			}
		}
		boardID();
	}
	
	public void boardID(){//give column ID, row ID, and box ID
		
		int rowID = 0;
		int columnID = 0;
		Node marker = root;
		Node ymarker = root;
		
		while(ymarker != null){
			marker = ymarker;
			while(marker != null){
				
				
				marker.setColumnID(columnID);
				marker.setRowID(rowID);
				marker.setBoxID(box(marker.getColumnID(), marker.getRowID()));
				columnID++;
				marker = marker.getRight();
			}
			columnID = 0;
			rowID++;
			ymarker = ymarker.getDown();
		
		}
	}
	
	public int box(int column, int row){//returns box number
		
		if(row <= 2 && column <= 2){//box 1
			return 1;
		}
		if(column >= 3 && column <= 5 && row <= 2){//box 2
			return 2;
		}
		if(column >= 6 && row <= 2){//box 3
			return 3;	
		}
		if(column <= 2 && row >= 3 && row <= 5){//box 4
			return 4;
		}
		if(column >= 3 && column <= 5 && row >= 3 && row <= 5){//box 5
			return 5;
		}
		if(column >= 6 && row >= 3 && row <= 5){//box 6
			return 6;
		}
		if(column <= 2 && row >= 6){//box 7
			return 7;
		}
		if(column >= 3 && column <= 5 && row >= 6){//box 8
			return 8;
		}
		if(column >= 6 && row >=6){//box 9
			return 9;
		}
		return 0;
	}
	
	public void loadBoard(int[] intake){//populate board possibilities from array
		
		int count = 0;
		Node marker = root;
		Node ymarker = root;
		
		while(ymarker != null){
			marker = ymarker;
			while(marker != null){
				
				if(intake[count] > 0){
					
					boolean[] poss = {false, false, false, false, false, false, false, false, false};
					poss[intake[count]-1] = true;
					marker.setData(poss);
				}
				
				
				count++;
				
				marker = marker.getRight();
			}
			
			ymarker = ymarker.getDown();
		}
		
	}

	public void displayID(){//currently displays boxID
		
		int count = 0;
		Node marker = root;
		Node ymarker = root;
		
		while(ymarker != null){
			marker = ymarker;
			while(marker != null){
				count++;
				System.out.print(marker.getBoxID() + " ");
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
	
	public void display(){//displays grid
		
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

	
	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	
	
}

