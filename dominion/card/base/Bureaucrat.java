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
		
		
		// TODO Auto-generated method stub
	}
}