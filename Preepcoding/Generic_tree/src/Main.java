import java.util.*;

public class Main {
    private static class Node{
        int data;
        ArrayList<Node> child = new ArrayList<>();
    }

    public static void mirror(Node node){
        // write your code here
        for(Node ch : node.child){
            mirror(ch);
        }
        Collections.reverse(node.child);
    }

    public static void traversal(Node root){
        System.out.println("node pre " + root.data);
        for (Node ch : root.child){
            System.out.println("Edge pre " + root.data +"____"+ ch.data);
            traversal(ch);
            System.out.println("Edge post " + root.data +"____"+ ch.data);
        }
        System.out.println("Node pre "+ root.data);
    }

    public static void display(Node root){
        String str = root.data+"->" ;
        for(Node ch: root.child){
            str+=ch.data+", ";
        }
        str+=".";
        System.out.println(str);

        for (Node ch: root.child){
            display(ch);
        }
    }

    public static int size(Node node){
        int s = 0;
        for(Node ch : node.child){
            int cs = size(ch);
            s = s+cs;
        }
        s = s+1;
        return s;
    }

    public static int max(Node root){
        int max = Integer.MIN_VALUE;
        for(Node ch : root.child){
            int cm = max(ch);
            max = Math.max(cm, max);
        }
        max = Math.max(root.data, max);
        return max;
    }

    public static int height(Node node) {
        // write your code here
        int h = -1;
        for(Node chi : node.child){
            int ch = height(chi);
            h = Math.max(h, ch);
        }
        h++;
        return h;
    }

    public static void level_order(Node root){
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        while (q.size()>0){
            root = q.remove();
            System.out.println(root.data + " ");
            for (Node ch : root.child){
                q.add(ch);
            }
        }
        System.out.println(".");
    }

    public static void level_order_linewise(Node root){
        Queue<Node> mq = new ArrayDeque<>();
        mq.add(root);
        Queue<Node> cq = new ArrayDeque<>();
        while (mq.size()>0){
            root = mq.remove();
            System.out.print(root.data + " ");
            for (Node ch : root.child){
                cq.add(ch);
            }
            if (mq.size() == 0){
                mq = cq;
                cq = new ArrayDeque<>();
                System.out.println();
            }
        }

    }

    public static void level_order_linewiseZZ(Node root){
        Stack<Node> ms = new Stack<>();
        ms.push(root);

        Stack<Node> cs = new Stack<>();
        int level = 1;

        while (ms.size()>0){
            root = ms.pop();
            System.out.print(root.data + " ");

            if (level%2==1){
                for (int i = 0; i < root.child.size(); i++) {
                    cs.push(root.child.get(i));
                }
            }else {
                for (int i = root.child.size()-1; i>=0; i--) {
                    cs.push(root.child.get(i));
                }
            }

            if (ms.size() == 0){
                ms = cs;
                cs = new Stack<>();
                level++;
                System.out.println();
            }
        }

    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 50, -1, 60 -1, -1, 30, 70 , -1, 80, 110, -1, 120, -1, -1, 90, -1, -1 , 40, 100, -1, -1, -1 };
        Node root = null;
        Stack<Node> st = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==-1){
                st.pop();
            }else {
                Node t = new Node();
                t.data = arr[i];
                if(st.size()>0){
                    st.peek().child.add(t);
                }else {
                    root = t;
                }
                st.push(t);
            }
        }
//        display(root);
//        System.out.println(size(root));
//        System.out.println(max(root));
//        System.out.println(height(root));
//        traversal(root);
//          level_order(root);
        level_order_linewise(root);
//        level_order_linewiseZZ(root);
        mirror(root);
        level_order_linewise(root);
    }
}
