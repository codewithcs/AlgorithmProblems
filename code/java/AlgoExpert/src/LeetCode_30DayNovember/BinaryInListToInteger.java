package LeetCode_30DayNovember;

public class BinaryInListToInteger {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val){
            this.val = val;
        }
        ListNode(int val, ListNode next){
            this.val = val;
            this.next = next;
        }
    }

    public int getDecimalValue(ListNode head) {

        int size = 0;
        ListNode node = head;

        while(node!= null){
            size++;
            node = node.next;
        }

        node = head;  int value = 0;

        while(node != null){
            if(node.val == 1){
                value += (int)Math.pow(2, size-1);
            }
            size-- ; node = node.next;
        }

        return  value;
    }
}
