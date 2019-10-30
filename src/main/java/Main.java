import org.xerial.snappy.Snappy;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// MI PESEL ES: 97061000005
//              9706100A005
//              12300000000
//              52090557562

// FALTA PASAR LO DEL PESEL A UNA CLASE Y HACERLE LOS TESTS,
// CREAR UNA CLASE QUE EJECUTE STREAMS (UNO CON RETORNO DE ESTRUCTURA Y OTRO SIN) Y SUS TESTS
//EJECUTAR LOS STREAMS DE FORMA ARTIFICIAL
// DEJAR PARA EL FINAL LO DE HACER LOS TESTS!!!

public class Main {


    private static boolean checkSumCheck (String pesel) {

        //stream to get ints from String
        int[] arrayPesel = pesel.chars().map(Character::getNumericValue).toArray();
        int suma = 0;
        int[] factrs ={1,3,7,9,1,3,7,9,1,3};
        for (int i = 0; i < factrs.length; i++) {
            suma = suma + arrayPesel[i] * factrs[i];
        }
        //System.out.println("la cuenta es "+suma);
        int mod = suma % 10;
        //System.out.println("modulo 10 es "+mod);
        int resta = 10-mod;
        //System.out.println("10 - " +mod+" = " + resta );
        //System.out.println("el utlimo digito es "+arrayPesel[arrayPesel.length-1]);
        boolean comp = resta == arrayPesel[arrayPesel.length-1];
        //System.out.println("Son iguales? "+comp);
        return comp;
    }


    private static boolean peselCheck (String pesel) {
        if (pesel.length()==11) {
            try {
                Long.parseLong(pesel); //to check if it is a number
            }
            catch (NumberFormatException e)
            {
                System.out.println("NOT A NUMEBER!!");
                return false;
            }

            boolean comp = checkSumCheck(pesel);
            if(comp){
                return true;
            }else{
                System.out.println("YOUR PESEL NUMBER DOES NOT PASS CHECKSUM INSPECTION!!!");
                return false;
            }
        }else{
            System.out.println("NOT 11 DIGITS!!");
            return false;
        }

    }



    private static void writeFile(List<String> text, String path) {
        try {
            FileWriter fw = new FileWriter(path, true);
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

        Scanner input = new Scanner(System.in);
        List<String> lines = new ArrayList<String>();
        String pesel = null;
        String lineNew;

        while(true){
            pesel = input.nextLine();
            if (!peselCheck(pesel)) {
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

        System.out.println("");

        String path = "/Users/alberto/Downloads/Programming-Lab-Part1-Femenias/output.txt";
        writeFile(lines, path);
        System.out.println(path+" has been generated");


        System.out.println("Using snappy library to compress your text");
        //HERE I USE A COMPRESSION LIBRARY TO COMPRESS THE LINES
        StringBuilder text = new StringBuilder();
        String linesAppended = String.valueOf(text.append(lines).append("\n"));
        byte[] compressed = Snappy.compress(linesAppended.getBytes("UTF-8"));
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
