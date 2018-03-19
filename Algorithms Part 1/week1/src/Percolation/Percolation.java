package Percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int size;
    private final boolean[][] sites;
    private int numberOfOpenSites;
    private final int virtualTop;
    private final int virtualBottom;
    private final WeightedQuickUnionUF qf;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        this.size = n;
        virtualTop = 0;
        virtualBottom = n * n + 1;
        this.sites = new boolean[n][n]; // with false as default boolean value, all sites are blocked
        numberOfOpenSites = 0;
        this.qf = new WeightedQuickUnionUF(n * n + 2); // adding 2 additional row for virtual top and bottom
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        checkRowAndCol(row, col);

        // Open site
        if (!sites[row - 1][col - 1]) {
            sites[row - 1][col - 1] = true;
            numberOfOpenSites = numberOfOpenSites + 1;
        }

        if (row == 1) { // union first row with virtual top
            qf.union(getIndex(row, col), virtualTop);
        }
        if (row == size) { // union last row with virtual bottom
            qf.union(getIndex(row, col), virtualBottom);
        }

        // Connect with opened neighbors
        int current = getIndex(row, col);

        if (col > 1 && isOpen(row, col - 1)) { // left
            int left = getIndex(row, col - 1);
            qf.union(current, left);
        }

        if (col < size && isOpen(row, col + 1)) { // right
            int right = getIndex(row, col + 1);
            qf.union(current, right);
        }

        if (row > 1 && isOpen(row - 1, col)) { // up
            int up = getIndex(row - 1, col);
            qf.union(current, up);
        }

        if (row < size && isOpen(row + 1, col)) { // down
            int down = getIndex(row + 1, col);
            qf.union(current, down);
        }

    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkRowAndCol(row, col);

        return sites[row - 1][col - 1];
    }

    // is site (row, col) full?
    // A full site is an open site that can be connected to
    // an open site in the top row via a chain of neighboring (left, right, up, down) open sites.
    public boolean isFull(int row, int col) {
        if (row == 0 || row > size || row < 0 || col == 0 || col > size || col < 0) {
            throw new IllegalArgumentException();
        }

        return qf.connected(virtualTop, getIndex(row, col));
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    private void checkRowAndCol(int row, int col) {
        if (row == 0 || row > size || row < 0 || row >= Integer.MAX_VALUE || col == 0 || col > size || col < 0 || col >= Integer.MAX_VALUE) {
            throw new IllegalArgumentException();
        }
    }

    private int getIndex(int row, int col) {
        return size * (row - 1) + col;
    }

    // does the system percolate?
    public boolean percolates() {
        return qf.connected(virtualTop, virtualBottom);
    }

    // test client (optional)
    public static void main(String[] args) {
        // empty
    }
}