package dominion.card.base;
import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Forgeron (Smithy)
 * 
 * +3 Cartes.
 */
public class Smithy extends ActionCard {

	public Smithy(String name, int cost) {
		super(name, cost);
	}

	@Override
	public void play(Player p) {
		// +3 Carte => pioche 3 carte
		for(int i=0; i<3; i++) {
			p.drawCard();			
		}
		
	}
}