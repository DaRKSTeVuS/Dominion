package dominion.card.base;
import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Marché (Market)
 * 
 * +1 Carte.
 * +1 Action.
 * +1 Achat.
 * +1 Pièce.
 */
public class Market extends ActionCard {

	public Market(String name, int cost) {
		super(name, cost);
	}

	@Override
	public void play(Player p) {
		p.incrementActions(1);	// +1 Action
		p.incrementBuys(1);		// +1 Achat
		p.incrementMoney(1);	// +1 Pièce
		p.drawCard();			// +1 Carte = pioche 1 carte
		
	}
}