package Uke41;

import Grensesnitt.Stakk;
import Tabeller.TabellStakk;

import static Tabeller.TabellStakk.snu;

public class Program4_1_2 {
    public static void main(String[] args) {
        // 4.1.2
        // 1) + 2)
        System.out.println("4.2.1");
        System.out.println("-------------------------------------------------");

        Stakk<Integer> stakk = new TabellStakk<>();
        stakk.leggInn(1); stakk.leggInn(2); stakk.leggInn(3);

        System.out.println("\nOppgave 1) og 2):");
        System.out.println(stakk);
        //stakk.nullstill();
        System.out.println(stakk);

        System.out.println("\nOppgave 3):");
        snu(stakk);
        System.out.println(stakk);

        System.out.println("\nOppgave 4):");


    }
}
