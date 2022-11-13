package Eksempelklasser;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringJoiner;
import java.util.function.Consumer;

public final class MGraf {
    private boolean[][] graf;                // grafmatrisen
    private int antall;                      // antall noder
    private String[] navn;                   // nodenavn - usortert
    private String[] snavn;                  // nodenavn - sortert
    private int[] indeks;                    // indekser
    private int[] forrige;                   // for senere bruk

    public MGraf(int dimensjon) {                 // konstruktør
        graf = new boolean[dimensjon][dimensjon];  // grafmatrisen
        antall = 0;                                // foreløpig ingen noder
        navn = new String[dimensjon];              // nodenavn - usortert
        snavn = new String[dimensjon];             // nodenavn - sortert
        indeks = new int[dimensjon];               // indekstabell
    }

    public MGraf() {  // standardkonstruktør
        this(10);
    }

    public MGraf(String url) throws IOException
    {
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

    public int antallNoder() {   // antall noder i grafem
        return antall;
    }

    public int dimensjon() {   // dimensjonen til tabellene
        return graf.length;
    }

    public String[] nodenavn() {   // navn på alle nodene sortert
        return Arrays.copyOf(snavn, antall);
    }

    private int finn(String nodenavn) {   // privat hjelpemetode - finner raden noden ligger i
        return Arrays.binarySearch(snavn, 0, antall, nodenavn);
    }

    public boolean nodeFinnes(String nodenavn) {   // finnes denne noden?
        return finn(nodenavn) >= 0;
    }

    private void utvid() {
        int nydimensjon = graf.length == 0 ? 1 : 2*graf.length;  // dobler

        navn = Arrays.copyOf(navn, nydimensjon);       // usortert navnetabell
        snavn = Arrays.copyOf(snavn, nydimensjon);     // sortert navnetabell
        indeks = Arrays.copyOf(indeks, nydimensjon);   // indekstabell

        boolean[][] gammelgraf = graf;
        graf = new boolean[nydimensjon][nydimensjon];  // grafmatrisen

        for (int i = 0; i < antall; i++) {
            System.arraycopy(gammelgraf[i], 0, graf[i], 0, antall);
        }
    }

    public boolean leggInnNode(String nodenavn) {    // ny node
        if (navn == null || nodenavn.length() == 0)
            throw new IllegalArgumentException("Noden må ha et navn!");

        int rad = finn(nodenavn);    // raden i den sorterte navnetabellen
        if (rad >= 0) return false;  // finnes fra før!

        if (antall >= graf.length) utvid();  // sjekker om det er fullt

        rad = -(rad + 1);  // for å få innsettingspunktet

        for (int i = antall; i > rad; i--) {
            snavn[i] = snavn[i - 1];    // forskyver i snavn[]
            indeks[i] = indeks[i - 1];  // forskyver i infeks[]
        }

        snavn[rad] = nodenavn;      // på rett sortert plass i snavn[]
        navn[antall] = nodenavn;    // på neste ledige plass
        indeks[rad] = antall;       // antall blir indeks i navn[] (bakerst)

        antall++;  // en ny node
        return true;  // vellykket innlegging
    }

    public void leggInnKant(String franode, String tilnode) {
        if (franode.equals(tilnode)) throw    // sjekker om de er like
                new IllegalArgumentException(franode + " er lik " + tilnode + "!");

        int i = finn(franode);  // indeks i den sorterte navnetabellen
        if (i < 0) throw new NoSuchElementException(franode + " er ukjent!");

        int j = finn(tilnode);  // indeks i den sorterte navnetabellen
        if (j < 0) throw new NoSuchElementException(tilnode + " er ukjent!");

        int fra = indeks[i];       // raden i matrisen
        int til = indeks[j];   // kolonnen i matrisen

        if (graf[fra][til]) throw  // true for avkrysset
                new IllegalArgumentException("Kanten finnes fra før!");

        graf[fra][til] = true;     // krysser av
    }

    public void leggInnKanter(String franode, String... tilnoder) {
        for (String tilnode : tilnoder) leggInnKant(franode, tilnode);
    }

    public String kanterFra(String nodenavn) {
        int i = finn(nodenavn);   // indeksen i den sorterte navnetabellen
        if (i < 0) return null;
        int rad = indeks[i];      // indeksen i den usorterte navnetabellen

        StringJoiner sj = new StringJoiner(", ", "[", "]");

        for (int kolonne = 0; kolonne < antall; kolonne++)
            if (graf[rad][kolonne]) sj.add(navn[kolonne]);

        return sj.toString();
    }

    private void dybdeFørstPre(int i, boolean[] besøkt, Consumer<String> oppgave) {
        besøkt[i] = true;                           // noden er besøkt
        oppgave.accept(navn[i]);                    // oppgaven utføres

        for (int j = 0; j < antall; j++)            // kantene til noden
        {
            if (graf[i][j] && !besøkt[j])
                dybdeFørstPre(j, besøkt, oppgave);  // rekursivt kall
        }
    }

    public void dybdeFørstPretraversering(String startnode, Consumer<String> oppgave) {
        int i = finn(startnode);  // indeks i den sorterte navnetabellen
        if (i < 0) throw new IllegalArgumentException(startnode + " er ukjent!");

        i = indeks[i];  // indeks i matrisen

        boolean besøkt[] = new boolean[antall];    // hjelpetabell
        dybdeFørstPre(i, besøkt, oppgave);  // kaller den rekursive metoden
    }

} // MGraf