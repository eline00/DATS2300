package Uke37;

import Grensesnitt.Komparator;
import Eksempelklasser.Person;
import Eksempelklasser.Student;
import Eksempelklasser.Studium;
import Tabeller.Tabell;

import java.util.Arrays;

public class Program1_4_6 {
    public static void main(String... args)
    {
        // Programkode 1.4.6 c)
        System.out.println("1.4.6");
        System.out.println("1)");

        Person[] p = new Person[5];                       // en persontabell
        p[0] = new Person("Kari", "Svendsen");            // Kari Svendsen
        p[1] = new Person("Boris", "Zukanovic");          // Boris Zukanovic
        p[2] = new Person("Ali", "Kahn");                 // Ali Kahn
        p[3] = new Person("Azra", "Zukanovic");           // Azra Zukanovic
        p[4] = new Person("Kari", "Pettersen");           // Kari Pettersen


        class FornavnKomparator implements Komparator<Person>
        {
            public int compare(Person p1, Person p2)        // to personer
            {
                return p1.fornavn().compareTo(p2.fornavn());  // sammenligner fornavn
            }
        }

        //Komparator<Person> c = new FornavnKomparator();   // en instans av klassen
        //Tabell.innsettingssortering(p, c);                // se Programkode 1.4.6 b)


        System.out.println(Arrays.toString(p));           // Utskrift av tabellen p
        // [Ali Kahn, Azra Zukanovic, Boris Zukanovic, Kari Svendsen, Kari Pettersen]


        // Programkode 1.4.6 e)
        //Komparator<Person> d = (p1,p2) -> p1.fornavn().compareTo(p2.fornavn());
        //Tabell.innsettingssortering(p, d);                // se Programkode 1.4.6 b)
        System.out.println(Arrays.toString(p));           // Utskrift av tabellen p

        // Programkode 1.5.6 f)
        Tabell.innsettingssortering(p, (p1,p2) -> p1.fornavn().compareTo(p2.fornavn()));
        System.out.println(Arrays.toString(p));

        // Programkode 1.4.6 g)
        Student[] s = new Student[9];                             // en studenttabell
        s[0] = new Student("Kari","Svendsen", Studium.Data);      // Kari Svendsen
        s[1] = new Student("Boris","Zukanovic", Studium.IT);      // Boris Zukanovic
        s[2] = new Student("Ali","Kahn", Studium.Anvendt);        // Ali Kahn
        s[3] = new Student("Azra","Zukanovic", Studium.IT);       // Azra Zukanovic
        s[4] = new Student("Kari","Pettersen", Studium.Data);     // Kari Pettersen
        s[5] = new Student("Ola", "Nordmann", Studium.Elektro);   // Ola Nordmann
        s[6] = new Student("Hege", "Hansen", Studium.Enkeltemne);   // Hege Hansen
        s[7] = new Student("Mona", "Sivertsen", Studium.Elektro);   // Mona Sivertsen
        s[8] = new Student("Marius", "Mikkelsen", Studium.Enkeltemne);   // Marius Mikkelsen

        System.out.println("\n2)");

        Tabell.innsettingssortering(s, (s1,s2) -> s1.studium().compareTo(s2.studium()));
        System.out.println(Arrays.toString(s));

        // Programkode 1.4.6 h)
        Tabell.innsettingssortering(s, (s1,s2) -> {
            int cmp = s1.studium().compareTo(s2.studium());
            return cmp != 0 ? cmp : s1.compareTo(s2);
        });
        for (Student t : s ) {
            System.out.println(t);
        }

        // 1.4.6 3)
        Tabell.innsettingssortering(s, (s1, s2) -> {
            int k = s1.studium().compareTo(s2.studium());
            if (k != 0) return k;
            k = s1.fornavn().compareTo(s2.fornavn());
            if (k != 0) return k;
            return s1.etternavn().compareTo(s2.etternavn());
        });

        // Programkode 1.4.6 i)
        String[] o = {"Lars","Anders","Bodil","Kari","Per","Berit"};
        Tabell.innsettingssortering(o, (o1,o2) -> {
            int i = o1.length() - o2.length();
            if (i != 0) return i;
            return o1.compareTo(o2);

        });

        System.out.println("\n5)");
        System.out.println(Arrays.toString(o));
        // Utskrift: [Per, Kari, Lars, Berit, Bodil, Anders]

        String[] l = {"21","18","8","13","20","6","16","25","3","10"};
        Tabell.innsettingssortering(l, (l1,l2) -> {
            int i = l1.length() - l2.length();
            if (i != 0) return i;
            return l1.compareTo(l2);

        });
        System.out.println(Arrays.toString(l));

        // Programkode 1.4.6 h)
        Komparator<Student> t = (t1,t2) ->
        {
            int cmp = t1.studium().name().compareTo(t2.studium().name());
            return cmp != 0 ? cmp : t1.compareTo(t2);
        };

        System.out.println("\n7)");
        //Tabell.innsettingssortering(s, t);
        System.out.println(Arrays.toString(s));
    }
}
