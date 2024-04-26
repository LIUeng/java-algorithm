package topic.liueng;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        Permutation _this = new Permutation();
        System.out.println(_this.permutation(arr));
    }

    public List<List<Integer>> permutation(int[] arr) {
        List<List<Integer>> ans = new ArrayList<>();

        List<Integer> res = new ArrayList<>();
        boolean[] used = new boolean[arr.length];

        this.dfs(arr, 0, used, res, ans);

        return ans;
    }

    public void dfs(int[] arr, int depth, boolean[] used, List<Integer> res, List<List<Integer>> ans) {
        int size = arr.length;

        if (depth >= size) {
            // 复制
            ans.add(new ArrayList<>(res));
            return;
        }

        for (int i = 0; i < size; i++) {
            if (used[i]) continue;
            used[i] = true;
            res.add(arr[i]);
            dfs(arr, depth + 1, used, res, ans);
            res.remove(res.size() - 1);
            used[i] = false;
        }
    }
}
