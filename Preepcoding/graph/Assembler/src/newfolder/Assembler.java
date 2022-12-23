package newfolder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
class Assembler {

    private HashMap<String, String> _imperativeStatements;
    private HashMap<String, String> _declarativeStatements;

    HashMap<String, String> symbolTable = new HashMap<String, String>();
    HashMap<String, String> literalTable = new HashMap<String, String>();

    HashMap<String, Integer> symbolTablePointerMapper = new HashMap<String, Integer>();
    HashMap<String, Integer> literalTablePointerMapper = new HashMap<String, Integer>();

    ArrayList<Integer> poolTable = new ArrayList<Integer>();

    Assembler() {
        initialize();
    }

    private HashMap<String, String> getMap(ArrayList<String> statements, int opcode) {
        HashMap<String, String> output = new HashMap<String, String>();

        for (String key : statements) {
            String value;

            if (opcode < 10) {
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
                Arrays.asList("STOP", "ADD", "SUB", "MULT", "MOVER", "MOVEM", "COMP", "BC", "DIV",
                        "READ", "PRINT"));
        _imperativeStatements = getMap(imperativeStatements, 0);

        ArrayList<String> declarativeStatements = new ArrayList<String>(Arrays.asList("DS", "DC"));
        _declarativeStatements = getMap(declarativeStatements, 1);
    }

    public static void main(String[] args) {
        /*
         * NOTE: The input must be in standard format, that is
         * [Label] [Opcode] [Operand]
         *
         * if label is missing the added blank space at before opcode
         *
         * There should be space between ORIGIN Or EQU operands for performing the
         * operation
         */
        Assembler assembler = new Assembler();
        assembler.passOne();
        assembler.passTwo();
    }

    // PASS ONE
    void passOne() {
        try {
            FileReader fileReader = new FileReader("input.txt");
            BufferedReader bufferReader = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter("ic.txt");
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            int locationPointer = 0;
            int symbolTablePointer = 1;
            int literalTablePointer = 1;
            int poolTablePointer = 1;
            String currentLine = bufferReader.readLine();
            String[] line = currentLine.split(" ");
            // START statement
            if (line[1].equals("START")) {
                bufferWriter.write("AD\t01\t");
                locationPointer = Integer.parseInt(line[2]);
                bufferWriter.write("C\t" + locationPointer + "\n");
            }
            while ((currentLine = bufferReader.readLine()) != null) {
                line = currentLine.split("[ ,]");
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
                    bufferWriter.write(String.valueOf(locationPointer));
                    symbolTable.put(line[0], String.valueOf(calculatedAddress));
                }
                // LTORG statement
                if (line[1].equals("LTORG")) {
                    // add the pointer in pool table
                    poolTable.add(poolTablePointer);
                    // check for literal and parse the literal and assign the location pointer to
                    // them
                    for (Entry<String, String> table : literalTable.entrySet()) {
                        if (table.getValue().isEmpty()) {
                            table.setValue(String.valueOf(locationPointer));
                            bufferWriter.write(table.getKey() + "\t" + String.valueOf(locationPointer) + "\n");
                            locationPointer++;
                            poolTablePointer++;
                            isLocationPointerSet = true;
                        }
                    }
                }
                // check for declarative statements
                for (Entry<String, String> table : _declarativeStatements.entrySet()) {
                    if (table.getKey().equals(line[1])) {
                        if (table.getKey().equals("DC")) {
                            symbolTable.put(line[0], String.valueOf(locationPointer));
                            bufferWriter.write("DL\t02\tC\t" + line[2]);
                            bufferWriter.write(String.valueOf(locationPointer));
                        } else {
                            // the statement is DS
                            int address = locationPointer + Integer.parseInt(line[2]);
                            symbolTable.put(line[0], String.valueOf(locationPointer));
                            bufferWriter.write("DL\t01\tC\t" + line[2] + "\t");
                            bufferWriter.write(String.valueOf(locationPointer));
                            locationPointer = address;
                            isLocationPointerSet = true;
                        }
                    } 
                }
                // check for imperative statements
                for (Entry<String, String> table : _imperativeStatements.entrySet()) {
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
                                bufferWriter.write("L\t" + literalTablePointer + "\t");
                                bufferWriter.write(String.valueOf(locationPointer));
                                literalTablePointerMapper.put(line[3], literalTablePointer);
                                literalTablePointer++;
                            } else {
                                // symbol is present
                                if (symbolTable.get(line[3]) == null) {
                                    symbolTable.put(line[3], "");
                                    symbolTablePointerMapper.put(line[3], symbolTablePointer);
                                    bufferWriter.write("S\t" + symbolTablePointer + "\t");
                                    bufferWriter.write(String.valueOf(locationPointer));
                                    symbolTablePointer++;
                                } else {
                                    bufferWriter.write("S\t" + symbolTablePointerMapper.get(line[3]) + "\t");
                                    bufferWriter.write(String.valueOf(locationPointer));
                                }
                            }
                        } else if (line.length > 2) {
                            if (symbolTable.get(line[2]) == null) {
                                symbolTable.put(line[2], "");
                                symbolTablePointerMapper.put(line[2], symbolTablePointer);
                                bufferWriter.write("S\t" + symbolTablePointer + "\t");
                                bufferWriter.write(String.valueOf(locationPointer));
                                symbolTablePointer++;
                            } else {
                                bufferWriter.write("S\t" + symbolTablePointerMapper.get(line[2]) + "\t");
                                bufferWriter.write(String.valueOf(locationPointer));
                            }
                        }
                    }
                }
                // END statement
                if (line[1].equals("END")) {
                    // add the pointer in pool table
                    poolTable.add(poolTablePointer);
                    // check for literal and parse the literal and assign the location pointer to
                    // them
                    for (Entry<String, String> table : literalTable.entrySet()) {
                        if (table.getValue().isEmpty()) {
                            table.setValue(String.valueOf(locationPointer));
                            bufferWriter.write(table.getKey() + "\t" + locationPointer + "\n");
                            locationPointer++;
                            isLocationPointerSet = true;
                        }
                    }
                    bufferWriter.write("AD\t05\t");
                    bufferWriter.write(String.valueOf(locationPointer));
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
             */
            FileWriter symbolTableFile = new FileWriter("symbolTable.txt");
            BufferedWriter bufferSymbolTableWriter = new BufferedWriter(symbolTableFile);
            System.out.println("------------- Symbol Table --------------");
            for (Entry<String, String> table : symbolTable.entrySet()) {
                bufferSymbolTableWriter.write(table.getKey() + "\t" + table.getValue() + "\n");
                System.out.println(table.getKey() + "\t" + table.getValue());
            }
            bufferSymbolTableWriter.close();
            /*
             * Write the literal table into the file
             */
            FileWriter literalTableFile = new FileWriter("literalTable.txt");
            BufferedWriter bufferLiteralTableWriter = new BufferedWriter(literalTableFile);
            System.out.println("------------- Literal Table --------------");
            for (Entry<String, String> table : literalTable.entrySet()) {
                bufferLiteralTableWriter.write(table.getKey() + "\t" + table.getValue() + "\n");
                System.out.println(table.getKey() + "\t" + table.getValue());
            }
            bufferLiteralTableWriter.close();
            /*
             * Write the pool table into the file
             */
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

    // PASS TWO
    void passTwo() {
        try {

            FileWriter machineCodeFile = new FileWriter("machineCode.txt");
            BufferedWriter bufferMachineCodeWriter = new BufferedWriter(machineCodeFile);

            FileReader intermediateCodeFile = new FileReader("ic.txt");
            BufferedReader bufferIntermediateFileReader = new BufferedReader(intermediateCodeFile);

            String currentLine = bufferIntermediateFileReader.readLine();

            while ((currentLine = bufferIntermediateFileReader.readLine()) != null) {
                if (currentLine.isEmpty())
                    continue;

                String[] line = currentLine.split("\t");

                if (Objects.equals(line[0], "IS")) {
                    bufferMachineCodeWriter.write(line[1] + "\t");
                    bufferMachineCodeWriter.write(line[2] + "\t");

                    if (line[3].equals("S")) {
                        for (Entry<String, Integer> table : symbolTablePointerMapper.entrySet()) {
                            if (table.getValue().equals(Integer.parseInt(line[4]))) {
                                bufferMachineCodeWriter.write(symbolTable.get(table.getKey()));
                            }
                        }
                    } else if (line[3].equals("L")) {
                        for (Entry<String, Integer> table : literalTablePointerMapper.entrySet()) {
                            if (table.getValue().equals(Integer.parseInt(line[4]))) {
                                bufferMachineCodeWriter.write(literalTable.get(table.getKey()));
                            }
                        }
                    }

                    bufferMachineCodeWriter.newLine();
                }

                if (line[0].contains("=")) {
                    bufferMachineCodeWriter.write(literalTable.get(line[0]));
                    bufferMachineCodeWriter.newLine();
                }
            }

            bufferIntermediateFileReader.close();
            bufferMachineCodeWriter.close();

            FileReader machineCodeFileReader = new FileReader("machineCode.txt");
            BufferedReader bufferMachineCodeReader = new BufferedReader(machineCodeFileReader);

            System.out.println("--------- Machine Code ----------");

            while ((currentLine = bufferMachineCodeReader.readLine()) != null) {
                System.out.println(currentLine);
            }

            bufferMachineCodeReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
