package LambdaExpression;
public class MyInterImpJava {
    public static void main(String[] args) {
        // Using our interface with the help of lambada
        SumInter i = (int a, int b)->{
            System.out.print(a+" + "+b+" = "+ (a+b) );
        };
        i.sum(5, 7);

        // 2nd syntax
        SumInter i2 = (a, b) -> System.out.print("\n"+a+" + "+b+" = "+ (a+b));
        i2.sum(5,5);
    }
}
