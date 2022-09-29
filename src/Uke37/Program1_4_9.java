package Uke37;

import java.awt.*;
import java.util.Comparator;

import Eksempelklasser.Dato;
import Eksempelklasser.Komparator;
import Tabell.Tabell;

public class Program1_4_9 {
    public static void main(String[] args) {
        //Programkode 1.4.9 f)
        System.out.println("1.4.9");
        System.out.println("\n3a)");


        int[] x = {3,5,6,2,6,1,4,7,7,4};         // x-koordinater
        int[] y = {3,6,3,5,5,2,1,4,2,4};         // y-koordinater

        Point[] punkt = new Point[x.length];     // en punkttabell
        for (int i = 0; i < punkt.length; i++) punkt[i] = new Point(x[i],y[i]);

        for (Point p : punkt) System.out.print("(" + p.x + "," + p.y + ") ");
        System.out.println();                    // linjeskift

        Tabell.innsettingssortering(punkt,
                Comparator.comparing(Point::getX).thenComparing(Point::getY));

        for (Point p : punkt) System.out.print("(" + p.x + "," + p.y + ") ");

        // Utskriften blir:
        // (3,3) (5,6) (6,3) (2,5) (6,5) (1,2) (4,1) (7,4) (7,2) (4,4)
        // (1,2) (2,5) (3,3) (4,1) (4,4) (5,6) (6,3) (6,5) (7,2) (7,4)


        /*
        System.out.println("\n\n3b)");
        Tabell.innsettingssortering(punkt, (p1, p2) ->
                {
                    int d = p1.x - p2.x;    // forskjellen mellom x-koordinatene
                    if (d != 0) return d;
                    return p1.y - p2.y;     // forskjellen mellom x-koordinatene
                }
        );
        for (Point p : punkt) System.out.print("(" + p.x + "," + p.y + ") ");

        System.out.println("\n\n3c)");
        Tabell.innsettingssortering(punkt,
                Comparator.comparingInt((Point p) -> p.x).thenComparingInt(p -> p.y));
        for (Point p : punkt) System.out.print("(" + p.x + "," + p.y + ") ");

        System.out.println();
        Tabell.innsettingssortering(punkt,
            Comparator.comparingInt((Point p) -> p.x).thenComparingInt(p -> p.y));
        for (Point p : punkt) System.out.print("(" + p.x + "," + p.y + ") ");
        */

        System.out.println("\n\n4a)");
        Dato[] d = new Dato[5];           // en datotabell
        d[0] = new Dato(24,12,2014);      // 24/12-2014
        d[1] = new Dato(24,12,2012);      // 24/12-2012
        d[2] = new Dato(9,12,2013);       // 9/12-2013
        d[3] = new Dato(25,12,2012);      // 25/12-2012
        d[4] = new Dato(10,12,2013);      // 10/12-2013


        //Tabell.innsettingssortering(d);
        Tabell.innsettingssortering(d, Comparator.naturalOrder());
        for (Dato i : d) System.out.print(i + " ");

        // Utskrift: 24/12-2012 25/12-2012 9/12-2013 10/12-2013 24/12-2014


    }
}
