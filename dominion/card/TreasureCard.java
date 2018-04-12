package dominion.card;
import java.util.*;

import dominion.*;

/**
 * Les cartes Trésor
 */
public abstract class TreasureCard extends Card {
	public TreasureCard(String name, int cost) {
		super(name, cost);		// utilisation du constructeur de Card
	}
	
	@Override
	public List<CardType> getTypes() {
		List<CardType> types = super.getTypes();// on récupère la liste des types(vide) de Card dans une nouvelle liste
		types.add(CardType.Treasure);			// on ajoute le type Treasure à cette liste
		return types;							//on retourne cette liste
	}
}