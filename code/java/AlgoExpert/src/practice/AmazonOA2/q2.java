package practice.AmazonOA2;

public class q2 {
    class ALNode{
        public int value;
        public ALNode next;
        public ALNode arbitrary;
        
        public ALNode(int val){
            this.value = val; this.next = null;
        }
        
        public ALNode(){
            this.value = -1; 
        }
        
    }
    
    public ALNode deepCopy(ALNode head) {
        if(head == null ){
            return null;
        }

        if(head.next == null){
            ALNode temp = new ALNode(head.value);
            temp.arbitrary = head.arbitrary == null ? null : temp;
            return temp;
        }

        ALNode first = head;
        ALNode second = head.next ;

        // Create cloned ALNodes. 
        while(second != null){
            ALNode third = new ALNode(first.value);
            first.next = third;
            third.next = second;
            first = second ;
            second = second.next;
        }

        first.next = new ALNode(first.value);

        // Assign random pointers. 
        first = head;
        second = head.next;
        while(second.next != null){
            second.arbitrary = first.arbitrary == null ? null :first.arbitrary.next;
            first = second.next;
            second = first.next;
        }

        second.arbitrary = first.arbitrary == null ? null : first.arbitrary.next;

        // Separate the two lists. 

        first = head;
        second = head.next ;
        ALNode third = second;

        while(second.next != null){
            first.next = second.next;
            second.next = first.next.next;
            first = first.next;
            second = second.next;
        }

        first.next = null;

        return third;
    }
}
