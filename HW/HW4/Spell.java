// Spell.java: Takes a command-line argument specifying the name of the file
// containing common misspellings (a line-oriented file with each
// comma-separated line containing a misspelled word and the correct spelling),
// then reads text from standard input and prints out the misspelled words in
// the text along with the line numbers where they occurred and their correct
// spellings.

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;

public class Spell {
    public static void main(String[] args) {

        SeparateChainingHashST<String, String> st = new SeparateChainingHashST<String, String>();
        In in1 = new In(args[0]);
        while (in1.hasNextLine()) {
            String line = in1.readLine();
            String[] wordsCorrect = line.split(",");
            String word = wordsCorrect[0];
            String correction = wordsCorrect[1];
            st.put(word, correction);
        }

        // read lines from file and correct misspelled words
        // (replaces sequences of whitespace with single space, but preserves newlines)
        In in2 = new In(args[1]);
        int lineCount = 0;
        while (in2.hasNextLine()) {
            String line = in2.readLine();
            lineCount++;
            line = line.replaceAll("[^A-Za-z0-9' ]", "");
            String[] words = line.split("\\s");
            for (int i = 0; i < words.length; i++) {
                if (st.contains(words[i])) StdOut.println(words[i] + ":" + lineCount + " -> " + st.get(words[i]));
            }
        }
    }
}
