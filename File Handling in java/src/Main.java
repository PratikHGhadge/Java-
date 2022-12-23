import java.awt.*;
import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception{
	// write your code here
        File f1 = new File("C:\\Users\\Admin\\Desktop\\java\\File Handaling in java\\name1.txt");
        System.out.println("Is exists :" + f1.exists());
        f1.createNewFile();
        System.out.println("Is exists :" + f1.exists());
        System.out.println(f1.length());
        System.out.println(f1.canRead());
        System.out.println(f1.canWrite());
        System.out.println(f1.getName());
        System.out.println(f1.delete());
        System.out.println("Is exists :" + f1.exists());
    }
}
