package PESELpackage;

public class PESELclass {

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
        return resta == arrayPesel[arrayPesel.length-1];
        //System.out.println("Son iguales? "+comp);
    }


    public boolean peselCheck (String pesel) {
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

}
