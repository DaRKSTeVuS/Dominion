package dominion.card.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dominion.*;
import dominion.card.*;

/**
 * Carte Voleur (Thief)
 * 
 * Tous vos adversaires dévoilent les 2 premières cartes de leur deck. 
 * S'ils dévoilent des cartes Trésor, ils en écartent 1 de votre choix. 
 * Parmi ces cartes Trésor écartées, recevez celles de votre choix. 
 * Les autres cartes dévoilées sont défaussées.
 */
public class Thief extends AttackCard {

	public Thief() {
		// Construction de la carte avec ses caractérisques
		super("Thief", 4);
	}

	@Override
	public void play(Player p) {
		// On initialise une liste de cartes à écarter
		ArrayList<String> toTrash = new ArrayList<String>();
		// On parcourt la liste des adversaires
		for (Player op : p.otherPlayers()) {
			// Si le joueur n'as pas de carte réaction
			if (!this.otherPlayerGotReaction(op)) {
				// On dévoile les 2 premières cartes de sa pioche
				// On cré une liste de cartes contennant les 2 premières cartes de sa pioche
				CardList pioche = new CardList();
				// à laquelle on ajoute les cartes
				pioche.add(op.drawCard());
				pioche.add(op.drawCard());				
				// On l'affiche
				pioche.toString();
				
				// On parcourt pioche
				// On discard toute carte qui n'est pas Trésor
				// pioche.get(i).getTypes().contains(CardType.Treasure) 
				// Si pioche est non vide				
					// On en choisit 1
					String inputt = p.chooseCard("Choisissez une carte", pioche, false);
					// On l'ajoute à la liste
					toTrash.add(inputt);
				}else {
					// Sinon on affiche un message
					System.out.println("Il n'y a pas de TreasureCard à voler !");
				}

			}

		}
		// Parmi la liste des cartes à écarter, on choisit celle qu'on veut recevoir
		// On cré un booléen indiquant si le joueur souhaite continuer à recevoir des cartes
		boolean choice = true;
		// Création de la liste de choix oui/non
		List<String> choices = Arrays.asList("y", "n");
		// Tant que la liste n'est pas vide et que l'on souhaite continuer à recevoir des cartes
		while (toTrash.size()>0 && choice == true) {
			String input = p.choose("Voulez-vous défausser une carte ? (y/n)", choices, false);
			// Si on décide de défausser une carte
			if (input.equals("y")) {
				String inputc = p.chooseCard("Choisissez une carte à recevoir", toTrash, true);
				// Si le joueur n'a pas choisit une carte de la liste
				if(inputc.equals(null)) {
					choice = false;
				}else {
					// Sinon, la carte est reçue
					./
				}
			}else {
				choice = false;
			}
		}

		// On écarte le reste
		for(int i=0; i<toTrash.size(); i++) {
			toTrash.remove(0);
		}
	}
}