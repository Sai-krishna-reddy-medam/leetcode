class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int low = 0;
        int high = m;
        int totalHalfLength = (m + n + 1) / 2;

        while (low <= high) {
            int cut1 = (low + high) / 2;
            int cut2 = totalHalfLength - cut1;

            int left1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int right1 = (cut1 == m) ? Integer.MAX_VALUE : nums1[cut1];

            int left2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
            int right2 = (cut2 == n) ? Integer.MAX_VALUE : nums2[cut2];

            if (left1 <= right2 && left2 <= right1) {
                if ((m + n) % 2 == 0) {
                    double median = (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                    return median;
                } else {
                    double median = Math.max(left1, left2);
                    return median;
                }
            } else if (left1 > right2) {
                high = cut1 - 1;
            } else {
                low = cut1 + 1;
            }
        }
        
        throw new IllegalArgumentException("Input arrays are not sorted.");
    }
}