package Uke39;

import Eksempelklasser.Liste;
import Eksempelklasser.TabellListe;

public class Program3_2_4 {
    public static void main(String[] args) {

        String[] s = {"hei", "på", "du", "!"};
        Liste<String> liste = new TabellListe<>();

        for (String ord : s) liste.leggInn(ord);
        System.out.println(liste);
        liste.oppdater(2, "deg");
        System.out.println(liste);
        liste.fjern(1);
        System.out.println(liste);
        liste.fjernHvis(x -> x.equals("deg"));
        System.out.println(liste);
    }
}
