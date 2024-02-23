/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private static List<Integer> lists = new ArrayList<>();
    private void addNodeByinorder(TreeNode root) {
        if(root != null) {
            if(root.left != null) {
                addNodeByinorder(root.left);
            }
            lists.add(root.val);
            if(root.right != null) {
                addNodeByinorder(root.right);
            }
        }
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        lists.clear();
        addNodeByinorder(root);
        return lists;
    }
}