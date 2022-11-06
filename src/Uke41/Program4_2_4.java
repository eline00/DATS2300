package Uke41;

import Eksempelklasser.EnkeltLenketListe;
import Eksempelklasser.LenketKø;
import Grensesnitt.Kø;
import Grensesnitt.Stakk;
import Tabeller.Tabell;
import Tabeller.TabellStakk;


import java.util.Comparator;

public class Program4_2_4 {
    public static void main(String[] args) {
        Integer[] a = Tabell.randPermInteger(10);

        Kø<Integer> kø = new EnkeltLenketListe<>();
        for (Integer i : a) kø.leggInn(i);

        System.out.println(kø);    // usortert

        Stakk<Integer> stakk = new TabellStakk<>();

        LenketKø.sorter(kø, stakk, Comparator.naturalOrder());

        System.out.println(kø);    // sortert
    }
}
