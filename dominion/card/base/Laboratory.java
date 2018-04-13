package dominion.card.base;
import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Laboratoire (Laboratory)
 * 
 * +2 Cartes.
 * +1 Action.
 */
public class Laboratory extends ActionCard {

	public Laboratory(String name, int cost) {
		super(name, cost);
	}

	@Override
	public void play(Player p) {
		p.incrementMoney(1);	// +1 Pièce
		p.drawCard();			// +1 Carte = pioche 1 carte
		p.drawCard();			// +1 Carte = pioche 1 carte
	}
}