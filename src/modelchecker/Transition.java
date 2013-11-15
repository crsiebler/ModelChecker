/*
 */
package modelchecker;

/**
 *
 * @author repoman
 */
public class Transition {
    char label; // Character given to label Transition
    int destIndex;  // Index of the Destination State in the ArrayList

    public Transition(char label, int destIndex) {
        this.label = label;
        this.destIndex = destIndex;
    }

    @Override
    public String toString() {
        return "{ " + label + " : " + destIndex + " }";
    }

    public char getLabel() {
        return label;
    }

    public void setLabel(char label) {
        this.label = label;
    }

    public int getDestIndex() {
        return destIndex;
    }

    public void setDestIndex(int destIndex) {
        this.destIndex = destIndex;
    }
}