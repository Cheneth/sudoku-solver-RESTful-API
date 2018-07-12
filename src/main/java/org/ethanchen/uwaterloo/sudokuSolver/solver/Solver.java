package org.ethanchen.uwaterloo.sudokuSolver.solver;

public class Solver {
	
	private Node root;
	
	public Solver(){
		
	}
	
	public Node solve(Node root){
		this.root = Node.clone(root);
		method1();
		int countIdentical = 0;
		int pastSolvedCells = solvedCells(this.root);
		
		
		while(countIdentical < 1){
			
			method2();
			method1();
			
			if(solvedCells(this.root) == pastSolvedCells){
				
				countIdentical++;
			}else{
				countIdentical = 0;
			}
			pastSolvedCells = solvedCells(this.root);
		}
		
		return this.root;
		
	}
	
	private int solvedCells(Node root){//pass the root of a grid in, outputs the amount of solved cells
		Node marker = root, ymarker = root;
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
		
		return count;
	}
	
	private void bomb(int possibility,int columnID, int rowID, int boxID){//makes every other cell in the same column, row, or box get rid of a possibility
		
		Node marker = root;
		Node ymarker = root;
		
		while(ymarker != null){
			marker = ymarker;
			while(marker != null){
				
				if(marker.getColumnID() == columnID || marker.getRowID() == rowID || marker.getBoxID() == boxID){
					if(marker.getColumnID() == columnID && marker.getRowID() == rowID && marker.getBoxID() == boxID){
						
					}else{
						boolean[] poss = new boolean[9];
						for(int x = 0; x < 9; x++){
							poss[x] = marker.getData()[x];
						}
						poss[possibility] = false;
						marker.setData(poss);
					}
					//marker.setData(possibility, false);
				}
					
				
				marker = marker.getRight();
			}
			
			ymarker = ymarker.getDown();
		}
	}
	
	private void method1(){//if only 1 possibility, it must be that
		Node marker = root;
		Node ymarker = root;
		
		while(ymarker != null){
			marker = ymarker;
			while(marker != null){
				
				if(Node.numberPossibilities(marker) == 1){
					marker.setValue(singlePossibility(marker));
					bomb(marker.getValue(), marker.getColumnID(), marker.getRowID(), marker.getBoxID());
				}
				
				marker = marker.getRight();
			}
			
			ymarker = ymarker.getDown();
		}
		
	}
			private int singlePossibility(Node node){//only use if numberPossibilities is 1
				if(Node.numberPossibilities(node) == 1){
					for(int i = 0; i < 9; i++){
						if(node.getData()[i] == true){
							return i;
						}
					}
				}
				return -1;
			}
			
	private void method2(){//if a possibility is the only one in a column, row, or box
		method2column();
		method2Row();
		method2Box();
	}
	
			private void method2column(){
				
				Node temp = root;
				
				int[] countPoss = new int[9];
				while(temp != null){//across grid
					Node columnTemp = temp;
					while(columnTemp != null){//search down grid
						
						for(int i = 0; i < 9; i++){//count possibilities
							if(columnTemp.getData()[i] == true){
								countPoss[i]++;
							}
						}
						
						columnTemp = columnTemp.getDown();
					}
					//if any possibility is only found once, set its possibilities
					for(int i = 0; i < 9; i++){//for each possibility
						if(countPoss[i] == 1){//if only 1
							columnTemp = temp;
							while(columnTemp != null){//search down grid
								if(columnTemp.getData()[i] == true){//found it, set possibilities
									boolean[] poss = {false, false, false, false, false, false, false, false, false};
									poss[i] = true;
									columnTemp.setData(poss);
								}
								
								
								columnTemp = columnTemp.getDown();
							}
						}
					}
					countPoss = new int[9];
					temp = temp.getRight();
				}
			}
			
			private void method2Row(){
				Node temp = root;
				
				int[] countPoss = new int[9];
				while(temp != null){//down grid
					Node rowTemp = temp;
					while(rowTemp != null){//search across grid
						
						for(int i = 0; i < 9; i++){//count possibilities
							if(rowTemp.getData()[i] == true){
								countPoss[i]++;
							}
						}
						
						rowTemp = rowTemp.getRight();
					}
					//if any possibility is only found once, set its possibilities
					for(int i = 0; i < 9; i++){//for each possibility
						if(countPoss[i] == 1){//if only 1
							rowTemp = temp;
							while(rowTemp != null){//search across grid
								if(rowTemp.getData()[i] == true){//found it, set possibilities
									boolean[] poss = {false, false, false, false, false, false, false, false, false};
									poss[i] = true;
									rowTemp.setData(poss);
								}
								
								
								rowTemp = rowTemp.getRight();
							}
						}
					}
					countPoss = new int[9];
					temp = temp.getDown();
				}
			}
			
			private void method2Box(){//TODO 
				
				int[] countPoss = new int[9];
				
				Node marker = root;
				Node ymarker = root;
				
				while(ymarker != null){//down boxes
					marker = ymarker;
					while(marker != null){//across boxes
						
						Node subMarker = marker;
						Node ySubMarker = marker;
							for(int j = 0; j < 3; j++){//down box
								subMarker = ySubMarker;
								for(int i = 0; i < 3; i++){//across box
									
									
									for(int x = 0; x < 9; x++){//count possibilities
										if(subMarker.getData()[x] == true){
											countPoss[x]++;
										}
									}
									
									subMarker = subMarker.getRight();
								}
								ySubMarker = ySubMarker.getDown();
								
							}
							//if any possibility is found only once, set its possibilities
							
							for(int x = 0; x < 9; x++){//for each possibility
								if(countPoss[x] == 1){//if only 1
									subMarker = marker;
									ySubMarker = marker;
									for(int j = 0; j < 3; j++){//down box
										subMarker = ySubMarker;
										for(int i = 0; i < 3; i++){//across box
											
											if(subMarker.getData()[x] == true){//found it, set possibilities
												boolean[] poss = {false, false, false, false, false, false, false, false, false};
												poss[x] = true;
												subMarker.setData(poss);
											}
											
											subMarker = subMarker.getRight();
										}
										ySubMarker = ySubMarker.getDown();
										
									}
								}
							}
							
						countPoss = new int[9];
						marker = marker.getRight().getRight().getRight();
					}
					ymarker = ymarker.getDown().getDown().getDown();
				}
				
				
			}
}
