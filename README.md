# T9Dict

This project allows for the encoding/decoding of messages using the T9 dictionary.

## Running

Compile all files using:
`javac *java`

Run the T9DictManager
`java T9DictManager`

Follow the onscreen prompts

## Use

Using the T9DictManager the following functions can be done:
* Encode string message to T9
* Decode string message from T9 based off of frequency of words
* Add any frequency dictionary file
* Add personal words to dictionary
* Set the top N number of words to be shown
* Save and Load a session for ease of use

## T9

T9 is *text* *on* *nine* *keys*. In short it allows for predictive typing by using one press per key on a nine digit keypad as shown below. 

![T9 Keypad](https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Telephone-keypad2.svg/440px-Telephone-keypad2.svg.png)

## Implementation

T9Dict is implemented using a tree and a hash. 

### Tree
The tree is implemented for predicting all words that could possibly occur along a keypad combination. For example if a user were to enter `843` the tree would follow the **8** node representing **tuv** followed by the **4** node representing **ghi** followed by **3** node representing **def**.

The possible words are stored along the tree. In this example the words returned would be ``vid, tie, the``.

### Hash
The returned words are then run through the hash table which matches to a hash table with the frequency of the word. The words are then prioritized by frequency and returned. In the above example the returned words sorted by frequency would become ``the, tie, vid``.
