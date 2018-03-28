import java.util.*;

/**
* Class which contains the Nodes for the T9 dictionary.
*/
class Node {

	//All the nodes representing the 26 letters in the alphabet
	Node abc;
	Node def;
	Node ghi;
	Node jkl;
	Node mno;
	Node pqrs;
	Node tuv;
	Node wxyz;
	LinkedList<String> word;

	/**
	* Constructor setting all nodes and Linked list to null
	*/
	Node() {
		this.word = null;
		
		this.abc = null;
		this.def = null;
		this.ghi = null;
		this.jkl = null;
		this.mno = null;
		this.pqrs = null;
		this.tuv = null;
		this.wxyz = null;


	}

	/**
	*	@override
	*	Method to return words stored in the linkedlist
	*	@return String list of words stored in node
	*/
	public String toString() {
		return this.word.toString();
	}
}