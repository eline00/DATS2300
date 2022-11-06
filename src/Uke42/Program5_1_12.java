package Uke42;

import Eksempelklasser.BinTre;

public class Program5_1_12 {
    public static void main(String[] args) {
        System.out.println("5.1.12\n----------------------------------------");

        // Oppgave 1:
        System.out.println("\nOppgave 1:");

        String[] bokstav = "EIBGAHKLODNMCJF".split("");
        int[] posisjon = {1,2,3,4,5,6,7,10,11,13,14,22,23,28,29};

        BinTre<String> tre = new BinTre<>(posisjon, bokstav);
        System.out.println("Antall: " + tre.antall());
        System.out.println("Høyde: " + tre.høyde());
        System.out.println("Innholder: " + tre.inneholder("B"));
        System.out.println("Posisjon: " + tre.posisjon("N"));

    }
}
