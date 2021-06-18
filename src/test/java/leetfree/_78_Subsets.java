package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;

public class _78_Subsets {

    @Test
    public void a() {
        List<List<Integer>> subsets = subsets(new int[]{1, 2, 3});
        assertEquals(8, subsets.size());
        Assert.assertThat(subsets, containsInAnyOrder(
                List.of(),
                List.of(1),
                List.of(2),
                List.of(3),
                List.of(1, 2),
                List.of(1, 3),
                List.of(2, 3),
                List.of(1, 2, 3)
        ));
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < 1 << nums.length; i++) {
            ArrayList<Integer> current = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if ((i >> j & 1) == 1)
                    current.add(nums[j]);
            }
            res.add(current);
        }
        return res;
    }
}
