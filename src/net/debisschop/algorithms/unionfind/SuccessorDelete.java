package net.debisschop.algorithms.unionfind;

public class SuccessorDelete extends WeightedQuickUnion {
    private int[] id;
    private int[] sz;
    private int[] max;

    public SuccessorDelete(int n) {
        super(n + 1);

        id = new int[n + 1];
        sz = new int[n + 1];
        max = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            max[i] = i;
        }
    }

    public void delete(int n) {
        if (n == 0) return;
        id[n] = n;
        if (! isElement(n - 1)) union(n, n - 1);
        if (n + 1 >= id.length) return;
        if (! isElement(n + 1)) union(n, n + 1);
    }

    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public boolean isElement(int n) { return n == 0 || id[n] == 0; }

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

    public int successor(int i) {
        if (isElement(i)) return i;
        if (i == 0 || i + 1 == id.length) return 0;

        int max = find(i);
        if (max + 1 == id.length) return 0;
        return max + 1;
    }
}
