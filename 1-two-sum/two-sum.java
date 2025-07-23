import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Create a HashMap to store the value and its index.
        Map<Integer, Integer> numMap = new HashMap<>();
        
        // Iterate through the array.
        for (int i = 0; i < nums.length; i++) {
            // Calculate the complement needed to reach the target.
            int complement = target - nums[i];
            
            // Check if the map already contains the complement.
            if (numMap.containsKey(complement)) {
                // If it does, we have found the solution.
                // Return the index of the complement and the current index.
                return new int[] {numMap.get(complement), i};
            }
            
            // If the complement is not in the map, add the current number and its index.
            numMap.put(nums[i], i);
        }
        
        // As the problem guarantees a solution, this line is unreachable.
        // It's included to satisfy the compiler's requirement for a return statement.
        throw new IllegalArgumentException("No two sum solution");
    }
}