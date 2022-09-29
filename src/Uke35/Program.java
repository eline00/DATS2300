package Uke35;

import Tabell.Tabell;

public class Program
{
    public static void main(String ... args)      // hovedprogram
    {

        int[] a = Tabell.randPerm(20); // tilfeldig permutasjon av 1 . . 20
        int[] b = new int[30];

        Tabell.kopier(a, 0, b, (b.length - a.length)/2, a.length);

        Tabell.skriv(a);
        System.out.println();
        Tabell.skriv(b);

    } // main

} // class Program
