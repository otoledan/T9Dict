import java.util.*;

public class Tree extends Node {

	Node root;

	public Tree() {
		root = new Node();
	}

	public void insert(String word) {
		insert_rec(word, 0, this.root);
	}

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

	public LinkedList<String> list(String word) {
		LinkedList<String> ll = new LinkedList<String>();
		Node node = getNode(word, 0, this.root);
		return list_rec(word, node, ll); 
	}

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

	public static void main(String[] args) {
		Tree t = new Tree();

		t.insert("ABCB");
		t.insert("hel");
		t.insert("hello");
		t.insert("hellos");
		t.insert("zebra");

		t.print(t.root);

		LinkedList<String> ll = t.list("hel");
		System.out.println(ll.toString());
	}
}