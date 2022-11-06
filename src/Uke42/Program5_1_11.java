package Uke42;

import Eksempelklasser.BinTre;

import java.util.Iterator;

public class Program5_1_11 {
    public static void main(String[] args) {
        System.out.println("5.1.11\n--------------------------------------------");

        // Oppgave 1:
        System.out.println("Oppgave 1: ");
        int[] posisjon = {1,2,3,4,5,6,7,8,9,10};             // posisjoner og
        Character[] verdi = {'A','B','C','D','E','F','G','H','I','J'};             // verdier i nivåorden

        BinTre<Character> tre = new BinTre<>(posisjon, verdi);  // konstruktør

        for (Character s : tre) System.out.print(s + " ");      // for-alle-løkke
        // Utskrift: H D I B J E A F C G

        // Oppgave 2:
        System.out.println("\n\nOppgave 2:");
        tre.forEach(s -> System.out.print(s + " "));
        tre.iterator().forEachRemaining(s -> System.out.print(s + " "));

        // Oppgave 5:
        System.out.println("\n\nOppgave 6:");

        char c = 'F';

        for (Iterator<Character> i = tre.iterator(); i.hasNext();) {
            if (i.next() == c) {
                System.out.println("Fant " + c + "!");
                break;
            }
        }
    }
}
