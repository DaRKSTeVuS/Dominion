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

	public Library(String name, int cost) {
		super(name, cost);
	}

	@Override
	public void play(Player p) {
		// On cré une liste pour les cartes mises de côté
		// Tant qu'on a moins de 7 cartes en main
			// On pioche une carte
			// Si la carte est une action
				// On propose au joueur de la mettre de côté
				// Si oui
					// On l'ajoute à la liste
		// On défausse las cartes actions piochées et mises de côté
	}
}