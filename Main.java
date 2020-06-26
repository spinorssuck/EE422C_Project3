/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * Rithvik Reddy Golamari
 * rrg2477
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Git URL:
 * Summer 2019
 */


package assignment3;
import java.util.*;
import java.io.*;

class Node{
	public String word;
	//Reference to previous node
	public Node parent;
	
	public Node(String word,Node parent){
			this.word = word;
			this.parent = parent;
	}		
	
	public Node(String word){
		this.parent  = null;
		this.word = word;	
	}

	
}	

public class Main {
	
	public static Set<String> dictionary;
	public static HashSet<String> visited;
	
	public static void main(String[] args) throws Exception {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file, for student testing and grading only
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default input from Stdin
			ps = System.out;			// default output to Stdout
		}
		initialize();
		
		// TODO methods to read in words, output ladder
	}
	
	public static void initialize() {
		visited = new HashSet<String>();
		dictionary = makeDictionary();
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		// TO DO
		return null;
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		// Returned list should be ordered start to end.  Include start and end.
		// If ladder is empty, return list with just start and end.
		// TODO some code
		
		return null; // replace this line later with real return
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		
		Queue<Node> queue = new LinkedList<Node>();
		start = start.toUpperCase();end = end.toUpperCase();
		visited.add(start);
		
		Node first = new Node(start);
		queue.add(first);
		while(!(queue.isEmpty()){
			Node node = queue.remove();
			String word = node.word;
			Queue<String> neighbors = getNeighbors(word);
			//Adding neighbors to the queue
			while(!neighbors.isEmpty()){
				Node neighbor = new Node(neighbors.remove(),node);
				//Creating arraylist if word is equal to end word
				if(neighbor.word.equals(end)){
					ArrayList<String> WordLadder = new ArrayList<String>();
					Node check = neighbor;
					while(check!=null){
						WordLadder.add(check.word);
						check = check.parent;
					}	
					
					return Collections.reverse(WordLadder);
				}	
				queue.add(neighbor);
			}
		}
		return null;
	}
    
	
	public static void printLadder(ArrayList<String> ladder) {
		
	}
	// TODO
	// Other private static methods here

	//Returns words adjacent to word if they are in the dictionary
	//Gives priority to words obtained from changing letter to corresponding letter of end word
	//neighbors differ by from the word by only one letter
	private static Queue<String> getNeighbors(String word) {
		//queue to be returned with all of the neighbors
		Queue<String> neighborsQueue = new LinkedList<String>();
		
		//counter to see if the two words differ by only one letter
		int differenceCount = 0;
		
		//change the word to character array
		char[] wordCharacters = word.toCharArray();
				
		//holds the neighboring word
		String neighbor; 
		
		//get an iterator to iterate through the words of the dictionary
		Iterator dictionaryIterator = dictionary.iterator();
		
		while(dictionaryIterator.hasNext()) {
			neighbor = (String) dictionaryIterator.next();
			char[] neighborCharacters = neighbor.toCharArray();
			
			for(int i = 0; i < word.length(); i++) {
				if(wordCharacters[i] != neighborCharacters[i]) {
					differenceCount++;
				}
			}
			
			if(differenceCount == 1) {
				neighborsQueue.add(neighbor);
			}
			
		}
		
		return neighborsQueue;
	}	

	/* Do not modify makeDictionary */
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
}
