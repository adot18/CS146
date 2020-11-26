// ThreeDice.java: Writes the sum of three random integers between 1 and 6, such
// as you might get when rolling three dice.

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ThreeDice {
    public static void main(String[] args) {

        int sum = 0;

        for (int i = 0; i < 3; i++) {
            sum += StdRandom.uniform(5) + 1;
        }

        StdOut.println(sum);
    }
}
