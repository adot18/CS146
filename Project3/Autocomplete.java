import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

// A data type that provides autocomplete functionality for a given set of
// string and weights, using Term and BinarySearchDeluxe.
public class Autocomplete {
    private Term[] terms;

    // Initialize the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        if (terms == null) {
            throw new java.lang.NullPointerException();
        }
        Arrays.sort(terms);
        this.terms = terms;
    }

    // All terms that start with the given prefix, in descending order of
    // weight.
    public Term[] allMatches(String prefix) {
        if (prefix == null) {
            throw new java.lang.NullPointerException();
        }
        Term[] matches;
        Term test = new Term(prefix, 0);
        int left = BinarySearchDeluxe.firstIndexOf(terms, test, Term.byPrefixOrder(prefix.length()));
        int right = BinarySearchDeluxe.lastIndexOf(terms, test, Term.byPrefixOrder(prefix.length()));
        int length = right - left + 1;
        if (left > 0 && right > 0 && left < right) {
            matches = new Term[length];
        } else {
            matches = new Term[0];
        }

        if (matches.length != 0) {
            for (int i = 0; i < length; i++) {
                matches[i] = terms[left + i];
            }
        }

        Arrays.sort(matches, Term.byReverseWeightOrder());
        return matches;
    }

    // The number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        if (prefix == null) {
            throw new java.lang.NullPointerException();
        }
        Term test = new Term(prefix, 0);
        int left = BinarySearchDeluxe.firstIndexOf(terms, test, Term.byPrefixOrder(prefix.length()));
        int right = BinarySearchDeluxe.lastIndexOf(terms, test, Term.byPrefixOrder(prefix.length()));
        int length = right - left + 1;
        return length;
    }

    // Entry point. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong();
            in.readChar();
            String query = in.readLine();
            terms[i] = new Term(query.trim(), weight);
        }
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            for (int i = 0; i < Math.min(k, results.length); i++) {
                StdOut.println(results[i]);
            }
        }
    }
}
