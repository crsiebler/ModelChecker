/*
 */
package modelchecker;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author repoman
 */
public class State {
    private int index;  // Index value of where State is Stored in ArrayList
    private String id;  // Identifier for State
    private boolean finalState; // Denotes State as Accepting or Rejecting
    private boolean initState;  // Starting Node in BFS of DFA
    private boolean discoverd;  // Denotes whether State has been found as a destination (USE: Breadth-First-Search)
    private boolean finished;   // Denotes whether State has completed searching through its Transitions
    private ArrayList<Transition> transitions;  // List of Transitions for the DFA

    public State(int index, String id) {
        this.index = index;
        this.id = id;
        this.finalState = false;
        this.initState = false;
        this.discoverd = false;
        this.finished = false;
        this.transitions = new ArrayList<>();
    }

    @Override
    public String toString() {
        String trans = "";
        // Foreach Transition Concatentate String Representation
        for (Transition tran : this.transitions) {
            trans += " "+tran;
        }
        
        return ((this.initState) ? ">>":"  ") + ((this.finalState) ? "|"+id+"|":" "+id+" ") + " -->" + trans;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final State other = (State) obj;
        
        return Objects.equals(this.id, other.id);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isFinalState() {
        return finalState;
    }

    public void setFinalState(boolean finalState) {
        this.finalState = finalState;
    }

    public boolean isInitState() {
        return initState;
    }

    public void setInitState(boolean initState) {
        this.initState = initState;
    }

    public boolean isDiscoverd() {
        return discoverd;
    }

    public void setDiscoverd(boolean discoverd) {
        this.discoverd = discoverd;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public ArrayList<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(ArrayList<Transition> transitions) {
        this.transitions = transitions;
    }
}