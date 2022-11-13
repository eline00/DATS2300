package Eksempelklasser;

import java.util.*;                             // Map og List
import java.io.*;                               // graf fra fil
import java.net.URL;                            // graf fra internett
import java.util.function.Consumer;

public final class Graf implements Iterable<String> { // final: skal ikke arves
    private static final class Node  {            // en indre nodeklasse
        private final String navn;                  // navn/identifikator
        private final List<Node> kanter;            // nodens kanter
        private byte innkanter = 0;                 // antall innkanter
        private boolean besøkt = false;             // hjelpevariabel brukes senere
        private Node forrige = null;                // hjelpevariabel brukes senere

        private Node(String navn) {                  // nodekonstruktør
            this.navn = navn;                         // nodens navn
            kanter = new LinkedList<>();              // oppretter kantlisten
        }

        public String toString() { return navn; }   // nodens navn
    } // Node

    private final Map<String, Node> noder;        // en map til å lagre nodene

    public Graf() { noder = new HashMap<>(); }    // standardkonstruktør

    public Graf(String url) throws IOException {
        this();   // standardkonstruktøren

        BufferedReader inn = new BufferedReader  // leser fra fil
                (new InputStreamReader((new URL(url)).openStream()));

        String linje;
        while ((linje = inn.readLine()) != null)
        {
            String[] navn = linje.split(" ");      // deler opp linjen

            leggInnNode(navn[0]);                  // noden kommer først

            for (int i = 1; i < navn.length; i++)  // så nodene det går kant til
            {
                leggInnNode(navn[i]);                // navnet på naboen
                leggInnKant(navn[0], navn[i]);       // legges inn som nabo
            }
        }

        inn.close();
    }

    public boolean leggInnNode(String navn) {      // ny node
        if (navn == null || navn.length() == 0)
            throw new IllegalArgumentException("Noden må ha et navn!");
        if (noder.get(navn) != null) return false; // finnes navnet fra før?
        return noder.put(navn, new Node(navn)) == null;
    }

    public boolean nodeFinnes(String navn) {     // finnes denne noden?
        return noder.get(navn) != null;
    }

    public Iterator<String> iterator() {         // klassen er iterable
        return noder.keySet().iterator();
    }

    public String[] nodenavn() {               // nodenavnene som en tabell
        return noder.keySet().toArray(new String[0]);
    }

    public void leggInnKant(String franode, String tilnode) {
        if (franode.equals(tilnode)) throw    // sjekker om de er like
                new IllegalArgumentException(franode + " er lik " + tilnode + "!");

        Node fra = noder.get(franode);  // henter franode
        if (fra == null) throw new NoSuchElementException(franode + " er ukjent!");

        Node til = noder.get(tilnode);  // henter tilnode
        if (til == null) throw new NoSuchElementException(tilnode + " er ukjent!");

        if(fra.kanter.contains(til)) throw
                new IllegalArgumentException("Kanten finnes fra før!");

        til.innkanter++;      // en ny innkant
        fra.kanter.add(til);  // legger til i kantlisten
    }

    public void leggInnKanter(String franode, String... tilnoder) {
        for (String tilnode : tilnoder) leggInnKant(franode, tilnode);
    }

    public String kanterFra(String node) {
        Node fra = noder.get(node);  // henter noden
        if (fra == null) return null;  // finnes noden?
        return fra.kanter.toString();  // listens toString-metode
    }

    public boolean erUrettet() { // sjekker om grafen er urettet
        for (Node p : noder.values()) {
            if (p.innkanter != p.kanter.size()) return false;
        }

        for (Node p : noder.values()) {
            for (Node q : p.kanter) {
                if (!q.kanter.contains(p)) return false;
            }
        }
        return true;
    }

    public int antallNoder() {
        return noder.size();
    }

    public boolean erIsolert(String nodenavn) {
        Node p = noder.get(nodenavn);
        if (p == null) throw new NoSuchElementException(nodenavn + " er ukjent!");

        return p.kanter.size() == 0 && p.innkanter == 0;
    }

