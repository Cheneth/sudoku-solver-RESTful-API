package org.ethanchen.uwaterloo.sudokuSolver.solver;

import org.ethanchen.uwaterloo.sudokuSolver.model.Solution;

public class SolutionUtility {

	public Solution solve(String input){//solves the input string
		int[] inputArray = new int[81];
		
		for(int i = 0; i < 81; i++){
			inputArray[i] = Character.getNumericValue(input.charAt(i));
		}
		
		Linkedgrid grid = new Linkedgrid(9);
		Node board = grid.getRoot();
		
		grid.loadBoard(inputArray);
		
		Solver solver = new Solver();
		
		board = solver.solve(grid.getRoot());
		
		Guesser guesser = new Guesser(board);
		
		guesser.guess();//recursive guessing in case the puzzle is not solvable otherwise.
		
		return new Solution(Node.displayTrue(guesser.getSolution()), Node.isSolved(guesser.getSolution()));
		
		
	}
	
}
