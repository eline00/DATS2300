package Eksempelklasser;

////////////////// class VGraf //////////////////////////////

import java.io.*;
import java.net.URL;
import java.util.*;

public final class VGraf implements Iterable<String> {
    private static final class Kant {             // en indre klasse
        private final Node til;                    // noden som kanten går til
        private int vekt;                          // kantens vekt

        private Kant(Node til, int vekt) {          // konstruktør
            this.til = til;                          // tilordnes verdi
            this.vekt = vekt;                        // tilordnes verdi
        }

        @Override
        public String toString() {
            return "(" + til.navn + "," + vekt + ")";
        }

    } // Kant

    private static final class Node {             // en indre klasse
        private static final int uendelig = 0x7fffffff;  // maks int-verdi

        private final String navn;                 // navn/identifikator
        private final List<Kant> kanter;           // nodens kanter

        private int avstand = uendelig;            // til senere bruk
        private boolean ferdig = false;            // til senere bruk
        private Node forrige = null;               // til senere bruk

        private Node(String navn) {                 // konstruktør
            this.navn = navn;                        // nodens navn
            kanter = new LinkedList<>();             // en tom liste
        }

        public String toString() {
            return navn;
        }

    } // Node

    private final Map<String, Node> noder;       // en map til å lagre nodene

    public VGraf() {                              // standardkonstruktør
        noder = new TreeMap<>();                   // oppretter en TreeMap
    }

    public VGraf(String url) throws IOException {
        try (BufferedReader inn = new BufferedReader  // leser fra fil
                (new InputStreamReader((new URL(url)).openStream()))) {
            noder = new TreeMap<>();                 // oppretter en TreeMap

            String linje;
            while ((linje = inn.readLine()) != null) {
                String[] s = linje.split(" ");         // deler opp linjen

                String fra = s[0];                     // franode kommer først
                leggInnNode(fra);

                for (int i = 1; i < s.length; i += 2) { // går gjennom kantene
                    String til = s[i];                   // noden kanten går til
                    int vekt = Integer.parseInt(s[i+1]); // kantens vekt

                    leggInnNode(til);                    // legger inn tilnode
                    leggInnKant(fra, til, vekt);         // legger inn kanten
                }
            }
        }
    }

    public boolean leggInnNode(String navn) {     // ny node
        if (navn == null) throw new IllegalArgumentException("navn er null!");
        if (noder.get(navn) != null) return false;

        noder.put(navn, new Node(navn));
        return true;
    }

    public boolean nodeFinnes(String navn) {     // finnes denne noden?
        return noder.get(navn) != null;
    }

    @Override
    public Iterator<String> iterator() {
        return noder.keySet().iterator();
    }

    public String[] nodenavn() {
        return noder.keySet().toArray(new String[0]);
    }

    public int leggInnKant(String franode, String tilnode, int vekt) {
        if (franode.equals(tilnode)) throw    // sjekker om de er like
                new IllegalArgumentException(franode + " er lik " + tilnode + "!");

        Node fra = noder.get(franode);  // henter franode
        if (fra == null) throw new NoSuchElementException(franode + " er ukjent!");

        Node til = noder.get(tilnode);  // henter tilnode
        if (til == null) throw new NoSuchElementException(tilnode + " er ukjent!");

        // sjekker om kanten finnes fra før
        for (Kant kant : fra.kanter) {  // går gjennom kantene
            if (kant.til == til) {
                int gammelvekt = kant.vekt;
                kant.vekt = vekt;
                return gammelvekt;
            }
        }
        // dette er en ny kant
        fra.kanter.add(new Kant(til, vekt));  // legger til i kantlisten

        return vekt;
    }

    public String kanterFra(String node) {              // kantene fra node
        Node fra = noder.get(node);                      // henter noden
        if (fra == null) return node + " er ukjent!";

        StringJoiner sj = new StringJoiner(", ");        // bygger en tegnstreng
        for (Kant k : fra.kanter) sj.add(k.toString());  // går gjennom listen
        return sj.toString();
    }

