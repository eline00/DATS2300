package Tabeller;

import Grensesnitt.Kø;
import Grensesnitt.Stakk;

import java.util.NoSuchElementException;

public class TabellKø<T> implements Kø<T>
{
    private T[] a;      // en tabell
    private int fra;    // posisjonen til den første i køen
    private int til;    // posisjonen til første ledige plass

    @SuppressWarnings("unchecked")      // pga. konverteringen: Object[] -> T[]
    public TabellKø(int lengde)
    {
        if (lengde < 1)
            throw new IllegalArgumentException("Må ha positiv lengde!");

        a = (T[])new Object[lengde];

        fra = til = 0;    // a[fra:til> er tom
    }

    public TabellKø()   // standardkonstruktør
    {
        this(8);
    }

    @Override
    public boolean leggInn(T verdi)   // null-verdier skal være tillatt
    {
        a[til] = verdi;                                 // ny verdi bakerst
        til++;                                          // øker til med 1
        if (til == a.length) til = 0;                   // hopper til 0
        if (fra == til) a = utvidTabell(2*a.length);    // sjekker og dobler
        return true;                                    // vellykket innlegging
    }

    private T[] utvidTabell(int lengde)
    {
        @SuppressWarnings("unchecked")      // pga. konverteringen: Object[] -> T[]
        T[] b = (T[])new Object[lengde];  // ny tabell

        // kopierer intervallet a[fra:a.length> over i b
        System.arraycopy(a,fra,b,0,a.length - fra);

        // kopierer intervallet a[0:fra> over i b
        System.arraycopy(a,0,b,a.length - fra, fra);

        fra = 0;
        til = a.length;

        return b;
    }

    @Override
    public T kikk() {
        if (fra == til) throw new NoSuchElementException("Køen er tom!"); // sjekker om stakken er tom
        return a[fra];
    }

    @Override
    public T taUt()
    {
        if (fra == til) throw new NoSuchElementException("Køen er tom!");  // sjekker om køen er tom

        T temp = a[fra];                  // tar vare på den første i køen
        a[fra] = null;                    // nuller innholdet
        fra++;                            // øker fra med 1
        if (fra == a.length) fra = 0;     // hopper til 0
        return temp;                      // returnerer den første
    }

    @Override
    public int antall() {
        return fra <= til ? til - fra : a.length + til - fra;
    }

    @Override
    public boolean tom() {
        return fra == til;
    }

    @Override
    public void nullstill() {
        while (fra != til) {
            a[fra++] = null;
            if (fra == a.length) fra = 0;
        }
    }

    public int indeksTil(T verdi) {
        if (fra == til) throw new NoSuchElementException("Køen er tom!");

        int indeksTil = 0;
        for (int i = fra; i < til; i++) {
            if (a[i] == verdi) {
                indeksTil = i;
                break;
            } else {
                indeksTil = -1;
            }
        }
        return indeksTil;
    }

    public String toString() {
        if (fra == til) return "[]";

        StringBuilder s = new StringBuilder();
        s.append("[").append(a[fra]);

        for (int i = fra+1; i < til; i++) {
            if (a[i] != null) {
                s.append(",").append(" ").append(a[i]);
            }
        }
        s.append("]");

        return s.toString();
    }

    public static <T> void snu(Stakk<T> A) {
        Kø<T> K = new TabellKø<>();        // hjelpekø

        while (!A.tom()) K.leggInn(A.taUt());
        while (!K.tom()) A.leggInn(K.taUt());
    }

    public static <T> void snu(Kø<T> A) {
        //Snu ved hvelp av én stakk
        /*
        Stakk<T> B = new TabellStakk<>();

        while (!A.tom()) B.leggInn(A.taUt());
        while (!B.tom()) A.leggInn(B.taUt());
         */

        // Snu ved hjelp av to hjelpekøer
        Kø<T> B = new TabellKø<T>();
        Kø<T> C = new TabellKø<T>();

        while (!A.tom())
        {
            while (!B.tom()) C.leggInn(B.taUt());
            B.leggInn(A.taUt());
            while (!C.tom()) B.leggInn(C.taUt());
        }
        while (!B.tom()) A.leggInn(B.taUt());
    }

} // class TabellKø