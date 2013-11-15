/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelchecker;

import java.util.ArrayList;

/**
 *
 * @author repoman
 */
public class State {
    private int index;  // Index value of where State is Stored in ArrayList
    private boolean finalState; // Denotes State as Accepting or Rejecting
    private boolean initState;  // Starting Node in BFS of DFA
    private boolean discoverd;  // Denotes whether State has been found as a destination (USE: Breadth-First-Search)
    private boolean finished;   // Denotes whether State has completed searching through its Transitions
    private ArrayList<Transition> transitions;  // List of Transitions for the DFA

    public State(int index) {
        this.index = index;
        this.finalState = false;
        this.initState = false;
        this.discoverd = false;
        this.finished = false;
        this.transitions = new ArrayList<>();
    }
    
    
}