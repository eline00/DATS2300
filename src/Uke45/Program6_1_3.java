package Uke45;

import Tabeller.Tabell;

import java.util.Arrays;

public class Program6_1_3 {
    public static void main(String[] args) {
        int n = 197;
        int[] hash = new int[n];

        for (int i = 0; i < 400; i++) {
            String s = "A";
            if (i < 100) s += 0;
            if (i < 10) s += 0;
            s += i;
            hash[s.hashCode() % n]++;
        }

        int m = Tabell.maks(hash);  // posisjonen til største verdi

        int maks = hash[m];         // den største verdien

        int[] frekvens = new int[maks + 1];
        for (int i = 0; i < hash.length; i++) frekvens[hash[i]]++;  // teller opp

        System.out.println(Arrays.toString(frekvens));  // [0, 48, 95, 54]
    }
}
