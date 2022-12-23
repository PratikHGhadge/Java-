package hiii;

public class Main {

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        Thread thr = new Thread(t1);
        MyAnotherThread t2 = new MyAnotherThread();
        thr.start();
        t2.start();
    }
}
