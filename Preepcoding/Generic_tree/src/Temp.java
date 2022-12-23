import java.util.Stack;

public class Temp {
    public static void main(String[] args) {
//        System.out.println(removeStars("leet**cod*e"));
        System.out.println(backspaceCompare("ab#c", "ad#c"));
    }

    public static boolean backspaceCompare(String s, String t) {
        StringBuilder st1 = new StringBuilder();
        for(int i = 0; i<s.length(); i++){
            if(s.charAt(i)=='#'){
                if(i!=0){
                    st1.deleteCharAt(st1.length() - 1);
                }
            }else{
                st1.append(s.charAt(i));
            }
        }
        StringBuilder st2 = new StringBuilder();
        for(int i = 0; i<s.length(); i++){
            if(s.charAt(i)=='#'){
                if(i!=0){
                    st2.deleteCharAt(st2.length() - 1);
                }
            }else{
                st2.append(t.charAt(i));
            }
        }
        return st1.equals(st2);
    }


    public static String removeStars(String s) {
        Stack<Character> st = new Stack();
        for(int i = 0; i<s.length(); i++){
            if(s.charAt(i)=='*'){
                st.pop();
            }else{
                st.push(s.charAt(i));
            }
        }
        StringBuilder ans = new StringBuilder();
        int size = st.size();
        for(int i = 0; i<size; i++){
            ans.append(st.pop());
        }
        return ans.reverse().toString();
    }
}
