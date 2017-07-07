package net.debisschop.algorithms.puzzle8;

import edu.princeton.cs.algs4.In;

class Helper {
  static Board readBoard(String file) {
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
