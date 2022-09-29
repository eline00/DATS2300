package Uke36;

import java.util.Arrays;

public class Program {
    public static void main(String[] args) {
        /* 1.3.1
        int[] a = {2, 3, 6, 1, 4, 5};
        int[] b = {2, 3, 6, 1, 5, 4};
        int[] c = {2, 3, 1, 6, 5, 4};
        int[] d = {2, 3, 6, 5, 4, 1};

        Tabell2.nestePermutasjon(a);
        Tabell2.nestePermutasjon(b);
        Tabell2.nestePermutasjon(c);
        Tabell2.nestePermutasjon(d);

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(c));
        System.out.println(Arrays.toString(d));

         */
        /* 1.3.4
        int[] a = Tabell.Tabell1.randPerm(100000);
        long tid = System.currentTimeMillis();
        Tabell2.utvalgssortering2(a);
        tid = System.currentTimeMillis() - tid;
        System.out.println(tid);
         */

        /*
        int[] a = {3, 8, 10, 12, 14, 16, 21, 24, 27, 30, 32, 33, 34, 37, 40};
        System.out.println(Tabell2.lineærsøk(a, 10, 8));
         */

        int[] a = {1, 3, 4, 4, 5, 7, 7, 8, 9, 10, 10, 12, 15, 15, 15};
        System.out.println(Tabell.Tabell.binærsøk3(a, 0, a.length, 15));

    }
}
