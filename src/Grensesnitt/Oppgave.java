package Grensesnitt;

@FunctionalInterface
public interface Oppgave<T>  // legges under hjelpeklasser
{
    void utførOppgave(T t);    // f.eks. utskrift til konsollet

    public static <T> Oppgave<T> konsollutskrift() { // en konstruksjonsmetode
        return t -> System.out.print(t + " ");        // et lambda-uttrykk
    }

    default Oppgave<T> deretter(Oppgave<? super T> oppgave) {
        return t -> { utførOppgave(t); oppgave.utførOppgave(t); };
    }
}
