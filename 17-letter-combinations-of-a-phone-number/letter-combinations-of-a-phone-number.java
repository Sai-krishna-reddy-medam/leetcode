import java.util.*;
class Solution {
    private List<String> result = new ArrayList<>();
    private final String[] phoneMap = {
        "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) {
            return result;
        }
        backtrack(0, new StringBuilder(), digits);
        return result;
    }
    private void backtrack(int index, StringBuilder currentPath, String digits) {
        if (index == digits.length()) {
            result.add(currentPath.toString());
            return;
        }
        String letters = phoneMap[digits.charAt(index) - '0'];
        for (char letter : letters.toCharArray()) {
            currentPath.append(letter);
            backtrack(index + 1, currentPath, digits);
            currentPath.deleteCharAt(currentPath.length() - 1);
        }
    }
}