package Eksempelklasser;

public final class Heltall implements Comparable<Heltall>
{
    private final int verdi;    // et heltall som instansvariabel

    public Heltall(int verdi) { this.verdi = verdi; }   // konstruktør

    public int intVerdi() { return verdi; }             // aksessor

    public int compareTo(Heltall h)        // Heltall som parameter
    {
        return verdi < h.verdi ? -1 : (verdi == h.verdi ? 0 : 1);
        /*
        if (verdi < h.verdi) return -1;
        else if (verdi == h.verdi) return 0;
        else return 1;
         */
        //return verdi - h.verdi;

    }


    public boolean equals(Object o)
    {
        if (o == this) return true;   // sammenligner med seg selv
        if (!(o instanceof Heltall)) return false;  // feil datatype
        return verdi == ((Heltall)o).verdi;
    }

    public boolean equals(Heltall h) { return verdi == h.verdi; }


    public int hashCode() { return 31 + verdi; }

    public String toString() { return Integer.toString(verdi); }

} // class Heltall