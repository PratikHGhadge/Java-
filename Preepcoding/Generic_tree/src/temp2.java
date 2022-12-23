import java.util.HashMap;
import java.util.Scanner;

public class temp2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int X = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(noOfMembers(arr, X));
    }

    public static int noOfMembers(int[] arr, int x){
        int n = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<arr.length; i++){
            if(map.containsKey(arr[i])){
                map.put(arr[i], map.get(arr[i])+1);
            }else{
                map.putIfAbsent(arr[i], 0);
            }
        }
        for(Integer key : map.keySet()){
            if(map.get(key)>=x){
                n++;
            }
        }
        return n;
    }
}
