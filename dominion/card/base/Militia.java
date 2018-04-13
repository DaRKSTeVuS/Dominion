package dominion.card.base;
import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Milice (Militia)
 * 
 * 2 Pièces.
 * Tous vos adversaires défaussent leurs cartes de façon à n'avoir que 3 cartes en main.
 */
public class Militia extends AttackCard {

	public Militia(String name, int cost) {
		super(name, cost);
	}

	@Override
	public void play(Player p) {
		p.incrementMoney(2); 	// +2 Pièces
		// Les adversaires défaussent 2 cartes
				for(Player o : p.otherPlayers()) {
					// On défausse 1 carte
					// On défausse une 2eme carte
				}
		
	}
}