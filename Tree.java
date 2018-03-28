import java.util.*;

/**
*	Class contains methods to create a tree from Nodes in Node.java 
*	to implement T9 dictionary.
*/
public class Tree extends Node {

	//	root node for tree
	Node root;

	/**
	*	Constructor for tree
	*/
	public Tree() {
		root = new Node();
	}

	/**
	*	Calling method for inserting word into the tree. Calls the
	*	recursive insert method
	*/
	public void insert(String word) {
		insert_rec(word, 0, this.root);
	}

	/**
	*	recuserviely inserts words into the tree
	*	@param word the word to be inserted into tree
	* 	@param position the position of the letter of which to follow the node
	*		   to insert into a node
	*	@node the parent node of the current letter position
	*/
	public void insert_rec(String word, int position, Node node) {
		if (position == word.length()) {
			if (node.word == null) {
				node.word = new LinkedList<String>();
			}
			node.word.add(word);
			return;
		}

		word = word.toLowerCase();

		char current_char = word.charAt(position);

		if (current_char >= 97 && current_char <= 99) {
			if (node.abc == null) {
				node.abc = new Node();
			}
			this.insert_rec(word, position + 1, node.abc);
		}

		else if (current_char >= 100 && current_char <= 102) {
			if (node.def == null) {
				node.def = new Node();
			}
			this.insert_rec(word, position + 1, node.def);
		}

		else if (current_char >= 103 && current_char <= 105) {
			if (node.ghi == null) {
				node.ghi = new Node();
			}
			this.insert_rec(word, position + 1, node.ghi);
		}

		else if (current_char >= 106 && current_char <= 108) {
			if (node.jkl == null) {
				node.jkl = new Node();
			}
			this.insert_rec(word, position + 1, node.jkl);
		}

		else if (current_char >= 109 && current_char <= 111) {
			if (node.mno == null) {
				node.mno = new Node();
			}
			this.insert_rec(word, position + 1, node.mno);
		}

		else if (current_char >= 112 && current_char <= 115) {
			if (node.pqrs == null) {
				node.pqrs = new Node();
			}
			this.insert_rec(word, position + 1, node.pqrs);
		}

		else if (current_char >= 116 && current_char <= 118) {
			if (node.tuv == null) {
				node.tuv = new Node();
			}
			this.insert_rec(word, position + 1, node.tuv);
		}

		else if (current_char >= 119 && current_char <= 122) {
			if (node.wxyz == null) {
				node.wxyz = new Node();
			}
			this.insert_rec(word, position + 1, node.wxyz);
		}
	}

	/**
	*	recuserviely returns the node of a word in the tree
	*	@param word the word to search in the tree
	* 	@param position the position of the letter of which to follow the node
	*		   to find
	*	@param node the parent node of the current letter position
	*	@return Node the node of a specified word
	*/
	public Node getNode(String word, int position, Node node) {
		if (position == word.length()) {
			return node;
		}

		word = word.toLowerCase();

		char current_char = word.charAt(position);

		if (current_char >= 97 && current_char <= 99) {
			if (node.abc == null) {
				node.abc = new Node();
			}
			return this.getNode(word, position + 1, node.abc);
		}

		else if (current_char >= 100 && current_char <= 102) {
			if (node.def == null) {
				node.def = new Node();
			}
			return this.getNode(word, position + 1, node.def);
		}

		else if (current_char >= 103 && current_char <= 105) {
			if (node.ghi == null) {
				node.ghi = new Node();
			}
			return this.getNode(word, position + 1, node.ghi);
		}

		else if (current_char >= 106 && current_char <= 108) {
			if (node.jkl == null) {
				node.jkl = new Node();
			}
			return this.getNode(word, position + 1, node.jkl);
		}

		else if (current_char >= 109 && current_char <= 111) {
			if (node.mno == null) {
				node.mno = new Node();
			}
			return this.getNode(word, position + 1, node.mno);
		}

		else if (current_char >= 112 && current_char <= 115) {
			if (node.pqrs == null) {
				node.pqrs = new Node();
			}
			return this.getNode(word, position + 1, node.pqrs);
		}

		else if (current_char >= 116 && current_char <= 118) {
			if (node.tuv == null) {
				node.tuv = new Node();
			}
			return this.getNode(word, position + 1, node.tuv);
		}

		else if (current_char >= 119 && current_char <= 122) {
			if (node.wxyz == null) {
				node.wxyz = new Node();
			}
			return this.getNode(word, position + 1, node.wxyz);
		}
		
		return node;
	}

	/**
	*	Calling method for listing all words stored in a node
	*	@param word the T9 word to search for
	*	@return LinkedList of all strings matching specified T9 word
	*/
	public LinkedList<String> list(String word) {
		LinkedList<String> ll = new LinkedList<String>();
		Node node = getNode(word, 0, this.root);
		return list_rec(word, node, ll); 
	}

	/**
	*	Recursive method for listing all words stored in a node
	*	@param word the T9 word to search for
	*	@param n the current node
	*	@param ll the LinkedList which will contain all the words to be stored
	*	@return LinkedList of all strings matching specified T9 word
	*/
	public LinkedList<String> list_rec(String word, Node n, LinkedList<String> ll) {
		if (n == null) {}
		else {
			if (n.word != null) {
				String [] arr = n.word.toArray(new String [0]);
				for (int i = 0; i < arr.length; i++) {
					String str = arr[i];
					if (str.length() == word.length()) {
						ll.add(str);
					}
				}
			}

			if (n != null) {
				list_rec(word, n.abc, ll);
				list_rec(word, n.def, ll);
				list_rec(word, n.ghi, ll);
				list_rec(word, n.jkl, ll);
				list_rec(word, n.mno, ll);
				list_rec(word, n.pqrs, ll);
				list_rec(word, n.tuv, ll);
				list_rec(word, n.wxyz, ll);
			}
		}

		return ll;
	}

	/**
	*	Method to see if a string exists in a node
	*	@param n Node to search and children to search
	*	@param str String word to search for
	*/
	public void exist(Node n, String str) {
		if (n == null) {
			return;
		}

		if (n.word != null && n.word.contains(str)) {
			System.out.println("\"" + str + "\" exists");
		}

		exist(n.abc, str);
		exist(n.def, str);
		exist(n.ghi, str);
		exist(n.jkl, str);
		exist(n.mno, str);
		exist(n.pqrs, str);
		exist(n.tuv, str);
		exist(n.wxyz, str);
	}

	/**
	*	Method to print all words stored in node
	*	@param n Node to search for and whose children will be searched
	*/
	public void print(Node n) {
		if (n == null) {
			return;
		}

		if (n.word != null) {
			System.out.println(n.word.toString());
		}

		print(n.abc);
		print(n.def);
		print(n.ghi);
		print(n.jkl);
		print(n.mno);
		print(n.pqrs);
		print(n.tuv);
		print(n.wxyz);
	}
}