package org.ethanchen.uwaterloo.sudokuSolver.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Solution {
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
