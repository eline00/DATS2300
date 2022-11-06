package Uke37;

import Eksempelklasser.Student;
import Eksempelklasser.Studium;
import Tabeller.Tabell;

public class Program1_4_5 {
    public static void main(String[] args) {
        System.out.println("1.4.5:");
        System.out.println("1)");

        // Programkode 1.4.5 b)
        for (Studium s : Studium.values()) {
            System.out.println(s.toString() + " (" + s.name() + ")");
        }
        // Ingeniørfag - data (Data)
        // Informasjonsteknologi (IT)
        // Anvendt datateknologi (Anvendt)
        // Enkeltemnestudent (Enkeltemne)

        System.out.println("\n5)");

        // Programkode 1.4.5 c)
        Student[] s = new Student[9];  // en Studenttabell

        s[0] = new Student("Kari", "Svendsen", Studium.Data);    // Kari Svendsen
        s[1] = new Student("Boris", "Zukanovic", Studium.IT);    // Boris Zukanovic
        s[2] = new Student("Ali", "Kahn", Studium.Anvendt);      // Ali Kahn
        s[3] = new Student("Azra", "Zukanovic", Studium.IT);     // Azra Zukanovic
        s[4] = new Student("Kari", "Pettersen", Studium.Data);   // Kari Pettersen
        s[5] = new Student("Ola", "Nordmann", Studium.Elektro);   // Ola Nordmann
        s[6] = new Student("Hege", "Hansen", Studium.Enkeltemne);   // Hege Hansen
        s[7] = new Student("Mona", "Sivertsen", Studium.Elektro);   // Mona Sivertsen
        s[8] = new Student("Marius", "Mikkelsen", Studium.Enkeltemne);   // Marius Mikkelsen


        Tabell.innsettingssortering(s);                     // Programkode 1.4.2 e)
        for (Student t : s) System.out.println(t);

        // Utskrift:
        // Hege Hansen Enkeltemne
        // Ali Kahn Anvendt
        // Marius Mikkelsen Enkeltemne
        // Ola Nordmann Elektro
        // Kari Pettersen Data
        // Mona Sivertsen Elektro
        // Kari Svendsen Data
        // Azra Zukanovic IT
        // Boris Zukanovic IT

    }
}
