package net.debisschop.algorithms.puzzle8;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import java.util.Comparator;
import net.debisschop.algorithms.puzzle8.Board;

public class Solver {

  private SearchNode solution;

  /**
   * find a solution to the initial board (using the A* algorithm)
   * @param initial
   */
  public Solver(Board initial) {
    if (initial == null) throw new IllegalArgumentException();

    MinPQ<SearchNode> queue = new MinPQ<>(new BoardComparator());
    MinPQ<SearchNode> altQueue = new MinPQ<>(new BoardComparator());

    if (initial.isGoal()) {
      solution = new SearchNode(initial, 0, null);
      return;
    }

    queue.insert(new SearchNode(initial, 0, null));
    altQueue.insert(new SearchNode(initial.twin(), 0, null));

    while (!(queue.min().board.isGoal() || altQueue.min().board.isGoal())) {
      advance(queue);
      advance(altQueue);
    }
    if (queue.min().board.isGoal()) {
      solution = queue.delMin();
    }
    altQueue.delMin();
  }

  private void advance(MinPQ<SearchNode> queue) {
    SearchNode node = queue.delMin();
    for (Board board : node.board.neighbors()) {
      if (node.previous != null && board.equals(node.previous.board))
        continue;
      queue.insert(new SearchNode(board, node.moves + 1, node));
    }
  }

  /**
   * is the initial board solvable?
   * @return
   */
  public boolean isSolvable() { return solution != null; }

  /**
   * min number of moves to solve initial board; -1 if unsolvable
   * @return
   */
  public int moves() {
    if (isSolvable())
      return solution.moves;
    return -1;
  }

  /**
   * sequence of boards in a shortest solution; null if unsolvable
   * @return
   */
  public Iterable<Board> solution() {
    if (!isSolvable()) return null;
    Stack<Board> sequence = new Stack<>();
    SearchNode searchNode = solution;
    while (searchNode.previous != null) {
      sequence.push(searchNode.board);
      searchNode = searchNode.previous;
    }
    sequence.push(searchNode.board);
    return sequence;
  }

  private class SearchNode {
    private Board board;
    private int moves;
    private SearchNode previous;
    private int score;
    private SearchNode(Board board, int moves, SearchNode previous) {
      this.board = board;
      this.moves = moves;
      this.previous = previous;
      score = board.manhattan() + moves;
    }
    private int getScore() { return score; }
  }

  private class BoardComparator implements Comparator<SearchNode> {
    @Override
    public int compare(SearchNode o1, SearchNode o2) {
      return Integer.compare(o1.getScore(), o2.getScore());
    }
  }
}
