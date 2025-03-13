package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	public class VillageSansChefException extends Exception {
		public VillageSansChefException() {
			super("Le village n'a pas de chef!");
		}

		public VillageSansChefException(String message) {
			super(message);
		}
	}

	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.marche = new Marche(nbEtals);
	}

	private static class Marche{
		private Etal[] etals;
		private Marche(int nbEtals){
			etals = new Etal[nbEtals];
			for (int i = 0; i < nbEtals; i++) {
				etals[i] = new Etal();
			}
		}
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit){
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		private int trouverEtalLibre(){
			for(int i = 0; i < etals.length; i++){
				if (!etals[i].isEtalOccupe()){
					return i;
				}
			}
			return -1;
		}
		private Etal[] trouverEtals(String produit){
			int c = 0;
			int j = 0;
			for(int i = 0; i < etals.length; i++){
				if (etals[i].contientProduit(produit) == true){
					c++;
				};
			}
			Etal[] EtalProd = new Etal[c];
			for(int i = 0; i < etals.length; i++){
				if (etals[i].contientProduit(produit) == true){
					EtalProd[j] = etals[i];
					j++;
				};
			}
			return EtalProd;
		}
		private Etal trouverVendeur(Gaulois gaulois){
			for(int i = 0; i < etals.length; i++){
				if (etals[i].getVendeur() == gaulois){
					return etals[i];
				};
			}
			return null;
		}
		private String afficherMarche(){
			int nbEtalsVides = 0;
			String renvoi = "";
			for(int i = 0; i < etals.length; i++){
				if (etals[i].isEtalOccupe() == false){
					nbEtalsVides++;
				}
				else {
					renvoi = renvoi + etals[i].afficherEtal() +  " ";
				}
			}
			renvoi += "Il reste " + nbEtalsVides + " étals non utilisés dans le marché.\n";
			return renvoi;
		}
	}

	public String getNom() {
		return nom;
	}
	public void setChef(Chef chef) {
		this.chef = chef;
	}
	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}
	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}
	/*public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}*/
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit){
		int indice = marche.trouverEtalLibre();
		String reponse = "";
		if (indice != -1){
			marche.utiliserEtal(indice ,vendeur, produit, nbProduit);
			reponse = "Le vendeur" + vendeur.getNom() + "vends le produit '" + produit + "' à l'Etal N°" + indice + ".";
		}
		else {
			reponse = vendeur.getNom() + "cherche un endroit pour vendre"  + nbProduit + " " + produit + ".";
		}
		return reponse;
	}
	public String rechercherVendeursProduit(String produit){
		Etal[] etalsAvecProduit = marche.trouverEtals(produit);
		String message;
		if (etalsAvecProduit.length == 0) {
			message = "Il n'y a pas de vendeur qui propose des " + produit + " au marché.";
			return message;
		} else {
			message = "Les vendeurs qui proposent des "+ produit + " sont :\n";
			for (int i = 0; i < etalsAvecProduit.length; i++) {
				Gaulois vendeur = etalsAvecProduit[i].getVendeur();
				message += "     - " + vendeur.getNom() + "\n";
			}
		}
		return message;
	}
	public Etal rechercherEtal(Gaulois vendeur){
		return marche.trouverVendeur(vendeur);
	}
	public String partirVendeur(Gaulois vendeur){
		String message = rechercherEtal(vendeur).libererEtal();
		return message;
	}
	public String afficherMarche(){
		StringBuilder chaine = new StringBuilder();
		chaine.append(marche.afficherMarche());
		return chaine.toString();
	}
	public String afficherVillageois() throws VillageSansChefException {
		if (chef == null) {
			throw new VillageSansChefException();
		}
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}