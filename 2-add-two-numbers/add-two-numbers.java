class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {       
        // A placeholder to mark the start of our new list. This makes adding the first node easy.
        ListNode resultDummyHead = new ListNode(0);       
        // This pointer will always point to the last node in our result list.
        ListNode resultTail = resultDummyHead;      
        // This will hold the "carry the one" value for our addition.
        int carry = 0;       
        // We need to keep going as long as there are digits in either list or a carry is left over.
        while (l1 != null || l2 != null || carry != 0) {           
            // Get the digit from the first list. If the list is finished, the digit is 0.
            int digit1 = (l1 != null) ? l1.val : 0;        
            // Get the digit from the second list. If the list is finished, the digit is 0.
            int digit2 = (l2 != null) ? l2.val : 0;           
            // Add the digits together with the carry from the last step.
            int sum = digit1 + digit2 + carry;           
            // The new digit for our result is the ones place of the sum.
            // For example, if sum is 17, the new digit is 7.
            int newDigit = sum % 10;           
            // The new carry is the "tens" place of the sum.
            // If sum is 17, the carry becomes 1.
            carry = sum / 10;            
            // Create a new node with the calculated digit and attach it to our result list.
            resultTail.next = new ListNode(newDigit);           
            // Move our tail pointer to the newly created node.
            resultTail = resultTail.next;          
            // Move to the next node in each list, if they exist.
            l1 = (l1 != null) ? l1.next : null;
            l2 = (l2 != null) ? l2.next : null;
        }       
        // The real result starts after our placeholder, so we return its next node.
        return resultDummyHead.next;
    }
}