package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Laboratoire (Laboratory)
 * 
 * +2 Cartes.
 * +1 Action.
 */
public class Laboratory extends ActionCard {

	public Laboratory() {
		// Construction de la carte avec ses caractérisques
		super("Laboratory", 5);
	}

	@Override
	public void play(Player p) {
		// +1 Pièce
		p.incrementMoney(1);
		// +2 Carte = pioche 2 carte
		for (int i = 0; i < 2; i++) {
			p.drawToHand();
		}
	}
}