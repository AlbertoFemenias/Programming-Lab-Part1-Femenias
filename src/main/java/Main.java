import PESELpackage.PESELclass;
import StreamUsage.StreamClass;
import org.xerial.snappy.Snappy;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;


// MI PESEL ES: 97061000005
//              9706100A005
//              12300000000
//              52090557562


public class Main {


    private static void writeFile(List<String> text, String path) {
        try {
            FileWriter fw = new FileWriter(path, false);
                PrintWriter pw = new PrintWriter(fw);
                for (String line : text){
                    pw.println(line);
                }
                pw.close();
        }
        catch (IOException e) {
                System.out.println("Error writing the file!");
        }
    }




    public static void main(final String... args) throws IOException {

        System.out.print("Hello world, please enter your PESEL number to continue: ");

        PESELclass peselOperator = new PESELclass();
        Scanner input = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        String pesel;
        String lineNew;

        while(true){
            pesel = input.nextLine();
            if (!peselOperator.peselCheck(pesel)) {
                System.out.print("Your PESEL is incorrect, please try again: ");
            }else{
                break;
            }
        }

        System.out.println("You have entered your: "+pesel+" PESEL succesfully!");
        System.out.println("Please, input some text that will be exported as a file:");


        while (input.hasNextLine()) {
            lineNew = input.nextLine();
            if (lineNew.isEmpty()) {
                break;
            }
            lines.add(lineNew);
        }

        System.out.println();

        String path = "/Users/alberto/Downloads/Programming-Lab-Part1-Femenias/output.txt";
        writeFile(lines, path);
        System.out.println(path+" has been generated");

        System.out.println();
        System.out.println("Now I am going to use some Streams");
        System.out.println("You entered (lines ordered alphabetically):");
        StreamClass streamnator = new StreamClass();
        streamnator.orderPrint(lines);
        System.out.println();

        System.out.print("I have a stack of PESEL numbers:  ");
        Stack<Long> PESELstack = new Stack();
        List<Long> inputList = Arrays.asList(97061000005L, 12300000000L, 52090557562L, 76090453562L, 56090233562L);
        PESELstack.addAll(inputList);
        System.out.println(Arrays.toString(PESELstack.toArray()));

        System.out.println("These are the ones from the stack that start with 5:");
        for (Long n : streamnator.createList(PESELstack, 5)){
            System.out.print(n+" ");
        }
        System.out.println();
        System.out.println();


        System.out.println("Now I am going to use a Maven libray");
        System.out.println("Using snappy library to compress your text");
        //HERE I USE A COMPRESSION LIBRARY TO COMPRESS THE LINES
        StringBuilder text = new StringBuilder();
        String linesAppended = String.valueOf(text.append(lines).append("\n"));
        byte[] compressed = Snappy.compress(linesAppended.getBytes(StandardCharsets.UTF_8));
        for (byte b : compressed){
            System.out.print(b+" ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Compression done. Nothing else to do, exisiting program");


    }
}











   /*      EXAMPLE OF USAGE OF THE COMPRESSION LIBRARY
        String input = "Hello snappy-java! Snappy-java is a JNI-based wrapper of "
                + "Snappy, a fast compresser/decompresser.";

        byte[] compressed = Snappy.compress(input.getBytes("UTF-8"));
        byte[] uncompressed = Snappy.uncompress(compressed);

        String result = new String(uncompressed, "UTF-8");
        String comprimio = new String(compressed, "UTF-8");
        System.out.println(result);
        System.out.println(comprimio);

    */
