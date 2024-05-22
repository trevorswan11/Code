namespace RemoveElement
{
    public class Solution
    {
        public static int RemoveElement(int[] nums, int val)
        {
            int k = 0;

            for (int i = 0; i < nums.Length; i++)
            {
                if (nums[i] != val)
                {
                    nums[k] = nums[i];
                    k += 1;
                }
            }
            return k;
        }

        
        public static string ToString(int[] nums)
        {
            string s = "[";

            for (int i = 0; i < nums.Length; i++)
            {
                s += nums[i];
                if (i != nums.Length - 1)
                {
                    s += ", ";
                }
            }

            return s + "]";
        }

        public static void Main()
        {
            int[] nums = [3, 2, 2, 3];
            int val = 3;
            Console.WriteLine(RemoveElement(nums, val) + "\n" + ToString(nums));
        }
    }
}