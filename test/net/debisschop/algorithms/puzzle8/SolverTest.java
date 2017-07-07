package net.debisschop.algorithms.puzzle8;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class SolverTest {

  @Test
  void isSolvable() {
    int[][] blocks;
    Solver solver;

    blocks = new int[][] {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
    solver = new Solver(new Board(blocks));
    assertThat(solver.isSolvable(), is(true));

    blocks = new int[][] {{0, 3, 1}, {4, 2, 5}, {7, 8, 6}};
    solver = new Solver(new Board(blocks));
    assertThat(solver.isSolvable(), is(false));
  }

  @Test
  void moves() {
    int[][] blocks;
    Solver solver;

    blocks = new int[][] {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
    solver = new Solver(new Board(blocks));
    assertThat(solver.moves(), is(4));

    blocks = new int[][] {{0, 3, 1}, {4, 2, 5}, {7, 8, 6}};
    solver = new Solver(new Board(blocks));
    assertThat(solver.moves(), is(-1));
  }

  @Test
  void solution() {
    int[][] blocks;
    Solver solver;

    blocks = new int[][] {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
    solver = new Solver(new Board(blocks));
    for (Board board : solver.solution()) {
      assertThat(board.dimension(), is(3));
    }

    blocks = new int[][] {{0, 3, 1}, {4, 2, 5}, {7, 8, 6}};
    solver = new Solver(new Board(blocks));
    assertNull(solver.solution(), "iterator should be null for unsolvable board");
  }

  @Test
  void main() {
    Solver solver;
    solver = new Solver(Helper.readBoard("puzzle00"));
    assertThat(solver.solution().iterator().next().isGoal(), is(true));
  }

}
