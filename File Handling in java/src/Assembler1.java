import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

class Assembler {
    private HashMap<String, String> Ip;
    private HashMap<String, String> DS;

    Assembler() {
        initialize();
    }

    private HashMap<String, String> getMap(ArrayList<String> statements, int opcode) {
        HashMap<String, String> output = new HashMap<String, String>();
        for (String key : statements) {
            String value;
            if (opcode < 9) {
                value = "0" + opcode;
            } else {
                value = Integer.toString(opcode);
            }
            output.put(key, value);
            opcode++;
        }
        return output;
    }

    private void initialize() {
        final ArrayList<String> imperativeStatements = new ArrayList<String>(
                Arrays.asList("STOP", "ADD", "SUB", "MULT", "MOVER", "MOVEM", "COMP",
                        "BC", "DIV",
                        "READ", "PRINT"));
        Ip = getMap(imperativeStatements, 0);
        ArrayList<String> declarativeStatements = new
                ArrayList<String>(Arrays.asList("DS", "DC"));
        DS = getMap(declarativeStatements, 1);
    }

    public static void main(String[] args) {
/*
* NOTE: The input must be in standard format, that is
* [Label] [Opcode] [Operand]
*
* if label is missing the added blank space at before opcode
*
* There should be space between ORIGIN Or EQU operands for performing the
operation
* */
        Assembler assembler = new Assembler();
        assembler.passOne();
    }

