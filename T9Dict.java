import java.util.*;
import java.io.*;

public class T9Dict {
	Hash hash;
	Tree tree;

	public T9Dict() {
		hash = new Hash();
		tree = new Tree();
	}

	public void insert(String word, int freq) {
		hash.insert(word, freq);
		tree.insert(word);
	}

	public String num2Letters(String num) {
		StringBuilder returnedWord = new StringBuilder("");
		num = num.toLowerCase();
		for (int i = 0; i < num.length(); i++) {
			int current_char = (int) num.charAt(i);
			current_char = current_char - 48;

			switch (current_char) {
				case 2: returnedWord.append("a");
				break;
				case 3: returnedWord.append("d");
				break;
				case 4: returnedWord.append("g");
				break;
				case 5: returnedWord.append("j");
				break;
				case 6: returnedWord.append("m");
				break;
				case 7: returnedWord.append("p");
				break;
				case 8: returnedWord.append("t");
				break;
				case 9: returnedWord.append("w");
				break;
				default: return "Word Not Found";
			}
		}

		return returnedWord.toString();
	}
	public void readFile(String fileName) {
        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            int i = 1;

            while((line = bufferedReader.readLine()) != null) {
            	this.insert(line, i);
            	i = i + 1;
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }

    public int minIndex(LinkedList<Integer> list) {
    	Integer [] arr = list.toArray(new Integer [0]);
    	int min = 20000;
    	int minIndex = -1;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < min) {
				minIndex = i;
				min = arr[i];
			}
		}
		return minIndex;
    } 

    public LinkedList<String> topN(String test, int n) {
    	LinkedList<String> topN = new LinkedList<String>();

    	String testOut = this.num2Letters(test);

		LinkedList<String> ll = this.tree.list(testOut);
		LinkedList<Integer> fll = this.hash.findFreq(ll);

		int i = 0;
		while (i < n && this.minIndex(fll) != -1) {
			int index = this.minIndex(fll);
			topN.add(ll.remove(index));
			fll.remove(index);
			i++;
		}

		return topN;
    }

    public String textToT9(String word) {
		word = word.toLowerCase();

		StringBuilder str = new StringBuilder("");

		for(int position = 0; position < word.length(); position++) {
			char current_char = word.charAt(position);

			if (current_char >= 97 && current_char <= 99) {
				str.append("2");
			}

			else if (current_char >= 100 && current_char <= 102) {
				str.append("3");
			}

			else if (current_char >= 103 && current_char <= 105) {
				str.append("4");
			}

			else if (current_char >= 106 && current_char <= 108) {
				str.append("5");
			}

			else if (current_char >= 109 && current_char <= 111) {
				str.append("6");
			}

			else if (current_char >= 112 && current_char <= 115) {
				str.append("7");
			}

			else if (current_char >= 116 && current_char <= 118) {
				str.append("8");
			}

			else if (current_char >= 119 && current_char <= 122) {
				str.append("9");
			}

			else if (current_char == 32) {
				str.append("0");
			}

			else {
				return "Invalid Input Entered";
			}
		}

		return str.toString();
	}

	public static void main(String[] args) {
		T9Dict t = new T9Dict();

		t.readFile("google-10000-english-usa.txt");

		String test = args[0];
		
		System.out.println(test);

		LinkedList<String> topN = t.topN(test, Integer.parseInt(args[1]));

		System.out.println(topN.toString());

	}
}