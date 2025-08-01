class Solution {
    public boolean isPalindrome(int x) {
        if(x>=0) {
            int copy = x;
            int result = 0;
            while(copy > 0) {
                int digit = copy % 10;
                result = result*10 + digit;
                copy/=10;

            }
            if(result == x) {
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
        
    }
}