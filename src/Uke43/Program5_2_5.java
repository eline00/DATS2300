package Uke43;

import Eksempelklasser.SBinTre;

public class Program5_2_5 {
    public static void main(String[] args) {
        // 5.2.5
        System.out.println("5.2.1\n--------------------------------------------");

        System.out.println("\nOppgave 1:");
        SBinTre<String> tre = SBinTre.balansert("ABCDEFGHIJKLMNO".split(""));
        System.out.println(tre.antall() + " " + tre.høyde() + " " + tre);
    }
}
