/*
 * Name:	Cory R Siebler
 * ID:		1000832292
 * Assignment:	CSE355 Optional Project
 * Description:	Parser Class to hold the algorithms to construct the DFA graph.
 *		Contains the BFS algorithm to gather a String within the Language
 *		of the Automaton constructed. Constructed DFA by using compiler type
 *		Parsing.		
 */
package modelchecker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import modelchecker.ModelChecker.Debug;

/**
 * @author repoman
 */
public class Parser {
	public static ArrayList<State> states; // Stores the DFA containing the States of the Graph
	
	//--------------------------------------------------------------//
	// printStates Method						//
	//								//
	// Foreach State in the ArrayList print the class object.	//	
	//--------------------------------------------------------------//
	public static void printStates() {
		for (State s : states) {
			System.out.println(s);
		}
	}
	
	//--------------------------------------------------------------//
	// bfs Method							//
	//								//
	// Breadth-First-Search algorithm to gather a String within the	//
	// language of the Automaton. Utilize two Queues to store the	//
	// States and the String of Transitions which brought the	//
	// machine to that State.					//
	//--------------------------------------------------------------//
	public static String bfs() {
		// BFS uses Queue data structure
		Queue<State> stateQueue = new LinkedList<>();
		Queue<StringBuilder> strings = new LinkedList<>();
		
		// Loop through States to determine Initial State
		for (State s : Parser.states) {
			if (s.isInitState()) {
				if (!s.isFinalState()) {
					// Add State to Queue to start BFS
					stateQueue.add(s);
					strings.add(new StringBuilder());
					s.setDiscoverd(true);
				} else {
					// If Initial State is also Final State Return Epsilon
					return "";
				}
			}
		}
		
		// Continue Until Queue of States is Empty signifying every Node has been visited
		while(!stateQueue.isEmpty()) {
			// Pop the First Node in the Queue and Visit
			State state = (State) stateQueue.remove();
			StringBuilder sb = (StringBuilder) strings.remove();
			
			if (Debug.BFS_DEBUG) System.out.println("VISITING: "+state.getId());
			
			// Loop through each Transition for the current State
			for (Transition t : state.getTransitions()) {
				State child = Parser.states.get(t.getDestIndex());
				if (Debug.BFS_DEBUG) System.out.println("TRANSITION: "+state.getId()+" --"+t.label+"--> "+child.getId());
				
				// Check to see if Child Node has been added to the Queue already
				if (!child.isDiscoverd()) {
					// Check if Child Node is Final State
					// If true then return String to reach the State
					if (child.isFinalState()) {
						clearStates();
						return sb.append(t.label).toString();
					}
					
					// Append the String to reach Child Node to String to reach current State
					strings.add(new StringBuilder(sb.toString()).append(t.label));
					stateQueue.add(child);
					child.setDiscoverd(true);
					if (Debug.BFS_DEBUG) System.out.println("DISCOVERED: "+child.getId());
				}
			}
		}
		
		return "";
	}
	
	//----------------------------------------------------------------------//
	// clearStates Method							//
	//									//
	// Method to reassign each Discovered State to False for BFS Algorithm.	//
	//----------------------------------------------------------------------//
	public static void clearStates() {
		for (State s : Parser.states) {
			s.setDiscoverd(false);
		}
	}
	
	//--------------------------------------------------------------//
	// parse Method							//
	//								//
	// Reads through the text file and gathers the information to	//
	// construct the DFA. Initializes the ArrayList of States.	//
	//--------------------------------------------------------------//
    public static void parse(File file) throws FileNotFoundException {
		Parser.states = new ArrayList<>();
		
        try (Scanner s = new Scanner(new BufferedReader(new FileReader(file)))) {
            if (s.hasNext("%")) alphabet(s);
			if (Debug.PARSE_DEBUG) System.out.println("ALPHABET COMPLETE");
            if (s.hasNext("%")) automaton(s);
			if (Debug.PARSE_DEBUG) System.out.println("AUTOMATON COMPLETE");
            if (s.hasNext("%")) initState(s);
			if (Debug.PARSE_DEBUG) System.out.println("INIT-STATE COMPLETE");
            if (s.hasNext("%")) finalStates(s);
			if (Debug.PARSE_DEBUG) System.out.println("FINAL-STATES COMPLETE");
        }
    }

	//------------------------------------------------------//
	// alphdabet Method					//
	//							//
	// Parses through the list of characters and adds them	//
	// to the alphabet by calling the character method.	// 
	//------------------------------------------------------//
    private static void alphabet(Scanner s) {
        s.nextLine();   // Consume BEGIN
        
        // Loop until Automaton BEGIN or EOF
        while (s.hasNext() && !s.hasNext("%")) {
            // Insert Characters into Alphabet
            character(s);
        }
    }

