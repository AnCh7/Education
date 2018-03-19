package Percolation;

import org.junit.Test;

import static org.junit.Assert.*;

public class PercolationTests {

    @Test
    public void noOpenSitesAfterCreation() {
        int n = 4;
        Percolation p = new Percolation(n);
        assertEquals(0, p.numberOfOpenSites());
    }

    @Test
    public void siteNotOpenedAfterCreation() {
        int n = 4;
        Percolation p = new Percolation(n);
        assertFalse(p.isOpen(2, 2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkRowBoundariesGuardWithZero() {
        int n = 4;
        Percolation p = new Percolation(n);
        p.open(0, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkRowBoundariesGuardWithNegative() {
        int n = 4;
        Percolation p = new Percolation(n);
        p.open(-1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkColBoundariesGuardWithZero() {
        int n = 4;
        Percolation p = new Percolation(n);
        p.open(2, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkColBoundariesGuardWithNegative() {
        int n = 4;
        Percolation p = new Percolation(n);
        p.open(2, -1);
    }

    @Test
    public void checkIfSiteWasOpened() {
        int n = 4;
        Percolation p = new Percolation(n);
        p.open(2, 2);
        assertTrue(p.isOpen(2, 2));
    }

    @Test
    public void checkNumberOfOpenSitesIsOne() {
        int n = 4;
        Percolation p = new Percolation(n);
        p.open(2, 2);
        assertEquals(1, p.numberOfOpenSites());
    }

    @Test
    public void checkNumberOfOpenSitesIsTwo() {
        int n = 4;
        Percolation p = new Percolation(n);
        p.open(1, 1);
        p.open(2, 2);
        assertEquals(2, p.numberOfOpenSites());
    }

    @Test
    public void checkIfFirstRowHaveFullSites() {
        int n = 4;
        Percolation p = new Percolation(n);
        p.open(1, 1);
        assertTrue(p.isFull(1, 1));
    }

    @Test
    public void checkIfSiteIfFull() {
        int n = 4;
        Percolation p = new Percolation(n);
        p.open(1, 2);
        p.open(2, 2);
        p.open(3, 2);
        p.open(3, 3);
        assertTrue(p.isFull(3, 3));
    }

    @Test
    public void checkIfSiteIfNotFull() {
        int n = 4;
        Percolation p = new Percolation(n);
        p.open(1, 2);
        p.open(2, 2);
        p.open(3, 2);
        p.open(3, 3);
        assertFalse(p.isFull(2, 4));
    }

    @Test
    public void checkIfSystemPercolates() {
        int n = 4;
        Percolation p = new Percolation(n);
        p.open(1, 2); // 1
        p.open(2, 2); // 5
        p.open(3, 2); // 9
        p.open(3, 3); // 10
        p.open(4, 3); // 13

        assertTrue(p.percolates());
    }
}
