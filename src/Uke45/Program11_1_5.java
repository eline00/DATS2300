package Uke45;

import Eksempelklasser.Graf;

import java.io.IOException;

public class Program11_1_5 {
    public static void main(String... args) throws IOException
    {
        String url = "https://www.cs.hioa.no/~ulfu/appolonius/kap11/1/graf6.txt";
        Graf graf = new Graf(url);

        String startnode = "A";
        graf.kortestVeiFra(startnode);

        String[] noder = graf.nodenavn();  // alle nodene

        for (String node : noder)
        {
            System.out.println("Kortest vei fra " + startnode + " til "
                    + node + ": " + graf.veiTil(node));
        }
    }
}
