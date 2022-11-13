package Uke45;

import Eksempelklasser.MGraf;

import java.io.IOException;
import java.util.Arrays;

public class Program11_1_3 {
    public static void main(String[] args) throws IOException {
        System.out.println("11.1.3\n---------------------------------------");
        MGraf graf = new MGraf(0);  // starter med tomme tabeller
        String[] navn = "ABEGFCD".split("");  // usortert rekkefølge
        for (String n : navn) graf.leggInnNode(n);  // legger inn
        System.out.println(Arrays.toString(graf.nodenavn()));  // [A, B, C, D, E, F, G]

        graf.leggInnKanter("A",  "B","C","D");              // fra A til B,C,D
        graf.leggInnKanter("B",  "A","D","E");              // fra B til A,D,E
        graf.leggInnKanter("C",  "A","D","F");              // fra C til A,D,F
        graf.leggInnKanter("D",  "A","B","C","E","F","G");  // fra D til A,B,C,E,F,G
        graf.leggInnKanter("E",  "B","D","G");              // fra E til B,D,G
        graf.leggInnKanter("F",  "C","D","G");              // fra F til C,D,G
        graf.leggInnKanter("G",  "D","E","F");              // fra G til D,E,F

        System.out.println();

        String url = "https://www.cs.hioa.no/~ulfu/appolonius/kap11/1/graf1.txt";
        String url2 = "https://www.cs.hioa.no/~ulfu/appolonius/kap11/1/graf2.txt";
        MGraf graf2 = new MGraf(url);
        for (String n : graf2.nodenavn())
            System.out.println(n + " -> " + graf2.kanterFra(n));
    }
}
