package Uke35;

public class min {
    public static int min1(int[] a, int fra, int til)
    {
        if (fra < 0 || til > a.length || fra >= til)
        {
            throw new IllegalArgumentException("Illegalt intervall!");
        }

        int m = fra;              // indeks til største verdi i a[fra:til>
        int minverdi = a[fra];   // største verdi i a[fra:til>

        for (int i = fra + 1; i < til; i++)
        {
            if (a[i] < minverdi)
            {
                m = i;                // indeks til største verdi oppdateres
                minverdi = a[m];     // største verdi oppdateres
            }
        }

        return m;  // posisjonen til største verdi i a[fra:til>
    }

    public static int min2(int[] a)  // bruker hele tabellen
    {
        return min1(a,0,a.length);     // kaller metoden over
    }

    public static void main(String[] args) {
        int[] a = {1, 5, 9, 2, 3, 7};

        System.out.println("Posistenen til det minste tallet er: " + min1(a, 0, a.length) + ", som har verdi " +
                a[min1(a, 0, a.length)]);
    }
}
