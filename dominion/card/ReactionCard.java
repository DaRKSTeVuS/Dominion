package dominion.card;
import java.util.*;

import dominion.*;

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
}