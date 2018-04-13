package dominion.card.base;
import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Festival
 * 
 * +2 Actions.
 * +1 Achat.
 * +2 Pièces.
 */
public class Festival extends ActionCard {

	public Festival(String name, int cost) {
		super(name, cost);
	}

	@Override
	public void play(Player p) {
		p.incrementActions(2);	// +2 Action
		p.incrementBuys(1);		// +1 Achat
		p.incrementMoney(2);	// +2 Pièce
		
	}
}