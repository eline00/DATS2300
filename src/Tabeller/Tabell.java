package Tabeller;

import java.util.*;

public class Tabell     // Samleklasse for tabellmetoder
{
    private Tabell() {
    }   // privat standardkonstruktør - hindrer instansiering

    // Metoden bytt(int[] a, int i, int j)       Programkode 1.1.8 d)
    public static void bytt(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // Metoden randPerm(int n)                   Programkode 1.1.8 e)
    public static int[] randPerm(int n)  // en effektiv versjon
    {
        Random r = new Random();         // en randomgenerator
        int[] a = new int[n];            // en tabell med plass til n tall

        Arrays.setAll(a, i -> i + 1);    // legger inn tallene 1, 2, . , n

        for (int k = n - 1; k > 0; k--)  // løkke som går n - 1 ganger
        {
            int i = r.nextInt(k + 1);        // en tilfeldig tall fra 0 til k
            bytt(a, k, i);                   // bytter om
        }

        return a;                        // permutasjonen returneres
    }

    // Metoden randPerm(int[] a)                 Programkode 1.1.8 f)
    public static void randPerm(int[] a)  // stokker om a
    {
        Random r = new Random();     // en randomgenerator

        for (int k = a.length - 1; k > 0; k--) {
            int i = r.nextInt(k + 1);  // tilfeldig tall fra [0,k]
            bytt(a, k, i);
        }
    }

    // Metoden maks(int[] a, int fra, int til)   Programkode 1.2.1 b)
    public static int maks(int[] a, int fra, int til) {
        if (a == null)
            throw new NullPointerException
                    ("Parametertabellen a er null!");

        fratilKontroll(a.length, fra, til);

        if (fra == til)
            throw new NoSuchElementException
                    ("fra(" + fra + ") = til(" + til + ") - tomt tabellintervall!");

        int m = fra;              // indeks til største verdi i a[fra:til>
        int maksverdi = a[fra];   // største verdi i a[fra:til>

        for (int i = fra + 1; i < til; i++) {
            if (a[i] > maksverdi) {
                m = i;                // indeks til største verdi oppdateres
                maksverdi = a[m];     // største verdi oppdateres
            }
        }

        return m;  // posisjonen til største verdi i a[fra:til>
    }

    // Metoden maks(int[] a)                     Programkode 1.2.1 c)
    public static int maks(int[] a)  // bruker hele tabellen
    {
        return maks(a, 0, a.length);     // kaller metoden over
    }

    // min-metodene - se Oppgave 1 i Avsnitt 1.2.1
    public static int min(int[] a, int fra, int til) {
        fratilKontroll(a.length, fra, til);

        int m = fra;              // indeks til største verdi i a[fra:til>
        int minverdi = a[fra];   // største verdi i a[fra:til>

        for (int i = fra + 1; i < til; i++) {
            if (a[i] < minverdi) {
                m = i;                // indeks til største verdi oppdateres
                minverdi = a[m];     // største verdi oppdateres
            }
        }

        return m;  // posisjonen til største verdi i a[fra:til>
    }

    public static int min(int[] a)  // bruker hele tabellen
    {
        return min(a, 0, a.length);     // kaller metoden over
    }

    public static void skriv(int[] a, int fra, int til) {

        fratilKontroll(a.length, fra, til);

        for (int i = fra; i < til; i++) {
            System.out.print(a[i] + " ");
        }
    }

    public static void skriv(int[] a) {
        skriv(a, 0, a.length);
    }

    public static void skrivln(int[] a, int fra, int til) {

        fratilKontroll(a.length, fra, til);

        skriv(a, 0, a.length);
        System.out.println();
    }

    public static void skrivln(int[] a) {
        skrivln(a, 0, a.length);
    }

    public static void fratilKontroll(int tablengde, int fra, int til) {
        if (fra < 0) // fra er negativ
            throw new ArrayIndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");
        if (til > tablengde) // til er utenfor tabellen
            throw new ArrayIndexOutOfBoundsException
                    ("til(" + til + ") > tablengde(" + tablengde + ")");
        if (fra > til) // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    public static void vhKontroll(int tablengde, int v, int h) {
        if (v < 0)
            throw new ArrayIndexOutOfBoundsException("v(" + v + ") < 0");
        if (h >= tablengde)
            throw new ArrayIndexOutOfBoundsException
                    ("h(" + h + ") >= tablengde(" + tablengde + ")");
        if (v > h + 1)
            throw new IllegalArgumentException
                    ("v = " + v + ", h = " + h);
    }

    public static int[] nestMaks(int[] a)  // legges i class Uke35.Tabell
    {
        int n = a.length;   // tabellens lengde

        if (n < 2) throw   // må ha minst to verdier!
                new java.util.NoSuchElementException("a.length(" + n + ") < 2!");

        int m = maks(a);  // m er posisjonen til tabellens største verdi

        bytt(a, 0, m);  // bytter slik at maks ligger fremst (bytt(a, a.length-1, m) for å legge størst bakerst)

        int nm = maks(a, 1, a.length);

        if (nm == m) {
            nm = 0;  //nest maks lå oppreinnelig først (nm = a.length-1 bakerst)
        }

        bytt(a, 0, m);  //bytter tilbake

        return new int[]{m, nm};      // m i posisjon 0 , nm i posisjon 1

    } // nestMaks

    public static void sortering(int[] a) {
        if (a.length < 2) throw   // må ha minst to verdier!
                new java.util.NoSuchElementException("a.length(" + a.length + ") < 2!");

        for (int i = a.length; i > 1; i--) {
            int m = maks(a, 0, i);
            bytt(a, i - 1, m);
        }
    }

    public static int[] nestMaks2(int[] a)   // en turnering
    {
        int n = a.length;                // for å forenkle notasjonen

        if (n < 2) // må ha minst to verdier!
            throw new IllegalArgumentException("a.length(" + n + ") < 2!");

        int[] b = new int[2 * n];          // turneringstreet
        System.arraycopy(a, 0, b, n, n);     // legger a bakerst i b

        for (int k = 2 * n - 2; k > 1; k -= 2)   // lager turneringstreet
            b[k / 2] = Math.max(b[k], b[k + 1]);

        int maksverdi = b[1], nestmaksverdi = Integer.MIN_VALUE;

        for (int m = 2 * n - 1, k = 2; k < m; k *= 2) {
            int tempverdi = b[k + 1];  // ok hvis maksverdi er b[k]
            if (maksverdi != b[k]) {
                tempverdi = b[k];
                k++;
            }
            if (tempverdi > nestmaksverdi) nestmaksverdi = tempverdi;
        }

        return new int[]{maksverdi, nestmaksverdi}; // størst og nest størst

    } // nestMaks

    public static void kopier(int[] a, int i, int[] b, int j, int ant) {
        for (int n = i + ant; i < n; ) {
            b[j++] = a[i++];
        }
    }

    public static void snu(int[] a, int v, int h)  // snur intervallet a[v:h]
    {
        while (v < h) {
            bytt(a, v++, h--);
        }
    }

    public static void snu(int[] a, int v)  // snur fra og med v og ut tabellen
    {
        snu(a, v, a.length - 1);
    }

    public static void snu(int[] a)  // snur hele tabellen
    {
        snu(a, 0, a.length - 1);
    }

    public static boolean nestePermutasjon(int[] a) {
        int i = a.length - 2;                    // i starter nest bakerst
        while (i >= 0 && a[i] > a[i + 1]) {
            i--;   // går mot venstre
        }
        if (i < 0) {
            return false;                 // a = {n, n-1, . . . , 2, 1}
        }

        int j = a.length - 1;                    // j starter bakerst
        while (a[j] < a[i]) {
            j--;                 // stopper når a[j] > a[i]
        }
        bytt(a, i, j);
        snu(a, i + 1);

        return true;                             // en ny permutasjon
    }

    public static void utvalgssortering(int[] a) {
        for (int i = 0; i < a.length - 1; i++)
            bytt(a, i, min(a, i, a.length));  // to hjelpemetoder
    }

    public static void utvalgssortering2(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int minIndeks = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[minIndeks]) {
                    minIndeks = j;
                }
            }
            int temp = a[minIndeks];
            a[minIndeks] = a[i];
            a[i] = temp;
        }
    }

    public static void utvalgssortering(int[] a, int fra, int til) {
        fratilKontroll(a.length, fra, til);

        for (int i = fra; i < til - 1; i++) {
            bytt(a, i, min(a, i, til));
        }
    }

    public static int lineærsøk(int[] a, int verdi) // legges i class Tabell
    {
        if (a.length == 0 || verdi > a[a.length - 1])
            return -(a.length + 1);  // verdi er større enn den største

        int i = 0;
        for (; a[i] < verdi; i++) ;  // siste verdi er vaktpost

        return verdi == a[i] ? i : -(i + 1);   // sjekker innholdet i a[i]
    }

    public static int lineærsøk(int[] a, int k, int verdi) {
        if (k < 1)
            throw new IllegalArgumentException("Må ha k > 0!");

        int j = k - 1;
        for (; j < a.length && verdi > a[j]; j += k) ;

        int i = j - k + 1;  // søker i a[j-k+1:j]
        for (; i < a.length && verdi > a[i]; i++) ;

        if (i < a.length && a[i] == verdi) return i;  // funnet
        else return -(i + 1);
    }

    public static int kvadratrotsøk(int[] a, int verdi) {
        return lineærsøk(a, (int) Math.sqrt(a.length), verdi);
    }

    public static int binærsøk1(int[] a, int fra, int til, int verdi) {
        fratilKontroll(a.length, fra, til);  // se Programkode 1.2.3 a)
        int v = fra, h = til - 1;  // v og h er intervallets endepunkter

        while (v <= h)    // fortsetter så lenge som a[v:h] ikke er tom
        {
            int m = (v + h) / 2;      // heltallsdivisjon - finner midten
            int midtverdi = a[m];   // hjelpevariabel for midtverdien

            if (verdi == midtverdi) return m;          // funnet
            else if (verdi > midtverdi) v = m + 1;     // verdi i a[m+1:h]
            else h = m - 1;                           // verdi i a[v:m-1]
        }

        return -(v + 1);    // ikke funnet, v er relativt innsettingspunkt
    }

    public static int binærsøk2(int[] a, int fra, int til, int verdi) {
        fratilKontroll(a.length, fra, til);  // se Programkode 1.2.3 a)
        int v = fra, h = til - 1;    // v og h er intervallets endepunkter

        while (v <= h)  // fortsetter så lenge som a[v:h] ikke er tom
        {
            int m = (v + h) / 2;     // heltallsdivisjon - finner midten
            int midtverdi = a[m];  // hjelpevariabel for  midtverdien

            if (verdi > midtverdi) v = m + 1;        // verdi i a[m+1:h]
            else if (verdi < midtverdi) h = m - 1;   // verdi i a[v:m-1]
            else return m;                           // funnet
        }

        return -(v + 1);   // ikke funnet, v er relativt innsettingspunkt
    }

    public static int binærsøk3(int[] a, int fra, int til, int verdi) {
        fratilKontroll(a.length, fra, til);  // se Programkode 1.2.3 a)
        int v = fra, h = til - 1;  // v og h er intervallets endepunkter

        while (v < h)  // obs. må ha v < h her og ikke v <= h
        {
            int m = (v + h) / 2;  // heltallsdivisjon - finner midten

            if (verdi > a[m]) v = m + 1;   // verdi må ligge i a[m+1:h]
            else h = m;                   // verdi må ligge i a[v:m]
        }
        if (h < v || verdi < a[v]) return -(v + 1);  // ikke funnet
        else if (verdi == a[v]) return v;            // funnet
        else return -(v + 2);                       // ikke funnet
    }

    public static void innsettingssortering(int[] a, int fra, int til) {
        fratilKontroll(a.length, fra, til);

        for (int i = fra + 1; i < til; i++)  // starter med i = 1
        {
            int temp = a[i];  // hjelpevariabel
            for (int j = i - 1; j >= 0 && temp < a[j]; j--) Tabell.bytt(a, j, j + 1);
        }
    }

    public static int maks(double[] a) {     // legges i class Tabell

        int m = 0;                           // indeks til største verdi
        double maksverdi = a[0];             // største verdi

        for (int i = 1; i < a.length; i++) {
            if (a[i] > maksverdi) {
                maksverdi = a[i];     // største verdi oppdateres
                m = i;                // indeks til største verdi oppdaters
            }
        }
        return m;     // returnerer posisjonen til største verdi
    }

    public static int maks(char[] a) {
        int m = 0;                          // indeks til største verdi
        char maksverdi = a[0];            // største verdi

        for (int i = 1; i < a.length; i++) {
            if (a[i] > (maksverdi)) {
                maksverdi = a[i];  // største verdi oppdateres
                m = i;             // indeks til største verdi oppdaters
            }
        }
        return m;  // returnerer posisjonen til største verdi
    }

    public static int maks(Integer[] a) {    // legges i class Tabell
        int m = 0;                          // indeks til største verdi
        Integer maksverdi = a[0];            // største verdi

        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(maksverdi) > 0) {
                maksverdi = a[i];  // største verdi oppdateres
                m = i;             // indeks til største verdi oppdaters
            }
        }
        return m;  // returnerer posisjonen til største verdi
    }

    public static <T extends Comparable<? super T>> int maks(T[] a) {
        int m = 0;                     // indeks til største verdi
        T maksverdi = a[0];            // største verdi

        for (int i = 1; i < a.length; i++)
            if (a[i].compareTo(maksverdi) > 0) {
                maksverdi = a[i];  // største verdi oppdateres
                m = i;             // indeks til største verdi oppdaters
            }
        return m;  // returnerer posisjonen til største verdi
    } // maks

    public static <T extends Comparable<? super T>> void innsettingssortering(T[] a) {
        for (int i = 1; i < a.length; i++)  // starter med i = 1
        {
            T verdi = a[i];        // verdi er et tabellelemnet
            int j = i - 1;        // j er en indeks
            // sammenligner og forskyver:
            for (; j >= 0 && verdi.compareTo(a[j]) < 0; j--) a[j + 1] = a[j];

            a[j + 1] = verdi;      // j + 1 er rett sortert plass
        }
    }

    public static void skriv(Object[] a, int fra, int til) {

        fratilKontroll(a.length, fra, til);

        for (int i = fra; i < til; i++) {
            System.out.print(a[i] + " ");
        }
    }

    public static void skriv(Object[] a) {
        skriv(a, 0, a.length);
    }

    public static void skrivln(Object[] a, int fra, int til) {

        fratilKontroll(a.length, fra, til);

        skriv(a, 0, a.length);
        System.out.println();
    }

    public static void skrivln(Object[] a) {
        skrivln(a, 0, a.length);
    }

    /*
    public static void bytt(Object[] a, int i, int j)
    {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
     */

    public static Integer[] randPermInteger(int n) {
        Integer[] a = new Integer[n];               // en Integer-tabell
        Arrays.setAll(a, i -> i + 1);               // tallene fra 1 til n

        Random r = new Random();   // hentes fra  java.util

        for (int k = n - 1; k > 0; k--) {
            int i = r.nextInt(k + 1);  // tilfeldig tall fra [0,k]
            bytt(a, k, i);             // bytter om
        }
        return a;  // tabellen med permutasjonen returneres
    }

    public static <T> void innsettingssortering(T[] a, Comparator<? super T> c) {
        for (int i = 1; i < a.length; i++)  // starter med i = 1
        {
            T verdi = a[i];        // verdi er et tabellelemnet
            int j = i - 1;        // j er en indeks

            // sammenligner og forskyver:
            for (; j >= 0 && c.compare(verdi, a[j]) < 0; j--) a[j + 1] = a[j];

            a[j + 1] = verdi;      // j + 1 er rett sortert plass
        }
    }

    public static <T> int maks(T[] a, int fra, int til, Comparator<? super T> c) {

        fratilKontroll(a.length, fra, til);

        if (fra == til) {
            throw new NoSuchElementException("fra(" + fra + ") = til(" + til + ") - tomt intervall!");
        }

        int m = fra;                     // indeks til største verdi
        T maksverdi = a[fra];            // største verdi

        for (int i = fra + 1; i < til; i++) {
            if (c.compare(a[i], maksverdi) > 0) {
                maksverdi = a[i];  // største verdi oppdateres
                m = i;             // indeks til største verdi oppdaters
            }
        }
        return m;  // returnerer posisjonen til største verdi
    }

    public static <T> int maks(T[] a, Comparator<? super T> c) {
        return maks(a, 0, a.length, c);
    }

    public static <T> int min(T[] a, int fra, int til, Comparator<? super T> c) {
        if (fra < 0 || til > a.length || fra >= til)
            throw new IllegalArgumentException("Illegalt intervall!");

        int m = fra;
        T minverdi = a[fra];

        for (int i = fra + 1; i < til; i++) {
            if (c.compare(a[i], minverdi) < 0) {
                m = i;
                minverdi = a[m];
            }
        }
        return m;
    }

    public static <T> int min(T[] a, Comparator<? super T> c) {
        return min(a, 0, a.length, c);
    }

    public static <T> void bytt(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static <T> void utvalgssortering(T[] a, Comparator<? super T> c) {
        for (int i = 0; i < a.length - 1; i++)
            bytt(a, i, min(a, i, a.length, c));  // to hjelpemetoder
    }

    public static <T> int binærsøk(T[] a, int fra, int til, T verdi, Comparator<T> c) {
        Tabell.fratilKontroll(a.length, fra, til);  // se Programkode 1.2.3 a)
        int v = fra, h = til - 1;    // v og h er intervallets endepunkter

        while (v <= h)  // fortsetter så lenge som a[v:h] ikke er tom
        {
            int m = (v + h) / 2;     // heltallsdivisjon - finner midten
            T midtverdi = a[m];  // hjelpevariabel for  midtverdien

            if (c.compare(verdi, midtverdi) > 0) v = m + 1;        // verdi i a[m+1:h]
            else if (c.compare(verdi, midtverdi) < 0) h = m - 1;   // verdi i a[v:m-1]
            else return m;                           // funnet
        }

        return -(v + 1);   // ikke funnet, v er relativt innsettingspunkt
    }

    public static <T> int binærsøk(T[] a, T verdi, Comparator<T> c) {
        return binærsøk(a, 0, a.length, verdi, c);
    }

    private static int parter(int[] a, int v, int h, int skilleverdi)
    {
        while (true)                                  // stopper når v > h
        {
            while (v <= h && a[v] < skilleverdi) v++;   // h er stoppverdi for v
            while (v <= h && a[h] >= skilleverdi) h--;  // v er stoppverdi for h

            if (v < h) bytt(a,v++,h--);                 // bytter om a[v] og a[h]
            else  return v;  // a[v] er nåden første som ikke er mindre enn skilleverdi
        }
    }

    private static int sParter(int[] a, int v, int h, int indeks)
    {
        bytt(a, indeks, h);           // skilleverdi a[indeks] flyttes bakerst
        int pos = parter(a, v, h - 1, a[h]);  // partisjonerer a[v:h - 1]
        bytt(a, pos, h);              // bytter for å få skilleverdien på rett plass
        return pos;                   // returnerer posisjonen til skilleverdien
    }

    private static void kvikksortering(int[] a, int v, int h)
    {
        if (v >= h) return;  // hvis v = h er a[v:h] allerede sortert

        int p = sParter(a,v,h,(v + h)/2);
        kvikksortering(a,v,p-1);
        kvikksortering(a,p+1,h);
    }

    public static void kvikksortering(int[] a) // sorterer hele tabellen
    {
        kvikksortering(a,0,a.length-1);
    }

    private static <T> int parter(T[] a, int v, int h, T skilleverdi, Comparator<? super T> c)
    {
        while (v <= h && c.compare(a[v],skilleverdi) < 0) v++;
        while (v <= h && c.compare(skilleverdi,a[h]) <= 0) h--;

        while (true)                                  // stopper når v > h
        {
            while (v <= h && (c.compare(a[v], skilleverdi) < 0)) v++;   // h er stoppverdi for v
            while (v <= h &&(c.compare(skilleverdi, a[h]) <= 0)) h--;  // v er stoppverdi for h

            if (v < h) bytt(a,v++,h--);                 // bytter om a[v] og a[h]
            else  return v;  // a[v] er nåden første som ikke er mindre enn skilleverdi
        }
    }

    public static <T> int parter(T[] a, T skilleverdi, Comparator<? super T> c)
    {
        return parter(a,0,a.length-1,skilleverdi,c);  // kaller metoden over
    }

    private static <T> int sParter(T[] a, int v, int h, int indeks, Comparator<? super T> c)
    {
        if (v < 0 || h >= a.length || indeks < v || indeks > h) throw new
                IllegalArgumentException("Ulovlig parameterverdi");

        bytt(a, indeks, h);           // skilleverdi a[indeks] flyttes bakerst
        int pos = parter(a, v, h - 1, a[h], c);  // partisjonerer a[v:h - 1]
        bytt(a, pos, h);              // bytter for å få skilleverdien på rett plass
        return pos;                   // returnerer posisjonen til skilleverdien
    }

    private static <T> void kvikksortering(T[] a, int v, int h, Comparator<? super T> c)
    {
        if (v >= h) return;  // hvis v = h er a[v:h] allerede sortert

        int p = sParter(a,v,h,(v + h)/2, c);
        kvikksortering(a,v,p-1, c);
        kvikksortering(a,p+1,h, c);
    }

    public static <T> void kvikksortering(T[] a, Comparator<? super T> c) // sorterer hele tabellen
    {
        kvikksortering(a,0,a.length-1, c);
    }

    private static <T> void flett(T[] a, T[] b, int fra, int m, int til, Comparator<? super T> c)
    {
        int n = m - fra;                // antall elementer i a[fra:m>
        System.arraycopy(a,fra,b,0,n);  // kopierer a[fra:m> over i b[0:n>

        int i = 0, j = m, k = fra;      // løkkevariabler og indekser

        while (i < n && j < til)        // fletter b[0:n> og a[m:til> og
        {                               // legger resultatet i a[fra:til>
            a[k++] = (c.compare(b[i], a[j])) <= 0 ? b[i++] : a[j++];
        }

        while (i < n) a[k++] = b[i++];  // tar med resten av b[0:n>
    }

    private static <T> void flettesortering(T[] a, T[] b, int fra, int til, Comparator<? super T> c)
    {
        if (til - fra <= 1) return;   // a[fra:til> har maks ett element
        int m = (fra + til)/2;        // midt mellom fra og til

        flettesortering(a, b, fra, m, c);   // sorterer a[fra:m>
        flettesortering(a, b, m, til, c);   // sorterer a[m:til>

        flett(a, b, fra, m, til, c);  // fletter a[fra:m> og a[m:til>
    }

    public static <T> void flettesortering(T[] a, Comparator<? super T> c)
    {
        T[] b = Arrays.copyOf(a, a.length/2);   // en hjelpetabell for flettingen
        flettesortering(a,b,0,a.length, c);          // kaller metoden over
    }
}