    // PASS ONE
    void passOne() {
        try {
            FileReader fileReader = new FileReader("C:\\Users\\Admin\\Desktop\\java\\File Handling in java\\src\\input.txt");
            BufferedReader bufferReader = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter("ic.txt");
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            HashMap<String, String> symbolTable = new HashMap<String, String>();
            HashMap<String, Integer> symbolTablePointerMapper = new HashMap<String,
                    Integer>();
            HashMap<String, String> literalTable = new HashMap<String, String>();
            ArrayList<Integer> poolTable = new ArrayList<Integer>();
            int locationPointer = 0;
            int symbolTablePointer = 1;
            int literalTablePointer = 1;
            int poolTablePointer = 1;
            String currentLine = bufferReader.readLine();
            String[] line = currentLine.split("\\s+");
// START statement
            if (line[1].equals("START")) {
                bufferWriter.write("AD\t01\t");
                locationPointer = Integer.parseInt(line[2]);
                bufferWriter.write("C\t" + locationPointer + "\n");
            }
            while ((currentLine = bufferReader.readLine()) != null) {
                line = currentLine.split(" |\\s+");
                boolean isLocationPointerSet = false;
// label is present
                if (!line[0].isEmpty()) {
                    symbolTable.put(line[0], String.valueOf(locationPointer));
                    if (symbolTable.get(line[0]) == null) {
                        symbolTablePointerMapper.put(line[0], symbolTablePointer);
                        symbolTablePointer++;
                    }
                }
// ORIGIN is present
                if (line[1].equals("ORIGIN")) {
// update the location counter accordingly
                    int address = Integer.parseInt(symbolTable.get(line[2]));
                    if (line[3].equals("+")) {
                        address += Integer.parseInt(line[4]);
                    } else {
                        address -= Integer.parseInt(line[4]);
                    }
                    bufferWriter.write("AD\t03\t");
                    bufferWriter.write("C\t" + address);
                    locationPointer = address;
                    isLocationPointerSet = true;
                }
// EQU statement
                if (line[1].equals("EQU")) {
                    int calculatedAddress = Integer.parseInt(symbolTable.get(line[2]));
// check for + operator if not them it is -
                    if (line[3].equals("+")) {
                        calculatedAddress += Integer.parseInt(line[4]);
                    } else {
                        calculatedAddress -= Integer.parseInt(line[4]);
                    }
                    bufferWriter.write("AD\t04\t");
                    symbolTable.put(line[0], String.valueOf(calculatedAddress));
                }
// LTORG statement
                if (line[1].equals("LTORG")) {
// add the pointer in pool table
                    poolTable.add(poolTablePointer);
// check for literal and parse the literal and assign the location pointer to them
                    for (Entry<String, String> table : literalTable.entrySet()) {
                        if (table.getValue().isEmpty()) {
                            table.setValue(String.valueOf(locationPointer));
                            bufferWriter.write(table.getKey() + "\n");
                            locationPointer++;
                            poolTablePointer++;
                            isLocationPointerSet = true;
                        }
                    }
                }
// check for declarative statements
                for (Entry<String, String> table : DS.entrySet()) {
                    if (table.getKey().equals(line[1])) {
                        if (table.getKey().equals("DC")) {
                            symbolTable.put(line[0], String.valueOf(locationPointer));
                            bufferWriter.write("DL\t02\tC\t" + line[2]);
                        } else {
// the statement is DS
                            int address = locationPointer + Integer.parseInt(line[2]);
                            symbolTable.put(line[0], String.valueOf(locationPointer));
                            locationPointer = address;
                            isLocationPointerSet = true;
                            bufferWriter.write("DL\t01\tC\t" + line[2]);
                        }
                    }
                }
// check for imperative statements
                for (Entry<String, String> table : Ip.entrySet()) {
                    if (table.getKey().equals(line[1])) {
                        bufferWriter.write("IS\t" + table.getValue() + "\t");
                        if (line.length > 2) {
// check for register and generate the IC for them
                            switch (line[2]) {
                                case "AREG" -> bufferWriter.write("1\t");
                                case "BREG" -> bufferWriter.write("2\t");
                                case "CREG" -> bufferWriter.write("3\t");
                                case "DREG" -> bufferWriter.write("4\t");
                            }
                        }
                        if (line.length > 3) {
// if literal
                            if (line[3].contains("=")) {
                                literalTable.put(line[3], "");
                                bufferWriter.write("L\t" + literalTablePointer);
                                literalTablePointer++;
                            } else {
// symbol is present
                                if (symbolTable.get(line[3]) == null) {
                                    symbolTable.put(line[3], "");
                                    symbolTablePointerMapper.put(line[3],
                                            symbolTablePointer);
                                    bufferWriter.write("S\t" + symbolTablePointer);
                                    symbolTablePointer++;
                                } else {
                                    bufferWriter.write("S\t" +
                                            symbolTablePointerMapper.get(line[3]));
                                }
                            }
                        }
                    }
                }
// END statement
                if (line[1].equals("END")) {
// add the pointer in pool table
                    poolTable.add(poolTablePointer);
// check for literal and parse the literal and assign the location pointer to them
                    for (Entry<String, String> table : literalTable.entrySet()) {
                        if (table.getValue().isEmpty()) {
                            table.setValue(String.valueOf(locationPointer));
                            bufferWriter.write(table.getKey() + "\n");
                            locationPointer++;
                            isLocationPointerSet = true;
                        }
                    }
                    bufferWriter.write("AD\t05");
                }
// add the new line at end of the line
                bufferWriter.newLine();
// if location pointer is not assigned above the increment the location pointer
                if (!isLocationPointerSet) {
                    locationPointer++;
                }
            }
// close the reader and writer file above
            bufferWriter.close();
            bufferReader.close();







            /*
             * Write the symbol table into the file
             * */
            FileWriter symbolTableFile = new FileWriter("symbolTable.txt");
            BufferedWriter bufferSymbolTableWriter = new
                    BufferedWriter(symbolTableFile);
            System.out.println("------------- Symbol Table --------------");
            for (Entry<String, String> table : symbolTable.entrySet()) {
                bufferSymbolTableWriter.write(table.getKey() + "\t" + table.getValue()
                        + "\n");
                System.out.println(table.getKey() + "\t" + table.getValue());
            }
            bufferSymbolTableWriter.close();
            /*
             * Write the literal table into the file
             * */
            FileWriter literalTableFile = new FileWriter("literalTable.txt");
            BufferedWriter bufferLiteralTableWriter = new
                    BufferedWriter(literalTableFile);
            System.out.println("------------- Literal Table --------------");
            for (Entry<String, String> table : literalTable.entrySet()) {
                bufferLiteralTableWriter.write(table.getKey() + "\t" + table.getValue()
                        + "\n");
                System.out.println(table.getKey() + "\t" + table.getValue());
            }
            bufferLiteralTableWriter.close();
            /*
             * Write the pool table into the file
             * */
            FileWriter poolTableFile = new FileWriter("poolTable.txt");
            BufferedWriter bufferPoolTableWriter = new BufferedWriter(poolTableFile);
            System.out.println("------------- Pool Table --------------");
            for (Integer integer : poolTable) {
                bufferPoolTableWriter.write(integer + "\n");
                System.out.println(integer);
            }
            bufferPoolTableWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
 