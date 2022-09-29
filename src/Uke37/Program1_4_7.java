package Uke37;

import Eksempelklasser.Komparator;
import Eksempelklasser.Person;
import Tabell.Tabell;

import java.util.Arrays;

public class Program1_4_7 {
    public static void main(String[] args) {

        // 1.4.7
        System.out.println("1.4.7:");
        System.out.println("2)");
        Double[] d = {5.7,3.14,7.12,3.9,6.5,7.1,7.11};

        //Tabell.innsettingssortering(d, Komparator.naturligOrden());
        System.out.println(Arrays.toString(d));
        //Tabell.innsettingssortering(d, Komparator.omvendtOrden());
        System.out.println(Arrays.toString(d));

        System.out.println("\n3)");
        Boolean[] b = {false, true, true, false, false, true, false, true};

        //Tabell.innsettingssortering(b, Komparator.naturligOrden());
        System.out.println(Arrays.toString(b));

        // Programkode 1.4.6 c)
        Person[] p = new Person[5];                       // en persontabell
        p[0] = new Person("Kari", "Svendsen");            // Kari Svendsen
        p[1] = new Person("Boris", "Zukanovic");          // Boris Zukanovic
        p[2] = new Person("Ali", "Kahn");                 // Ali Kahn
        p[3] = new Person("Azra", "Zukanovic");           // Azra Zukanovic
        p[4] = new Person("Kari", "Pettersen");           // Kari Pettersen

        //Tabell.innsettingssortering(p, Komparator.orden(Person::etternavn));

        System.out.println("\n4)");
        System.out.println(Arrays.toString(p));           // Utskrift av tabellen p
        // [Ali Kahn, Azra Zukanovic, Boris Zukanovic, Kari Svendsen, Kari Pettersen]

        // Programkode 1.4.7 i)
        String[] s = {"Lars","Anders","Bodil","Kari","Per","Berit"};
        //Tabell.innsettingssortering(s, Komparator.orden(String::length));

        System.out.println("\n5)");
        System.out.println(Arrays.toString(s));
        // Utskrift: [Per, Kari, Lars, Berit, Bodil, Anders]

        //Tabell.innsettingssortering(s, Komparator.orden(x -> -x.length()));
        System.out.println(Arrays.toString(s));


    }
}
