package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Milice (Militia)
 * 
 * 2 Pièces.
 * Tous vos adversaires défaussent leurs cartes de façon à n'avoir que 3 cartes en main.
 */
public class Militia extends AttackCard {

	public Militia() {
		super("Militia", 4);
	}

	@Override
	public void play(Player p) {
		p.incrementMoney(2); 	// +2 Pièces
		// Les adversaires défaussent 2 cartes
				for(Player o : p.otherPlayers()) {
					// On défausse 1 carte parmis celle de la main du joueur
					CardList cchoices = new CardList();
					for (Card c: o.cardsInHand()) {
						cchoices.add(c);
					}
					String inputc = o.chooseCard("Choisissez une carte à défausser.", cchoices, true);
					// On défausse la carte @param inputc
					o.defausse(inputc);
					// On défausse une 2eme carte
					String inputc2 = o.chooseCard("Choisissez une carte à défausser.", cchoices, true);
					// On défausse la carte @param inputc
					o.defausse(inputc2);
				}
		
	}
}