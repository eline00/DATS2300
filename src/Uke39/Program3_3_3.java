package Uke39;

import Eksempelklasser.EnkeltLenketListe;
import Grensesnitt.Liste;

public class Program3_3_3 {
    public static void main(String[] args) {
        // 3.3.3 4)
        System.out.println("3.3.3");
        System.out.println("\n4)");

        String[] a = {"hei", "på", "du", "der", "borte", "!"};
        Liste<String> liste = new EnkeltLenketListe<>();

        for(String s : a) {
            liste.leggInn(s);           // legger inn verdiene i a inn i en lenket liste
        }

        System.out.println(liste);      // bruket toString()-metoden for å skrive ut listen
        // Utskrift: [hei, p?, du, der, borte, !]

        liste.fjern(4);         // fjerner det 4. elementet i liste, "borte"
        System.out.println(liste);
        // Utskrift: [hei, p?, du, der, !]

        liste.fjern("der");      // fjerner elementet med verdi "der"
        System.out.println(liste);
        // Utskrift: [hei, p?, du, !]

        System.out.println(liste.indeksTil("hei"));     // finner indeksen til verdi "hei"
        // Utskrift: 0

        System.out.println(liste.inneholder("der"));        // inneholder listen verdien "der"?
        // Utskrift: false
    }
}
