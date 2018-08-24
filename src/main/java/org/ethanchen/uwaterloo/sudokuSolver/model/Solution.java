package org.ethanchen.uwaterloo.sudokuSolver.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Solution {
	
	//a solution contains the solution to a given puzzle (if a puzzle was unsolvable it will have the 
	//property of solvable as false, therefore the value of the solution will be disregarded)
	
	private String solution;
	private boolean solvable;
	
	public Solution(String solution, boolean solvable) {
		
		this.solution = solution;
		this.solvable = solvable;
	}
	
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public boolean isSolvable() {
		return solvable;
	}
	public void setSolvable(boolean solvable) {
		this.solvable = solvable;
	}
	
	
}
