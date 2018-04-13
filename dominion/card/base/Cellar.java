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
		// On va pouvoir défausser autant de carte qu'on voudra, dans la limite de la main
		// On va garder ce chiffre dans une variable
		int nbcdef = 0;
		while(p.getActionCards() != null ) {
			// On propose au joueur de défausser une carte jusqu'à ce qu'il n'ait plus 
			// de carte à défausser ou passe. il peut passer à tout momment
			// En revanche, il ne peut répondre que par oui ou par non
			List<String> choices = Arrays.asList("oui", "non");
			String input = p.choose("Voulez-vous défausser une carte (y/n)", choices, false);
			// Si on décide de défausser une carte
			if (input == "oui") {
				// On propose la liste des cartes à défausser, et le joueur en choisit une
				CardList cchoices = new CardList();
				for (Card c: p.getActionCards()) {
					cchoices.add(c);
				}
				// On propose au joueur la liste des cartes disponibles
				// Le joueur peut toujours passer s'il le souhaite
				String inputc = p.chooseCard("Choisissez une carte à défausser.", cchoices, true);
				// On défausse la carte @param inputc
				boolean test = p.defausse(inputc);
				// Si le test est vrai, alors la carte à été défaussée correctement
				if (test) {
					// Alors on incrémente le nombre de cartes défaussées
					nbcdef += 1;
				}
				// Sinon, la carte n'était pas dans la main du joueur, on ne fait rien
			}
			// Si la réponse est non, on sort de la boucle
			else {
				break;
			}
		}
		// On pioche une carte pour chaque carte défaussée
		for(int i=0; i<nbcdef; i++) {
			p.drawCard();
		}
		// Sinon, l'action est finie
		p.incrementActions(-1);	// -1 Action pour l'utilisation de la carte
	}
}