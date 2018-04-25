package dominion.card;

import java.util.*;

/**
 * Les cartes Attaque
 * Rmq: les cartes Attaque sont toutes des cartes Action
 */
public abstract class AttackCard extends ActionCard {

	public AttackCard(String name, int cost) {
		super(name, cost);		// utilisation du constructeur de ActionCard
	}
	
	public List<CardType> getTypes() {
		List<CardType> types = super.getTypes();// on récupère la liste des types(Action) de ActionCard dans une nouvelle liste
		types.add(CardType.Attack);				// on ajoute le type Attack a cette liste
		return types;							//on retourne cette liste
	}
}