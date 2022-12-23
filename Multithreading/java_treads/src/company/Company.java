package company;

public class Company {
    int n;
    boolean f = false;
    synchronized public void produce_item(int val){
        if (f){
            try {
                wait();
            }catch (Exception e){}
        }
        this.n = val;
        System.out.println("Produced " + this.n);
        f=true;
        notify();
    }
    synchronized public int consume_item(){
        if (!f){
            try {
                wait();
            }catch (Exception e){}
        }
        System.out.println("consumed : " +this.n);
        f=false;
        notify();
        return this.n;
    }
}
