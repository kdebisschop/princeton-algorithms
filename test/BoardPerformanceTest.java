import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

class BoardPerformanceTest {
  private int constructLoops = 5000;

  @ExtendWith(TimingExtension.class)
  @Test
  void construct4() {
    for (int i = 0; i < constructLoops; i++)
      new Board(new int[4][4]);
  }

  @ExtendWith(TimingExtension.class)
  @Test
  void construct8() {
    for (int i = 0; i < constructLoops; i++)
      new Board(new int[8][8]);
  }

  @ExtendWith(TimingExtension.class)
  @Test
  void construct16() {
    for (int i = 0; i < constructLoops; i++)
      new Board(new int[16][16]);
  }

  @ExtendWith(TimingExtension.class)
  @Test
  void construct32() {
    for (int i = 0; i < constructLoops; i++)
      new Board(new int[32][32]);
  }

  @ExtendWith(TimingExtension.class)
  @Test
  void construct64() {
    for (int i = 0; i < constructLoops; i++)
      new Board(new int[64][64]);
  }

  @ExtendWith(TimingExtension.class)
  @Test
  void construct128() {
    for (int i = 0; i < constructLoops; i++)
      new Board(new int[128][128]);
  }

  void isGoal(int size) {
    int[][] blocks = new int[size][size];
    for (int m = 0; m < size; m++)
      for (int n = 0; n < size; n++)
        blocks[m][n] = 1 + m * size + n;
    blocks[size - 1][size - 1] = 0;
    Board board = new Board(blocks);
    for (int i = 0; i < constructLoops; i++) {
      board.isGoal();
    }
  }

  @ExtendWith(TimingExtension.class)
  @org.junit.jupiter.api.Test
  void isGoal4() { isGoal(4); }

  @ExtendWith(TimingExtension.class)
  @org.junit.jupiter.api.Test
  void isGoal8() { isGoal(8); }

  @ExtendWith(TimingExtension.class)
  @org.junit.jupiter.api.Test
  void isGoal16() { isGoal(16);}

  @ExtendWith(TimingExtension.class)
  @org.junit.jupiter.api.Test
  void isGoal32() { isGoal(32); }

  @ExtendWith(TimingExtension.class)
  @org.junit.jupiter.api.Test
  void isGoal64() { isGoal(64); }
}
