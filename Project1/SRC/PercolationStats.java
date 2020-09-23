import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


// Estimates percolation threshold for an N-by-N percolation system.
public class PercolationStats {

    public double[] trialResult;
    int gridSize;
    int t;

    // Perform T independent experiments (Monte Carlo simulations) on an
    // N-by-N grid.
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Cannot have N or T <= 0");
        }
        this.gridSize = N;
        this.t = T;
        this.trialResult = new double[t];

        Percolation percolation;

        for (int f = 0; f < t; f++) {
            percolation = new Percolation(N);
            int openSites = 0;
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                }
            }
            openSites = percolation.numberOfOpenSites();
            trialResult[f] = (double) openSites / (double) (gridSize * gridSize);

        }
    }

    // Sample mean of percolation threshold.
    public double mean() {
        return StdStats.mean(trialResult);
    }

    // Sample standard deviation of percolation threshold.
    public double stddev() {
        return StdStats.stddev(trialResult);
    }

    // Low endpoint of the 95% confidence interval.
    public double confidenceLow() {
        return mean() - (1.96 * stddev() / Math.sqrt(trialResult.length));
    }

    // High endpoint of the 95% confidence interval.
    public double confidenceHigh() {
        return mean() + (1.96 * stddev() / Math.sqrt(trialResult.length));
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(N, T);
        StdOut.printf("mean           = %f\n", stats.mean());
        StdOut.printf("stddev         = %f\n", stats.stddev());
        StdOut.printf("confidenceLow  = %f\n", stats.confidenceLow());
        StdOut.printf("confidenceHigh = %f\n", stats.confidenceHigh());
    }
}
