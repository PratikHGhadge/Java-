package Pratik;
import LambdaExpression.MyInter;
public class Main implements MyInter {
    @Override
    public void sayHello() {
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        // write your code here
        // direct implementation of interface on our working class
        Main obj = new Main();
        obj.sayHello();   // say hello method is from MyInter interface

        // Interface using anonymous class
        MyInter i = new MyInter() {
            @Override
            public void sayHello() {
                System.out.println("hello world.......");
            }
        };
        i.sayHello();
        MyInter i2 = new MyInter() {
            @Override
            public void sayHello() {
                System.out.println("hello 2.....");
            }
        };
        i2.sayHello();

        // Using our interface with the help of lambada
        MyInter i4 = () -> {
            System.out.println("this is first time i am using lambada");
        };
        i4.sayHello();
        // or
        MyInter i5 = () -> System.out.println("hello again !");
        i5.sayHello();

    }
}
