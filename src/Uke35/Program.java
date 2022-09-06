package Uke35;

public class Program
{
    public static void main(String ... args)      // hovedprogram
    {

        int[] a = Tabell1.randPerm(20); // tilfeldig permutasjon av 1 . . 20
        int[] b = new int[30];

        Tabell1.kopier(a, 0, b, (b.length - a.length)/2, a.length);

        Tabell1.skriv(a);
        System.out.println();
        Tabell1.skriv(b);

    } // main

} // class Program
