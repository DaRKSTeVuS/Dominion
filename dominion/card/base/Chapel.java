package dominion.card.base;
import java.util.*;

import dominion.*;
import dominion.card.*;

/**
 * Carte Chapelle (Chapel)
 * 
 * Écartez jusqu'à 4 cartes de votre main.
 */
public class Chapel extends ActionCard {

	public Chapel(String name, int cost) {
		super(name, cost);
	}

	@Override
	public void play(Player p) {
		int i = 0; // Compteur des cartes défaussées
		// Tant qu'il nous reste des cartes a défausser (on en a pas encore défaussé 4) et
		// qu'il nous reste des cartes en main
		while (p.cardsInHand().size()>0 && i<4) {
			// On propose au joueur d'écarter une carte
			List<String> choices = Arrays.asList("oui", "non");
			String input = p.choose("Voulez-vous écarter une carte (y/n)", choices, false);
			// Si oui, on lui demande laquelle (parmis ses cartes en main)
			if (input == "oui") {
				CardList cchoice = new CardList();
				for (Card c: p.getActionCards()) {
					cchoice.add(c);
				}
				String inputc = p.chooseCard("Choisissez une carte à écarter.", cchoice, true);
				// On écarte la carte
				p.ecarter(inputc);
				// On incrémente le compteur de cartes
				i++;
			}
			// Si non, on sort de la boucle
			else {
				break;
			}
		}
	}
}