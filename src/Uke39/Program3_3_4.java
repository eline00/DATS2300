package Uke39;

import Eksempelklasser.EnkeltLenketListe;

import java.util.Iterator;

public class Program3_3_4 {
    public static void main(String[] args) {
        // 3.3.4
        System.out.println("3.3.4");
        System.out.println("----------------------------------");

        String[] a = {"er", "du", "der", "borte", "nå", "?"};
        EnkeltLenketListe<String> liste = new EnkeltLenketListe<>();

        for (String s : a) {
            liste.leggInn(s);
        }
        System.out.println("Opprinnelig liste: " + liste);
        // Utskrift: Opprinnelig liste: [er, du, der, borte, n?, ?]

        // 3.3.4 1)
        /*
        System.out.println("\n1)");
        Iterator<String> iterator = liste.iterator();

        for (String s : a) {
            liste.leggInn(s);
        }

        iterator.remove();

         */

        // 2)
        System.out.println("\n2)");
        liste.fjernHvis(x -> x.equals("borte"));        // fjerner alle forekomster av "borte"
        System.out.println(liste);
        // Utskrift: [er, du, der, n?, ?]

        liste.forEach(x -> System.out.println(x + " "));      // skriver ut hvert element i listen med mellomrom mellom
        // Utskrift: er du der n? ?

        // 4)
        System.out.println("\n\n4)");


    }
}
