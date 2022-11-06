package Uke42;

import Eksempelklasser.BinTre;
import Grensesnitt.Oppgave;

import java.util.StringJoiner;

public class Program5_1_7 {
    public static void main(String[] args) {
        System.out.println("5.1.7\n--------------------------------------------");

        // Oppgave 5:
        System.out.println("Oppgave 5:");
        BinTre<Character> tre = new BinTre<>();   // et tomt tre

        String v = "ABICDJLEFKMGHNO";  // verdiene i nivåorden
        int[] p = {1,2,3,4,5,6,7,10,11,13,14,22,23,28,29};

        for (int i = 0; i < p.length; i++)
            tre.leggInn(p[i],v.charAt(i));  // autoboksing fra char til Character

        tre.preorden(Oppgave.konsollutskrift());  // skriver ut

        // Utskrift: A B C D E F G H I J K L M N O

        // Oppgave 6:
        System.out.println("\n\nOppgave 6:");
        BinTre<Character> tre2 = new BinTre<>();   // et tomt tre

        String s = "HBKADIOCFJMEGLN";  // verdiene i nivåorden
        int[] pos = {1,2,3,4,5,6,7,10,11,13,14,22,23,28,29};

        for (int i = 0; i < pos.length; i++)
            tre2.leggInn(pos[i],s.charAt(i));  // autoboksing fra char til Character

        tre2.inorden(Oppgave.konsollutskrift());  // skriver ut

        // Utskrift: A B C D E F G H I J K L M N O
    }

}
