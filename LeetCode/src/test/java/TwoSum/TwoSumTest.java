package TwoSum;

import org.junit.*;
import static org.junit.Assert.*;

public class TwoSumTest {
    // Object for testing method
    TwoSum test = new TwoSum();

    // Test given cases
    @Test
    public void testCases() {
        int[] nums = new int[] {2,7,11,15};
        int target = 9;
        assertArrayEquals(new int[] { 0, 1 }, test.twoSum(nums, target));
        

        nums = new int[] { 3, 2, 4};
        target = 6;
        assertArrayEquals(new int[] { 1, 2 }, test.twoSum(nums, target));

        nums = new int[] { 3, 3 };
        assertArrayEquals(new int[] { 0, 1 }, test.twoSum(nums, target));
    }
}
