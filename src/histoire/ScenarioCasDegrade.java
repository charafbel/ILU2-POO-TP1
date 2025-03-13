package histoire;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {
    public static void main(String[] args) {
        // Test du cas où l'étal n'est pas occupé
        Etal etal = new Etal();
        Gaulois acheteur = new Gaulois("Astérix", 2);
        try {
            String resultat = etal.acheterProduit(5, acheteur);
            System.out.println(resultat);
        } catch (Exception e) {
            System.out.println("Une erreur est survenue lors de l'achat : " + e.getMessage());
        }

        // Test du cas où le village n'a pas de chef
        Village village = new Village("Village sans chef", 10, 5);
        try {
            String infoVillageois = village.afficherVillageois();
            System.out.println(infoVillageois);
        } catch (Village.VillageSansChefException e) {
            System.out.println("Une erreur est survenue lors de l'affichage des villageois : " + e.getMessage());
            System.out.println("Il faut d'abord désigner un chef pour ce village.");
        }

        System.out.println("Fin du test");
    }
}
