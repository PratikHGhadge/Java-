package Pratik;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTrees {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree {
        static int idx = -1;

        public static void inorder(Node root) {
            if (root == null) {
                return;
            }
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }

        public static void preOrder(Node root) {
            if (root == null) {
                return;
            }
            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }

        public static void postorder(Node root) {
            if (root == null) {
                return;
            }
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }

        public static Node buildeTree(int nodes[]) {
            idx++;
            if (nodes[idx] == -1) {
                return null;
            }
            Node newNode = new Node(nodes[idx]);
            newNode.left = buildeTree(nodes);
            newNode.right = buildeTree(nodes);
            return newNode;
        }

        public static void levelOrder(Node root) {
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            q.add(null);
            while (!q.isEmpty()) {
                Node currNode = q.poll();
                if (currNode == null) {
                    System.out.println();
                    if (q.isEmpty()) {
                        break;
                    } else {
                        q.add(null);
                    }
                } else {
                    System.out.print(currNode.data + " ");
                    if (currNode.left != null) {
                        q.add(currNode.left);
                    }
                    if (currNode.right != null) {
                        q.add(currNode.right);
                    }
                }
            }
        }
    }

    public static int countOFNodes(Node root) {
        if (root == null) {
            return 0;
        }
        int leftcount = countOFNodes(root.left);
        int rightCount = countOFNodes(root.right);
        return leftcount + rightCount + 1;
    }

    public static int sumOFNodes(Node root) {
        if (root == null) {
            return 0;
        }
        int leftSum = sumOFNodes(root.left);
        int rightSum = sumOFNodes(root.right);
        return leftSum + rightSum + root.data;
    }

    public static int height(Node root) {
        if (root == null) {
            return 0;
        }
        int leftheight = height(root.left);
        int rightHeight = height(root.right);
        return Math.max(leftheight, rightHeight) + 1;
    }

    public static int diameter(Node root) {
        if (root == null) {
            return 0;
        }
        int dia1 = diameter(root.left);
        int dia2 = diameter(root.right);
        int dia3 = height(root.left) + height(root.right) + 1;
        return Math.max(dia3, Math.max(dia1, dia2));
    }


    // 2nd approch for finding diameter
    static class Treeinfo{
        int ht;
        int dia;
        Treeinfo(int ht, int dia){
            this.ht = ht;
            this.dia = dia;
        }
    }

    public static Treeinfo diameter2(Node root){
        if (root==null){
            return new Treeinfo(0, 0);
        }
        Treeinfo left = diameter2(root.left);
        Treeinfo right = diameter2(root.right);
        int myHeight = Math.max(left.ht, right.ht)+1;
        int diam1 = left.dia;
        int diam2 = right.dia;
        int diam3 = left.ht + right.ht +1 ;
        int myDiam =  Math.max(diam3, Math.max(diam1, diam2));
        return new Treeinfo(myHeight, myDiam);
    }

    public static void main(String[] args) {
        int[] nodes = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
        BinaryTree tree = new BinaryTree();
        Node root = tree.buildeTree(nodes);
        System.out.print("Inorder :");
        tree.inorder(root);
        System.out.println();
        System.out.print("preOrder :");
        tree.preOrder(root);
        System.out.println();
        System.out.print("PostOrder :");
        tree.postorder(root);
        System.out.println();
        System.out.println("LevelOrder :");
        tree.levelOrder(root);
        System.out.println("Count of nodes : " + countOFNodes(root));
        System.out.println("Sum of nodes : " + sumOFNodes(root));
        System.out.println("height of tree : " + height(root));
        System.out.println("diameter of tree : " + diameter(root));
        System.out.println("diameter2 of tree : " + diameter2(root).dia);
    }
}
