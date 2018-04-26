package dominion.card.base;

import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Bibliothèque (Library)
 * 
 * Piochez jusqu'à ce que vous ayez 7 cartes en main. 
 * Chaque carte Action piochée peut être mise de côté. 
 * Défaussez les cartes mises de côté lorsque vous 
 * avez terminé de piocher.
 */
public class Library extends ActionCard {

	public Library() {
		// Construction de la carte avec ses caractérisques
		super("Library", 5);
	}

	@Override
	public void play(Player p) {
		// On calcule le nombre de carte dans la main du joueur
		int nbCardPlayer = p.cardsInHand().size();
		// On initialisate une variablie pour stocké la carte pioché
		Card tmpC;
		// On initialise la liste qui contiendra les réponses
		ArrayList<String> choix = new ArrayList<String>();
		// On ajoute la réponse oui
		choix.add("oui");
		// On ajoute la réponse non
		choix.add("non");
		// On crée la variable de réponse
		String input;
		// On pioche jusqu'as 7 cartes
		for (int i = nbCardPlayer; i <= 7; i++) {
			// On récupère la carte pioché
			tmpC = p.drawCard();
			// Si c'est une carte action
			if (tmpC instanceof ActionCard) {
				// On affiche la carte pioché
				System.out.println("Vous avez piocher la carte : " + tmpC.toString());
				// On demande au joueur s'il veux la conserver
				input = p.choose("Voulez vous conserver cette carte Action : " + tmpC.toString() + " ?", choix, false);
				// Si non
				if (input.equals("non")) {
					// On décrémente le compteur pour piocher une autre carte
					i--;
					// On defausse la carte
					p.defausse(tmpC);
				}
			}
		}
	}
}