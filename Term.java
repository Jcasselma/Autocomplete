import java.util.Comparator;

public class Term implements Comparable<Term> {
		
		public String query;
	    public long weight;
	    
	    // Initializes a term with the given query string and weight.
	    public Term(String query, long weight){
	    	this.query = query;
	    	this.weight = weight;
	    }

	    // Compares the two terms in descending order by weight.
	    public static Comparator<Term> byReverseWeightOrder(){
	    	return new compareByReverseWeight();
	    }
	    
	      // Compares the two terms in lexicographic order but using only the first r characters of each query.
	    public static Comparator<Term> byPrefixOrder(int r){
	    	return new compareByPrefixOrder(r);
	    }
	    
	    public static class compareByReverseWeight implements Comparator<Term> {
			@Override
			public int compare(Term t1, Term t2) {
				if(t1.weight > t2.weight){
					return -1;
				}
				if(t1.weight < t2.weight){
					return 1;
				}
				else{
				return 0;
				}
	        }
	    }
	    public static class compareByPrefixOrder implements Comparator<Term> {
	    	private int prefixLen;
	    	
	    	//Initialize compareByPrefixOrder with local r
			public compareByPrefixOrder(int r) {
				prefixLen = r;
			}
			
			@Override
			public int compare(Term t1, Term t2) {
				String first = t1.query;
				String second = t2.query;
				String firstPrefix = first.substring(0, prefixLen);
				String secondPrefix = second.substring(0, prefixLen);
				
				//https://docs.oracle.com/javase/tutorial/java/data/comparestrings.html
				if(firstPrefix.compareTo(secondPrefix) < 0){
					return -1;
				}
				if(firstPrefix.compareTo(secondPrefix) > 0){
					return 1;
				}
				else
					return 0;
				}
	        }
	    	

	    // Compares the two terms in lexicographic order by query.
	    public int compareTo(Term that){
			return (this.query).compareTo(that.query);
	    }

	    // Returns a string representation of this term in the following format:
	    // the weight, followed by a tab, followed by the query.
	    public String toString(){
			return (this.weight + "\t" + this.query);
	    	
	    }

	    // unit testing (required)
	    public static void main(String[] args)   {
	    	
	    	
	    }

	    
}
