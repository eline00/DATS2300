package Uke39;

import Grensesnitt.Liste;
import Eksempelklasser.TabellListe;

import java.util.Iterator;

public class Program3_2_5 {
    public static void main(String[] args) {
        // 3.2.5 3)
        // Programkode 3.2.5 a)
        String[] s = {"Per","Kari","Ole"};

        Liste<String> liste = new TabellListe<>();

        for (String navn : s) liste.leggInn(navn);

        System.out.println("3.2.5");
        System.out.println("\nProgramkode a)");
        System.out.println(liste);
        // Utskrift: [Per, Kari, Ole]

        // Programkode 3.2.5 b)
        System.out.println("\nProgramkode b)");
        Iterator<String> i = liste.iterator();     // oppretter en iterator
        System.out.println(i.next());              // den første i listen

        //liste.fjern("Per");                      // fjerner den første
        System.out.println(i.next());              // den neste i listen

        // Programkode 3.2.5 c)
        Iterator<String> k = liste.iterator();     // oppretter en iterator i'2
        Iterator<String> j = liste.iterator();     // oppretter en iterator j

        System.out.println("\nProgramkode c)");
        System.out.println(k.next());              // den første i listen
        k.remove();                                // fjerner den første
        System.out.println(j.next());              // den første i listen

    }
}
