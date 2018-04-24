import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Autocomplete {
	public Term[] suggestions;
	public Term[] matches;
	
    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms){
    	suggestions = new Term[terms.length];
    	for(int i = 1; i < terms.length; i++){
    		suggestions[i] = terms[i];
    	}
    }
    
    // Returns all terms that start with the given prefix, in descending order of weight.
    public Term[] allMatches(String prefix){
		int prefixLen = prefix.length();
		Term prefixTerm = new Term(prefix, 0);
		int first = (BinarySearchDeluxe.firstIndexOf(suggestions, prefixTerm, Term.byPrefixOrder(prefixLen)));			
			if(first == -1){
				throw new NullPointerException();
			}	
			this.matches = new Term[(BinarySearchDeluxe.lastIndexOf(suggestions, prefixTerm, Term.byPrefixOrder(prefixLen))) - first + 1];
			for(int i = 0; i < matches.length; i++){
				matches[i] = suggestions[first++];
			}
			Arrays.sort(matches, Term.byReverseWeightOrder());
		return matches;
		}
    
    public int numberOfMatches(String prefix){
    	if (prefix == null) {
    		throw new NullPointerException();
    	}
    	
    	int prefixLen = prefix.length();
		Term prefixTerm = new Term(prefix, 0);
		return ((BinarySearchDeluxe.lastIndexOf(suggestions, prefixTerm, Term.byPrefixOrder(prefixLen)))
				- (BinarySearchDeluxe.firstIndexOf(suggestions, prefixTerm, Term.byPrefixOrder(prefixLen))) + 1);
    }
     
    // unit testing (required) PROVIDED FROM THE INSTRUCTION SITE
    public static void main(String[] args) {

    	 // read in the terms from a file
        String filename = "cities.txt";
        In in = new In(filename);
        int n = in.readInt();
        Term[] terms = new Term[n];
        for (int i = 0; i < n; i++) {
            long weight = in.readLong();           // read the next weight
            in.readChar();                         // scan past the tab
            String query = in.readLine();          // read the next query
            terms[i] = new Term(query, weight);    // construct the term
        }

        // read in queries from standard input and print out the top k matching terms
        int k = 6;
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            StdOut.printf("%d matches\n", autocomplete.numberOfMatches(prefix));
            for (int i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }
    }
}
