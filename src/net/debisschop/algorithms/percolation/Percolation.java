package net.debisschop.algorithms.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private WeightedQuickUnionUF connections;
  private boolean[][] lattice;
  private int[] max;
  private int rows;
  private int cols;
  private int openSites;
  private int top;

  /**
   * Creates n-by-n grid, with all sites blocked.
   *
   * @param n Size of square grid.
   */
  public Percolation(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException("Size of grid must be larger than 0");
    }
    rows = n;
    cols = n;
    top = 0;
    int size = n * n;
    connections = new WeightedQuickUnionUF(1 + size);
    lattice = new boolean[n][n];
    max = new int[1 + size];
    for (int i = 0; i < size; i++) {
      max[i] = i;
    }
  }

  /**
   * Opens a site (row, column) if it is not open already.
   *
   * @param row row index
   * @param col column index
   */
  public void open(int row, int col) {
    if (isOpen(row, col)) {
      return;
    }

    lattice[row - 1][col - 1] = true;
    openSites++;

    int neighbor;
    int cell = unionIndex(row, col);

    int newMax = cell;

    if (row == 1) {
      neighbor = top;
      newMax = maximum(newMax, max[connections.find(neighbor)]);
      union(cell, neighbor);
    } else if (isOpen(row - 1, col)) {
      neighbor = unionIndex(row - 1, col);
      newMax = maximum(newMax, max[connections.find(neighbor)]);
      union(cell, neighbor);
    }

    if (row != rows && isOpen(row + 1, col)) {
      neighbor = unionIndex(row + 1, col);
      newMax = maximum(newMax, max[connections.find(neighbor)]);
      union(cell, neighbor);
    }

    if (col > 1 && isOpen(row, col - 1)) {
      neighbor = unionIndex(row, col - 1);
      newMax = maximum(newMax, max[connections.find(neighbor)]);
      union(cell, neighbor);
    }
    if (col < cols && isOpen(row, col + 1)) {
      neighbor = unionIndex(row, col + 1);
      newMax = maximum(newMax, max[connections.find(neighbor)]);
      union(cell, neighbor);
    }

    max[connections.find(cell)] = newMax;
  }

  private void union(int p, int q) {
    connections.union(p, q);
  }

  private int maximum(int p, int q) {
    if (p > q) {
      return p;
    }
    return q;
  }

  /**
   * Is site (row, column) open?
   *
   * @param row row index
   * @param col column index
   * @return True is site is open.
   */
  public boolean isOpen(int row, int col) {
    validateIndexes(row, col);
    int rowIndex = row - 1;
    int colIndex = col - 1;
    return lattice[rowIndex][colIndex];
  }

  /**
   * Is site (row, column) full?
   *
   * @param row The row index.
   * @param col The column index.
   * @return True if site is full.
   */
  public boolean isFull(int row, int col) {
    validateIndexes(row, col);
    int site = unionIndex(row, col);
    return connections.connected(top, site);
  }

  /**
   * Returns number of open sites.
   *
   * @return Number of open sites in grid
   */
  public int numberOfOpenSites() {
    return openSites;
  }

  /**
   * Does the system percolate?
   *
   * @return True if system percolates
   */
  public boolean percolates() {
    return max[connections.find(0)] > (rows - 1) * cols;
  }

  /**
   * @param row Index of row.
   * @param col Index of column.
   */
  private void validateIndexes(int row, int col) {
    if (row < 1) {
      throw new IndexOutOfBoundsException("Row must be greater than 0");
    }
    if (col < 1) {
      throw new IndexOutOfBoundsException("Column must be greater than 0");
    }
    int size = lattice.length;
    if (row > size) {
      throw new IndexOutOfBoundsException("Row is outside lattice");
    }
    if (col > size) {
      throw new IndexOutOfBoundsException("Column is outside lattice");
    }
  }

  private int unionIndex(int row, int col) {
    return (row - 1) * cols + col;
  }
}
