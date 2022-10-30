package org.example.abner.leetcode.greedy;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

public class L874 {

    public int robotSim(int[] commands, int[][] obstacles) {
        int[] direx = {0, 1, 0, -1};
        int[] direy = {1, 0, -1, 0};
        int curx = 0, cury = 0;
        int curdire = 0;
        int comLen = commands.length;
        int ans = 0;
        Set<Pair<Integer, Integer>> obstacleSet = new HashSet<>();
        for (int[] obstacle : obstacles) {
            obstacleSet.add(new Pair<>(obstacle[0], obstacle[1]));
        }
        for (int command : commands) {
            if (command == -1) {
                curdire = (curdire + 1) % 4;
            } else if (command == -2) {
                curdire = (curdire + 3) % 4;
            } else {
                for (int j = 0; j < command; j++) {
                    int nx = curx + direx[curdire];
                    int ny = cury + direy[curdire];
                    if (!obstacleSet.contains(new Pair<>(nx, ny))) {
                        curx = nx;
                        cury = ny;
                        ans = Math.max(ans, curx * curx + cury * cury);
                    } else {
                        break;
                    }
                }
            }
        }
        return ans;
    }

}
