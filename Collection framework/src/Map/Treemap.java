package Map;
import java.util.*;
public class Treemap {
    public static void main(String[] args) {
        TreeMap<Integer,String> map = new TreeMap<>();
        map.put(1000, "Tom");
        map.put(2000, "peter");
        map.put(3000, "steve");
        map.put(11000, "Naveen");
        map.put(500, "Robby");

        System.out.println(map);

        map.forEach((k, v)->{
            System.out.println("key = " + k + " value = " + v);
        });

        System.out.println(map.lastKey());
        System.out.println(map.firstKey());
        Set<Integer> keyslessthan2k = map.headMap(3000).keySet();
        System.out.println(keyslessthan2k);
    }
}
