package dominion.card.base;
import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Chambre du conseil (Council Room)
 * 
 * +4 Cartes.
 * +1 Achat.
 * Tous vos adversaires piochent 1 carte.
 */
public class CouncilRoom extends ActionCard {

	public CouncilRoom(String name, int cost) {
		super(name, cost);
	}

	@Override
	public void play(Player p) {
		// On pioche 4x 1 carte
		for(int i=0; i<4; i++) {
			p.drawCard();
		}
		p.incrementBuys(1); 	// +1 Achat
		// Les adversaires piochent une carte
		for(Player o : p.otherPlayers()) {
			o.drawCard() ;
		}

		p.incrementActions(-1);	// -1 Action pour l'utilisation de la carte
	}
}