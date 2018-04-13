package dominion.card.base;
import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Cave (Cellar)
 * 
 * +1 Action.
 * Défaussez autant de cartes que vous voulez.
 * +1 Carte par carte défaussée.
 */
public class Cellar extends ActionCard {

	public Cellar(String name, int cost) {
		super(name, cost);
	}

	@Override
	public void play(Player p) {
		p.incrementActions(1);	// +1 Action
		// proposition de défausser une carte. Peut être passé
		List<String> choices = Arrays.asList("oui", "non");
		String input = p.choose("Voulez-vous défausser une carte (y/n)", choices, true);
		// Si on décide de défausser une carte
		if (input == "oui") {
			// On propose la liste des cartes à défausser, et le joueur en choisit une
			
			// On re-propose de défausser une carte, jusqu'à ce que le joueur n'ait plus 
			// de carte à défausser ou passe
			
			// On défausse la carte
			
			// On pioche une carte
			
		}
		// Sinon, l'action est finie
		p.incrementActions(-1);	// -1 Action pour l'utilisation de la carte
	}
}