import java.util.ArrayList;
import java.util.List;

class Solution {
    // A representation of the tree, where tree[i] holds all neighbors of node i.
    private List<Integer>[] tree;
    private int[] nodeValues;

    // --- Pre-computation Data from DFS ---
    // Stores the XOR sum of each node's subtree.
    private int[] subtreeXorSums;
    // Used to check for ancestry in constant time.
    private int[] enterTime;
    private int[] exitTime;
    private int dfsTimer;

    /**
     * Finds the minimum possible score by removing any two edges.
     *
     * The strategy is to:
     * 1.  Pretend the tree is rooted at node 0.
     * 2.  Run a single DFS to calculate properties for every subtree (like its total XOR sum).
     * 3.  Brute-force check every possible pair of edges to remove.
     * 4.  For each pair, calculate the XOR sums of the three resulting components and find the score.
     * 5.  Keep track of the best (minimum) score found.
     */
    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        this.nodeValues = nums;
        this.tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        // Build the tree from the list of edges.
        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
            tree[edge[1]].add(edge[0]);
        }

        // Initialize arrays for our DFS pre-computation.
        this.subtreeXorSums = new int[n];
        this.enterTime = new int[n];
        this.exitTime = new int[n];
        this.dfsTimer = 0;

        // Let's start the magic! Run DFS from our pretend root (node 0).
        runDFS(0, -1); // -1 represents a non-existent parent.

        int totalTreeXor = subtreeXorSums[0];
        int minScoreFound = Integer.MAX_VALUE;

        // Now, let's check every pair of edges to remove.
        // We can identify an edge by its "child" node in our rooted tree (nodes 1 to n-1).
        for (int nodeA = 1; nodeA < n; nodeA++) {
            for (int nodeB = nodeA + 1; nodeB < n; nodeB++) {

                // These are the XOR sums of the two subtrees we're considering cutting off.
                int xorSubtreeA = subtreeXorSums[nodeA];
                int xorSubtreeB = subtreeXorSums[nodeB];

                int component1, component2, component3;

                // The logic depends on whether one cut is inside the other's subtree.
                // We use our pre-computed DFS times to check for this relationship.
                if (isAncestor(nodeA, nodeB)) {
                    // Case 1: Subtree A contains Subtree B.
                    // The 3 components are: Subtree B, (Subtree A - Subtree B), and the rest of the tree.
                    component1 = xorSubtreeB;
                    component2 = xorSubtreeA ^ xorSubtreeB;
                    component3 = totalTreeXor ^ xorSubtreeA;

                } else if (isAncestor(nodeB, nodeA)) {
                    // Case 2: Subtree B contains Subtree A.
                    // The 3 components are: Subtree A, (Subtree B - Subtree A), and the rest of the tree.
                    component1 = xorSubtreeA;
                    component2 = xorSubtreeB ^ xorSubtreeA;
                    component3 = totalTreeXor ^ xorSubtreeB;

                } else {
                    // Case 3: The subtrees are separate.
                    // The 3 components are: Subtree A, Subtree B, and the rest of the tree.
                    component1 = xorSubtreeA;
                    component2 = xorSubtreeB;
                    component3 = totalTreeXor ^ xorSubtreeA ^ xorSubtreeB;
                }

                int currentScore = calculateScore(component1, component2, component3);
                minScoreFound = Math.min(minScoreFound, currentScore);
            }
        }

        return minScoreFound;
    }

    /**
     * A helper to calculate the score from the three component XORs.
     * Score = (largest XOR) - (smallest XOR).
     */
    private int calculateScore(int val1, int val2, int val3) {
        int maxVal = Math.max(val1, Math.max(val2, val3));
        int minVal = Math.min(val1, Math.min(val2, val3));
        return maxVal - minVal;
    }

    /**
     * Runs a Depth First Search to explore the tree and calculate subtree properties.
     */
    private int runDFS(int currentNode, int parentNode) {
        enterTime[currentNode] = dfsTimer++;
        int runningXor = nodeValues[currentNode];

        for (int neighbor : tree[currentNode]) {
            if (neighbor != parentNode) {
                runningXor ^= runDFS(neighbor, currentNode);
            }
        }

        subtreeXorSums[currentNode] = runningXor;
        exitTime[currentNode] = dfsTimer++;
        return runningXor;
    }

    /**
     * Checks if `potentialAncestor` is an ancestor of `potentialDescendant`.
     * This works because an ancestor's DFS visit will always start before
     * and end after any of its descendants' visits.
     */
    private boolean isAncestor(int potentialAncestor, int potentialDescendant) {
        return enterTime[potentialAncestor] < enterTime[potentialDescendant] &&
               exitTime[potentialAncestor] > exitTime[potentialDescendant];
    }
}