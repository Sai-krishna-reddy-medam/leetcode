import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
class Solution {
    public int removeDuplicates(int[] nums) {
        LinkedHashSet<Integer> uniqueElements = new LinkedHashSet<>();
        for (int number : nums) {
            uniqueElements.add(number);
        }
        int k = 0;
        for (int uniqueNum : uniqueElements) {
            nums[k] = uniqueNum;
            k++;
        }
        return k;
    }
}