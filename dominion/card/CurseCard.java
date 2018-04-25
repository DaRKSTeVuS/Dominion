package dominion.card;

import java.util.*;

/**
 * Les cartes Malédiction
 */
public abstract class CurseCard extends Card {
	public CurseCard(String name, int cost) {
		super(name, cost);		// utilisation du constructeur de Card
	}
	
	@Override
	public List<CardType> getTypes() {
		List<CardType> types = super.getTypes();// on récupère la liste des types(vide) de Card dans une nouvelle liste
		types.add(CardType.Curse);				// on ajoute le type Curse à cette liste
		return types;							//on retourne cette liste
	}
}