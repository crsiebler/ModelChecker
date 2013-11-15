/*
 */
package modelchecker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import modelchecker.ModelChecker.Debug;

/**
 *
 * @author repoman
 */
public class Parser {
	public static ArrayList<State> states;
	
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

    private static void alphabet(Scanner s) {
        s.nextLine();   // Consume BEGIN
        
        // Loop until Automaton BEGIN or EOF
        while (s.hasNext() && !s.hasNext("%")) {
            // Insert Characters into Alphabet
            character(s);
        }
    }

    private static void character(Scanner s) {
        // Assign Character from String
        char alpha = (char) s.nextLine().charAt(0);
        
        // Insert Character into Alphabet
        if (Debug.PARSE_DEBUG) System.out.println("INSERT CHAR: "+alpha);
    }
	
	private static int addState(String id) {
		Parser.states.add(new State(Parser.states.size(), id));
		if (Debug.PARSE_DEBUG) System.out.println("ADDING STATE: "+id);
		return (Parser.states.size() - 1);
	}

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

	private static void transition(Scanner s, State state, String line) {
		int sourceIndex;
		char tran;
		
		// Create new Transition from Source
		String[] data = line.split(" ");

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
		
		while (s.hasNext() && !s.hasNext("%")) {
			line = s.nextLine();
			
			if (!line.contains(" ")) {
				destination(s, state, tran, line);
			} else {
				if (Debug.PARSE_DEBUG) System.out.println(state);
				transition(s, state, line);
			}
		}
	}
	
	private static void destination(Scanner s, State state, char tran, String line) {
		int index = Parser.states.indexOf(new State(0, line));

		if (index != -1) {
			state.getTransitions().add(new Transition(tran, index));
		} else {
			state.getTransitions().add(new Transition(tran, addState(line)));
		}
		
		if (Debug.PARSE_DEBUG) System.out.println("ADDING TRANSITION: "+tran);
	}

    private static void initState(Scanner s) {
        String id = "";
        
        s.nextLine();  // Consume BEGIN
        
        if (s.hasNext()) {
			int sourceIndex = Parser.states.indexOf(new State(0, s.nextLine()));
			
			if (sourceIndex != -1) {
				Parser.states.get(sourceIndex).setInitState(true);
				if (Debug.PARSE_DEBUG) System.out.println("INITIAL STATE: "+sourceIndex);
			}
        }
    }

    private static void finalStates(Scanner s) {
        s.nextLine();   // Consume BEGIN
        
        while (s.hasNext() && !s.hasNext("%")) {
            // Initialize Final States
			int sourceIndex = Parser.states.indexOf(new State(0, s.nextLine()));
			
			if (sourceIndex != -1) {
				Parser.states.get(sourceIndex).setFinalState(true);
				if (Debug.PARSE_DEBUG) System.out.println("FINAL STATE: "+sourceIndex);
			}
        }
    }
}