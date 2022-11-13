package Uke45;

import Eksempelklasser.Graf;

import java.io.IOException;
import java.util.StringJoiner;

public class Program11_1_4 {
    public static void main(String[] args) throws IOException {
        System.out.println("11.1.4\n---------------------------------------");

        System.out.println();
        String url = "https://www.cs.hioa.no/~ulfu/appolonius/kap11/1/graf2.txt";
        String url2 = "https://www.cs.hioa.no/~ulfu/appolonius/kap11/1/graf2.txt";
        String url3 = "https://www.cs.hioa.no/~ulfu/appolonius/kap11/1/graf1.txt";
        String url4 = "https://www.cs.hioa.no/~ulfu/appolonius/kap11/1/graf1b.txt";
        String url5 = "https://www.cs.hioa.no/~ulfu/appolonius/kap11/1/graf3.txt";
        Graf graf = new Graf(url);
        graf.dybdeFørstPretraversering("A", x -> System.out.print(x + " "));
        // Utskrift: A B D E G F C

        System.out.println("\n");
        Graf graf2 = new Graf(url5);
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        graf2.breddeFørstTraversering("A", sj::add);
        System.out.println(sj.toString());  // Utskrift: [A, B, C, D, E, F, G]

        System.out.println("\nOppgave 1a) + b) + c)");
        Graf graf3 = new Graf(url5);
        graf3.dybdeFørstPretraversering("A", x -> System.out.print(x + " "));
        // Utskrift: A B D E G F C
    }
}
