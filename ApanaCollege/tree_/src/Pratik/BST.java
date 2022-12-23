package Pratik;

import java.util.ArrayList;
import java.util.List;

public class BST {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int val) {
            this.data = val;
        }
    }

    public static Node insert(Node root, int val) {
        if (root == null) {
            root = new Node(val);
            return root;
        }
        if (root.data > val) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }
        return root;
    }

    public static void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    public static boolean search(Node root, int key) {
        if (root == null) {
            return false;
        } else if (root.data == key) {
            return true;
        } else if (key > root.data) {
            return search(root.right, key);
        } else {
            return search(root.left, key);
        }
    }

    public static Node delete(Node root, int val){
        if (root.data>val){
            root.left = delete(root.left, val);
        }
        else if (root.data<val){
            root.right = delete(root.right, val);
        }else{ //  root.data == val
            // case 1
             if (root.left == null && root.right == null ){
                 return null;
             }
             //case 2
            if (root.left == null){
                return root.right;
            }else if(root.right == null){
                return root.left;
            }
            //case 3
            Node Is = inorderSuccessor(root.right);
            root.data = Is.data;
            root.right = delete(root.right, Is.data);
        }
        return root;
    }

    public static Node inorderSuccessor(Node root){
        while (root.left != null){
            root = root.left;
        }
        return root;
    }

    public static void printNoInRange(Node root, int x, int y){
        if (root==null){
            return;
        }
        if (root.data>=x && root.data<=y){
            printNoInRange(root.left, x, y);
            System.out.print(root.data+" ");
            printNoInRange(root.right, x, y);
        }else if (root.data>=y){
            printNoInRange(root.left, x, y);
        }else {
            printNoInRange(root.right, x, y);
        }
    }

    public static void rootToLeafPath(Node root, List<Integer> path){
        if (root==null){
            return;
        }
        path.add(root.data);
        if (root.left==null && root.right==null){
            System.out.println(path);
        }
        else {
            rootToLeafPath(root.left, path);
            rootToLeafPath(root.right, path);
        }
        path.remove(path.size()-1);
    }

    public static void main(String[] args) {
        int[] values = {8, 5, 3, 6, 10, 11 , 14};
        Node root = null;
        for (int i = 0; i < values.length; i++) {
            root = insert(root, values[i]);
        }
        inOrder(root);
        System.out.println();
//        if (search(root, 7)){
//            System.out.println("found");
//        }else {
//            System.out.println("Not found");
//        }
//        delete(root, 3);
//        delete(root, 7);
//        inOrder(root);
//          printNoInRange(root, 2,4);
        List<Integer> ans = new ArrayList<Integer>();
        rootToLeafPath(root, ans);
    }
}