    public boolean erKant(String franode, String tilnode) {
        Node fra = noder.get(franode);  // henter franode
        if (fra == null) throw new NoSuchElementException(franode + " er ukjent!");

        Node til = noder.get(tilnode);  // henter tilnode
        if (til == null) throw new NoSuchElementException(tilnode + " er ukjent!");

        for (Node n : fra.kanter) {
            if (n.navn.equals(tilnode)) {
                return true;
            }
        }
        return false;
    }

    public int grad(String nodenavn) {
        Node p = noder.get(nodenavn);
        if (p == null) throw new NoSuchElementException(nodenavn + " er ukjent!");

        return p.kanter.size();
    }

    public String[] kantTabellFra(String node) {
        Node n = noder.get(node);
        if (n == null) throw new NoSuchElementException(n + " er ukjent!");

        String[] s = new String[n.kanter.size()];
        int i = 0;
        for (Node noden : n.kanter) {
            s[i++] = noden.navn;
        }
        return s;
    }

    public void nullstill() {
        for (Node p : noder.values())
        {
            p.besøkt = false; p.forrige = null;
        }
    }

    private void dybdeFørstPre(Node p, Consumer<String> oppgave) {
        p.besøkt = true;
        oppgave.accept(p.navn);   // preoppgave - accept() er eneste metode i Consumer

        for (Node q : p.kanter)   // tar alle kantene fra p
        {
            if (!q.besøkt) dybdeFørstPre(q, oppgave);  // rekursivt kall
        }
    }

    public void dybdeFørstPretraversering(String startnode, Consumer<String> oppgave) {
        Node p = noder.get(startnode);
        if (p == null) throw new IllegalArgumentException(startnode + " er ukjent!");
        dybdeFørstPre(p, oppgave);  // kaller den rekursive metoden
    }

    public void breddeFørstTraversering(String startnode, Consumer<String> oppgave) {
        Node p = noder.get(startnode);             // henter startnoden
        if (p == null) throw new IllegalArgumentException(startnode + " er ukjent!");

        Queue<Node> kø = new ArrayDeque<>();       // oppretter en nodekø
        p.besøkt = true;                           // noden p er den første vi besøker
        kø.offer(p);                               // legger noden p i køen

        while (!kø.isEmpty()) {                     // så lenge køen ikke er tom
            p = kø.poll();                           // tar ut en node fra køen
            oppgave.accept(p.navn);                  // utfører oppgaven

            for (Node q : p.kanter) {                 // nodene det går en kant til
                if (!q.besøkt) {                        // denne er ikke besøkt
                    q.besøkt = true;                     // nå er den besøkt
                    kø.offer(q);                         // legger noden i køen
                }
            }
        }
    }

    public void kortestVeiFra(String node) {
        Node p = noder.get(node);             // henter startnoden
        if (p == null) throw new IllegalArgumentException(node + " er ukjent!");

        Queue<Node> kø = new ArrayDeque<>();       // oppretter en kø
        p.besøkt = true;                           // p er den første vi besøker
        kø.offer(p);                               // legger p i køen

        while (!kø.isEmpty()) {                     // så lenge køen ikke er tom
            p = kø.poll();                           // tar ut en node fra køen

            for (Node q : p.kanter)  {                // kantene fra p
                if (!q.besøkt) {                        // denne er ikke besøkt
                    q.besøkt = true;                     // nå er den besøkt
                    q.forrige = p;                       // vi kom dit fra p
                    kø.offer(q);                         // legger noden i køen
                }
            }
        }
    }

    public String veiTil(String node) { // returnerer veien i rett rekkefølge
        Node p = noder.get(node);
        if (p == null) throw new IllegalArgumentException(node + " er ukjent!");
        if (p.forrige == null) return "[], 0";  // ingen vei til p

        Deque<String> stakk = new ArrayDeque<>(); // bruker en deque som stakk
        int noder = -1;

        while (p != null) {
            stakk.push(p.navn);                 // legger nodenavnet på stakken
            p = p.forrige;                      // går til forrige node på veien
            noder++;                            // en node til på veien
        }

        return stakk.toString() + ", " + noder;
    }
} // Graf
