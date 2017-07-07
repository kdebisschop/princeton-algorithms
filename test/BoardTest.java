import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.*;

import edu.princeton.cs.algs4.In;
import java.util.Iterator;

class BoardTest {
  private int constructLoops = 500;

  @org.junit.jupiter.api.Test
  void dimension() {
    Board board;

    board = new Board(new int[1][1]);
    assertThat(board.dimension(), is(1));

    board = readBoard("puzzle2x2-00");
    assertThat(board.dimension(), is(2));

    board = new Board(new int[2][2]);
    assertThat(board.dimension(), is(2));

    board = readBoard("puzzle3x3-00");
    assertThat(board.dimension(), is(3));

    board = new Board(new int[3][3]);
    assertThat(board.dimension(), is(3));

    board = readBoard("puzzle4x4-00");
    assertThat(board.dimension(), is(4));

    board = new Board(new int[4][4]);
    assertThat(board.dimension(), is(4));
  }

  @org.junit.jupiter.api.Test
  void hamming() {
    int[][] blocks;
    Board board;

    blocks = new int[][] {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
    board = new Board(blocks);
    assertThat(board.hamming(), is(5));

    blocks = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    board = new Board(blocks);
    assertThat(board.hamming(), is(0));

    blocks = new int[][] {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
    board = new Board(blocks);
    assertThat(board.hamming(), is(8));
  }

  @org.junit.jupiter.api.Test
  void manhattan() {
    int[][] blocks;
    Board board;

    blocks = new int[][] {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
    board = new Board(blocks);
    assertThat(board.manhattan(), is(10));

    blocks = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    board = new Board(blocks);
    assertThat(board.manhattan(), is(0));

    blocks = new int[][] {{0, 1, 2}, {5, 4, 3}, {7, 6, 8}};
    board = new Board(blocks);
    assertThat(board.manhattan(), is(8));

    try {
      new Board(new int[][] {{9, 1, 2}, {5, 4, 3}, {7, 6, 8}});
      fail("Illegal argument should throw exception");
    } catch (AssertionError e) {
      assertThat(e.getMessage(), not(is("")));
    }
  }

  @org.junit.jupiter.api.Test
  void isGoal() {
    int[][] blocks;
    Board board;

    blocks = new int[][] {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
    board = new Board(blocks);
    assertThat(board.isGoal(), is(false));

    blocks = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    board = new Board(blocks);
    assertThat(board.isGoal(), is(true));

    blocks = new int[][] {{0, 1, 2}, {5, 4, 3}, {7, 6, 8}};
    board = new Board(blocks);
    assertThat(board.isGoal(), is(false));
  }

  @org.junit.jupiter.api.Test
  void twin() {
    int[][] blocks;
    Board board;

    blocks = new int[][] {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
    board = new Board(blocks);
    assertThat(board.equals(board.twin()), is(false));

    blocks = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    board = new Board(blocks);
    assertThat(board.equals(board.twin()), is(false));

    blocks = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    board = new Board(blocks);
    assertThat(board.equals(board.twin()), is(false));
    assertThat(board.hamming(), is(0));
    assertThat(board.twin().hamming(), is(2));
  }

  @org.junit.jupiter.api.Test
  void equals() {
    int[][] blocks;
    Board board1;
    Board board2;

    blocks = new int[][] {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
    board1 = new Board(blocks);
    board2 = new Board(blocks);
    assertThat(board1.equals(board2), is(true));

    blocks = new int[][] {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
    board2 = new Board(blocks);
    assertThat(board1.equals(board2), is(true));
    blocks[0][0] = 9;
    assertThat(board1.equals(board2), is(true));
  }

  @org.junit.jupiter.api.Test
  void neighbors() {
    int[][] blocks;
    Board board;

    blocks = new int[][] {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
    board = new Board(blocks);
    Iterable<Board> iterable = board.neighbors();
    Iterator<Board> iterator1 = iterable.iterator();
    assertThat(iterator1.hasNext(), is(true));
    Iterator<Board> iterator2 = iterable.iterator();
    assertThat(iterator2.hasNext(), is(true));
    while (iterator1.hasNext()) iterator1.next();
    assertThat(iterator1.hasNext(), is(false));
    assertThat(iterator2.hasNext(), is(true));
  }

  @org.junit.jupiter.api.Test
  void toStringTest() {
    int[][] blocks;
    Board board;

    blocks = new int[][] {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
    board = new Board(blocks);
    assertThat(board.toString(), is("3\n 0 1 3\n 4 2 5\n 7 8 6"));
  }

  @org.junit.jupiter.api.Test
  void main() {
  }


  private Board readBoard(String file) {
    In in = new In("test/net/debisschop/algorithms/puzzle8/" + file + ".txt");
    int n = in.readInt();
    int[][] tiles = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        tiles[i][j] = in.readInt();
      }
    }
    return new Board(tiles);
  }

}
