package File_Copy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
    public static void main(String[] args)throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream("bbc.txt");
        FileOutputStream fos = new FileOutputStream("abc.txt");
        int data;
        while ((data=fis.read())!=-1){
            fos.write(data);
        }
        System.out.println("File copied ");
    }
}
