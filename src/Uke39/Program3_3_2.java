package Uke39;

import Eksempelklasser.EnkeltLenketListe;

public class Program3_3_2 {
    public static void main(String[] args) {
        // 3.3.2 4)
        System.out.println("3.3.2");
        System.out.println("----------------------------------");

        Integer[] a = {1, 4, 2, 7, 7, 2, 5, 3, 20};
        EnkeltLenketListe<Integer> liste = new EnkeltLenketListe<Integer>();

        // legger inn verdiene i a inn i liste
        for (Integer integer : a) {
            liste.leggInn(integer);
        }

        System.out.println("\n4)");
        System.out.println(liste);

        // legger inn verdi i gitt indeks
        liste.leggInn(4, 0);
        System.out.println(liste);

        // skriver ut antallet
        System.out.println("Antall = " + liste.antall());

    }
}
