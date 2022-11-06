package Uke43;

import Eksempelklasser.SBinTre;

import java.util.Comparator;
import java.util.stream.Stream;

public class Program5_2_3 {
    public static void main(String[] args) {
        // 5.2.3
        System.out.println("5.2.3\n---------------------------------------");

        // Venstre tre:
        Character[] a = {'I', 'E', 'O', 'A', 'G', 'M', 'C', 'F', 'H', 'K', 'N', 'B', 'D', 'J', 'L'};      // en tabell

        SBinTre<Character> tre = SBinTre.sbintre(Stream.of(a));    // et binært søketre

        System.out.println("\nOppgave 3:");
        System.out.println("Antall: " + tre.antall());
        System.out.println("Høyde: " + tre.høyde());
        System.out.println(tre);

        // Høyre tre:
        Integer[] b = {8,2,10,1,6,9,14,4,7,12,15,3,5,11,13};
        SBinTre<Integer> tre2 = SBinTre.sbintre(Stream.of(b));

        System.out.println();
        System.out.println("Antall: " + tre2.antall());
        System.out.println("Høyde: " + tre2.høyde());
        System.out.println(tre2);

        //Oppgave 4:
        System.out.println("\n\nOppgave 4:");

        Character[] c = {'H','J','C','F','D','M','A','I','E','K','G','L','B'};
        SBinTre<Character> treA = SBinTre.sbintre(Stream.of(c));
        System.out.println(treA);

        Character[] d = {'E','H','B','E','G','F','D','I','H','A','E','C'};
        SBinTre<Character> treB = SBinTre.sbintre(Stream.of(d));
        System.out.println(treB);

        Integer[] e = {4, 1, 8, 5, 3, 10, 7, 2, 6, 9};
        SBinTre<Integer> treC = SBinTre.sbintre(Stream.of(e));
        System.out.println(treC);

        Integer[] f = {9, 4, 17, 12, 15, 1, 8, 10, 2, 5, 4, 20, 11, 6, 16, 9};
        SBinTre<Integer> treD = SBinTre.sbintre(Stream.of(f));
        System.out.println(treD);

        String[] s = {"Sohil","Per","Thanh","Fatima","Kari","Jasmin"};
        SBinTre<String> treE = SBinTre.sbintre(Stream.of(s));
        System.out.println(treE);

        Integer[] x = {10, 5, 20, 10, 3, 8, 13, 18, 7, 5, 6, 12, 4, 9, 11, 10, 22};
        SBinTre<Integer> treF = SBinTre.sbintre(Stream.of(x));
        System.out.println(treF);
    }
}
