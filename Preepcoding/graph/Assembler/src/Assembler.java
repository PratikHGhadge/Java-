import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Assembler {
    HashMap<String, String> Ip = new HashMap<>();
    HashMap<String , String> Ds = new HashMap<>();
    HashMap<String, String> symbolTable = new HashMap<>();
    HashMap<String, String> literalTable = new HashMap<>();
    ArrayList<Integer> poolTable = new ArrayList<>();
    Assembler(){
        initiolize();
    }
    private void initiolize(){
        Ip.put("STOP", "01");
        Ip.put("ADD", "02");
        Ip.put("SUB", "03");
        Ip.put("MULT", "04");
        Ip.put("MOVER", "05");
        Ip.put("MOVEM", "06");
        Ip.put("COMP", "07");
        Ip.put("BC", "08");
        Ip.put("DIV", "09");
        Ip.put("READ", "10");
        Ip.put("PRINT", "11");
        Ds.put("DS", "01");
        Ds.put("DC", "02");
    }
    private void pass1(){
        try{
            FileReader fr = new FileReader("input.txt");
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter("Ic.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            int locationPointer = 0;
            int sp = 1;
            int lp = 1;
            int pp = 1;

            String currentLine = br.readLine();
            String[] line = currentLine.split(" ");
            // for start
            if(line[1].equals("START")){
                bw.write("AD\t01\tC\t"+line[2]+"\n");
                locationPointer = Integer.parseInt(line[2]);
            }
            while((currentLine=br.readLine())!=null){
                line = currentLine.split("[ ,]");
                boolean isLocationPointerSet = false;
                // if lable is present
                if(!line[0].isEmpty()){
                    symbolTable.put(line[0], ""+locationPointer);
                }
                //ORIGIN IS PRESENT
                if (line[1].equals("ORIGIN")){
                    // update loction pointer
                    int address = Integer.parseInt(symbolTable.get(line[2]));
                    if (line[3].equals("+")){
                        address = address + Integer.parseInt(line[4]);
                    }else {
                        address = address - Integer.parseInt(line[4]);
                    }
                    bw.write("AD\t03\tC\t"+address);
                    locationPointer = address;
                    isLocationPointerSet = true;
                }
                //Equ is Prasent
                if (line[1].equals("EQU")){
                    int address = Integer.parseInt(symbolTable.get(line[2]));
                    if (line[3].equals("+")){
                        address = address + Integer.parseInt(line[4]);
                    }else {
                        address = address - Integer.parseInt(line[4]);
                    }
                    bw.write("AD\t04\t"+address);
                    symbolTable.put(line[0], ""+address);
                }
                //If LTORG OCCURS
                if (line[1].equals("LTORG")){

                }












                br.readLine();
                if (!isLocationPointerSet){
                    locationPointer++;
                }
            }



        }catch (IOException e){
            System.out.println(e);
        }
    }
    public static void main(String[] args){
        Assembler obj = new Assembler();
        obj.pass1();
    }
}
