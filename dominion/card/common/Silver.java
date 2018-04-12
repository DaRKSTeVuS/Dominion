package dominion.card.common;
import dominion.*;
import dominion.card.*;

/**
 * Carte Argent (Silver)
 * 
 * 2 Pièces
 */
public class Silver extends TreasureCard {
	public Silver() { super("Silver", 3);	}
	
	public int treasureValue() {
		return 2;
	}
	
	@Override
	public void play(Player p) {
		p.incrementMoney(2);	// on ajoute la valeur d'une pièce d'argent à la monney du Player p
	}
}
