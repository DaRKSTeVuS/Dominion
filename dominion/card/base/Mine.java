package dominion.card.base;

import java.util.Arrays;
import java.util.List;

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
		// Construction de la carte avec ses caractérisques
		super("Mine", 5);
	}

	@Override
	public void play(Player p) {
		// On recupère les cartes trésor en main
		CardList handTre = p.getTreasureCards();
		// On creer une variable pour stocké la carte choisi
		Card tmpC;
		// Si le joueur à des cartes Trésor en main
		if (!handTre.isEmpty()) {
			// On demande au joueur quelle carte Trésor de sa main il souhaite écarter
			String input = p.chooseCard("Quelle carte trésor voulez-vous écarter ?", handTre, false);
			// On écarte cette carte
			tmpC = p.ecarter(input);
			// On regarde dans les cartes disponibles à l'achat
			for (Card c : p.getGame().availableSupplyCards()) {
				// S'il y a une TreasureCard
				if (c instanceof TreasureCard) {
					// Si la carte trouvé à un coup inferieur a la carté ecarté + 3
					if (c.getCost() <= tmpC.getCost() + 3) {
						// On crée une liste de choix
						List<String> choices = Arrays.asList("y", "n");
						// On demande au joueur s'il veux acheter cette carte
						String input2 = p.choose("Voulez vous acheter la carte " + c.toString() + " ? (y/n)", choices, false);
						// S'il répond oui
						if (input2.equals("y")) {
							// On retire la carte de la réserve
							p.getGame().removeFromSupply(c.getName());
							// On l'ajoute à sa main
							p.supplyToHand(c.toString());
						}
					}
				}
			}
		}
	}
}