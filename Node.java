import java.util.*;

class Node {
	Node abc;
	Node def;
	Node ghi;
	Node jkl;
	Node mno;
	Node pqrs;
	Node tuv;
	Node wxyz;
	LinkedList<String> word;

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

	public String toString() {
		return this.word.toString();
	}
}