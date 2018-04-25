package dominion.card.base;

import dominion.*;
import dominion.card.*;

/**
 * Carte Bureaucrate (Bureaucrat)
 * Coût : 4
 * 
 * Recevez une carte Argent; placez-la sur votre deck.
 * Tous vos adversaires dévoilent une carte Victoire et 
 * la placent sur leur deck (sinon ils dévoilent leur 
 * main afin que vous puissiez voir qu'ils n'ont pas de 
 * cartes Victoire).
 */
public class Bureaucrat extends AttackCard {

	public Bureaucrat() {
		// Construction de la carte avec ses caractérisques
		super("Bureaucrat", 4);
	}

	@Override
	public void play(Player p) {
		// Recevez une carte Silver
		p.gain("Silver");
		// On parcours la liste des adversaire
		for (Player op : p.otherPlayers()) {
			// On recupere les cartes victoire du joueurs
			CardList vicCard = op.getVictoryCards();
			// Si le joueur {code op} n'as pas de carte victoire en main
			if (vicCard.isEmpty()) {
				// On montre sa main
				System.out.println(op.cardsInHand().toString());
			} else {
				// Sinon on demande au joueur {code op} quelle carte il veut defausser
				String input = op.chooseCard("Quelle carte victoire voulez vous mettre sur votre pioche ?", vicCard, false);
				// On dévoile la carte et on la met au dessus de la pioche
				System.out.println("Carte mise en haut de la pioche : " + op.putOnTopDraw(input).toString());
			}
		}
	}
}