    public boolean erKant(String franode, String tilnode) {
        Node fra = noder.get(franode);  // henter franode
        if (fra == null) throw new NoSuchElementException(franode + " er ukjent!");

        Node til = noder.get(tilnode);  // henter tilnode
        if (til == null) throw new NoSuchElementException(tilnode + " er ukjent!");

        for (Kant k : fra.kanter) if (k.til == til) return true;

        return false;
    }

    public boolean fjernKant(String franode, String tilnode) {
        Node fra = noder.get(franode);  // henter franode
        if (fra == null) throw new NoSuchElementException(franode + " er ukjent!");

        Node til = noder.get(tilnode);  // henter tilnode
        if (til == null) throw new NoSuchElementException(tilnode + " er ukjent!");

        for (Iterator<Kant> i = fra.kanter.iterator(); i.hasNext(); ) {
            Kant k = i.next();
            if (k.til == til) {
                i.remove();   // fjerner den som nettopp er lest
                return true;  // kanten er fjernet
            }
        }
        return false;  // ingen kant
    }

    public String[] kantTabellFra(String node) {
        Node fra = noder.get(node);                      // henter noden
        if (fra == null) throw new NoSuchElementException(node + " er ukjent!");

        String[] tabell = new String[fra.kanter.size()];
        int i = 0;
        for (Kant k : fra.kanter) tabell[i++] = k.til.navn;
        return tabell;
    }

    public String[] kantTabellTil(String node) {
        Node til = noder.get(node);                      // henter noden
        if (til == null) throw new NoSuchElementException(node + " er ukjent!");

        List<String> liste = new ArrayList<>();

        for (String franode : noder.keySet()) {
            for (Kant k : noder.get(franode).kanter)
            {
                if (k.til == til) liste.add(franode);
            }
        }

        return liste.toArray(new String[liste.size()]);
    }

    public void kortestVeiFra(String nodenavn) { // en enkel versjon
        Node start = noder.get(nodenavn); // henter startnoden
        if (start == null) throw new NoSuchElementException(nodenavn + " er ukjent!");

        List<Node> aktiv = new ArrayList<>();  // usortert liste for aktive noder
        start.avstand = 0;                     // start har avstand lik 0
        aktiv.add(start);                      // startnoden aktiviseres

        while (!aktiv.isEmpty()) {
            int m = 0;  // leter i aktivlisten etter den med minst avstand
            for (int i = 1; i < aktiv.size(); i++) {   // resten av de aktive
                if (aktiv.get(i).avstand < aktiv.get(m).avstand) m = i;
            }

            Node denne = aktiv.remove(m);           // minst avstand - se Oppgave 2
            denne.ferdig = true;                    // denne er tatt ut

            for (Kant k : denne.kanter) {            // kantene til denne
                Node etterfølger = k.til;             // direkte etterfølger til denne
                if (etterfølger.ferdig) continue;     // tar ikke med de ferdige

                if (denne.avstand + k.vekt < etterfølger.avstand) { // sammenligner
                    if (etterfølger.avstand == Node.uendelig) aktiv.add(etterfølger);
                    etterfølger.avstand = denne.avstand + k.vekt;  // oppdaterer
                    etterfølger.forrige = denne;  // vei til etterfølger går via denne
                }
            } // for
        } // while
    } // metode

    public int avstand(String nodenavn) {
        Node node = noder.get(nodenavn);
        if (node == null) throw new NoSuchElementException(node + " er ukjent!");
        return node.avstand;
    }

    public String veiTil(String nodenavn) {
        Node node = noder.get(nodenavn);
        if (node == null) throw new NoSuchElementException(node + " er ukjent!");

        Deque<String> kø = new LinkedList<>();
        while (node != null) {
            kø.addFirst(node.navn);
            node = node.forrige;
        }

        return kø.toString();
    }

    public void nullstill() {
        for (Node node : noder.values()) {
            node.avstand = Node.uendelig;
            node.ferdig = false;
            node.forrige = null;
        }
    }
} // VGraf