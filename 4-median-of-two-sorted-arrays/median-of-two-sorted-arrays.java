class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int marged_arr[] = new int[m+n];
        for(int i = 0 ; i < m ; i++) {
            marged_arr[i] = nums1[i];
        }
        for(int i = 0 ; i < n ; i++) {
            marged_arr[m+i] = nums2[i];
        }
        Arrays.sort(marged_arr);
        if((n+m) % 2 == 0) {
            double median = (double) (marged_arr[marged_arr.length/2]+marged_arr[marged_arr.length/2-1])/2;
            return median;
        }else {
            double median = (double) marged_arr[marged_arr.length/2];
            return median;

        }
    }
}