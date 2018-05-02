package dominion.card;

import java.util.*;
import dominion.Player;

/**
 * Les cartes Réaction
 * Rmq: les cartes Réaction sont toutes des cartes Action
 */
public abstract class ReactionCard extends ActionCard {
	public ReactionCard(String name, int cost) {
		// Utilisation du constructeur de ActionCard
		super(name, cost);
	}

	public List<CardType> getTypes() {
		// On récupère la liste des types(Action) de ActionCard dans une nouvelle liste
		List<CardType> types = super.getTypes();
		// On ajoute le type Réaction a cette liste
		types.add(CardType.Reaction);
		// On retourne cette liste
		return types;							
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
			System.out.println("Carte " + this.getName() + " devoilée, la carte attack n'as pas d'effet");
			// On retourve vrai
			return true;
		} else {
			// On signale qu'il ne souhaite pas la jouer
			return false;
		}
	}
}