package hiii;

public class ThreadOp {
    public static void main(String[] args) {
        System.out.println("Program Started.....");
        int x = 56+34;
        System.out.println("sum is " + x);
        // Thread
        Thread t = Thread.currentThread();
        System.out.println("Current running thread is "+ t.getName());
        t.setName("Pratik");
        try{Thread.sleep(5000);}catch (Exception e){}
        System.out.println("Current running thread is "+ t.getName());
        System.out.println("Id of Current running thread is "+ t.getId());

        MyAnotherThread t2 = new MyAnotherThread();
        t2.start();

        System.out.println("Program ended.....");
    }
}
