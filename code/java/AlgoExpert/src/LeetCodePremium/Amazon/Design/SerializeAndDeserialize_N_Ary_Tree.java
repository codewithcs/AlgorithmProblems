package LeetCodePremium.Amazon.Design;

import java.util.*;

public class SerializeAndDeserialize_N_Ary_Tree {
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        List<String> list=new LinkedList<>();
        serializeHelper(root,list);
        return String.join(",",list); // good technique.
    }

    private void serializeHelper(Node root, List<String> list){
        if(root==null){
            return;
        }else{
            list.add(String.valueOf(root.val));
            list.add(String.valueOf(root.children.size())); // >=0
            for(Node child:root.children){
                serializeHelper(child,list);
            }
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data.isEmpty()) {
            return null;
        }

        String[] ss=data.split(",");
        Queue<String> q = new LinkedList<>(Arrays.asList(ss));
        return deserializeHelper(q);
    }

    private Node deserializeHelper(Queue<String> q){
        Node root=new Node();
        root.val=Integer.parseInt(q.poll());

        int size=Integer.parseInt(q.poll());

        root.children = new ArrayList<Node>(size);
        for(int i=0;i<size;i++){
            root.children.add(deserializeHelper(q));
        }

        return root;
    }

    class Node {
        public int val;
        public List<Node> children; // children are stored as a list.

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    /// Level Order Solution:
    public String serialize2(Node root) {
        if (root == null)   return null;
        StringBuilder sb = new StringBuilder();
        Queue<Node> queue = new LinkedList<>();
        sb.append(root.val);
        sb.append("#");
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                if (curr != null) {
                    List<Node> children = curr.children;
                    if (children == null || children.size() == 0) {
                        sb.append("null");
                    } else {
                        for (Node c : children) {
                            sb.append(c.val);
                            sb.append(",");
                            queue.offer(c);
                        }
                    }
                    sb.append("#");
                }
            }
        }
        //   System.out.println(sb.toString());
        return sb.toString().substring(0, sb.length() - 1);
    }

    // Decodes your encoded data to tree.
    public Node deserialize2(String data) {
        if (data == null)   return null;
        Queue<Node> parents = new LinkedList<>();
        String[] elements = data.split("#");
        Node root = new Node(Integer.valueOf(elements[0]), null);
        parents.offer(root);
        for (int i = 1; i < elements.length; i++) {
            Node parent = parents.poll();
            String[] kids = elements[i].split(",");
            List<Node> c = new ArrayList<>();
            for (String kid : kids) {
                if (kid.length() == 0)  continue;
                if (kid.equals("null")) continue;
                Node k = new Node(Integer.valueOf(kid), null);
                c.add(k);
                parents.offer(k);
            }
            parent.children = c;
        }
        return root;
    }


}
