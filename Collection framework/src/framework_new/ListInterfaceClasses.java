package framework_new;

import java.util.ArrayList;
import java.util.List;

public class ListInterfaceClasses {
    public static void main(String[] args) {
        List<String> al = new ArrayList<>();

        al.add("item 1");
        al.add("item 2");
        al.add(1, "item 3");

        System.out.println(al);
    }
}
