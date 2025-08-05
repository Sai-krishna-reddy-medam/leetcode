import java.util.HashMap;
import java.util.Map;

class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);
        int total = romanMap.get(s.charAt(s.length() - 1));
        for (int i = s.length() - 2; i >= 0; i--) {
            int currentVal = romanMap.get(s.charAt(i));
            int previousVal = romanMap.get(s.charAt(i + 1));
            if (currentVal < previousVal) {
                total -= currentVal;
            } 
            else {
                total += currentVal;
            }
        }
        return total;
    }
    public static void main(String[] args) {
        Solution solver = new Solution();
        System.out.println("\"III\" -> " + solver.romanToInt("III"));
        System.out.println("\"LVIII\" -> " + solver.romanToInt("LVIII"));
        System.out.println("\"MCMXCIV\" -> " + solver.romanToInt("MCMXCIV"));
    }
}
