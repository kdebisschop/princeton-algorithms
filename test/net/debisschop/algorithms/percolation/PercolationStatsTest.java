package net.debisschop.algorithms.percolation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PercolationStatsTest {

  @Test
  public void mean() throws Exception {
    PercolationStats stats = new PercolationStats(200, 100);
    assertEquals(0.593, stats.mean(), 0.004);
    PercolationStats stats2 = new PercolationStats(2, 10000);
    assertEquals(0.667, stats2.mean(), 0.004);
  }

  @Test
  public void stddev() throws Exception {
    PercolationStats stats = new PercolationStats(200, 100);
    assertEquals(0.009, stats.stddev(), 0.002);
    PercolationStats stats2 = new PercolationStats(2, 10000);
    assertEquals(0.118, stats2.stddev(), 0.001);
  }

  @Test
  public void confidenceLo() throws Exception {
    PercolationStats stats = new PercolationStats(200, 100);
    assertEquals(0.592, stats.confidenceLo(), 0.004);
    PercolationStats stats2 = new PercolationStats(2, 10000);
    assertEquals(0.665, stats2.confidenceLo(), 0.01);
  }

  @Test
  public void confidenceHi() throws Exception {
    PercolationStats stats = new PercolationStats(200, 100);
    assertEquals(0.594, stats.confidenceHi(), 0.004);
    PercolationStats stats2 = new PercolationStats(2, 10000);
    assertEquals(0.67, stats2.confidenceHi(), 0.01);
  }
}
