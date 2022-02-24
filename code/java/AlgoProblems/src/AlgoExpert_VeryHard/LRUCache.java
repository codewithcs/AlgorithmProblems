package AlgoExpert_VeryHard;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    // Can also use 2 extra dummy nodes, head and tail. Node next to head will be most recently used and previous to tail will be least recently used.
    static class LRUCache1{
        Map<String, DoublyLinkedListNode> cache = new HashMap<>();
        int maxSize;
        int currentSize = 0;
        DoublyLinkedList listOfMostRecent = new DoublyLinkedList();

        public LRUCache1(int maxSize){
            this.maxSize = maxSize > 1 ? maxSize : 1;
        }

        // O(1) time | O(1) space
        public void insertKeyValuePair(String key, int value){
            if (!cache.containsKey(key)){
                if(currentSize == maxSize){
                    evictLeastRecent();
                } else {
                    currentSize++;
                }
                cache.put(key, new DoublyLinkedListNode(key, value));
            } else {
                replaceKey(key, value);
            }
            updateMostRecent(cache.get(key));
        }

        // O(1) time | O(1) space
        public LRUResult getValueFromKey(String key){
            if(!cache.containsKey(key)){
                return new LRUResult(false, -1);
            }
            updateMostRecent(cache.get(key));
            return new LRUResult(true, cache.get(key).value);
        }

        // O(1) time | O(1) space
        public String getMostRecentKey(){
            if(listOfMostRecent.head == null){
                return "";
            }
            return listOfMostRecent.head.key;
        }

        public void evictLeastRecent(){
            String keyToRemove = listOfMostRecent.tail.key;
            listOfMostRecent.removeTail();
            cache.remove(keyToRemove);
        }

        public void updateMostRecent(DoublyLinkedListNode node){
            listOfMostRecent.setHeadTo(node);
        }

        public void replaceKey(String key, int value){
            if(!this.cache.containsKey(key)){ // already checked this ?
                return ;
            }
            cache.get(key).value = value;
        }
    }

    static class DoublyLinkedList {
        DoublyLinkedListNode head = null ;
        DoublyLinkedListNode tail = null ;

        // All the pointer setting is done here.
        public void setHeadTo(DoublyLinkedListNode node){

            if(head == node){ // important edge case
                return ;
            } else if(head == null){
                head = node;
                tail = node;
            } else if(head == tail ){ // edge case
                tail.prev = node;
                node.next = tail; // makes sense
                head = node;
            } else {
                if(tail == node){
                    removeTail();
                }
                node.removeBindings();
                head.prev = node;
                node.next = head ;
                head = node;
            }
        }

        public void removeTail(){
            if(tail == null){ // Edge Case
                return ;
            }
            if(tail == head){ // Edge Case
                head = null;
                tail = null;
                return ;
            }
            tail = tail.prev ;
            tail.next = null;
        }
    }

    static class DoublyLinkedListNode{
        String key;
        int value;
        DoublyLinkedListNode prev = null;
        DoublyLinkedListNode next = null ;

        public DoublyLinkedListNode(String key, int value){
            this.key = key;
            this.value = value;
        }

        public void removeBindings(){
            if(prev != null){
                prev.next = next;
            }
            if(next != null){
                next.prev = prev;
            }
            prev = null;
            next = null;
        }

    }

    static class LRUResult{
        boolean found;
        int value;
        public LRUResult(boolean result, int value){
            this.found = found;
            this.value = value;
        }
    }
}
