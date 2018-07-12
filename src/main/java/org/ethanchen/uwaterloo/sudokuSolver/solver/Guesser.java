package org.ethanchen.uwaterloo.sudokuSolver.solver;

import java.util.Stack;

public class Guesser {
	Node root;
	boolean found = false;
	boolean bottom = false;
	boolean[] poss = new boolean[9];
	
	Stack<Node> stack = new Stack<Node>();
	Solver solve = new Solver();
	Linkedgrid grid = new Linkedgrid(9);
	
	Guesser(Node root){
		this.root = root;
		stack.push(this.root);
		stack.push(Node.clone(stack.get(0)));
		
	}
	
	public boolean guess(){
		
		if(found == false){
			boolean found = false;
			
			stack.push(Node.clone(stack.peek()));
			
			Node marker = grid.getRoot();
			Node ymarker = marker;
			
			while(ymarker != null && found == false){
				marker = ymarker;
				while(marker != null && found == false){
					
					for(int i = 0; i < 9; i++){
					
						if(Node.findNode(stack.peek(), marker.getColumnID(), marker.getRowID()).getData()[i] == true && Node.findNode(stack.peek(), marker.getColumnID(), marker.getRowID()).getValue() == -1){//if it is a possible guess
							//make guess
							boolean tempPoss[] = new boolean[9];
							tempPoss[i] = true;
							Node.findNode(stack.peek(), marker.getColumnID(), marker.getRowID()).setData(tempPoss);
							//solve the guessed grid
							Node temp = solve.solve(stack.peek());
							stack.pop();
							stack.push(temp);
							
							//Node.display(stack.peek());
							//System.out.println(isInvalid(stack.peek()));
							//System.out.println(stack.size());
							//System.out.println("-------------------------------------------------------------------------");
							
							if(isInvalid(stack.peek()) || stack.size() > 4){//if invalid start fresh
								stack.pop();
								stack.push(Node.clone(stack.peek()));
								
								/*boolean[] poss1 = new boolean[9];
								for(int x = 0; x < 9; x++){
									poss1[x] = Node.findNode(stack.peek(), marker.getColumnID(), marker.getRowID()).getData()[x];
								}
								poss1[i] = false;
								
								Node.findNode(stack.peek(), marker.getColumnID(), marker.getRowID()).setData(poss1);*/
								
							}else if(!Node.isSolved(stack.peek())){//if not solved, guess again
								
								guess();
								if(found == true){
									return true;
								}else if(found == false){
									
									stack.pop();
									stack.push(Node.clone(stack.peek()));
									
								}
								
							}else{//if solved return true back to original statement
								found = true;
								return true;
							}
							
						}
					}
					
					marker = marker.getRight();
				}
				ymarker = ymarker.getDown();
			}
			
			stack.pop();
			return false;
		
		}else{
			return true;
		}
	}
	
	public boolean isInvalid(Node root){
		Node marker = root;
		Node ymarker = root;
		
		while(ymarker != null){
			
			marker = ymarker;
			
			while(marker != null){
				
				if(Node.numberPossibilities(marker) == 0){
					
					return true;
				}
				
				marker = marker.getRight();
				
			}
			
			ymarker = ymarker.getDown();
			
		}
		
		return false;
	}
	
	public Node getSolution(){
		
		return stack.peek();
		
		
	}

	
}


