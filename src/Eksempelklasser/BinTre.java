package Eksempelklasser;

import Grensesnitt.Kø;
import Grensesnitt.Oppgave;
import Grensesnitt.Stakk;
import Tabeller.TabellKø;
import Tabeller.TabellStakk;

import java.util.*;

public class BinTre<T> implements Iterable<T> {         // et generisk binærtre
    private static final class Node<T>  // en indre nodeklasse
    {
        private T verdi;            // nodens verdi
        private Node<T> venstre;    // referanse til venstre barn/subtre
        private Node<T> høyre;      // referanse til høyre barn/subtre

        private Node(T verdi, Node<T> v, Node<T> h)    // konstruktør
        {
            this.verdi = verdi; venstre = v; høyre = h;
        }

        private Node(T verdi) { this.verdi = verdi; }  // konstruktør

    } // class Node<T>

    private Node<T> rot;      // referanse til rotnoden
    private int antall;       // antall noder i treet
    private int endringer;

    public BinTre() {       // konstruktør
        rot = null;
        antall = 0;
        endringer = 0;
    }

    public BinTre(int[] posisjon, T[] verdi) { // konstruktør
        if (posisjon.length > verdi.length) throw new
                IllegalArgumentException("Verditabellen har for få elementer!");

        for (int i = 0; i < posisjon.length; i++) leggInn(posisjon[i],verdi[i]);
    }

    public final void leggInn(int posisjon, T verdi) {  // final: kan ikke overstyres
        if (posisjon < 1) throw new
                IllegalArgumentException("Posisjon (" + posisjon + ") < 1!");

        Node<T> p = rot, q = null;    // nodereferanser

        int filter = Integer.highestOneBit(posisjon) >> 1;   // filter = 100...00

        while (p != null && filter > 0) {
            q = p;
            p = (posisjon & filter) == 0 ? p.venstre : p.høyre;
            filter >>= 1;  // bitforskyver filter
        }

        if (filter > 0) throw new
                IllegalArgumentException("Posisjon (" + posisjon + ") mangler forelder!");
        else if (p != null) throw new
                IllegalArgumentException("Posisjon (" + posisjon + ") finnes fra før!");

        p = new Node<>(verdi);          // ny node

        if (q == null) rot = p;         // tomt tre - ny rot
        else if ((posisjon & 1) == 0)   // sjekker siste siffer i posisjon
            q.venstre = p;                // venstre barn til q
        else
            q.høyre = p;                  // høyre barn til q

        antall++;                       // en ny verdi i treet
        endringer++;
    }

    //public int antall() { return antall; }               // returnerer antallet

    private static int antall(Node<?> p) { // ? betyr vilkårlig type
        if (p == null) return 0;            // et tomt tre har 0 noder

        return 1 + antall(p.venstre) + antall(p.høyre);
    }

    public int antall() {
        return antall(rot);                 // kaller hjelpemetoden
    }

    public int antallBladnoder(Node<?> p) {
        if (p.venstre == null && p.høyre == null) return 1;

        return (p.venstre == null ? 0 : antallBladnoder(p.venstre))
                + (p.høyre == null ? 0 : antallBladnoder(p.høyre));
    }

    public int antallBladnoder() {
        return rot == null ? 0 : antallBladnoder(rot);
    }

    private static int høyde(Node<?> p) { // ? betyr vilkårlig type
        if (p == null) return -1;          // et tomt tre har høyde -1

        return 1 + Math.max(høyde(p.venstre), høyde(p.høyre));
    }

    public int høyde()
    {
        return høyde(rot);                 // kaller hjelpemetoden
    }

    private static <T> boolean inneholder(Node<T> p, T verdi)
    {
        if (p == null) return false;    // kan ikke ligge i et tomt tre
        return verdi.equals(p.verdi) || inneholder(p.venstre,verdi)
                || inneholder(p.høyre,verdi);
    }

    public boolean inneholder(T verdi)
    {
        return inneholder(rot,verdi);   // kaller den private metoden
    }

    private static <T> int posisjon(Node<T> p, int k, T verdi) {
        if (p == null) return -1;                  // ligger ikke i et tomt tre
        if (verdi.equals(p.verdi)) return k;       // verdi ligger i p
        int i = posisjon(p.venstre,2*k,verdi);     // leter i venstre subtre
        if (i > 0) return i;                       // ligger i venstre subtre
        return posisjon(p.høyre,2*k+1,verdi);      // leter i høyre subtre
    }

