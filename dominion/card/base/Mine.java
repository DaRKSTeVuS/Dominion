package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Mine
 * 
 * Écartez une carte Trésor de votre main. Recevez une carte 
 * Trésor coûtant jusqu'à 3 Pièces de plus ; ajoutez cette 
 * carte à votre main.
 */
public class Mine extends ActionCard {

	public Mine() {
		super("Mine", 5);
	}

	@Override
	public void play(Player p) {
		// Si le joueur à des cartes Trésor en main
			// On demande au joueur quelle carte Trésor de sa main il souhaite écarter
			// On écarte cette carte
			// On propose au joueur la liste des cartes trésor disponible (coutant
			// jusqu'a 3 pièce de plus que this.getCost()
			// Le joueur en choisit une 
			// On l'ajoute à la main
		// Sinon, on ne fait rien
	}
}