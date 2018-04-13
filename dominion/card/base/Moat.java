package dominion.card.base;
import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Douves (Moat)
 * 
 * +2 Cartes.
 * Lorsqu’un adversaire joue une carte Attaque, vous 
 * pouvez dévoiler cette carte de votre main. Dans ce cas, 
 * l’Attaque n’a pas d’effet sur vous.
 */
public class Moat extends ReactionCard {

	public Moat(String name, int cost) {
		super(name, cost);
	}

	@Override
	public void play(Player p) {
		// TODO Auto-generated method stub
		p.incrementActions(-1);	// -1 Action pour l'utilisation de la carte
	}
}