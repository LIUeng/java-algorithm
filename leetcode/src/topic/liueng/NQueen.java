package topic.liueng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class NQueen {
    public static void main(String[] args) {
        NQueen _this = new NQueen();
//        System.out.println(_this.solveNQueen(4));
        System.out.println(_this.solveNQueenByBitWise(4));
    }

    public List<List<String>> solveNQueenByBitWise(int n) {
        List<List<String>> result = new ArrayList<>();
        int[] queens = new int[n];

        bitWise(n, 0, result, queens, 0, 0, 0);

        return result;
    }

    public List<List<String>> solveNQueen(int n) {
        List<List<String>> result = new ArrayList<>();
        int[] queens = new int[n];

        HashSet<Integer> columns = new HashSet<>();
        HashSet<Integer> leftDiagonals = new HashSet<>();
        HashSet<Integer> rightDiagonals = new HashSet<>();

        dfs(n, 0, result, queens, columns, leftDiagonals, rightDiagonals);

        return result;
    }

    public void bitWise(int n, int row, List<List<String>> result, int[] queens, int columns, int leftDiagonal,
                        int rightDiagonal
    ) {
        if (row >= n) {
            result.add(generateBoard(queens, n));
            return;
        }

        int availablePositions = ((1 << n) - 1) & (~(columns | leftDiagonal | rightDiagonal));
        while (availablePositions != 0) {
            int position = availablePositions & (-availablePositions);
            availablePositions = availablePositions & (availablePositions - 1);

            int column = Integer.bitCount(position - 1);
            queens[row] = column;

            bitWise(n, row + 1, result, queens, position | columns, (leftDiagonal | position) << 1,
                    (rightDiagonal | position) >> 1
            );

            queens[row] = -1;
        }
    }

    public void dfs(int n, int row, List<List<String>> result, int[] queens, HashSet<Integer> columns,
                    HashSet<Integer> leftDiagonals, HashSet<Integer> rightDiagonals
    ) {
        if (row >= n) {
            // handle result
            result.add(generateBoard(queens, n));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (columns.contains(i)) {
                continue;
            }
            int leftDiagonal = row - i;
            if (leftDiagonals.contains(leftDiagonal)) {
                continue;
            }
            int rightDiagonal = row + i;
            if (rightDiagonals.contains(rightDiagonal)) {
                continue;
            }
            queens[row] = i;
            columns.add(i);
            leftDiagonals.add(leftDiagonal);
            rightDiagonals.add(rightDiagonal);

            dfs(n, row + 1, result, queens, columns, leftDiagonals, rightDiagonals);

            queens[row] = -1;
            columns.remove(i);
            leftDiagonals.remove(leftDiagonal);
            rightDiagonals.remove(rightDiagonal);
        }
    }

    public List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}
