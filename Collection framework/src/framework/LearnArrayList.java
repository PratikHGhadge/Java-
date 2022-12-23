package framework;

import java.util.ArrayList;
import java.util.Iterator;

public class LearnArrayList {
    public static void main(String[] args) {
//
//        ArrayList<String> name = new ArrayList<>();
//
//        name.add("Pratik");
//        name.add("Pratik");
//        name.add("Pratik");
//        name.add("Pratik");
//        name.add("abcdefg");
//        name.add("Pratik");
//        name.add("Pratik");
//        name.add("Pratik");
//        name.add("Pratik");
//        name.add(4, "ghadge");
//        for(String a : name){
//            System.out.println(a);
//        }
//        //        name.remove(4);
////        System.out.println(name.contains("Pratik"));
////
////        System.out.println(name.get(4));








        ArrayList<Integer> list = new ArrayList<>();
        list.add(50);
        list.add(55);
        list.add(52);
        list.add(57);
        list.add(59);

        list.add(58);
        list.add(57);
        list.add(56);

        Iterator<Integer> it = list.iterator();

        while (it.hasNext()){
            System.out.println(" iterator "+ it.next());
        }
    }
}
