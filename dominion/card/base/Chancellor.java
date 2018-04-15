package dominion.card.base;
import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Chancellier (Chancellor)
 * 
 * +2 Pièces.
 * Vous pouvez immédiatement défausser votre deck.
 */
public class Chancellor extends ActionCard {

	public Chancellor(String name, int cost) {
		super(name, cost);
	}

	@Override
	public void play(Player p) {
		p.incrementMoney(2);	// +2 Pièces
		// Propose au joueur de défausser immédiatement son deck
		List<String> choices = Arrays.asList("oui", "non");
		String input = p.choose("Voulez-vous défausser une carte (y/n)", choices, false);
		// Si oui, défausse le deck {@code draw}
		if (input == "oui") {
			p.discardDraw();
		}
		// Si non, ne fait rien de plus
		
	}
}