package histoire;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {
    public static void main(String[] args) {
        Etal etal = new Etal();
        Gaulois acheteur = new Gaulois("Astérix", 2);
        try {
            String resultat = etal.acheterProduit(5, acheteur);
            System.out.println(resultat);
        } catch (Exception e) {
            System.out.println("Une erreur est survenue lors de l'achat : " + e.getMessage());
        }
        System.out.println("Fin du test");
    }
}


