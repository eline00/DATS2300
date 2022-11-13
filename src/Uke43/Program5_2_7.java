package Uke43;

import Eksempelklasser.SBinTre;

import java.util.stream.Stream;

public class Program5_2_7 {
    public static void main(String[] args) {
        Integer[] a = {5,10,3,8,13,7,16,2,6,11};

        SBinTre<Integer> tre = SBinTre.sbintre(Stream.of(a));  // Programkode 5.2.3 c)

        System.out.println(tre.gulv(10)); // Utskrift: 10
        System.out.println(tre.gulv(9));  // Utskrift: 8
        System.out.println(tre.gulv(1));  // Utskrift: null
        System.out.println();
        System.out.println(tre.større(10));  // Utskrift: 11
        System.out.println(tre.større(12));  // Utskrift: 13
        System.out.println(tre.større(16));  // Utskrift: null
        System.out.println();
        System.out.println(tre.maks());     // Utskrift: 16
        System.out.println();
        System.out.println(tre.tak(8));     // Utskrift: 8
        System.out.println(tre.tak(4));     // Utskrift: 5
        System.out.println(tre.tak(17));    // Utskrift: null
        System.out.println();
        System.out.println(tre.mindre(4));      // Utskrift: 3
        System.out.println(tre.mindre(5));      // Utskrift: 3
        System.out.println(tre.mindre(2));      // Utskrift: null
    }
}
