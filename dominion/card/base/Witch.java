package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Sorcière (Witch)
 * 
 * +2 Cartes.
 * Tous vos adversaires recoivent une carte Curse.
 */
public class Witch extends AttackCard {

	public Witch() {
		// Construction de la carte avec ses caractérisques
		super("Witch", 5);
	}

	@Override
	public void play(Player p) {
		// On pioche 1 carte 2x
		p.drawCard() ;
		p.drawCard() ;
		// Les adversaires reçoivent une carte Malédiction
		for(Player o : p.otherPlayers()) {
			o.gain(p.getGame().getFromSupply("Malédiction"));
			p.getGame().removeFromSupply("Malédicton");
		}
	}
}