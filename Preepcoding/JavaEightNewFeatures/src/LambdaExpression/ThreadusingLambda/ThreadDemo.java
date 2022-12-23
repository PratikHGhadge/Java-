package LambdaExpression.ThreadusingLambda;

public class ThreadDemo {
    public static void main(String[] args) {
        // First Tread: Thread - Pratik

        Runnable thread1 = () -> {
            // this is body of the thread
            //stuff
            for (int i = 0; i < 10; i++) {
                System.out.println("value of i is : " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t = new Thread(thread1);
        t.setName("Pratik");
        t.start();
    }
}
