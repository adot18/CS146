// FrequencyCounter.java: Reads in a command-line integer and sequence of words
// from standard input and prints out all the words (whose length exceeds the
// threshold) that occur most frequently to standard output. It also prints out
// the number of words whose length exceeds the threshold and the number of
// distinct such words.

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class FrequencyCounter {
    public static void main(String[] args) {
        int distinct = 0, words = 0;
        int minlen = Integer.parseInt(args[0]);
        ArrayST<String, Integer> st = new ArrayST<String, Integer>();

        // compute frequency counts
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            if (key.length() < minlen) continue;
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            } else {
                st.put(key, 1);
                distinct++;
            }
        }

        // find keys with the highest frequency count
        ArrayList<String> maxA = new ArrayList<String>();
        int max = 0;
        for (String word : st.keys()) {
            if (st.get(word) > max) {
                max = st.get(word);
            }
        }
        for (String word : st.keys()) {
            if (st.get(word) == max) {
                if (maxA.contains(word)) {
                    continue;
                }
                maxA.add(word);
            }
        }
        for (int i = 0; i < maxA.size(); i++) {
            StdOut.print(maxA.get(i) + " ");
        }
        StdOut.println(max);
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
    }
}

