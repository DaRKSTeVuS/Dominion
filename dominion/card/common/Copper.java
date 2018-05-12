package dominion.card.common;

import dominion.*;
import dominion.card.*;

/**
 * Carte Cuivre (Copper)
 * 
 * 1 Pièce
 */
public class Copper extends TreasureCard {
	public Copper() {
		super("Copper", 0);
	}

	public int treasureValue() {
		return 1;
	}

	@Override
	public void play(Player p) {
		// on ajoute la valeur d'une pièce de cuivre à la monney du Player p
		p.incrementMoney(1);
	}
}
