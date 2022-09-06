package Uke36;
import Uke35.Tabell1;

public class Tabell {
    public static void snu(int[] a, int v, int h)  // snur intervallet a[v:h]
    {
        while (v < h) {
            bytt(a, v++, h--);
        }
    }

    public static void snu(int[] a, int v)  // snur fra og med v og ut tabellen
    {
        snu(a, v, a.length - 1);
    }

    public static void snu(int[] a)  // snur hele tabellen
    {
        snu(a, 0, a.length - 1);
    }

    public static boolean nestePermutasjon(int[] a)
    {
        int i = a.length - 2;                    // i starter nest bakerst
        while (i >= 0 && a[i] > a[i + 1]) {
            i--;   // går mot venstre
        }
        if (i < 0) {
            return false;                 // a = {n, n-1, . . . , 2, 1}
        }

        int j = a.length - 1;                    // j starter bakerst
        while (a[j] < a[i]) {
            j--;                 // stopper når a[j] > a[i]
            bytt(a,i,j);
            snu(a,i + 1);               // bytter og snur
        }

        return true;                             // en ny permutasjon
    }
}
