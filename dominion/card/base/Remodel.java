package dominion.card.base;
import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Rénovation (Remodel)
 * 
 * Écartez une carte de votre main.
 * Recevez une carte coûtant jusqu'à 2 Pièces de plus que la carte écartée.
 */
public class Remodel extends ActionCard {

	public Remodel(String name, int cost) {
		super(name, cost);
	}

	@Override
	public void play(Player p) {
		// Le joueur choisit une carte de sa main qu'il souhaite écarter
		// On écarte cette carte et on récupère sa valeur
		
		// Le joueur choisit une carte parmis les suplyStack coutant jusqu'à 2 Pièces de plus
		// On l'ajouta à sa main
	}
}