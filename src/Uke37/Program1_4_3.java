package Uke37;

import java.util.Arrays;

public class Program1_4_3 {
    public static void main(String[] args) {
        System.out.println("1.4.3:");
        Integer[] a = Tabeller.Tabell.randPermInteger(20);
        System.out.println(Arrays.toString(a));
        // En mulig utskrift: [7, 1, 8, 2, 10, 3, 4, 6, 5, 9]

        Tabeller.Tabell.innsettingssortering(a);
        System.out.println(Arrays.toString(a));
        // Utskrift: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        double[] d = {5.7,3.14,7.12,3.9,6.5,7.1,7.11};
        Double[] d2 = new Double[d.length];

        for(int i = 0; i < d.length; i++) {
            d2[i] = d[i];
        }

        System.out.println();
        System.out.println(Arrays.toString(d2));
        Tabeller.Tabell.innsettingssortering(d2);
        System.out.println(Arrays.toString(d2));

        f(1, 1);

    }

    //public static void f(int a, Integer b) { };
    public static void f(Integer a, int b) { };
}
