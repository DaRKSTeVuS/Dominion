package dominion.card.base;
import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Village
 * 
 * +1 Carte.
 * +2 Actions.
 */
public class Village extends ActionCard {

	public Village(String name, int cost) {
		super(name, cost);
	}

	@Override
	public void play(Player p) {
		p.incrementActions(2); 	// +2 Actions
		p.drawCard();			// +1 Carte => pioche 1 carte
		
	}
}