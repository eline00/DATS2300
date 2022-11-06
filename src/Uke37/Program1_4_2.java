package Uke37;

import java.util.Arrays;

public class Program1_4_2 {
    public static void main(String[] args) {
        System.out.println("\n1.4.2:");
        String[] q = {"Sohil","Per","Thanh","Fatima","Kari","Jasmin"};
        int p = Tabeller.Tabell.maks(q);        // hvilken maks-metode?
        System.out.println(q[p]);      // Utskrift:  Thanh

        String[] w = {"Per","Kari","Ole","Anne","Ali","Eva"};
        Tabeller.Tabell.innsettingssortering(w);
        System.out.println(Arrays.toString(w));  // [Ali, Anne, Eva, Kari, Ole, Per]
    }
}
