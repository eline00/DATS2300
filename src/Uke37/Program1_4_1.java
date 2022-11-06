package Uke37;

import Tabeller.Tabell;

public class Program1_4_1 {
    public static void main(String[] args) {
        int[] a = {5,2,7,3,9,1,8,4,6};
        double[] d = {5.7,3.14,7.12,3.9,6.5,7.1,7.11};
        String[] s = {"Sohil","Per","Thanh","Fatima","Kari","Jasmin"};
        char[] c = "JASMIN".toCharArray();
        Integer[] i = {5,2,7,3,9,1,8,4,6};

        int k = Tabell.maks(a);     // posisjonen til den største i a
        int l = Tabell.maks(d);     // posisjonen til den største i d
        int m = Tabell.maks(s);     // posisjonen til den største i s
        int n = Tabell.maks(c);     // posisjonen til den største i c
        int o = Tabell.maks(i);     // posisjonen til den største i i

        System.out.println("1.4.1:");
        System.out.println(a[k] + "  " + d[l] + "  " + s[m] + "  " + c[n] + "  " + i[o]);

        String b = "Å", t = "Ø";
        System.out.println(b.compareTo(t));
        System.out.println(Boolean.compare(false, true));
    }

}
