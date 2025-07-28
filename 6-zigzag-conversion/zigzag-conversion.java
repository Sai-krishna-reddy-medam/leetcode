class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }
        StringBuilder result = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                result.append(s.charAt(j + i));            
                boolean isIntermediateRow = i != 0 && i != numRows - 1;
                if (isIntermediateRow) {
                    int diagonalIndex = j + cycleLen - i;
                    if (diagonalIndex < n) {
                        result.append(s.charAt(diagonalIndex));
                    }
                }
            }
        }
        return result.toString();
    }
}
