package WritingDataIntoFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FOSDemo {
    public static void main(String[] args)throws IOException, FileNotFoundException {
        FileOutputStream fos = new FileOutputStream("bbc.txt");
        fos.write(5);;
        System.out.println("data is saved ");
    }
}
