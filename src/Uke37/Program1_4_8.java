package Uke37;

import Eksempelklasser.Komparator;
import Tabell.Tabell;

import java.util.Arrays;

public class Program1_4_8 {
    public static void main(String[] args) {
        System.out.println("1.4.8:");

        String[] s = {"21","18","8","13","20","6","16","25","3","10"};

        //Tabell.innsettingssortering(s, Komparator.orden(String::length).deretter(x -> x));
        System.out.println("\n4)");
        System.out.println(Arrays.toString(s));
    }
}
