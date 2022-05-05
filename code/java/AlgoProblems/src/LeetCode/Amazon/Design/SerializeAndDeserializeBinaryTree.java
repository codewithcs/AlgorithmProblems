package LeetCode.Amazon.Design;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SerializeAndDeserializeBinaryTree {
    public String rserialize(TreeNode root, String str) { // We can avoid storing "null", Can replace "null" with some other symbol.
                                                            // can also store number of children.
        // Recursive serialization.
        if (root == null) {
            str += "null,";
        } else {
            str += str.valueOf(root.val) + ",";
            str = rserialize(root.left, str);
            str = rserialize(root.right, str);
        }
        return str;
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return rserialize(root, "");
    }

    // Using String Builder improves the complexity significantly.
    public StringBuilder rserialize2(TreeNode root, StringBuilder str) {
        if (root == null) {
            str.append("null,");
        } else {
            str.append(root.val); str.append(","); // have to use 2 appends.
            str = rserialize2(root.left, str);
            str = rserialize2(root.right, str);
        }
        return str;
    }

    public String serialize2(TreeNode root) {
        return rserialize2(root, new StringBuilder()).toString();
    }

    public TreeNode rdeserialize(List<String> l) {
        // Recursive deserialization.
        if (l.get(0).equals("null")) {
            l.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
        l.remove(0);
        root.left = rdeserialize(l);
        root.right = rdeserialize(l);

        return root;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] data_array = data.split(",");
        List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
        // no need to use queue.
        return rdeserialize(data_list);
    }

    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }
}