    public int posisjon(T verdi) {
        return posisjon(rot,1,verdi);  // kaller den private metoden
    }

    public int makspos() {
        IntObject o = new IntObject(-1);
        if (!tom()) makspos(rot, 1, o);
        return o.get();
    }

    private static void makspos(Node<?> p, int pos, IntObject o) {
        if (pos > o.get()) o.set(pos);
        if (p.venstre != null) makspos(p.venstre, 2*pos, o);
        if (p.høyre != null) makspos(p.høyre, 2*pos + 1, o);

    }

    public boolean tom() { return antall == 0; }         // tomt tre?

    private Node<T> finnNode(int posisjon) { // finner noden med gitt posisjon

        if (posisjon < 1) return null;

        Node<T> p = rot;   // nodereferanse
        int filter = Integer.highestOneBit(posisjon >> 1);   // filter = 100...00

        for (; p != null && filter > 0; filter >>= 1)
            p = (posisjon & filter) == 0 ? p.venstre : p.høyre;

        return p;   // p blir null hvis posisjon ikke er i treet
    }

    public boolean finnes(int posisjon) {   // finnes posisjon fra før?
        return finnNode(posisjon) != null;
    }

    public T hent(int posisjon) {       // verdien i noden med gitt posisjon
        Node<T> p = finnNode(posisjon);

        if (p == null) throw new
                IllegalArgumentException("Posisjon (" + posisjon + ") finnes ikke i treet!");

        return p.verdi;
    }

    public T oppdater(int posisjon, T nyverdi) {  // ny verdi i noden med gitt posisjon
        Node<T> p = finnNode(posisjon);

        if (p == null) throw new
                IllegalArgumentException("Posisjon (" + posisjon + ") finnes ikke i treet!");

        T gammelverdi = p.verdi;
        p.verdi = nyverdi;

        endringer++;
        return gammelverdi;
    }


    public T fjern(int posisjon) {        // fjerner noden med gitt posisjon
        if (posisjon < 1) throw new
                IllegalArgumentException("Posisjon(" + posisjon + ") < 1!");

        Node<T> p = rot, q = null;               // hjelpepekere
        int filter = Integer.highestOneBit(posisjon >> 1);   // binært siffer

        while (p != null && filter > 0) {
            q = p;
            if ((filter & posisjon) == 0) {
                p = p.venstre;
            } else {
                p = p.høyre;
            }
            filter >>= 1;
        }

        if (p == null) throw new
                IllegalArgumentException("Posisjon(" + posisjon + ") er utenfor treet!");

        if (p.venstre != null || p.høyre != null) throw new
                IllegalArgumentException("Posisjon(" + posisjon + ") er ingen bladnode!");

        if (p == rot) rot = null;
        else if (p == q.venstre) q.venstre = null;
        else q.høyre = null;

        antall--;
        endringer++;
        return p.verdi;
    }

    public void nivåorden(Oppgave<? super T> oppgave) {    // ny versjon

        if (tom()) return;                   // tomt tre
        Kø<Node<T>> kø = new TabellKø<>();   // Se Avsnitt 4.2.3
        kø.leggInn(rot);                     // legger inn roten

        while (!kø.tom())                    // så lenge køen ikke er tom
        {
            Node<T> p = kø.taUt();             // tar ut fra køen
            oppgave.utførOppgave(p.verdi);     // den generiske oppgaven

            if (p.venstre != null) kø.leggInn(p.venstre);
            if (p.høyre != null) kø.leggInn(p.høyre);
        }
    }

    public int[] nivåer() {  // returnerer en tabell som inneholder nivåantallene

        if (tom()) return new int[0];       // en tom tabell for et tomt tre

        int[] a = new int[8];               // hjelpetabell
        Kø<Node<T>> kø = new TabellKø<>();  // hjelpekø
        int nivå = 0;                       // hjelpevariabel

        kø.leggInn(rot);    // legger roten i køen

        while (!kø.tom())   // så lenge som køen ikke er tom
        {
            // utvider a hvis det er nødvendig
            if (nivå == a.length) a = Arrays.copyOf(a,2*nivå);

            int k = a[nivå] = kø.antall();  // antallet på dette nivået

            for (int i = 0; i < k; i++)  // alle på nivået
            {
                Node<T> p = kø.taUt();

                if (p.venstre != null) kø.leggInn(p.venstre);
                if (p.høyre != null) kø.leggInn(p.høyre);
            }

            nivå++;  // fortsetter på neste nivå
        }

        return Arrays.copyOf(a, nivå);  // fjerner det overflødige
    }

