package dominion;
import java.util.*;
import dominion.card.*;
import dominion.card.common.*;

/**
 * Class représentant une partie de Dominion
 */
public class Game {
	/**
	 * Tableau contenant les joueurs de la partie
	 */
	private Player[] players;

	/**
	 * Index du joueur dont c'est actuellement le tour
	 */
	private int currentPlayerIndex;

	/**
	 * Liste des piles dans la réserve du jeu.
	 * 
	 * On suppose ici que toutes les listes contiennent des copies de la même
	 * carte.
	 * Ces piles peuvent être vides en cours de partie si toutes les cartes de 
	 * la pile ont été achetées ou gagnées par les joueurs.
	 */
	private List<CardList> supplyStacks;

	/**
	 * Liste des cartes qui ont été écartées (trash)
	 */
	private CardList trashedCards;

	/**
	 * Constructeur
	 * 
	 * @param playerNames liste des noms des joueurs qui participent à la 
	 * partie. Le constructeur doit créer les objets correspondant aux joueurs
	 * @param kingdomStacks liste de piles de réserve à utiliser correspondant 
	 * aux cartes "royaume" à utiliser dans la partie, auxquelles le 
	 * constructeur doit ajouter les piles "communes":
	 * - 60 Copper
	 * - 40 Silver
	 * - 30 Gold
	 * - 8 (si 2 joueurs) ou 12 (si 3 ou 4 joueurs) Estate, Duchy et Province
	 * - 10 * (n-1) Curse où n est le nombre de joueurs dans la partie
	 */
	public Game(String[] playerNames, List<CardList> kingdomStacks) {
		// Initialisation de {@code players}
		this.players = new Player[playerNames.length];
		// Pour chaque nom de joueurs dans {@code playerNames}
		for (int i = 0; i < this.players.length; i++) {
			// On prend le nom, et on creer un nouveau joueur à l'affectant à this
			this.players[i] = new Player(playerNames[i], this);
		}
		// On initialise la Liste des piles dans la réserve du jeu
		this.supplyStacks = new ArrayList<CardList>();
		// On parcours {@code kindomStacks}
		for (CardList c : kingdomStacks) {
			// On ajoute par copie les CardList contenue dans {@code kingdomStacks}
			this.supplyStacks.add(new CardList(c));
		}
		// Création de la liste de carte copper
		CardList copper = new CardList();
		// On ajoute 60 cartes copper à la CardList {@code copper}
		for (int i = 0; i < 60; i++) {
			copper.add(new Copper());
		}
		// On ajoute copper au {@code supplyStacks}
		this.supplyStacks.add(copper);
		// Création de la liste de carte silver
		CardList silver = new CardList();
		// On ajoute 40 cartes silver à la CardList {@code silver}
		for (int i = 0; i < 40; i++) {
			silver.add(new Silver());
		}
		// On ajoute copper au {@code supplyStacks}
		this.supplyStacks.add(silver);
		// Création de la liste de carte gold
		CardList gold = new CardList();
		// On ajoute 30 cartes gold à la CardList {@code gold}
		for (int i = 0; i < 30; i++) {
			gold.add(new Gold());
		}
		// On ajoute gold au {@code supplyStacks}
		this.supplyStacks.add(gold);
		// On initialise le nombre de cartes à distribuer
		int nbCards = 0;
		// Si le nombre de joueurs est égal à 2
		if (this.players.length == 2) {
			// on prévois d'ajouter 8 cartes
			nbCards = 8;
			// Si le nombre de joueurs est 3 ou 4	
		} else if ((this.players.length == 3) && (this.players.length == 4)) {
			// on prévois d'ajouter 12 cartes
			nbCards = 12;
		}
		// Création de la liste de carte Estate
		CardList estate = new CardList();
		// On ajoute 8 cartes Duchy
		CardList duchy = new CardList();
		// Création de la liste de carte Province
		CardList province = new CardList();
		// Ajoute {@code nbCards} fois des cartes :
		for (int i = 0; i < nbCards; i++) {
			// de type Estate
			estate.add(new Estate());
			// de type Duchy
			duchy.add(new Duchy());
			// de type Province
			province.add(new Province());
		}
		// On ajoute {@code estate} au {@code supplyStacks}
		this.supplyStacks.add(estate);
		// On ajoute {@code duchy} au {@code supplyStacks}
		this.supplyStacks.add(duchy);
		// On ajoute {@code province} au {@code supplyStacks}
		this.supplyStacks.add(province);
		// Creation de la liste de carte Curse
		CardList curse = new CardList();
		// On calcul le nombre de cartes à ajouter (10 * (n-1) cartes Curse où n est le nombre de joueurs dans la partie)
		nbCards = 10 * (this.players.length - 1);
		// On ajoute {@code nbCards} cartes Curse à la CardList {@code : curse}
		for (int i = 0; i < nbCards; i++) {
			curse.add(new Curse());
		}
		// On ajoute curse au {@code supplyStacks}
		this.supplyStacks.add(curse);

	}

