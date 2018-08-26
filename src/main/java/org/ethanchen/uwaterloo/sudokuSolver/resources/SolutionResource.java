package org.ethanchen.uwaterloo.sudokuSolver.resources;



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ethanchen.uwaterloo.sudokuSolver.model.Solution;
import org.ethanchen.uwaterloo.sudokuSolver.solver.SolutionUtility;

@Path("/solver")
public class SolutionResource {//takes get requests with path /solver/{puzzle} then returns solved puzzle in JSON format
	@GET //specify type of HTTP request methods
	@Path("/{puzzle}") //URL extension (within the brackets becomes the variable puzzle in getSolution().)
	@Produces(MediaType.APPLICATION_JSON) //type of data returned
	public Solution getSolution(@PathParam("puzzle") String incomingPuzzle){
	//	String input = ""+incomingPuzzle;
		
		SolutionUtility utility = new SolutionUtility();
		
		return utility.solve(incomingPuzzle);
		
		
	}
	
}
