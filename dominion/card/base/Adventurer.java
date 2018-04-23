package dominion.card.base;
import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Aventurier (Adventurer)
 * 
 * Dévoilez des cartes de votre deck jusqu'à ce que 2 
 * cartes Trésor soient dévoilées. Ajoutez ces cartes
 * Trésor à votre main et défaussez les autres cartes 
 * dévoilées.
 */
public class Adventurer extends ActionCard {

	public Adventurer(String name, int cost) {
		super(name, cost);
	}

	@Override
	public void play(Player p) {
		// On init un compteuir de trésor
		// Tant que le compteur est <= 2
			// On dévoile des cartes du deck 
			// Si c'est une carte Trésor, on l'ajouta a la main & on incrémente le compteur
			// Sinon, on la défausse
	}
}