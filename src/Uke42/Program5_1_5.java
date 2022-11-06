package Uke42;

import Eksempelklasser.BinTre;

public class Program5_1_5 {
    public static void main(String[] args) {
        // Oppgave 1:
        int[] posisjon = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,17,18,19,21,23,26,27,29};
        Character[] verdi = {'A','B','C','D','E','F','G','H','I','J','K',
                'L','M','N','O','P','Q','R','S','T','U','V'};

        BinTre<Character> tre = new BinTre<>(posisjon, verdi);  // den nye konstruktøren

        // Oppgave 4:
        int[] pos = {1,2,3,5,10,11,22,23,44,47};
        Integer[] val = {1,2,3,4,5,6,7,8,9,10};

        BinTre<Integer> tre2 = new BinTre<>(pos, val);

        // Oppgave 5:
        BinTre<Integer> tre3 = new BinTre<>();

        int n = 15;
        for (int i = 1; i <= n; i++) tre3.leggInn(i,i);

        // Oppgave 6:
        int[] p = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384};
        Integer[] v = {1, 2, 3, 4, 5, 6, 7, 8 ,9, 10, 11, 12, 13, 14, 15};

        BinTre<Integer> tre4 = new BinTre<>(p, v);

        // Oppgave 7:
        int[] p2 = {1, 2, 3, 4, 7, 8, 15, 16, 31, 32, 63, 64, 127, 128, 255};
        Integer[] v2 = {1, 2, 3, 4, 5, 6, 7, 8 ,9, 10, 11, 12, 13, 14, 15};

        BinTre<Integer> tre5 = new BinTre<>(p2, v2);
    }
}
