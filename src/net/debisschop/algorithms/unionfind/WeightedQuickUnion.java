package net.debisschop.algorithms.unionfind;

public class WeightedQuickUnion {
    private int[] id;
    private int[] sz;
    private int[] max;

    public WeightedQuickUnion(int n) {
        id = new int[n];
        sz = new int[n];
        max = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            max[i] = i;
        }
    }

    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        int rootId;
        int nodeId;
        if (i == j) return;
        if (sz[i] < sz[j]) {
            rootId = j;
            nodeId = i;
        } else {
            rootId = i;
            nodeId = j;
        }
        if (max[rootId] < max[nodeId]) max[rootId] = max[nodeId];
        id[nodeId] = rootId;
        sz[rootId] += sz[nodeId];
    }

    public int find(int i) {
        return max[root(i)];
    }
}
