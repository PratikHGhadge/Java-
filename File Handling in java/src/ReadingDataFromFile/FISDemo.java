package ReadingDataFromFile;

import java.io.*;

public class FISDemo {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\Admin\\Desktop\\java\\File Handling in java\\src\\ReadingDataFromFile\\abc.txt");
        int data = fis.read();
        int data1 = fis.read();
        int data2 = fis.read();
        System.out.println("Data1 : "+data+" - "+ (char)data);
        System.out.println("Data2 : "+data1+" - "+ (char)data1);
        System.out.println("Data3 : "+data2+" - "+ (char)data2);
        fis.close();
    }
}
