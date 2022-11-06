package Uke42;

import Eksempelklasser.BinTre;
import Eksempelklasser.TabellListe;
import Grensesnitt.Liste;
import Grensesnitt.Oppgave;
import Tabeller.Tabell;

import java.util.Arrays;
import java.util.StringJoiner;

public class Program5_1_6 {
    public static void main(String[] args) {
        System.out.println("5.1.6" + "\n--------------------------------------------");
        int[] posisjon = {1,2,3,4,5,6,7,10,11,13,14,22,23,28,29};  // posisjoner og
        Character[] verdi = {'E','I','B','G','A','H','K','L','O','D','N','M','C','J','F'};              // verdier i nivåorden

        // Oppgave 3:
        System.out.println("\nOppgave 3: ");
        BinTre<Character> tre = new BinTre<>(posisjon, verdi);        // en konstruktør
        tre.nivåorden(Oppgave.konsollutskrift());
        System.out.println();
        tre.nivåorden(Oppgave.konsollutskrift().deretter(c -> System.out.print(' ')));
        // Utskrift: E I B G A H K L O D N M C J F

        // Oppgave 4:
        System.out.println("\n\nOppgave 4: ");
        Liste<Character> liste = new TabellListe<>();  // en liste
        tre.nivåorden(c -> liste.leggInn(c));          // lambda-uttrykk som argument
        System.out.println(liste);                     // skriver ut listen

        // Oppgave 5:
        BinTre<Character> tre2 = new BinTre<>();             // et tomt tre

        System.out.println();
        System.out.println("\nOppgave 5:");
        int[] p = {1,2,3,4,5,6,7,10,11,13,14,22,23,28,29};  // posisjoner i nivåorden
        String v = "EIBGAHKLODNMCJF";                       // verdier i nivåorden
        for (int i = 0; i < p.length; i++) tre2.leggInn(p[i],v.charAt(i));

        StringJoiner s = new StringJoiner(", ", "[", "]");

        //tre2.nivåorden(c -> s.add(c.toString()));

        String verdier = s.toString();
        System.out.println(verdier);

        tre2.nivåorden(c -> {
            if (c > 'D') {
                s.add(c.toString());
            }
        });

        String verdier2 = s.toString();
        System.out.println(verdier2);

        // Oppgave 10:
        System.out.println("\n\nOppgave 10:");
        int[] nivåer = tre.nivåer();  // bruker Programkode 5.1.6 k)

        System.out.print("Nivåer: " + Arrays.toString(nivåer));
        System.out.print("\nTreets bredde: " + nivåer[Tabell.maks(nivåer)]);
        System.out.println("\nTreets høyde: " + (nivåer.length - 1));

        // Utskrift: Nivåer: [1, 2, 4, 4, 4]
        // Treets bredde:
        // 4 Treets høyde: 4
    }
}
