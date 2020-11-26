import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

// An immutable data type that represents an autocomplete term: a query string
// and an associated real-valued weight.
public class Term implements Comparable<Term> {
    String query;
    long weight;

    // Construct a term given the associated query string, having weight 0.
    public Term(String query) {
        this.query = query;
        this.weight = 0;

        if (query == null) {
            throw new java.lang.NullPointerException("String cannot be null");
        }


    }

    // Construct a term given the associated query string and weight.
    public Term(String query, long weight) {
        this.query = query;
        this.weight = weight;
        if (query == null) {
            throw new java.lang.NullPointerException("String cannot be null");
        }
        if (weight < 0) {
            throw new java.lang.IllegalArgumentException("weight < 0");
        }
    }

    // A reverse-weight comparator.
    public static Comparator<Term> byReverseWeightOrder() {
        return new ReverseWeightOrder();
    }


    // Helper reverse-weight comparator.
    private static class ReverseWeightOrder implements Comparator<Term> {
        public int compare(Term v, Term w) {
            if (v.weight > w.weight) {//if descending
                return -1;
            } else if (v.weight == w.weight) {//if equal
                return 0;
            } else {
                return 1;//if ascending
            }
        }
    }

    // A prefix-order comparator.
    public static Comparator<Term> byPrefixOrder(int r) {
        return new PrefixOrder(r);
    }

    // Helper prefix-order comparator.
    private static class PrefixOrder implements Comparator<Term> {
        private int r;

        PrefixOrder(int r) {
            this.r = r;
        }

        public int compare(Term v, Term w) {
            String s1 = v.query;
            String s2 = w.query;
            int minlength;

            if (s1.length() < s2.length()) { //find the smallest of the two strings
                minlength = s1.length();
            } else {
                minlength = s2.length();
            }

            if (minlength >= r) { //if R is less than the minimum length use the substring (0, R) for each string and compareTo
                return s1.substring(0, r).compareTo(s2.substring(0, r));
            } else {
                if (s1.substring(0, minlength).compareTo(s2.substring(0, minlength)) == 0) { //if the substrings are equal return the smaller one
                    if (s1.length() == minlength) {
                        return -1;
                    } else { //return the smaller, s2
                        return 1;
                    }
                } else {
                    return s1.substring(0, minlength).compareTo(s2.substring(0, minlength));
                }
            }
        }
    }

    // Compare this term to that in lexicographic order by query and
    // return a negative, zero, or positive integer based on whether this
    // term is smaller, equal to, or larger than that term.
    public int compareTo(Term that) {
        return (this.query.compareTo(that.query));
    }

    // A string representation of this term.
    public String toString() {
        return weight + "\t" + query;
    }


    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        int k = Integer.parseInt(args[1]);
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();
            in.readChar();
            String query = in.readLine();
            terms[i] = new Term(query.trim(), weight);
        }
        StdOut.printf("Top %d by lexicographic order:\n", k);
        Arrays.sort(terms);
        for (int i = 0; i < k; i++) {
            StdOut.println(terms[i]);
        }
        StdOut.printf("Top %d by reverse-weight order:\n", k);
        Arrays.sort(terms, Term.byReverseWeightOrder());
        for (int i = 0; i < k; i++) {
            StdOut.println(terms[i]);
        }
    }
}
