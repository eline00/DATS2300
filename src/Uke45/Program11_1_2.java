package Uke45;

import Eksempelklasser.Graf;

import java.io.IOException;

public class Program11_1_2 {
    public static void main(String[] args) throws IOException {
        System.out.println("11.1.2\n----------------------------------------------");
        String[] nodenavn = "ABCDEFG".split(""); // A, B, C, D, E, F og G
        Graf graf = new Graf();
        for (String navn : nodenavn) graf.leggInnNode(navn);
        for (String navn : graf) System.out.print(navn + " ");  // A B C D E F G

        graf.leggInnKanter("A",  "B","C","D");              // fra A til B,C,D
        graf.leggInnKanter("B",  "A","D","E");              // fra B til A,D,E
        graf.leggInnKanter("C",  "A","D","F");              // fra C til A,D,F
        graf.leggInnKanter("D",  "A","B","C","E","F","G");  // fra D til A,B,C,E,F,G
        graf.leggInnKanter("E",  "B","D","G");              // fra E til B,D,G
        graf.leggInnKanter("F",  "C","D","G");              // fra F til C,D,G
        graf.leggInnKanter("G",  "D","E","F");              // fra G til D,E,F

        System.out.println("\n");

        for (String node : graf) { // bruker iteratoren i grafen
            System.out.println(node + " -> " + graf.kanterFra(node));
        }
        // A -> [B, C, D]
        // B -> [A, D, E]
        // osv.

        System.out.println();

        String url = "https://www.cs.hioa.no/~ulfu/appolonius/kap11/1/graf1b.txt";
        Graf graf2 = new Graf(url);
        for (String navn : graf2) System.out.print(navn + " ");
        // A B C D E F G

        System.out.println();

        System.out.println("\nOppgave 1:");
        System.out.println(graf2.erUrettet());  // Utskrift: false
        graf2.leggInnKant("C", "A");            // en ny kant
        System.out.println(graf2.erUrettet());  // Utskrift: true

        System.out.println();

        for (String node : graf2) { // bruker iteratoren i grafen
            System.out.println(node + " -> " + graf2.kanterFra(node));
        }

        System.out.println("\nOppgave 2:");
        String url2 = "https://www.cs.hioa.no/~ulfu/appolonius/kap11/1/graf2.txt";
        Graf graf3 = new Graf(url2);
        for (String node : graf3) {
            System.out.println(node + " -> " + graf3.kanterFra(node));
        }
    }
}
