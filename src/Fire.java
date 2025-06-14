import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.management.Query;

public class Fire {
    /**
     * Returns how long it takes for all vulnerable trees to be set on fire if a
     * match is lit at a given location.
     * 
     * The forest is represented via a rectangular 2d char array where t represents
     * a tree
     * and . represents an empty space.
     * 
     * At time 0, the tree at location [matchR, matchC] is set on fire. At every
     * subsequent
     * time step, any tree that is adjacent (up/down/left/right) to a burning tree
     * is also
     * set on fire.
     * 
     * 
     * EXAMPLE
     * forest:
     * 
     * t..tttt.t
     * ..tt....t
     * ..ttttttt
     * tttt.....
     * 
     * matchR: 2
     * matchC: 6
     * 
     * Result: 8
     * 
     * Explanation:
     * At time 0, the tree at (2, 6) is set on fire. At time 1, its adjacent trees
     * also catch on fire
     * At time 2, the trees adjacent to those catch as well. At time 8, the last
     * tree to catch is at
     * (0,6). In this example, there is one tree that never burns, so it is not
     * included in the time calculation.
     * 
     * 
     * @param forest a 2d array where t represents a tree and . represents the
     *               ground
     * @param matchR The row the match is lit at
     * @param matchC The column the match is lit at
     * @return the time at which the final tree to be incinerated starts burning
     */
    public static int timeToBurn(char[][] forest, int matchR, int matchC) {
        // HINT: when adding to your BFS queue, you can include more information than
        // just a location. What other information might be useful?

        int maxCol = 0;
        for(char[] row: forest){
            if(maxCol < row.length){
                maxCol = row.length;
            }
        }

        boolean[][] visited = new boolean[forest.length][maxCol];

        Queue<int[]> queue = new LinkedList<>();

        int unburnStarter = 0;
        int[] match = {matchR, matchC, 0};
        queue.add(match);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curR = current[0];
            int curC = current[1];

            if(visited[curR][curC]){
                continue;
            }

            visited[curR][curC] = true;

            queue.addAll(neighborTrees(forest, current));
            
            unburnStarter = current[2];
        }

        return unburnStarter;
    }

    public static List<int[]> neighborTrees(char[][] forest, int[] location) {
        int curR = location[0];
        int curC = location[1];
        int depth = location[2] + 1;
        List<int[]> neighbors = new ArrayList<>();

        int[][] directions = {
                { -1, 0 },
                { 1, 0 },
                { 0, -1 },
                { 0, 1 }
        };

        for (int[] move : directions) {
            int newR = curR + move[0];
            int newC = curC + move[1];

            if (newR >= 0 && newR < forest.length &&
                    newC >= 0 && newC < forest[newR].length &&
                    forest[newR][newC] == 't') {
                int[] validMove = { newR, newC, depth };
                neighbors.add(validMove);
            }
        }

        return neighbors;
    }
}