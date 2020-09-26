import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;


// Models an N-by-N percolation system.
public class Percolation {
    private int gridSize;
    private boolean[][] percolationGrid;
    private WeightedQuickUnionUF weightedQUF;
    private int virtualTopSite;
    private int virtualBottomSite;
    public int openSite;
    private WeightedQuickUnionUF weightedQUF2;

    private final static int Virtual_Sites = 2;


    // Create an N-by-N grid, with all sites blocked.
    //...
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("N cannot be less than or equal to 0");
        }
        this.gridSize = N;
        this.percolationGrid = new boolean[N][N];
        this.virtualTopSite = 0;
        this.virtualBottomSite = N * N + 1;
        this.weightedQUF = new WeightedQuickUnionUF(N * N + Virtual_Sites);
        this.weightedQUF2 = new WeightedQuickUnionUF(N * N + 1);

        for (int i = 0; i < N; i++) {
            for (int k = 0; k < N; k++) {
                percolationGrid[i][k] = false;
            }

        }

    }

    // Open site (row, col) if it is not open already.
    public void open(int row, int col) {
        percolationGrid[row][col] = true;

        int currentSite = encode(row, col);
        openSite++;


        connectToAdjacentSite(row, col - 1, currentSite);  //Connects current site plus the left one

        connectToAdjacentSite(row, col + 1, currentSite);  //Connects current site and the site to the right

        connectToAdjacentSite(row - 1, col, currentSite); //Connects the current site and the site above it

        connectToAdjacentSite(row + 1, col, currentSite); //Connects the current site and the site above it


        //Connect to virtual top and bot
        if (row == 0) {
            weightedQUF.union(currentSite, virtualTopSite);
            weightedQUF2.union(currentSite, virtualTopSite);
        }

        if (row == gridSize - 1)
            weightedQUF.union(virtualBottomSite, currentSite);

    }

    // Is site (row, col) open?
    public boolean isOpen(int row, int col) {
        //...
        checkBoundary(row, col);
        return percolationGrid[row][col]; //Returns the value in the grid
    }

    // Is site (row, col) full?
    public boolean isFull(int row, int col) {
        //...
        int currentSite = encode(row, col);

        return weightedQUF2.connected(currentSite, virtualTopSite);


    }

    // Number of open sites.
    public int numberOfOpenSites() {
        //...
        return openSite;
    }

    // Does the system percolate?
    public boolean percolates() {
        return weightedQUF.connected(virtualTopSite, virtualBottomSite);
    }

    // An integer ID (1...N) for site (row, col).
    private int encode(int row, int col) {
        return row * gridSize + col + 1;
    }


    //Check boundary of percolation system
    private void checkBoundary(int i, int j) {
        if (i >= gridSize || j >= gridSize || i < 0 || j < 0) {
            throw new java.lang.IndexOutOfBoundsException(
                    "Index is out of bounds");
        }
    }

    private void connectToAdjacentSite(int row, int col, int currentSite) {
        try {
            if (isOpen(row, col)) {
                weightedQUF.union(currentSite, encode(row, col));
                weightedQUF2.union(currentSite, encode(row, col));
            }
        } catch (IndexOutOfBoundsException e) {

        }
    }


    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Percolation perc = new Percolation(N);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        StdOut.println(perc.numberOfOpenSites() + " open sites");
        if (perc.percolates()) {
            StdOut.println("percolates");
        } else {
            StdOut.println("does not percolate");
        }

        // Check if site (i, j) optionally specified on the command line
        // is full.
        if (args.length == 3) {
            int i = Integer.parseInt(args[1]);
            int j = Integer.parseInt(args[2]);
            StdOut.println(perc.isFull(i, j));
        }
    }
}
