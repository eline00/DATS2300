package Uke39;

import Eksempelklasser.TabellListe;

import java.util.Arrays;

public class Program3_2_2 {
    public static void main(String[] args) {
        // 3.2.2 2)
        String[] s = {"Hei", "på", "deg", null, "!", null};

        TabellListe<String> liste = new TabellListe<>(s);
        System.out.println(liste.antall());
        System.out.println(liste.tom());
        System.out.println(liste.hent(2));
        System.out.println(liste.indeksTil("Hei"));
        System.out.println(liste.inneholder("på"));

        // 3.2.2 3)
        System.out.println();
        System.out.println(liste);
    }
}