    private static <T> void preorden(Node<T> p, Oppgave<? super T> oppgave) {
        oppgave.utførOppgave(p.verdi);                       // utfører oppgaven

        if (p.venstre != null) preorden(p.venstre,oppgave);  // til venstre barn
        if (p.høyre != null) preorden(p.høyre,oppgave);      // til høyre barn
    }

    public void preorden(Oppgave<? super T> oppgave) {
        if (!tom()) preorden(rot,oppgave);  // sjekker om treet er tomt
    }

    public void preorden2(Oppgave<? super T> oppgave) {  // ny versjon
        if (tom()) return;

        Stakk<Node<T>> stakk = new TabellStakk<>();
        Node<T> p = rot;    // starter i roten

        while (true) {
            oppgave.utførOppgave(p.verdi);

            if (p.venstre != null) {
                if (p.høyre != null) stakk.leggInn(p.høyre);
                p = p.venstre;
            } else if (p.høyre != null) { // her er p.venstre lik null
                p = p.høyre;
            } else if (!stakk.tom()) {    // her er p en bladnode
                p = stakk.taUt();
            } else {                      // p er en bladnode og stakken er tom
                break;                   // traverseringen er ferdig
            }
        }
    }

    private static <T> void inorden(Node<T> p, Oppgave<? super T> oppgave) {
        if (p.venstre != null) inorden(p.venstre,oppgave);
        oppgave.utførOppgave(p.verdi);
        if (p.høyre != null) inorden(p.høyre,oppgave);
    }

    public void inorden(Oppgave <? super T> oppgave) {
        if (!tom()) inorden(rot,oppgave);
    }

    public void inorden2(Oppgave<? super T> oppgave){  // iterativ inorden
        if (tom()) return;            // tomt tre

        Stakk<Node<T>> stakk = new TabellStakk<>();
        Node<T> p = rot;   // starter i roten og går til venstre
        for ( ; p.venstre != null; p = p.venstre) {
            stakk.leggInn(p);
        }

        while (true) {
            oppgave.utførOppgave(p.verdi);      // oppgaven utføres

            if (p.høyre != null) {        // til venstre i høyre subtre
                for (p = p.høyre; p.venstre != null; p = p.venstre)
                {
                    stakk.leggInn(p);
                }
            }
            else if (!stakk.tom()) {
                p = stakk.taUt();   // p.høyre == null, henter fra stakken
            } else break;          // stakken er tom - vi er ferdig
        } // while
    }

    private static <T> void postorden(Node<T> p, Oppgave<? super T> oppgave) {
        if (p.venstre != null) postorden(p.venstre,oppgave);
        if (p.høyre != null) postorden(p.høyre,oppgave);
        oppgave.utførOppgave(p.verdi);
    }

    public void postorden(Oppgave <? super T> oppgave) {
        if (!tom()) postorden(rot,oppgave);
    }

    public T førstInorden() {
        if (tom()) throw new NoSuchElementException("Treet er tomt!");

        Node<T> p = rot;
        while (p.venstre != null) p = p.venstre;

        return p.verdi;
    }

    public T førstPostorden() {
        if (tom()) throw new NoSuchElementException("Treet er tomt!");

        Node<T> p = rot;
        while (true)
        {
            if (p.venstre != null) p = p.venstre;
            else if (p.høyre != null) p = p.høyre;
            else return p.verdi;
        }
    }

    private static <T> Node<T> trePreorden(T[] preorden, int rot, T[] inorden, int v, int h) {
        if (v > h) return null;  // tomt intervall -> tomt tre
        int k = v; T verdi = preorden[rot];
        while (!verdi.equals(inorden[k])) k++;  // finner verdi i inorden[v:h]

        Node<T> venstre = trePreorden(preorden, rot + 1, inorden, v, k - 1);
        Node<T> høyre   = trePreorden(preorden, rot + 1 + k - v, inorden, k + 1, h);

        return new Node<>(verdi, venstre, høyre);
    }

    public static <T> BinTre<T> trePreorden(T[] preorden, T[] inorden) {
        BinTre<T> tre = new BinTre<>();
        tre.rot = trePreorden(preorden, 0, inorden, 0, inorden.length - 1);

        tre.antall = preorden.length;
        return tre;
    }

    public void nullstill() {
        if (!tom()) nullstill(rot);
        rot = null;
        antall = 0;
    }

