package ReadingDataFromFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FISDemo2 {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream("bbc.txt");
        int data;
        while ((data=fis.read())!=-1){
            System.out.println("Data1 : "+data+" - "+ (char)data);
        }
        fis.close();
    }
}
