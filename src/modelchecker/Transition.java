/*
 * Name:	Cory R Siebler
 * ID:		1000832292
 * Assignment:	CSE355 Optional Project
 * Description: Stores the Transition information. Destination State repesented by index in ArrayList.	
 */
package modelchecker;

/**
 * @author csiebler
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
