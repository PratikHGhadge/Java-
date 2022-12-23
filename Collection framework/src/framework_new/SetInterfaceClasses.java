package framework_new;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetInterfaceClasses {
    public static void main(String[] args) {
        int count[] = {1000, 900, 800, 125, 650};

        //HashSet – stores its elements in a hash table, it is the best-performing implementation out of all,
        // but insertion order is not maintained here.
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < 5; i++) {
            set.add(count[i]);
        }
        System.out.println(set);

        //TreeSet - stores its elements in a red-black tree, orders its elements based on their values;
        // it is substantially slower than HashSet.
        TreeSet<Integer> sortedSet = new TreeSet<>();
        for (int i = 0; i < 5; i++) {
            sortedSet.add(count[i]);
        }
        System.out.println("The sorted list is:");
        System.out.println(sortedSet);
        System.out.println("The First element of the set is: "+ (Integer)sortedSet.first());
        System.out.println("The last element of the set is: "+ (Integer)sortedSet.last());



        //LinkedHashSet – implemented as a hash table with a linked list running through it,
        // orders its elements based on the order in which they
        // were inserted into the set (insertion-order).
        Set<Integer> Lset = new LinkedHashSet<>();
        Lset.add(32);
        Lset.add(2);
        Lset.add(54);
        Lset.add(21);
        Lset.add(65);
        System.out.println(set);
        set.remove(54);
        System.out.println(set);
        System.out.println(set.contains(21));
        System.out.println(set.isEmpty());
        System.out.println(set.size());
        set.clear();
        System.out.println(set);
    }
}
