/*
 */
package modelchecker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import modelchecker.ModelChecker.Debug;

/**
 *
 * @author repoman
 */
public class Parser {
    public static void parse(File file) throws FileNotFoundException {
        try (Scanner s = new Scanner(new BufferedReader(new FileReader(file)))) {
            if (s.hasNext("%")) alphabet(s);
            if (s.hasNext("%")) automaton(s);
            if (s.hasNext("%")) initState(s);
            if (s.hasNext("%")) finalStates(s);
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
        if (Debug.DEBUG) System.out.println("INSERT CHAR: "+alpha);
    }

    private static void automaton(Scanner s) {
        s.nextLine();   // Consume BEGIN
        
        // Loop until Initial BEGIN or EOF
        while (s.hasNext() && !s.hasNext("%")) {
            // Insert Transitions into Automaton
            // TODO: Implement Transitions
            String line = s.nextLine();
            
            if (line.contains(" ")) {
                String[] source = line.split(" ");
            } else {
                
            }
        }
    }

    private static void initState(Scanner s) {
        String id = "";
        
        s.nextLine();  // Consume BEGIN
        
        if (s.hasNext()) {
            id = s.next();
        }
    }

    private static void finalStates(Scanner s) {
        s.nextLine();   // Consume BEGIN
        
        while (s.hasNext() && !s.hasNext("%")) {
            // Initialize Final States
        }
    }
}