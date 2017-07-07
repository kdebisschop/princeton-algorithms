package net.debisschop.algorithms.puzzle8;

import java.util.Arrays;
import java.util.Stack;

public class Board {

  final private int[][] blocks;
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
  public Board(int[][] blocks) {
    gridSize = blocks.length;
    this.blocks = copy(blocks);
    calculateScores();
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
        block = blocks[i][j];
        assert block <= max && block >= 0;
        if (block == 0) {
          zeroI = i;
          zeroJ = j;
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
        if (blocks[i][j] > 0 && blocks[i][j] != index(i, j)) return false;
    return true;
  }

  /**
   * a board that is obtained by exchanging any pair of blocks
   * @return
   */
  public Board twin() {
    if (blocks[0][0] == 0 || blocks[0][1] == 0) return new Board(exch(1, 0, 1, 1));
    else return new Board(exch(0, 0, 0, 1));
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
    Board that = (Board) y;
    return Arrays.deepEquals(this.blocks, that.blocks);
  }

  /**
   * all neighboring boards
   * @return
   */
  public Iterable<Board> neighbors() {
    Stack<Board> neighbors = new Stack<>();
    if (zeroI > 0) neighbors.push(new Board(move(zeroI, zeroJ, zeroI - 1, zeroJ)));
    if (zeroJ > 0) neighbors.push(new Board(move(zeroI, zeroJ, zeroI, zeroJ - 1)));
    if (zeroI < dimension() - 1) neighbors.push(new Board(move(zeroI, zeroJ, zeroI + 1, zeroJ)));
    if (zeroJ < dimension() - 1) neighbors.push(new Board(move(zeroI, zeroJ, zeroI, zeroJ + 1)));
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
        string.append(' ').append(blocks[i][j]);
    }
    return string.toString();
  }

  private int[][] copy(int[][] original) {
    int copy[][] = new int[gridSize][gridSize];
    for (int i = 0; i < gridSize; i++)
      copy[i] = original[i].clone();
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

  private int[][] exch(int i, int j, int m, int n) {
    assert Math.abs(i - m + j - n) == 1 && (Math.abs(i - m) == 0 || Math.abs(j - n) == 0);
    int[][] copy = copy(blocks);
    int temp = copy[i][j];
    copy[i][j] = copy[m][n];
    copy[m][n] = temp;
    return copy;
  }

  private int[][] move(int i, int j, int m, int n) {
    assert blocks[i][j] == 0 || blocks[m][n] == 0;
    return exch(i, j, m, n);
  }
}
