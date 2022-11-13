package Uke43;

import Eksempelklasser.SBinTre;

import java.util.Iterator;
import java.util.stream.Stream;

public class Program5_2_9 {
    public static void main(String[] args) {
        System.out.println("5.2.9\n---------------------------------------");

        System.out.println("\nOppgave 1:");
        Integer[] a = {2,8,6,1,7,4,3,9,5,10};                  // verdier
        SBinTre<Integer> tre = SBinTre.sbintre(Stream.of(a));  // Programkode 5.2.3 c)

        Iterator<Integer> i = tre.iterator();      // en iterator er opprettet

        tre.fjernMin();  // en innlegging er en endring
        i.next();        // kaster en ConcurrentModificationException
    }
}
