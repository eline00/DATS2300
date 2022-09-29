package Uke37;

import Eksempelklasser.Heltall;
import Eksempelklasser.Person;
import Tabell.Tabell;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Arrays.sort;

public class Program1_4_4 {
    public static void main(String[] args) {
        System.out.println("1.4.4");
        System.out.println("1a)");
        int[] a = {5,2,7,3,9,1,8,10,4,6};          // en int-tabell
        Heltall[] h = new Heltall[a.length];       // en Heltall-tabell

        for (int i = 0; i < h.length; i++) h[i] = new Heltall(a[i]);
        Tabell.innsettingssortering(h);           // generisk sortering
        System.out.println(Arrays.toString(h));   // utskrift

        System.out.println("\n1d)");
        Heltall x = new Heltall(3), y = new Heltall(3);  // x og y er like
        System.out.println(x.compareTo(y) + "  " + x.equals(y));

        System.out.println("\n1e)");
        Heltall i = new Heltall(3), j = new Heltall(3);  // x og y er like
        System.out.println(i.hashCode() + "  " + j.hashCode());

        System.out.println("\n2b)");
        Person[] p = new Person[7];                   // en persontabell

        p[0] = new Person("Kari","Svendsen");         // Kari Svendsen
        p[1] = new Person("Boris","Zukanovic");       // Boris Zukanovic
        p[2] = new Person("Ali","Kahn");              // Ali Kahn
        p[3] = new Person("Azra","Zukanovic");        // Azra Zukanovic
        p[4] = new Person("Kari","Pettersen");        // Kari Pettersen
        p[5] = new Person("Eline", "Jorgensen");      // Eline Jorgensen
        p[6] = new Person ("Gisle", "Garen");         // Gisle Garen


        int m = Tabell.maks(p);                       // posisjonen til den største
        System.out.println(p[m] + " er storst");      // skriver ut den største

        sort(p);               // generisk sortering
        System.out.println(Arrays.toString(p));       // skriver ut sortert

        // Utskrift:

        // Boris Zukanovic er størst
        // [Ali Kahn, Kari Pettersen, Kari Svendsen, Azra Zukanovic, Boris Zukanovic]

        System.out.println("\n2i)");
        /*
        Stream s = Arrays.stream(p);
        Optional<Person> resultat = s.max(Comparator.naturalOrder());
        resultat.ifPresent(System.out::println);
         */
        Arrays.stream(p).max(Comparator.naturalOrder()).ifPresent(System.out::println);
    }
}
