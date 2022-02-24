package LeetCodePremium.Amazon.Design;
import java.util.ArrayDeque;

public class SerializeAndDeserializeBST {

    // With BST, there is a pattern. Mirror image traversal of the current traversal.
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    public StringBuilder postorder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return sb;
        }
        postorder(root.left, sb);
        postorder(root.right, sb);
        sb.append(root.val);
        sb.append(' ');
        return sb;
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = postorder(root, new StringBuilder());
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public TreeNode helper(Integer lower, Integer upper, ArrayDeque<Integer> nums) {
        if (nums.isEmpty()) {
            return null;
        }
        int val = nums.getLast();
        if (val < lower || val > upper) {
            return null;
        }

        nums.removeLast();
        TreeNode root = new TreeNode(val);
        root.right = helper(val, upper, nums);
        root.left = helper(lower, val, nums);
        return root;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        ArrayDeque<Integer> nums = new ArrayDeque<Integer>();

        for (String s : data.split("\\s+")) {
            nums.add(Integer.valueOf(s)); // add() or offer() to use it as a queue.
        }

        return helper(Integer.MIN_VALUE, Integer.MAX_VALUE, nums);
    }

    // Approach 2:
    // Encodes a tree to a list.
    public void postorder2(TreeNode root, StringBuilder sb) {
        if (root == null)
            return;
        postorder2(root.left, sb);
        postorder2(root.right, sb);
        sb.append(intToString(root.val));
    }

    // Encodes integer to bytes string
    public String intToString(int x) {
        char[] bytes = new char[4];
        for (int i = 3; i > -1; --i) {
            bytes[3 - i] = (char) (x >> (i * 8) & 0xff);
        }
        return new String(bytes);
    }

    // Encodes a tree to a single string.
    public String serialize2(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        postorder2(root, sb);
        return sb.toString();
    }

    // Decodes list to tree.
    public TreeNode helper2(Integer lower, Integer upper, ArrayDeque<Integer> nums) {
        if (nums.isEmpty())
            return null;
        int val = nums.getLast();
        if (val < lower || val > upper) {
            return null;
        }

        nums.removeLast();
        TreeNode root = new TreeNode(val);
        root.right = helper2(val, upper, nums);
        root.left = helper2(lower, val, nums);
        return root;
    }

    // Decodes bytes string to integer
    public int stringToInt(String bytesStr) {
        int result = 0;
        for (char b : bytesStr.toCharArray()) {
            result = (result << 8) + (int) b;
        }
        return result;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize2(String data) {
        ArrayDeque<Integer> nums = new ArrayDeque<Integer>();
        int n = data.length();
        for (int i = 0; i < (int) (n / 4); ++i) {
            nums.add(stringToInt(data.substring(4 * i, 4 * i + 4)));
        }

        return helper2(Integer.MIN_VALUE, Integer.MAX_VALUE, nums);
    }
}
