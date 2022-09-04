package Uke34;

import java.util.Arrays;

public class minmaks {
    public static int maks(int[] a) {
        int maksverdi = a[0];
        for (int i = 1; i < a.length; i++) {
            int verdi = a[i];
            if(verdi > maksverdi) {
                maksverdi = verdi;
            }
        }
        return maksverdi;
    }

    public static int min(int[] a) {
        int minverdi = a[0];
        for (int i = 1; i < a.length; i++) {
            int verdi = a[i];
            if (verdi < minverdi) {
                minverdi = verdi;
            }
        }
        return minverdi;
    }

    public static int[] minmaks(int[] a) {
        int imaks = 0;
        int imin = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] > a[imaks]) {
                imaks = i;
            }
            else if (a[i] < a[imin]) {
                imin = i;
            }
        }
        int[] b = {imin, imaks};
        return b;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] b = {1, 3, 2, 7, 5, 9, 6, 8, 10, 4};

        System.out.println("Største verdi i er " + maks(a));
        System.out.println("Minste verdi i er " + min(a));

        System.out.println("Posisjonen til det minste og det største tallet er " + Arrays.toString(minmaks(a)));
        System.out.println("Posisjonen til det minste og det største tallet er " + Arrays.toString(minmaks(b)));

    }
}