	//----------------------------------------------//
	// character Method				//
	//						//
	// Adds a character to the alphabet list.	//
	//----------------------------------------------//
    private static void character(Scanner s) {
        // Assign Character from String
        char alpha = (char) s.nextLine().charAt(0);
        
        // Insert Character into Alphabet
        if (Debug.PARSE_DEBUG) System.out.println("INSERT CHAR: "+alpha);
    }
	
	//--------------------------------------------------------------//
	// addState Method						//
	//								//
	// Creates a new State and adds it to the list of States.	//
	//--------------------------------------------------------------//
	private static int addState(String id) {
		Parser.states.add(new State(Parser.states.size(), id));
		if (Debug.PARSE_DEBUG) System.out.println("ADDING STATE: "+id);
		return (Parser.states.size() - 1);
	}

	//------------------------------------------------------//
	// automaton Method					//
	//							//
	// Parses through the Automaton and adds Transistions.	//
	//------------------------------------------------------// 
    private static void automaton(Scanner s) {
        s.nextLine();   // Consume BEGIN
        
        // Loop until Initial BEGIN or EOF
        while (s.hasNext() && !s.hasNext("%")) {
			State state = null;
			
            // Insert Transitions into Automaton
			String line = s.nextLine();
			
			if (line.contains(" ")) {
				transition(s, state, line);
			}
        }
    }

	//--------------------------------------------------------------//
	// transition Method						//
	//								//
	// Adds a transition to the State and gathers the character.	//
	//--------------------------------------------------------------//
	private static void transition(Scanner s, State state, String line) {
		int sourceIndex;
		char tran;
		
		// Create new Transition from Source
		String[] data = line.split(" ");

		// Find the source State to add transition to
		sourceIndex = Parser.states.indexOf(new State(0, data[0]));
		tran = data[1].charAt(0);
		
		if (Debug.PARSE_DEBUG) System.out.println("SOURCE INDEX: "+sourceIndex);
		if (Debug.PARSE_DEBUG) System.out.println("TRANSITION: "+tran);

		if (sourceIndex == -1) {
			// State Not Found Create New State
			state = Parser.states.get(addState(data[0]));
		} else {
			// Grab State from ArrayList to add Transition
			state = Parser.states.get(sourceIndex);
		}
		
		// Continue adding transitions until INIT section or EOF
		while (s.hasNext() && !s.hasNext("%")) {
			line = s.nextLine();
			
			// Check to see if there is a new Transition or another Destination State
			if (!line.contains(" ")) {
				// Add Destination for Transition
				destination(s, state, tran, line);
			} else {
				// Add a new Transition
				if (Debug.PARSE_DEBUG) System.out.println(state);
				transition(s, state, line);
			}
		}
	}
	
	//--------------------------------------------------------------//
	// destination Method						//
	//								//
	// Creates a new Transition from the source State given in the	//
	// State parameter, and uses the character parameter for the	//
	// transition and uses the integer index to represent the	//
	// destination State.						//
	//--------------------------------------------------------------//
	private static void destination(Scanner s, State state, char tran, String line) {
		int index = Parser.states.indexOf(new State(0, line));

		if (index != -1) {
			state.getTransitions().add(new Transition(tran, index));
		} else {
			state.getTransitions().add(new Transition(tran, addState(line)));
		}
		
		if (Debug.PARSE_DEBUG) System.out.println("ADDING TRANSITION: "+tran);
	}

	//------------------------------------------------------//
	// initState Method					//
	//							//
	// Reads in the String to represent the Inital State.	//
	// Sets the initial State boolean to true.		//
	//------------------------------------------------------//
    private static void initState(Scanner s) {
        String id = "";
        
        s.nextLine();  // Consume BEGIN
        
        if (s.hasNext()) {
			int sourceIndex = Parser.states.indexOf(new State(0, s.nextLine()));
			
			// Check that State is found
			if (sourceIndex != -1) {
				Parser.states.get(sourceIndex).setInitState(true);
				if (Debug.PARSE_DEBUG) System.out.println("INITIAL STATE: "+sourceIndex);
			}
        }
    }

	//--------------------------------------------------------------//
	// finalStates Method						//
	//								//
	// Find the State that represents the String and set the 	//
	// Final boolean to true.					//
	//--------------------------------------------------------------//
    private static void finalStates(Scanner s) {
        s.nextLine();   // Consume BEGIN
        
        while (s.hasNext() && !s.hasNext("%")) {
            // Initialize Final States
			int sourceIndex = Parser.states.indexOf(new State(0, s.nextLine()));
			
			// Check that State is found
			if (sourceIndex != -1) {
				Parser.states.get(sourceIndex).setFinalState(true);
				if (Debug.PARSE_DEBUG) System.out.println("FINAL STATE: "+sourceIndex);
			}
        }
    }
}
