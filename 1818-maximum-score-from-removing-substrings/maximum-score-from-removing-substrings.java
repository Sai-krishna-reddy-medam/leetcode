class Solution {
    public int maximumGain(String s, int x, int y) {
        if (x < y) {
            return maximumGain(new StringBuilder(s).reverse().toString(), y, x);
        }
        int totalScore = 0;        
        StringBuilder sb1 = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == 'b' && sb1.length() > 0 && sb1.charAt(sb1.length() - 1) == 'a') {
                sb1.deleteCharAt(sb1.length() - 1); // Pop 'a' from the builder.
                totalScore += x;
            } else {
                sb1.append(c); // Push the current character.
            }
        }       
        // The remaining string is what's left in sb1. Now, we process it for "ba".
        String remainingString = sb1.toString();
        StringBuilder sb2 = new StringBuilder();
        for (char c : remainingString.toCharArray()) {
            if (c == 'a' && sb2.length() > 0 && sb2.charAt(sb2.length() - 1) == 'b') {
                sb2.deleteCharAt(sb2.length() - 1); // Pop 'b'.
                totalScore += y;
            } else {
                sb2.append(c); // Push the current character.
            }
        }      
        return totalScore;
    }
}