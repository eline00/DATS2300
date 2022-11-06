package Uke43;

import Eksempelklasser.Person;
import Eksempelklasser.SBinTre;

import java.util.Comparator;

public class Program5_2_2 {
    public static void main(String[] args) {
        // 5.2.2
        System.out.println("5.2.2\n--------------------------------------");
        SBinTre<Person> tre1 = SBinTre.sbintre();          // 1. konstruksjonsmetode

        Comparator<Person> c = Comparator.naturalOrder();
        SBinTre<Person> tre2 = SBinTre.sbintre(c);         // 2. konstruksjonsmetode

        System.out.println("\nOppgave 1:");
        System.out.println(tre1.antall() + " " + tre2.antall());

        // Utskrift: 0 0
    }
}
