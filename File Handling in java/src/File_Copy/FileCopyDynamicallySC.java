package File_Copy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileCopyDynamicallySC {
    public static void main(String[] args)throws FileNotFoundException, IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter source file name");
        String srcFile = sc.nextLine();
        System.out.println("Enter destination file name");
        String destFile = sc.nextLine();
        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(destFile);
        int data;
        while ((data=fis.read())!=-1){
            fos.write(data);
        }
        System.out.println("File copied ");
    }
}