    private void nullstill(Node<T> p) {
        if (p.venstre != null) {
            nullstill(p.venstre);
            p.venstre = null;
        }
        if (p.høyre != null) {
            nullstill(p.høyre);
            p.høyre = null;
        }
        p.verdi = null;
    }

    @Override
    public String toString()
    {
        StringJoiner s = new StringJoiner(", ", "[", "]");
        if (!tom()) inorden(x -> s.add(x != null ? x.toString() : "null"));
        return s.toString();
    }

    private static <T> Node<T> random(int n, Random r)
    {
        if (n == 0) return null;                      // et tomt tre
        else if (n == 1) return new Node<>(null);     // tre med kun en node

        int k = r.nextInt(n);    // k velges tilfeldig fra [0,n>

        Node<T> venstre = random(k,r);     // tilfeldig tre med k noder
        Node<T> høyre = random(n-k-1,r);   // tilfeldig tre med n-k-1 noder

        return new Node<>(null,venstre,høyre);
    }

    public static <T> BinTre<T> random(int n)
    {
        if (n < 0) throw new IllegalArgumentException("Må ha n >= 0!");

        BinTre<T> tre = new BinTre<>();
        tre.antall = n;

        tre.rot = random(n,new Random());   // kaller den private metoden

        return tre;
    }

    public Iterator<T> iterator()     // skal ligge i class BinTre
    {
        return new InordenIterator();
    }

    private class InordenIterator implements Iterator<T> {
        private final Stakk<Node<T>> s = new TabellStakk<>();
        private Node<T> p = null;
        private int iteratorendringer;

        private Node<T> først(Node<T> q) {   // en hjelpemetode
            while (q.venstre != null)        // starter i q
            {
                s.leggInn(q);                  // legger q på stakken
                q = q.venstre;                 // går videre mot venstre
            }
            return q;                        // q er lengst ned til venstre
        }

        private InordenIterator() {         // konstruktør
            if (tom()) return;               // treet er tomt
            p = først(rot);                  // bruker hjelpemetoden
            iteratorendringer = endringer;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException("Ingen verdier!");

            if (iteratorendringer != endringer)
                throw new ConcurrentModificationException("Teet er endret!");

            T verdi = p.verdi;                        // tar vare på verdien

            if (p.høyre != null) p = først(p.høyre);  // p har høyre subtre
            else if (s.tom()) p = null;               // stakken er tom
            else p = s.taUt();                        // tar fra stakken

            return verdi;                             // returnerer verdien
        }

        @Override
        public boolean hasNext() {
            return p != null;
        }
    } // InordenIterator

    public Iterator<T> omvendtIterator(){
        return new OmvendtInordenIterator();
    }

    private class OmvendtInordenIterator implements Iterator<T> {
        private final Stakk<Node<T>> s;
        private Node<T> p = null;

        private Node<T> sist(Node<T> q) {   // en hjelpemetode
            while (q.høyre != null) {      // starter i q
                s.leggInn(q);                  // legger q på stakken
                q = q.høyre;                 // går videre mot høyre
            }
            return q;                        // q er lengst ned til høyre
        }

        private OmvendtInordenIterator() {         // konstruktør
            s = new TabellStakk<>();
            if (tom()) return;               // treet er tomt
            p = sist(rot);                  // bruker hjelpemetoden
        }

        @Override
        public boolean hasNext() {
            return p != null;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException("Ingen verdier!");

            T verdi = p.verdi;                        // tar vare på verdien

            if (p.venstre != null) p = sist(p.venstre);  // p har høyre subtre
            else if (s.tom()) p = null;               // stakken er tom
            else p = s.taUt();                        // tar fra stakken

            return verdi;                             // returnerer verdien
        }
    }

    public Iterator<T> preordenIterator() {
        return new PreordenIterator();
    }

    private class PreordenIterator implements Iterator<T> {
        private final Stakk<Node<T>> s;
        private Node<T> p;

        private PreordenIterator() {         // konstruktør
            s = new TabellStakk<>();
            p = rot;                  // bruker hjelpemetoden
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();

            T verdi = p.verdi;

            if (p.venstre != null)                  // går til venstre
            {
                if (p.høyre != null) s.leggInn(p.høyre);
                p = p.venstre;
            }
            else if (p.høyre != null) p = p.høyre;  // går til høyre
            else if (s.tom()) p = null;             // ingen flere i treet
            else p = s.taUt();                      // tar fra satkken

            return verdi;
        }

        @Override
        public boolean hasNext() {
            return p != null;
        }
    } // InordenIterator




} // class BinTre<T>
