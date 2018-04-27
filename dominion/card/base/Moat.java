package dominion.card.base;

import java.util.List;

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

	public Moat() {
		// Construction de la carte avec ses caractérisques
		super("Moat", 2);
	}

	@Override
	public void play(Player p) {
		// Si c'est le tour du joueur p
		if(true) {
			// +1 Carte = pioche 1 carte
			p.drawCard();
			// +1 Carte = pioche 1 carte
			p.drawCard();			
		}
		// Si c'est le tour d'un autre joueur
		// Anuler l'efet sur soi
	}

	public static void main(String[] args) {
		Card test = new Moat();
		if (test.getTypes().contains(CardType.Reaction)) {
			System.out.println(test.getTypes().toString());
		}
	}
}