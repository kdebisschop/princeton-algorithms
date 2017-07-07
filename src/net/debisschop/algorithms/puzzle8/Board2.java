package net.debisschop.algorithms.puzzle8;

import java.util.Arrays;
import java.util.Stack;

public class Board2 {

  final private int[] blocks;
  private int gridSize;
  private int hammingScore = 0;
  private int manhattanScore = 0;
  private int zeroI;
  private int zeroJ;

  /**
   * Construct a board from an n-by-n array of blocks
   *
   * @param blocks where blocks[i][j] = block in row i, column j
   */
  public Board2(int[][] blocks) {
    gridSize = blocks.length;
    this.blocks = copy(blocks);
    calculateScores();
  }

  private Board2(int[] blocks, int gridSize) {
    this.gridSize = gridSize;
    this.blocks = blocks;
  }

  /**
   * Gets the size of this board.
   *
   * @return board gridSize n
   */
  public int dimension() {
    return gridSize;
  }

  /**
   * Gets Hamming distance from solution.
   * @return number of blocks out of place
   */
  public int hamming() {
    return hammingScore;
  }

  /**
   * Gets Manhattan distance from solution
   * @return sum of Manhattan distances between blocks and goal
   */
  public int manhattan() {
    return manhattanScore;
  }

  /**
   * Gets distance from solution
   */
  private void calculateScores() {
    int block;
    int max = gridSize * gridSize - 1;
    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        block = blocks[arrayIndex(i, j)];
        if (block == 0) {
          zeroI = i;
          zeroJ = j;
        } else if (block < 0 || block > max) {
          throw new IllegalArgumentException("Tile value is out of range");
        } else if (block != index(i, j)) {
          hammingScore++;
          manhattanScore += Math.abs(row(block) - i) + Math.abs(col(block) - j);
        }
      }
    }
  }

  /**
   * is this board the goal board?
   * @return
   */
  public boolean isGoal() {
    for (int i = 0; i < dimension(); i++)
      for (int j = 0; j < dimension(); j++)
        if (blocks[arrayIndex(i, j)] > 0 && blocks[arrayIndex(i, j)] != index(i, j)) return false;
    return true;
  }

  /**
   * a board that is obtained by exchanging any pair of blocks
   * @return
   */
  public Board2 twin() {
    if (blocks[0] == 0 || blocks[1] == 0) return new Board2(exch(1, 0, 1, 1), gridSize);
    else return new Board2(exch(0, 0, 0, 1), gridSize);
  }

  /**
   * does this board equal y?
   * @param y
   * @return
   */
  public boolean equals(Object y) {
    if (y == this) return true;
    if (y == null) return false;
    if (y.getClass() != this.getClass()) return false;
    Board2 that = (Board2) y;
    return Arrays.equals(this.blocks, that.blocks);
  }

  /**
   * all neighboring boards
   * @return
   */
  public Iterable<Board2> neighbors() {
    Stack<Board2> neighbors = new Stack<>();
    if (zeroI > 0)
      neighbors.push(new Board2(exch(zeroI, zeroJ, zeroI - 1, zeroJ), gridSize));
    if (zeroJ > 0)
      neighbors.push(new Board2(exch(zeroI, zeroJ, zeroI, zeroJ - 1), gridSize));
    if (zeroI < dimension() - 1)
      neighbors.push(new Board2(exch(zeroI, zeroJ, zeroI + 1, zeroJ), gridSize));
    if (zeroJ < dimension() - 1)
      neighbors.push(new Board2(exch(zeroI, zeroJ, zeroI, zeroJ + 1), gridSize));
    return neighbors;
  }

  /**
   * string representation of this board (in the output format specified below)
   * @return
   */
  public String toString() {
    StringBuilder string = new StringBuilder();
    string.append(dimension());
    for (int i = 0; i < dimension(); i++) {
      string.append('\n');
      for (int j = 0; j < dimension(); j++)
        string.append(' ').append(blocks[arrayIndex(i, j)]);
    }
    return string.toString();
  }

  private int[] copy(int[][] original) {
    int copy[] = new int[gridSize * gridSize];
    for (int i = 0; i < gridSize; i++)
      System.arraycopy(original[i], 0, copy, i * gridSize, gridSize);
    return copy;
  }

  private int row(int index) {
    assert index <= dimension() * dimension();
    return (index - 1) / dimension();
  }

  private int col(int index) {
    assert index <= dimension() * dimension();
    return (index - 1) % dimension();
  }

  private int index(int row, int col) {
    assert row < dimension();
    assert col < dimension();
    return 1 + col + row * dimension();
  }

  private int arrayIndex(int row, int col) {
    assert row < dimension();
    assert col < dimension();
    return col + row * dimension();
  }

  private int[] exch(int i, int j, int m, int n) {
    assert Math.abs(i - m + j - n) == 1 && (Math.abs(i - m) == 0 || Math.abs(j - n) == 0);
    int[] copy = blocks.clone();
    int from = arrayIndex(i, j);
    int to = arrayIndex(m, n);
    int temp = copy[from];
    copy[from] = copy[to];
    copy[to] = temp;
    return copy;
  }
}
