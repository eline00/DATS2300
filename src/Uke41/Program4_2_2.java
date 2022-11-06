package Uke41;

import Tabeller.TabellKø;

import static Tabeller.TabellKø.snu;

public class Program4_2_2 {
    public static void main(String[] args) {
        // 4.2.2
        System.out.println("4.2.2");
        System.out.println("--------------------------------------");

        TabellKø<Integer> kø = new TabellKø<>();

        System.out.println("\nOppgave 6) og 7):");
        kø.leggInn(1);
        kø.leggInn(2);
        kø.leggInn(3);
        System.out.println(kø);

        kø.nullstill();
        System.out.println(kø);

        System.out.println("\nOppgave 8)");
        kø.leggInn(1);
        kø.leggInn(2);
        kø.leggInn(3);
        System.out.println(kø.indeksTil(1));
        System.out.println(kø.indeksTil(2));
        System.out.println(kø.indeksTil(3));

        System.out.println("\nOppgave 10):");
        System.out.println(kø);
        snu(kø);
        System.out.println(kø);
    }
}