	/**
	 * Renvoie le joueur correspondant à l'indice passé en argument
	 * On suppose {@code index} est un indice valide du tableau 
	 * {@code this.players}
	 * 
	 * @param index indice dans le tableau des joueurs du joueur à renvoyer
	 */
	public Player getPlayer(int index) {
		// On retourne le joueur correspondant à l'addresse passée en argument
		return this.players[index];
	}

	/**
	 * Renvoie le nombre de joueurs participant à la partie
	 */
	public int numberOfPlayers() {
		// On retourne le nombre de joueurs dans la partie (idéalement 2,3 ou 4)
		return this.players.length;
	}

	/**
	 * Renvoie l'indice du joueur passé en argument dans le tableau des 
	 * joueurs, ou -1 si le joueur n'est pas dans le tableau.
	 */
	private int indexOfPlayer(Player p) {
		// On parcourt le tableau de joueurs
		for(int i=0; i<this.numberOfPlayers(); i++) {
			// Si on trouve le joueur
			if(this.getPlayer(i) == p) {
				// On renvoie l'index
				return i ;
			}
		}
		// Si on a pas trouvé le joueur, on renvoie -1
		return -1;
	}

	/**
	 * Renvoie la liste des adversaires du joueur passé en argument, dans 
	 * l'ordre dans lequel ils apparaissent à partir du joueur {@code p}.
	 * 
	 * @param p joueur dont on veut renvoyer la liste des adversaires. On 
	 * suppose que {@code p} est bien dans le tableau des joueurs.
	 * @return un {@code ArrayList} contenant les autres joueurs de la partie 
	 * en commençant par celui qui se trouve juste après {@code p} et en 
	 * terminant par celui qui se trouve juste avant (le tableau est considéré 
	 * comme cyclique c'est-à-dire qu'après le premier élément on revient au 
	 * premier).
	 */
	public List<Player> otherPlayers(Player p) {
		// On met le nombre de joueurs dans une variable, pour éviter de la recalculer à chaque fois
		int nbPlayers = this.numberOfPlayers() ;
		// On cré une ArrayList qui contiendra les autres joueurs
		ArrayList<Player> otherp = new ArrayList<Player>() ;
		// On place stock l'index du joueur courant dans une variable
		// Elle nous servira à parcourir le tableau
		int i = this.currentPlayerIndex;
		// On commence à parcourir le tableau des joueurs, tant que la taille de la 
		// liste des autres joueurs est strictement inférieure au tableau des joueurs
		// normalement, la taille de la liste = taille du tableau -1
		while (otherp.size() < nbPlayers) {
			// Si on est déja à la fin du tableau, on repart au début
			if (i == nbPlayers) i = 0;
			// Sinon, on commence à partit du suivant
			else i += 1;
			// On ajoute le joueur à la liste
			otherp.add(this.getPlayer(i));
		}
		// On retourne la liste des autres joueurs
		return otherp;
	}

	/**
	 * Renvoie la liste des cartes qui sont disponibles à l'achat dans la 
	 * réserve.
	 * 
	 * @return une liste de cartes contenant la première carte de chaque pile 
	 * non-vide de la réserve (cartes royaume et cartes communes)
	 */
	public CardList availableSupplyCards() {
		// Initialisation de la CardList retournée
		CardList res = new CardList();
		// On parcours {@code this.supplyStacks}
		for (CardList c : this.supplyStacks) {
			// On controle que la CardList courante n'est pas null
			if (c.get(0) != null) {
				// On l'ajoute au résultat
				res.add(c.get(0));
			}
		}
		return res;
	}

