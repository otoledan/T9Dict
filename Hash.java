import java.util.*;

/**
* The Hash class is the implementation of a Hash Table for dictionary words
* and frequency of said words.
*
* @author otoledan
* @date 3/28/2018
*/
public class Hash {

	//Initial variables
	String [] dictionary;
	int [] frequency;
	int size;
	int elements;
	double limit;

	/**
	*	Hash Constructor sets the initial valuues of the hash objects
	*/
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

	/**
	*	Prints out all items stored in Hash Array
	*/
	public void getDictionary() {
		for (int i=0; i < this.size; i++) {
			//if (this.dictionary[i] != null)
				System.out.println(i + ": " + this.dictionary[i]);
		}
	}

	/**
	*	Hash function returns a unique integer based on a string
	*	@param str String to hash
	*	@return int hashed integer which will be used to store the string
	*/
	private int hash_function(String str) {
		int hash_index = 0;

		for(int i=0; i < str.length(); i++) {
			char temp = str.charAt(i);
			hash_index = hash_index + (i*i*temp);
		}

		return hash_index % this.size;
	}

	/**
	*	Tests if the capacity of the hash table has been 
	*	filled in order to avoid too many collisions
	*	@return boolean true if the capacity has been reached
	*/
	private boolean reached_limit() {
		double curr_limit = (double) (this.elements + 1)/this.size;

		if (curr_limit >= this.limit) {
			return true;
		}

		return false;
	}

	/**
	*	Tests if string exists in Hash and prints to terminal
	* 	@param str String to test
	*/
	public void exist(String str) {
		for (int i = 0; i < this.dictionary.length; i++) {
			if (this.dictionary[i] != null && this.dictionary[i].equals(str)) {
				System.out.println("\"" + str + "\" is located at " + i);
				return;
			}
		}
		System.out.println("\"" + str + "\" does not exist");
	}

	/**
	*	Inserts string and frequency into Hash table
	*	@param str String to insert
	*	@freq freq of specified word
	*/
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

	/**
	*	Enlarges the hash tables and then rehashes all words 
	*	and frequencies into the new hash tables
	*/
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

	/**
	*	Returns the Frequency of a word
	*	@param word The string whose frequency is to be found
	*	@return int the frequency of word
	*/
	public int getFreq(String word) {
		int hash_index = hash_function(word) % this.size;

		while (this.dictionary[hash_index] == null || !this.dictionary[hash_index].equals(word)) {
			hash_index = (hash_index + 1) % this.size;
		}

		return this.frequency[hash_index];
	}

	/**
	*	Returns LinkedList of Frequencies based on a LinkedList of words
	*	@param words LinkedList of words to find their frequencies
	*	@return LinkedList of integers with the frequencies of the words entered
	*/
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