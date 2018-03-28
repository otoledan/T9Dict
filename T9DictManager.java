import java.io.*;
import java.util.*;

public class T9DictManager {
	T9Dict t9Dict;
	int topN;
	int current_personal_word_freq;
	LinkedList<String> personal_words;
	String dictionary;

	T9DictManager() {
		this.t9Dict = new T9Dict();
		topN = 3;
		current_personal_word_freq = 0;
		personal_words = new LinkedList<String>();
		dictionary = "";
	}

	public void instructions() {
		System.out.println("\n\nWelcome to T9Dict!\n");

		System.out.println("Please select one of the following options:");
		System.out.println("-------------------------------------------");
		System.out.println("  1: Write out message in T9");
		System.out.println("  2: Convert Message to T9");
		System.out.println("  3: Add Freqency Dictionary File");
		System.out.println("  4: Add Personal Words to Dictionary");
		System.out.println("  5: Set top N number of words to be shown");
		System.out.println("  6: Save current session");
		System.out.println("  7: Load saved session");
		System.out.println("  8: Quit\n");
	}

	public void t9toMessageInstructions() {
		System.out.println("T9 Keyboard for your use:\n");
		System.out.println("----------------------------");
		System.out.println("| 1      | 2 abc  | 3 def  |");
		System.out.println("----------------------------");
		System.out.println("| 4 ghi  | 5 jkl  | 6 mno  |");
		System.out.println("----------------------------");
		System.out.println("| 7 pqrs | 8 tuv  | 9 wxyz |");
		System.out.println("----------------------------");
		System.out.println("| *      | 0 |__| | #      |");
		System.out.println("----------------------------\n");

		System.out.println("Please type out your message below using 0 as the space bar");
		System.out.println("  i.e. \"8430784250276960369058677068370843052990364\" will return");
		System.out.println("       \"The quick brown fox jumps over the lazy dog\"\n\n");
	}

	public String [][] t9toMessage(String t9) {
		String [] arr = t9.split("0");
		String [][] returnedWords = new String [topN][arr.length];
		LinkedList<String> linkedList_of_words;

		for (int i = 0; i < arr.length; i++) {
			linkedList_of_words = this.t9Dict.topN(arr[i], topN);

			for (int j = 0; j < topN; j++) {
				if (j >= linkedList_of_words.size()) {
					String space = "";
					for (int k = 0; k < arr[i].length(); k++) {
						space = space + " ";
					}
					returnedWords[j][i] = space;
				}
				else {
					returnedWords[j][i] = linkedList_of_words.get(j);
				}
			}
		}

		return returnedWords;
	}

	public String print_prediction(String [][] pred) {
		StringBuilder str = new StringBuilder("");
		
		for (int i = 0; i < pred.length; i++) {
			for (int j = 0; j < pred[0].length; j++) {
				if (i == 0) {
					str.append(pred[i][j] + " ");
				}
				else {
					str.append(pred[i][j] + "|");
				}
			}
			str.append("\n");
		}

		return str.toString();
	}

	public void option1() {
		t9toMessageInstructions();

		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.print("Enter message: ");
		
		String t9 = reader.next(); // Scans the next token of the input as an int.

		String [][] array_of_message = t9toMessage(t9);
		String pred = print_prediction(array_of_message);

		System.out.println("\n" + pred);
	}

	public void option2() {
		System.out.println("Enter a message to be converted to T9. Make sure to only use letters and spaces.");

		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.print("Enter message: ");
		
		String t9 = reader.nextLine(); // Scans the next token of the input as an int.

		System.out.println("\n" + this.t9Dict.textToT9(t9));
	}

	public void option3() {
		System.out.println("Please enter location of Frequency Dictionary file:");

		Scanner reader = new Scanner(System.in);  // Reading from System.in
		
		String dict_location = reader.next(); // Scans the next token of the input as an int.

		dictionary = dict_location;

		this.t9Dict.readFile(dict_location);
	}

	public void option4() {
		System.out.print("Please enter new word to add to the Frequency Dictionary: ");

		Scanner reader = new Scanner(System.in);

		String word = reader.next().toLowerCase();

		this.t9Dict.insert(word, current_personal_word_freq);

		current_personal_word_freq = current_personal_word_freq - 1;

		this.personal_words.add(word);
	}

	public void option5() {
		System.out.println("When converting T9 to text the top \"" + this.topN + "\" words will be shown: ");

		System.out.print("Change to: ");

		Scanner scan = new Scanner(System.in);

		int num = 0;

		try {
			num = scan.nextInt();
		} catch (Exception e) {
			System.out.println("Please enter a valid numeric input greater than 0");
			return;
		}

		if (num < 1) {
			System.out.println("Please enter a valid numeric input greater than 0");
			return;
		}

		this.topN = num;
	}

	public void option6() {
		try {
    		StringBuilder str = new StringBuilder("");
    		
    		str.append(this.dictionary + "\n");
    		str.append(this.topN + "\n");
    		str.append((this.current_personal_word_freq*-1) + "\n");

    		ListIterator<String> iter = this.personal_words.listIterator();

    		while (iter.hasNext()) {
    			str.append(iter.next() + "\n");
    		}

    		BufferedWriter writer = new BufferedWriter(new FileWriter("saved.t9"));
    		writer.write(str.toString());
     
    		writer.close();

    		System.out.println("Saved current session");

		} catch (Exception e) {
			System.out.println("An error occured when saving");
		}

	}

	public void option7() {
		try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader("saved.t9");

            System.out.println(fileReader.toString());

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int i = 0;
            int j = 0;

            String line = bufferedReader.readLine();
            System.out.println(line);
            this.dictionary = line;
            this.t9Dict.readFile(line);

            this.topN = bufferedReader.read() - 48;

            //bufferedReader.skip(1);

            this.current_personal_word_freq = (bufferedReader.read() - 10) * -1;
            System.out.println(this.current_personal_word_freq);
            line = bufferedReader.readLine();

            while((line = bufferedReader.readLine()) != null) {
				this.t9Dict.insert(line, j);
				this.personal_words.add(line);
				j = j - 1;
            }

            // Always close files.
            bufferedReader.close();  

            System.out.println("Loaded saved session");
		
		}catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                "saved.t9" + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + "saved.t9" + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
	}

	public static void main(String[] args) {
		T9DictManager t9 = new T9DictManager();

		Scanner scan = new Scanner(System.in);
		int option = -1;

		do {
			t9.instructions();

			try {
				option = Integer.parseInt(scan.next());
			} catch (Exception e) {
				System.out.println("Please Enter a valid numeric Input");
			}

			switch (option) {
				case 1:	t9.option1();
						break;
				case 2: t9.option2();
						break;
				case 3: t9.option3();
						break;
				case 4: t9.option4();
						break;
				case 5: t9.option5();
						break;
				case 6: t9.option6();
						break;
				case 7: t9.option7();
						break;
				case 8: System.out.println("Thank you and Goodbye.");
						break;
				default: option = -1;

			}

		} while (option != 8);
	}                    
}