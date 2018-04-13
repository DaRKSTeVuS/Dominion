package dominion.card.base;
import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Milice (Militia)
 * 
 * 2 Pièces.
 * Tous vos adversaires défaussent leurs cartes de façon à n'avoir que 3 cartes en main.
 */
public class Militia extends AttackCard {

	public Militia(String name, int cost) {
		super(name, cost);
	}

	@Override
	public void play(Player p) {
		// TODO Auto-generated method stub
		p.incrementActions(-1);	// -1 Action pour l'utilisation de la carte
	}
}