	/**
	 * Renvoie une représentation de l'état de la partie sous forme d'une chaîne
	 * de caractères.
	 * 
	 * Cette représentation comporte
	 * - le nom du joueur dont c'est le tour
	 * - la liste des piles de la réserve en indiquant pour chacune :
	 *   - le nom de la carte
	 *   - le nombre de copies disponibles
	 *   - le prix de la carte
	 *   si la pile n'est pas vide, ou "Empty stack" si la pile est vide
	 */
	public String toString() {
		Player currentPlayer = this.players[this.currentPlayerIndex];
		String r = String.format("     -- %s's Turn --\n", currentPlayer.getName());
		for (List<Card> stack: this.supplyStacks) {
			if (stack.isEmpty()) {
				r += "[Empty stack]   ";
			} else {
				Card c = stack.get(0);
				r += String.format("%s x%d(%d)   ", c.getName(), stack.size(), c.getCost());
			}
		}
		r += "\n";
		return r;
	}

	/**
	 * Renvoie une carte de la réserve dont le nom est passé en argument.
	 * 
	 * @param cardName nom de la carte à trouver dans la réserve
	 * @return la carte trouvée dans la réserve ou {@code null} si aucune carte 
	 * ne correspond
	 */
	public Card getFromSupply(String cardName) {
		// On parcours {@code this.supplyStacks}
		for (CardList cl : this.supplyStacks) {
			// On parcours la CardList courante
			for (Card c : cl) {
				// Si le nom de la Card correspond à @param cardName 
				if (c.getName().equals(cardName)) {
					// On retourne cette carte
					return c;
				}
			}
		}
		// Si rien n'est trouvé on retourne {@code null}
		return null;
	}

	/**
	 * Retire et renvoie une carte de la réserve
	 * 
	 * @param cardName nom de la carte à retirer de la réserve
	 * @return la carte retirée de la réserve ou {@code null} si aucune carte
	 * ne correspond au nom passé en argument
	 */
	public Card removeFromSupply(String cardName) {
		// Initialisation et affectation de la carte de retour
		// avec la méthode {@code getFromSupply}
		Card resCard = this.getFromSupply(cardName);
		// Si la carte est dans la reserve
		if (resCard != null) {
			// On parcours la réserve
			for (CardList cl : this.supplyStacks) {
				// Pour la retirer
				cl.remove(resCard);
			}
		}
		// On retourne la carte si elle est trouvée, sinon on retourne {@code null}
		return resCard;
	}

	/**
	 * Teste si la partie est terminée
	 * 
	 * @return un booléen indiquant si la partie est terminée, c'est-à-dire si
	 * au moins l'unedes deux conditions de fin suivantes est vraie
	 *  - 3 piles ou plus de la réserve sont vides
	 *  - la pile de Provinces de la réserve est vide
	 * (on suppose que toute partie contient une pile de Provinces, et donc si 
	 * aucune des piles non-vides de la réserve n'est une pile de Provinces, 
	 * c'est que la partie est terminée)
	 */
	public boolean isFinished() {
		
	}

	/**
	 * Boucle d'exécution d'une partie.
	 * 
	 * Cette méthode exécute les tours des joueurs jusqu'à ce que la partie soit
	 * terminée. Lorsque la partie se termine, la méthode affiche le score 
	 * final et les cartes possédées par chacun des joueurs.
	 */
	public void run() {
		while (! this.isFinished()) {
			// joue le tour du joueur courant
			this.players[this.currentPlayerIndex].playTurn();
			// passe au joueur suivant
			this.currentPlayerIndex += 1;
			if (this.currentPlayerIndex >= this.players.length) {
				this.currentPlayerIndex = 0;
			}
		}
		System.out.println("Game over.");
		// Affiche le score et les cartes de chaque joueur
		for (int i = 0; i < this.players.length; i++) {
			Player p = this.players[i];
			System.out.println(String.format("%s: %d Points.\n%s\n", p.getName(), p.victoryPoints(), p.totalCards().toString()));
		}
	}
}