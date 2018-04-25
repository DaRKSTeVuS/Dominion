package dominion.card;

import java.util.*;

/**
 * Les cartes Action
 */
public abstract class ActionCard extends Card {

	public ActionCard(String name, int cost) {
		super(name, cost);		// utilisation du constructeur de Card
	}
	
	@Override
	public List<CardType> getTypes() {
		List<CardType> types = super.getTypes(); // on récupère la liste des types(vide) de Card dans une nouvelle liste
		types.add(CardType.Action);				// on ajoute le type Action à cette liste
		return types;							//on retourne cette liste
	}
	
	
	
}