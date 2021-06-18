package leetfree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
 * stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized
 * to a string and this string can be deserialized to the original tree structure.
 */
public class _297_SerializeAndDeserializeBinaryTree {

    @Test
    public void a() {
        TreeNode root = TreeNode.of(1,
                TreeNode.of(2),
                TreeNode.of(3,
                        TreeNode.of(4),
                        TreeNode.of(5)
                )
        );

        Codec codec = new Codec();
        String serialize = codec.serialize(root);
        TreeNode codecDecodec = codec.deserialize(serialize);

        assertEquals(codec.serialize(root), codec.serialize(codecDecodec));
    }

    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            return rSerialize(root, sb).toString();
        }

        private StringBuilder rSerialize(TreeNode root, StringBuilder sb) {
            if (root == null)
                sb.append("n,");
            else {
                sb.append(root.val);
                sb.append(',');
                rSerialize(root.left, sb);
                rSerialize(root.right, sb);
            }

            return sb;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] split = data.split(",");
            List<String> list = new ArrayList<>(Arrays.asList(split));
            return rDeserialize(list);
        }

        private TreeNode rDeserialize(List<String> split) {
            String current = split.remove(0);
            if (current.equals("n"))
                return null;
            else {
                Integer value = Integer.valueOf(current);
                return TreeNode.of(value,
                        rDeserialize(split),
                        rDeserialize(split)
                );
            }
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        TreeNode(int x, TreeNode l, TreeNode r) {
            val = x;
            left = l;
            right = r;
        }

        public static TreeNode of(int x, TreeNode l, TreeNode r) {
            return new TreeNode(x, l, r);
        }

        public static TreeNode of(int x) {
            return new TreeNode(x, null, null);
        }
    }
}
