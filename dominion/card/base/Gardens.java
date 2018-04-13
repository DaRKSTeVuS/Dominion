package dominion.card.base;
import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Jardins (Gardens)
 * 
 * Vaut 1VP pour chaque 10 cartes dans votre deck (arrondi à l'unité inférieure).
 */
public class Gardens extends VictoryCard {

	public Gardens(String name, int cost) {
		super(name, cost);
	}
	
	public int victoryValue(Player p) {
		// TO DO
		return 0;
	}

	@Override
	public void play(Player p) {
		// ne fait rien
	}
}