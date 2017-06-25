package net.debisschop.algorithms.percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class PercolationStats {

  private int n;
  private double mean;
  private double stddev;
  private double confLo;
  private double confHi;

  /**
   * Perform trials independent experiments on an n-by-n grid.
   *
   * @param n Size of grid (number of columns = number of rows).
   * @param trials Number of trials to run.
   */
  public PercolationStats(int n, int trials) {
    if (n <= 0) {
      throw new IllegalArgumentException("Size of grid must be greater than 0");
    }
    if (trials <= 0) {
      throw new IllegalArgumentException("Number of trials must be greater than 0");
    }

    this.n = n;

    int randRow;
    int randCol;
    Percolation lattice;
    int[] thresholds = new int[trials];

    for (int trial = 0; trial < trials; trial++) {
      lattice = new Percolation(n);
      do {
        randRow = 1 + StdRandom.uniform(n);
        randCol = 1 + StdRandom.uniform(n);
        lattice.open(randRow, randCol);
      } while (!lattice.percolates());
      thresholds[trial] = lattice.numberOfOpenSites();
    }
    mean = StdStats.mean(thresholds);
    stddev = StdStats.stddev(thresholds);
    double dev = 1.96 * stddev / Math.sqrt(trials);
    confLo = mean - dev;
    confHi = mean + dev;
  }

  /**
   * @return ample mean of percolation threshold.
   */
  public double mean() {
    return mean / (n * n);
  }

  /**
   * @return sample standard deviation of percolation threshold.
   */
  public double stddev() {
    return stddev / (n * n);
  }

  /**
   * @return low end-point of 95% confidence interval.
   */
  public double confidenceLo() {
    return confLo / (n * n);
  }

  /**
   * @return high end-point of 95% confidence interval.
   */
  public double confidenceHi() {
    return confHi / (n * n);
  }

  /**
   * Test client.
   *
   * @param args Grid size and number of trials.
   */
  public static void main(String[] args) {
    if (args.length < 2) {
      Logger log = Logger.getLogger("net.debisschop.percolation");
      log.setLevel(Level.ALL);
      ConsoleHandler handler = new ConsoleHandler();
      handler.setFormatter(new SimpleFormatter());
      handler.setLevel(Level.ALL);
      log.addHandler(handler);
      log.warning("Please provide grid size and number of trials");
      return;
    }
    int gridSize = Integer.parseInt(args[0]);
    int numberOfTrials = Integer.parseInt(args[1]);
    PercolationStats test = new PercolationStats(gridSize, numberOfTrials);
    double confLo = test.confidenceLo();
    double confHi = test.confidenceHi();
    System.out.printf("mean                    = %f%n", test.mean() / (gridSize ^ 2));
    System.out.printf("stddev                  = %f%n", test.stddev());
    System.out.printf("95%% confidence interval = [%f, %f]%n", confLo, confHi);
  }
}
