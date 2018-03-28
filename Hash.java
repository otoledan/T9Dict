import java.util.*;

public class Hash {
	String [] dictionary;
	int [] frequency;
	int size;
	int elements;
	double limit;

	public Hash() {
		this.dictionary = new String [10];
		this.frequency = new int [10];
		this.size = 10;
		this.elements = 0;
		this.limit = 0.6;
	}

	public Hash(int size_of_hash) {
		this.dictionary = new String [size_of_hash];
		this.frequency = new int [size_of_hash];
		this.size = size_of_hash;
		this.elements = 0;
		this.limit = 0.6;
	}

	public void getDictionary() {
		for (int i=0; i < this.size; i++) {
			//if (this.dictionary[i] != null)
				System.out.println(i + ": " + this.dictionary[i]);
		}
	}

	private int hash_function(String str) {
		int hash_index = 0;

		for(int i=0; i < str.length(); i++) {
			char temp = str.charAt(i);
			hash_index = hash_index + (i*i*temp);
		}

		return hash_index % this.size;
	}

	private boolean reached_limit() {
		double curr_limit = (double) (this.elements + 1)/this.size;

		if (curr_limit >= this.limit) {
			return true;
		}

		return false;
	}

	public void exist(String str) {
		for (int i = 0; i < this.dictionary.length; i++) {
			if (this.dictionary[i] != null && this.dictionary[i].equals(str)) {
				System.out.println("\"" + str + "\" is located at " + i);
				return;
			}
		}
		System.out.println("\"" + str + "\" does not exist");
	}

	public void insert(String str, int freq) {
		if (reached_limit()) {
			rehash();
		}

		int hash_index = hash_function(str) % this.size;

		while (this.dictionary[hash_index] != null) {
			hash_index = (hash_index + 1) % this.size;
		}

		this.dictionary[hash_index] = str;
		this.frequency[hash_index] = freq;
		this.elements = this.elements + 1;
	}

	private void rehash() {
		String [] oldDict = this.dictionary;
		int [] oldFreq = this.frequency;
		
		String [] newDict = new String [this.size * 2];
		int [] newFreq = new int [this.size * 2];
		
		this.dictionary = newDict;
		this.frequency = newFreq;
		this.size = this.size * 2;
		this.elements = 0;
		
		for (int i = 0; i < oldDict.length; i++) {
			String str = oldDict[i];
			if (str != null) {
				this.insert(str, oldFreq[i]);
			}
		}
	}

	public int getFreq(String word) {
		int hash_index = hash_function(word) % this.size;

		while (this.dictionary[hash_index] == null || !this.dictionary[hash_index].equals(word)) {
			hash_index = (hash_index + 1) % this.size;
		}

		return this.frequency[hash_index];
	}

	public LinkedList<Integer> findFreq(LinkedList<String> words) {
		String current_word = null;

		LinkedList<Integer> freqs = new LinkedList<Integer>();

		ListIterator<String> iter = words.listIterator(0);

		while (iter.hasNext()) {
			current_word = iter.next();
			int freq = getFreq(current_word);
			freqs.add(freq);
		}

		return freqs;
	}
}