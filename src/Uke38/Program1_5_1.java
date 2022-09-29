package Uke38;


import static Tabell.Tabell.bytt;

public class Program1_5_1 {

    //Programkode 1.5.1 a) iterativ
    public static int a(int n)           // n må være et ikke-negativt tall
    {
        if (n < 0) throw new IllegalArgumentException("n er negativ!");

        int x = 0, y = 1;
        int a_n = 1;
        for (int i = 0; i < n; i++) {
            a_n = 2*y + 3*x;
            x = y;
            y = a_n;
        }
        return a_n;
    }

    //Programkode 1.5.1 b) iterativ
    public static int tverrsum(int n)
    {
        int sum = 0;
        while (n > 0) {
            sum += n%10;
            n /= 10;
        }
        return sum;
    }

    public static int sifferrot(int n) {
        while (n >= 10) {
            n = tverrsum(n);
        }
        return n;
    }

    public static int kvadratsum(int n) {
        if (n == 1) return 1;
        else return kvadratsum(n-1) + n*n;
    }

    public static int sum(int k, int n) {
        if (k == n) return k;
        int m = (k + n)/2;          // finner midten
        return sum(k, m) + sum(m+1, n);
    }

    public static int maks(int[] a, int v, int h) {
        if (v == h) return v;
        int m = (v+h)/2;        // finner midten
        int mvenste = maks(a, v, m);
        int mhoyre = maks(a, m+1, h);

        if (a[mvenste] >= a[mhoyre]) {
            return mvenste;
        } else {
            return mhoyre;
        }
    }

    public static int maks(int[] a) {
        return maks(a, 0, a.length);
    }

    public static int fakultet(int n) {
        if (n == 0) return 1;
        return n*fakultet(n-1);
    }

    public static int fib(int n)         // det n-te Fibonacci-tallet
    {
        if (n <= 1) return n;              // fib(0) = 0, fib(1) = 1
        else return fib(n-1) + fib(n-2);   // summen av de to foregående
    }

    public static int tverrsum2(int n)
    {
        System.out.println("tverrsum(" + n + ") starter!");
        int sum = (n < 10) ? n : tverrsum2(n / 10) + (n % 10);
        System.out.println("tverrsum(" + n + ") er ferdig!");
        return sum;
    }

    public static int euklid(int a, int b)
    {
        System.out.println("euklid(" + a + "," + b + ") starter!");
        if (b == 0)
        {
            System.out.println("euklid(" + a + "," + b + ") er ferdig!");
            return a;
        }
        int r = a % b;            // r er resten
        int k = euklid(b,r);       // rekursivt kall
        System.out.println("euklid(" + a + "," + b + ") er ferdig!");
        return k;
    }

    private static int parter0(int[] a, int v, int h, int skilleverdi)
    {
        while (true)                                  // stopper når v > h
        {
            while (v <= h && a[v] < skilleverdi) v++;   // h er stoppverdi for v
            while (v <= h && a[h] >= skilleverdi) h--;  // v er stoppverdi for h

            if (v < h) bytt(a,v++,h--);                 // bytter om a[v] og a[h]
            else  return v;  // a[v] er nåden første som ikke er mindre enn skilleverdi
        }
    }

    private static int sParter0(int[] a, int v, int h, int indeks)
    {
        bytt(a, indeks, h);           // skilleverdi a[indeks] flyttes bakerst
        int pos = parter0(a, v, h - 1, a[h]);  // partisjonerer a[v:h - 1]
        bytt(a, pos, h);              // bytter for å få skilleverdien på rett plass
        return pos;                   // returnerer posisjonen til skilleverdien
    }


    private static void kvikksortering0(int[] a, int v, int h)
    {
        System.out.println("Kallet med [" + v + ":" + h + "] starter!");
        if (v >= h) return;   // tomt eller maks ett element

        int k = sParter0(a,v,h,(v + h)/2);   // se Programkode 1.3.9 f)
        kvikksortering0(a,v,k-1);
        kvikksortering0(a,k+1,h);

        System.out.println("Kallet med [" + v + ":" + h + "] er ferdig!");

    }

    public static void kvikksortering0(int[] a) {
        if (a.length > 0) kvikksortering0(a, 0, a.length-1);
    }

    public static void main(String... args)
    {
        int[] a = {4, 1, 7, 8, 3, 2, 9, 6, 10, 5};
        kvikksortering0(a);

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

}
