package Uke39;

import Eksempelklasser.TabellListe;

public class Program3_2_3 {
    public static void main(String[] args) {
        String[] s = {"5", "1", "2", "2", "4", "3", "10", "5", "5"};
        TabellListe<String> liste = new TabellListe<>(s);

        System.out.println(liste);
        System.out.println(liste.fjern("1"));
        System.out.println(liste);

    }
}
