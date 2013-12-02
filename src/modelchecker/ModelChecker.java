/*
 * Name:	Cory R Siebler
 * ID:		1000832292
 * Assignment:	CSE355 Optional Project
 * Description:	Main execution of Program. Calls Parser to gather correct information from
 * 		text file. Reads in Automaton and stores them as an ArrayList of States.
 *		Perform BFS to gather String that is in the Language of the DFA. Utilize
 *		Queue Linked List to store the Transitions and States discovered while tranversing
 *		the graph construction from the Langauge.
 */
package modelchecker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author csiebler
 */
public class ModelChecker {
    //--------------------------------------------------//
	// Debug Interface									//
	//													//
	// Contains the boolean variables to control the	//
	// debugging options of the program.				//
	//--------------------------------------------------//
	public interface Debug {
		public static final boolean PARSE_DEBUG = true;
		public static final boolean STATE_DEBUG = true;
		public static final boolean BFS_DEBUG = true;
		public static final boolean CMPL_DEBUG = true;
		public static final boolean DFA_DEBUG = true;
	}

	/**
	 * @param args the command line arguments
	 * @throws java.io.FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		File file;
		ArrayList<ArrayList<State>> machines;
		
		if (args.length > 0) {
			file = new File(args[0]);
		} else {
//			file = new File("tests/exmp01_pt40.txt");
//			file = new File("tests/exmp01_pt60.txt");
			file = new File("tests/exmp01_pt80.txt");
//			file = new File("tests/exmp01_pt100.txt");
		}
		
		try (Scanner s = new Scanner(new BufferedReader(new FileReader(file)))) {
			machines = Parser.parse(s);
		}
		
		if (Debug.STATE_DEBUG)	Parser.printStates(machines.get(0));
		System.out.println("FINDING STRING");
		System.out.println(Parser.bfs(machines.get(0)));
		System.out.println("BFS COMPLETE");
		Parser.complement(machines.get(0));
		System.out.println("COMPLEMENT PERFORMED");
		if (Debug.STATE_DEBUG)	Parser.printStates(machines.get(0));
		System.out.println(Parser.bfs(machines.get(0)));
		System.out.println("BFS COMPLETE");
	}
}