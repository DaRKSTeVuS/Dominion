package dominion.card;

import java.util.*;
import dominion.Player;

/**
 * Les cartes Réaction
 * Rmq: les cartes Réaction sont toutes des cartes Action
 */
public abstract class ReactionCard extends ActionCard {
	public ReactionCard(String name, int cost) {
		super(name, cost);		// utilisation du constructeur de ActionCard
	}

	public List<CardType> getTypes() {
		List<CardType> types = super.getTypes();// on récupère la liste des types(Action) de ActionCard dans une nouvelle liste
		types.add(CardType.Reaction);			// on ajoute le type Réaction a cette liste
		return types;							//on retourne cette liste
	}

	/**
	 * Demande à un joueur qui pocede une reaction card s'il souhaite la jouer
	 * 
	 * @param p joueur qui pocede la carte
	 * @return true s'il souhaite la jouer, sinon false
	 */
	public boolean reaction (Player p) {
		// On demande au joueur s'il souhaite la jouer
		List<String> choices = Arrays.asList("y", "n");
		String input = p.choose("Voulez vous jouer la carte " + this.toString() + " ? (y/n)", choices, true);
		// Si oui
		if (input.equals("y")) {
			// On joue la carte
			p.defausse(this);;
			// On retourve vrai
			return true;
		} else {
			// On signale qu'il ne souhaite pas la jouer
			return false;
		}
	}
}