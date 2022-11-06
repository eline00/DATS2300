package Tabeller;

import Grensesnitt.Stakk;

import java.util.*;

public class TabellStakk<T> implements Stakk<T>
{
    private T[] a;                     // en T-tabell
    private int antall;                // antall verdier på stakken

    public TabellStakk()               // konstruktør - tabellengde 8
    {
        this(8);
    }

    @SuppressWarnings("unchecked")     // pga. konverteringen: Object[] -> T[]
    public TabellStakk(int lengde)     // valgfri tabellengde
    {
        if (lengde < 0)
            throw new IllegalArgumentException("Negativ tabellengde!");

        a = (T[])new Object[lengde];     // oppretter tabellen
        antall = 0;                      // stakken er tom
    }


    @Override
    public void leggInn(T verdi) {
        if (antall == a.length)
            a = Arrays.copyOf(a, antall == 0 ? 1 : 2*antall);   // dobler

        a[antall++] = verdi;
    }

    @Override
    public T kikk() {
        if (antall == 0) throw new NoSuchElementException("Stakken er tom!"); // sjekker om stakken er tom

        return a[antall-1];    // returnerer den øverste verdien
    }

    @Override
    public T taUt() {
        if (antall == 0) throw new NoSuchElementException("Stakken er tom!"); // sjekker om stakken er tom

        antall--;             // reduserer antallet

        T temp = a[antall];   // tar var på det øverste objektet
        a[antall] = null;     // tilrettelegger for resirkulering

        return temp;          // returnerer den øverste verdien
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }

    @Override
    public void nullstill() {
        if (antall == 0) throw new NoSuchElementException("Stakken er tom!"); // sjekker om stakken er tom

        for (int i = 0; i < a.length; i++) {
            a[i] = null;
        }

        antall = 0;
    }

    public static <T> void snu(Stakk<T> A) {
        Stakk<T> B = new TabellStakk<>();     // hjelpestakk
        int n = A.antall()-1;

        while (n > 0) {
            T temp = A.taUt();      // tar ut øverste element i A
            for (int i = 0; i < n; i++) {
                B.leggInn(A.taUt());        // legger inn resten av verdiene inn i B
            }
            A.leggInn(temp);        // legger temp tilbake i A
            while (!B.tom()) A.leggInn(B.taUt());       // legger inn resten av verdiene tilbake i A
            n--;
        }
    }

    public static <T> void kopier(Stakk<T> A, Stakk<T> B) {
        int lengde = A.antall();

        while (lengde > 0) {
            for (int i = 0; i < lengde; i++) {
                B.leggInn(A.taUt());
            }
            T temp = B.kikk();
            for (int j = 0; j < lengde; j++) {
                A.leggInn(B.taUt());
            }
            B.leggInn(temp);
            lengde--;
        }
    }

    public static <T> void sorter(Stakk<T> A, Comparator<? super T> c) {
        Stakk<T> B = new TabellStakk<T>();
        T temp; int n = 0;

        while (!A.tom())
        {
            temp = A.taUt();
            n = 0;
            while (!B.tom() && c.compare(temp,B.kikk()) < 0)
            {
                n++; A.leggInn(B.taUt());
            }
            B.leggInn(temp);
            for (int i = 0; i < n; i++) B.leggInn(A.taUt());
        }

        while (!B.tom()) A.leggInn(B.taUt());
    }


    public String toString() {
        if (antall == 0) return "[]";   // hvis stakken er tom

        StringBuilder s = new StringBuilder();
        s.append("[").append(a[0]);   // legger til første element

        for (int i = 1; i < a.length; i++) {
            if (a[i] != null) {
                s.append(",").append(" ").append(a[i]);    // legger til resten av emenetene
            }
        }

        s.append("]");

        return s.toString();
    }

}  // class TabellStakk
