package hiii;
// Crating our thread using runnable interface
public class MyThread implements Runnable{
    public void run(){
        // task for thread
        for (int i = 1; i <= 10; i++) {
            System.out.println("Value of i is "+ i);
            try
            {
                Thread.sleep(1000);
            }
            catch (Exception e){

            }
        }
    }
}
