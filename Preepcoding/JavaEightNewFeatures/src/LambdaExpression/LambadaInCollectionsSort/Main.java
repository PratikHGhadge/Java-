package LambdaExpression.LambadaInCollectionsSort;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void test1(){
        Integer[] arr = {2,5,1,-1,0,5,3,2,-4,3};
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(arr));
        Collections.sort(list);
        System.out.println(list);
    }

    public static void main(String[] args) {
      test1();
    }
}
