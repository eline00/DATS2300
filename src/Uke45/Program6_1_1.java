package Uke45;

public class Program6_1_1 {
    public static void main(String[] args) {
        System.out.println("6.1.1\n-------------------------------------------------");

        System.out.println("\nOppgave 1:");
        String[] s = {"A"};
        String a = "A", b = "A", c = new String("A"), d = s[0];

        boolean lik1 = (a == b), lik2 = (a == c), lik3 = (a == d);
        boolean eq1 = a.equals(b), eq2 = a.equals(c), eq3 = a.equals(d);
        int h1 = a.hashCode(), h2 = b.hashCode(), h3 = c.hashCode(), h4 = d.hashCode();

        System.out.println("lik1: " + lik1);
        System.out.println("lik2: " + lik2);
        System.out.println("lik3: " + lik3);
        System.out.println("eq1: " + eq1);
        System.out.println("eq2: " + eq2);
        System.out.println("eq3: " + eq3);

        System.out.println(h1 + " " + h2 + " " + h3 + " " + h4);

        System.out.println("\n\nOppgave 2:");
        int[] e = {1,2,3}, f = e, g = {1,2,3};
        boolean lik4 = (e == f), lik5 = (e == g), lik6 = e.equals(g);

        System.out.println("lik4: " + lik4);
        System.out.println("lik5: " + lik5);
        System.out.println("lik6: " + lik6);
    }
}
