package Uke45;

import Eksempelklasser.LenketHashTabell;

public class Program6_1_4 {
    public static void main(String[] args) {
        System.out.println("6.1.4\n-------------------------------------------------");

        String[] navn = {"Olga","Basir","Ali","Per","Elin","Siri",
                "Ole","Mette","Anne","Åse","Leif","Mona","Lise"};

        LenketHashTabell<String> hashtabell = new LenketHashTabell<>();

        for (String n : navn) hashtabell.leggInn(n);

        System.out.println(hashtabell);
        // [Elin, Basir, Leif, Ole, Olga, Per, Mette, Mona, Anne, Ali, Lise, Åse, Siri]
    }
}
