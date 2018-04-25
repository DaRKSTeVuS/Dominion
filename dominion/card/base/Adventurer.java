package dominion.card.base;

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
		// On init une carte
		Card tmpC;
		// On init un compteuir de trésor
		int cptT = 0;
		// Tant que le compteur est <= 2
		while (cptT <= 2) {
			// On dévoile des cartes du deck 
			tmpC = p.drawCard();
			System.out.println(tmpC.toString());
			// Si c'est une carte Trésor, on l'ajouta a la main & on incrémente le compteur
			if (tmpC instanceof TreasureCard) {
				cptT++;
			} else {
				// Sinon, on la défausse
				p.defausse(tmpC);
			}
		}
			
	}
}