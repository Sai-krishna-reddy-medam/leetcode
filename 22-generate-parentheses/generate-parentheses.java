import java.util.ArrayList;
import java.util.List;
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }
    private void backtrack(List<String> result, StringBuilder currentStr, int openCount, int closeCount, int maxPairs) {
        if (currentStr.length() == maxPairs * 2) {
            result.add(currentStr.toString());
            return;
        }
        if (openCount < maxPairs) {
            currentStr.append('(');
            backtrack(result, currentStr, openCount + 1, closeCount, maxPairs);
            currentStr.deleteCharAt(currentStr.length() - 1);
        }
        if (closeCount < openCount) {
            currentStr.append(')');
            backtrack(result, currentStr, openCount, closeCount + 1, maxPairs);
            currentStr.deleteCharAt(currentStr.length() - 1);
        }
    }
}