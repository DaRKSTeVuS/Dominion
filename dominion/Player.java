package dominion;
import java.util.*;
import dominion.card.*;

/**
 * Un joueur de Dominion
 */
public class Player {
	/**
	 * Nom du joueur
	 */
	private String name;

	/**
	 * Nombre d'actions disponibles
	 */
	private int actions;

	/**
	 * Nombre de pièces disponibles pour acheter des cartes
	 */
	private int money;

	/**
	 * Nombre d'achats disponibles
	 */
	private int buys;

	/**
	 * Référence vers la partie en cours
	 */
	private Game game;

	/**
	 * Liste des cartes dans la main du joueur
	 */
	private CardList hand;

	/**
	 * Liste des cartes dans la défausse du joueur
	 */
	private CardList discard;

	/**
	 * Liste des cartes dans la pioche du joueur
	 */
	private CardList draw;

	/**
	 * Listes des cartes qui ont été jouées pendant le tour courant
	 */
	private CardList inPlay;

	/**
	 * Constructeur
	 * 
	 * Initialise les différentes piles de cartes du joueur, place 3 cartes
	 * Estate et 7 cartes Copper dans la défausse du joueur puis fait piocher 5
	 * cartes en main au joueur.
	 * 
	 * @param name: le nom du joueur
	 * @param game: le jeu en cours
	 * 
	 * Indications: On peut utiliser la méthode {@code this.endTurn()} pour 
	 * préparer la main du joueur après avoir placé les cartes dans la défausse.
	 */
	public Player(String name, Game game) {
		// Appel du constructeur parent
		super();
		// Affectation du {@code name}
		this.name = name;
		// Affectation de la {@code game}
		this.game = game;
		// On place 3 cartes Estate
		for (int i = 0; i < 3; i++) {
			this.gain("Estate");
		}
		// Et 7 Cartes copper
		for (int i = 0; i < 7; i++) {
			this.gain("Copper");
		}
		// this.discard.add();
		// - Les compteurs d'actions, argent et achats du joueur sont remis à 0
		// - Les cartes en main et en jeu sont défaussées
		// - Le joueur pioche 5 cartes en main
		this.endTurn();
	}

	/**
	 * Getters et setters
	 */
	public String getName() {
		return this.name;
	}

	public int getActions() {
		return this.actions;
	}

	public int getMoney() {
		return this.money;
	}

	public int getBuys() {
		return this.buys;
	}

	public Game getGame() {
		return this.game;
	}

	/**
	 * Incrémente le nombre d'actions du joueur
	 * 
	 * @param n nombre d'actions à ajouter (ce nombre peut être négatif si l'on
	 * souhaite diminuer le nombre d'actions)
	 */
	public void incrementActions(int n) {
		// On incrémente les actions de n
		this.actions =+ n;
	}

	/**
	 * Incrémente le nombre de pièces du joueur
	 * 
	 * @param n nombre de pièces à ajouter (ce nombre peut être négatif si l'on
	 * souhaite diminuer le nombre de pièces)
	 */
	public void incrementMoney(int n) {
		// On incrémente les pièces de n
		this.money =+ n;
	}

	/**
	 * Incrémente le nombre d'achats disponibles du joueur
	 * 
	 * @param n nombre d'achats à ajouter (ce nombre peut être négatif si l'on
	 * souhaite diminuer le nombre d'achats)
	 */
	public void incrementBuys(int n) {
		// On incrémente les achats de n
		this.buys =+ n;
	}

	/**
	 * Renvoie une liste des cartes que le joueur a en main.
	 * La liste renvoyée doit être une nouvelle {@code CardList} dont les 
	 * éléments sont les mêmes que ceux de {@code this.hand}.
	 */
	public CardList cardsInHand() {
		// On cré une nouvelle liste résultat
		CardList hand = new CardList() ;
		// On parcourt la main
		for(Card c : this.hand) {
			// et on la copie dans le résultat
			hand.add(c);
		}
		// On retourne le résultat
		return hand;
	}

	/**
	 * Renvoie une liste de toutes les cartes possédées par le joueur
	 * (le deck complet c'est-à-dire toutes les cartes dans la main, la
	 * défausse, la pioche et en jeu)
	 */
	public CardList totalCards() {
		// On cré une nouvelle liste de carte qui sera retournée à la fin
		CardList all = new CardList();
		// On y ajoute les cartes de la main
		for(Card c : this.cardsInHand()) {
			all.add(c);
		}
		// On y ajoute les cartes de la défausse
		for(Card c : this.discard) {
			all.add(c);
		}
		// On y ajoute les cartes de la pioche
		for(Card c : this.draw) {
			all.add(c);
		}
		// On y ajoute les cartes en jeu
		for(Card c : this.inPlay){
			all.add(c);
		}
		// On retourne la liste
		return all;
	}

	/**
	 * Renvoie le nombre total de points de victoire du joueur
	 * 
	 * Ce total est calculé en ajoutant les valeurs individuelles de toutes les
	 * cartes dans le deck du joueur (en utilisant la méthode
	 * {@code victoryValue()}) des cartes
	 */
	public int victoryPoints() {
		// On initialise les Points de Victoire à 0
		int VP = 0 ;
		// On parcourt la totalité des cartes possédées par le joueur, où qu'elles soient
		for(Card c : this.totalCards()) {
			// On fait la somme de tous les Points de Victoire
			VP += c.victoryValue(this);
		}
		// On retourne cette somme
		return VP ;
	}

	/**
	 * Renvoie une liste des autres joueurs de la partie.
	 * 
	 * Les adversaires sont listés dans l'ordre de jeu, c'est-à-dire que le
	 * premier de la liste est celui qui joue immédiatement après le joueur,
	 * puis le suivant, et ainsi de suite jusqu'au joueur qui joue immédiatement
	 * avant le joueur.
	 * 
	 * Rmq: Cette méthode fait appel à la méthode {@code otherPlayers(Player p)}
	 * de la classe {@code Game}.
	 */
	public List<Player> otherPlayers() {
		// On appelle la méthode {@code otherPlayers(Player p)} avec p = this
		return this.game.otherPlayers(this);
	}

	/**
	 * Pioche une carte dans la pioche du joueur.
	 * 
	 * Si la pioche du joueur est vide, on commence par mélanger la défausse
	 * et transférer toutes les cartes de la défausse dans la pioche.
	 * On retire et renvoie ensuite la première carte de la pioche si elle n'est
	 * pas vide (sinon la méthode ne fait rien et renvoie {@code null})
	 * 
	 * @return la carte piochée, {@code null} si aucune carte disponible
	 */
	public Card drawCard() {
		// On vérifie que la pioche n'est pas vide (= null)
		if(this.draw != null) {
			// Si c'est le cas, on mélange la défausse 
			this.discard.shuffle();
			// Puis on transfère tout dans la pioche
			for(Card c : this.discard) {
				this.draw.add(c);
			}
		}
		// Ensuite, on ajoute la 1ere carte de la pioche dans la main du joueur
		// Ou pas
		// Puis on la retire de la pioche (et on la retourne)
		return this.draw.remove(0);
	}

	/**
	 * Renvoie une représentation de l'état du joueur sous forme d'une chaîne
	 * de caractères.
	 * 
	 * Cette représentation comporte
	 * - le nom du joueur
	 * - le nombre d'actions, de pièces et d'achats du joueur
	 * - le nombre de cartes dans la pioche et dans la défausse du joueur
	 * - la liste des cartes en jeu du joueur
	 * - la liste des cartes dans la main du joueur
	 */
	public String toString() {
		String r = String.format("     -- %s --\n", this.name);
		r += String.format("Actions: %d     Money: %d     Buys: %d     Draw: %d     Discard: %d\n", this.actions, this.money, this.buys, this.draw.size(), this.discard.size()); 
		r += String.format("In play: %s\n", this.inPlay.toString());
		r += String.format("Hand: %s\n", this.hand.toString());
		return r;
	}

	/**
	 * Renvoie la liste de toutes les cartes Trésor dans la main du joueur
	 */
	public CardList getTreasureCards() {
		// Initialisation de la liste de carte à retourner
		CardList res = new CardList();
		// Parcour de la totatlité de la main
		for (Card c : this.hand) {
			// Si la carte courante est de type TreasureCard
			if (c instanceof TreasureCard) {
				// On l'ajoute à la liste de retour
				res.add(c);
			}
		}
		// On renvoie la liste
		return res;
	}

	/**
	 * Renvoie la liste de toutes les cartes Action dans la main du joueur
	 */
	public CardList getActionCards() {
		// Initialisation de la liste de carte à retourner
		CardList res = new CardList();
		// Parcour de la totatlité de la main
		for (Card c : this.cardsInHand()) {
			// Si la carte courante est de type ActionCard
			if (c instanceof ActionCard) {
				// On l'ajoute à la liste de retour
				res.add(c);
			}
		}
		// On renvoie la liste
		return res;
	}

	/**
	 * Renvoie la liste de toutes les cartes Victoire dans la main du joueur
	 */
	public CardList getVictoryCards() {
		// Initialisation de la liste de carte à retourner
		CardList res = new CardList();
		// Parcour de la totatlité de la main
		for (Card c : this.hand) {
			// Si la carte courante est de type VictoryCard
			if (c instanceof VictoryCard) {
				// On l'ajoute à la liste de retour
				res.add(c);
			}
		}
		// On renvoie la liste
		return res;
	}

	/**
	 * Joue une carte de la main du joueur.
	 * 
	 * @param c carte à jouer
	 * 
	 * Cette méthode ne vérifie pas que le joueur a le droit de jouer la carte,
	 * ni même que la carte se trouve effectivement dans sa main.
	 * La méthode retire la carte de la main du joueur, la place dans la liste
	 * {@code inPlay} et exécute la méthode {@code play(Player p)} de la carte.
	 */
	public void playCard(Card c) {
		// On active l'effet de la carte
		c.play(this);
		// On place la carte dans "inPlay" 
		this.inPlay.add(c);
		// On retire cette carte de la main du joueur
		this.hand.remove(c);
	}

	/**
	 * Joue une carte de la main du joueur.
	 * 
	 * @param cardName nom de la carte à jouer
	 * 
	 * S'il existe une carte dans la main du joueur dont le nom est égal au
	 * paramètre, la carte est jouée à l'aide de la méthode 
	 * {@code playCard(Card c)}. Si aucune carte ne correspond, la méthode ne
	 * fait rien.
	 */
	public void playCard(String cardName) {
		// On vérifie que la carte est dans la main du joueur
		if (this.hand.getCard(cardName) != null) {
			// On appelle la méthode {@code playCard(Card c)} 
			this.playCard(this.hand.getCard(cardName));
		}
		// Sinon, on ne fait rien
	}

	/**
	 * Le joueur gagne une carte.
	 * 
	 * @param c carte à gagner (éventuellement {@code null})
	 * 
	 * Si la carte n'est pas {@code null}, elle est placée sur la défausse du
	 * joueur. On suppose que la carte a correctement été retirée de son 
	 * emplacement précédent au préalable.
	 */
	public void gain(Card c) {
		// On vérifie que la carte n'est pas nulle
		if(c != null) {
			// Alors on la place dans la défausse du joueur "this"
			this.discard.add(c) ;
		}
		// Sinon, on ne fait rien
	}

	/**
	 * Le joueur gagne une carte de la réserve
	 * 
	 * @param cardName nom de la carte à gagner. S'il existe une carte dans la 
	 * réserve ayant ce nom, cette carte est retirée de la réserve et placée 
	 * sur la défausse du joueur.
	 * @return la carte qui a été ajoutée à la défausse du joueur, ou {@code 
	 * null} si aucune carte n'a été prise dans la réserve.
	 */
	public Card gain(String cardName) {
		// On appelle la méthode {@code gain(Card c)} pour la défausser
		// Si la carte se trouve dans "supplyStacks", rien ne se passera
		this.gain(this.game.getFromSupply(cardName));
		// On retire la carte de la réserve
		// Si elle ne s'y trouve pas, @return sera {@code null} 
		return this.game.removeFromSupply(cardName) ;
	}

	/**
	 * Le joueur achète une carte de la réserve
	 * 
	 * La méthode cherche une carte dans la réserve dont le nom est égal au
	 * paramètre, puis vérifie que le joueur a assez de pièces pour l'acheter 
	 * et au moins un achat disponible.
	 * Si le joueur peut acheter la carte, le coût de la carte est soustrait à
	 * l'argent du joueur, le nombre d'achats disponibles est décrémenté de 1 
	 * et la carte est gagnée par le joueur.
	 * 
	 * @param cardName nom de la carte à acheter
	 * @return la carte qui a été gagnée ou {@code null} si l'achat n'a pas eu 
	 * lieu
	 */
	public Card buyCard(String cardName) {
		// On trouve la carte dans la réserve
		if (this.game.getFromSupply(cardName) != null) {
			// On stock le coût dans une variable (pour éviter la surcharge de calcul)
			int ccost =  this.game.getFromSupply(cardName).getCost();
			// On vérifie que le joueur a assez de Pièces pour réaliser l'achat
			if(this.getMoney() >= ccost) {
				// On vérifie qu'il a assez de Points d'Achat
				if(this.getBuys() > 0) {
					// Si tout est bon, on soustrait le coût de la carte à l'argent du joueur
					this.incrementMoney(- ccost);
					// On décrémente les Points d'Achat de 1
					this.incrementBuys(-1);
					// Le joueur gagne la carte {@code gain(String cardName)} et on la retourne
					return this.gain(cardName);
				}
			}
		}
		// Sinon, on retourne null
		return null ;
	}

	/**
	 * Attend une entrée de la part du joueur (au clavier) et renvoie le choix
	 *  du joueur.
	 * 
	 * @param instruction message à afficher à l'écran pour indiquer au joueur
	 * la nature du choix qui est attendu
	 * @param choices une liste de chaînes de caractères correspondant aux
	 * choix valides attendus du joueur (la liste sera convertie en ensemble 
	 * par la fonction pour éliminer les doublons, ce qui permet de compter 
	 * correctement le nombre d'options disponibles)
	 * @param canPass booléen indiquant si le joueur a le droit de passer sans
	 * faire de choix. S'il est autorisé à passer, c'est la chaîne de
	 * caractères vide ("") qui signifie qu'il désire passer.
	 * 
	 * @return la méthode lit l'entrée clavier jusqu'à ce qu'un choix valide
	 * soit entré par l'utilisateur (un élément de {@code choices} ou
	 * éventuellement la chaîne vide si l'utilisateur est autorisé à passer).
	 * Lorsqu'un choix valide est obtenu, il est renvoyé.
	 * 
	 * Si l'ensemble {@code choices} ne comporte qu'un seul élément et que
	 * {@code canPass} est faux, l'unique choix valide est automatiquement
	 * renvoyé sans lire l'entrée de l'utilisateur.
	 * 
	 * Si l'ensemble des choix est vide, la chaîne vide ("") est 
	 * automatiquement renvoyée par la méthode (indépendamment de la valeur de 
	 * {@code canPass}).
	 * 
	 * Exemple d'utilisation pour demander à un joueur de répondre à une 
	 * question :
	 * <pre>
	 * {@code
	 * List<String> choices = Arrays.asList("y", "n");
	 * String input = p.choose("Do you want to ...? (y/n)", choices, false);
	 * }
	 * </pre>
	 */
	public String choose(String instruction, List<String> choices, boolean canPass) {
		// La liste de choix est convertie en ensemble pour éviter les doublons
		Set<String> choiceSet = new HashSet<String>();
		for (String c: choices) {
			choiceSet.add(c);
		}
		if (choiceSet.isEmpty()) {
			// Aucun choix disponible
			return "";
		} else if (choiceSet.size() == 1 && !canPass) {
			// Un seul choix possible (renvoyer cet unique élément)
			return choiceSet.iterator().next();
		} else {
			Scanner sc = new Scanner(System.in);
			String input;
			// Lit l'entrée de l'utilisateur jusqu'à obtenir un choix valide
			while (true) {
				System.out.print("\n\n");
				// Affiche l'état du jeu
				System.out.print(this.game);
				System.out.print("\n");
				// Affiche l'état du joueur
				System.out.print(this);
				System.out.print("\n");
				// Affiche l'instruction
				System.out.println(">>> " + instruction);
				System.out.print("> ");
				// Lit l'entrée de l'utilisateur au clavier
				input = sc.nextLine();
				if (choiceSet.contains(input) || (canPass && input.equals(""))){
					// On ferme le scanner
					sc.close();
					// Si une réponse valide est obtenue, elle est renvoyée
					return input;
				}
			}
		}
	}

	/**
	 * Attend une entrée de la part du joueur et renvoie le choix du joueur.
	 * Dans cette méthode, la liste des choix est donnée sous la forme d'une 
	 * liste de cartes et le joueur doit choisir le nom d'une de ces cartes.
	 * 
	 * @param instruction message à afficher à l'écran pour indiquer au joueur
	 * la nature du choix qui est attendu
	 * @param choices liste de cartes parmi lesquelles il faut en choisir une
	 * parmi lesquelles l'utilisateur doit choisir
	 * @param canPass booléen indiquant si le joueur a le droit de passer sans
	 * faire de choix. S'il est autorisé à passer, c'est la chaîne de
	 * caractères vide ("") qui signifie qu'il désire passer.
	 * 
	 * La méthode commence par construire une liste de tous les noms des cartes 
	 * dans {@code choices} puis appelle la méthode précédente pour faire 
	 * choisir un nom parmi cette liste à l'utilisateur.
	 * 
	 * Exemple d'utilisation pour faire choisir le nom d'une carte Action de sa
	 * main à un joueur (dans cet exemple le joueur n'a pas le droit de passer 
	 * s'il a au moins une carte Action en main, mais la méthode peut quand 
	 * même renvoyer {@code ""} s'il n'a aucune carte Action en main) :
	 * <pre>
	 * {@code
	 * CardList choices = new CardList();
	 * for (Card c: p.cardsInHand()) {
	 *   if (c.getTypes().contains(CardType.Action)) {
	 *     choices.add(c);
	 *   }
	 * }
	 * String input = p.chooseCard("Choose an Action card.", choices, false);
	 * </pre>
	 */
	public String chooseCard(String instruction, CardList choices, boolean canPass) {
		// Liste de noms de cartes
		List<String> stringChoices = new ArrayList<String>();
		for (Card c: choices) {
			// Tous les noms sont ajoutés à l'ensemble
			stringChoices.add(c.getName());
		}
		// Appel de la méthode précédente en passant l'ensemble de noms
		return this.choose(instruction, stringChoices, canPass);
	}

	/**
	 * Démarre le tour du joueur
	 * 
	 * Les compteurs d'actions et achats sont mis à 1
	 */
	public void startTurn() {
		// Compteur d'action initialisé à 1
		this.actions = 1;
		// Compteur d'achats initialisé à 1
		this.buys = 1;
	}

	/**
	 * Termine le tour du joueur
	 * 
	 * - Les compteurs d'actions, argent et achats du joueur sont remis à 0
	 * - Les cartes en main et en jeu sont défaussées
	 * - Le joueur pioche 5 cartes en main
	 */
	public void endTurn() {
		// Compteur d'action remis à 0
		this.actions = 0;
		// Compteur d'achats remis à 0
		this.buys = 0;
		// Défausse des cartes en main (hand)
		for(Card c : this.hand) {
			this.defausse(c);
		}
		// Défausse des cartes en jeu (inPlay)
		for(Card c : this.inPlay) {
			// Ajout dans la défausse
			this.discard.add(c);
			// Retrait des cartes en jeu
			this.inPlay.remove(c);
		}
		// Le joueur pioche 5 cartes
		for(int i=0; i<5; i++) {
			this.drawCard();
		}
	}

	/**
	 * Exécute le tour d'un joueur
	 * 
	 * Cette méthode exécute successivement les 5 phases du tour d'un joueur:
	 * 
	 * 1. (Préparation) la méthode {@code startTurn()} est appelée
	 * 
	 * 2. (Action) Tant que le joueur a des actions disponibles, on lui demande 
	 * de choisir le nom d'une carte Action de sa main à jouer. Il peut passer à
	 * tout moment à la phase suivante (soit de manière forcée s'il n'a plus de 
	 * carte Action en main soit volontairement en entrant la chaîne vide). 
	 * Lorsqu'il joue une carte Action, la fonction décrémente son nombre 
	 * d'actions puis joue la carte.
	 * 
	 * 3. (Trésor) Le joueur joue toutes les cartes Trésor de sa main 
	 * automatiquement (dans le jeu de base il n'y a aucune raison de ne pas 
	 * jouer tous les trésors automatiquement).
	 * 
	 * 4. (Achat) Tant que le joueur a au moins un achat disponible, on lui 
	 * demande de choisir le nom d'une carte de la réserve qu'il veut acheter. 
	 * Il ne peut acheter que des cartes dont le prix est inférieur à l'argent 
	 * dont il dispose. Le joueur peut passer (et terminer son tour) à tout 
	 * moment pendant cette phase.
	 * 
	 * 5. (Fin) La méthode {@code endTurn()} est appelée pour terminer le tour 
	 * du joueur
	 */
	public void playTurn() {
		// (1) Préparation
		// On appelle {@code startTurn()}
		this.startTurn();
		// (2) Action
		// Tant qu'on a des actions en main
		// On demande au joueur s'il souhaite jouer une carte
		// Si le joueur veut jouer une carte
		// On lui propose les cartes disponibles à l'utilisation
		// et le joueur choisit une carte qu'il souhaite utiliser
		// On place la carte dans inPlay
		// On retire la carte de la main
		// On utilise la carte {@code play(Player p)
		// -1 Action pour l'utilisation de la carte

		// (3) Trésor
		// Joue automatiquement toute les cartes trésor de la main du joueur
		for(Card c : this.getTreasureCards()) {
			c.play(this);
		}		
		// (4) Achat
		// tant qu'il est possible de faire des achats
		while(this.getBuys() > 0 && this.getMoney() > 0){
			// On demande au joueur s'il souhaite acheter une carte
			List<String> choices = Arrays.asList("oui", "non");
			String input = this.choose("Voulez-vous acheter une carte (y/n)", choices, false);
			// Si le joueur désire faire un achat
			if(input == "oui") {
				// On lui propose les cartes disponibles à l'achat
				// et le joueur choisit une carte qu'il souhaite acheter  
				// On utilise la méthode {@code buyCard(Card c)}
			}
		}

		// (5) Fin du tour
		// On appelle {@code endTurn()}
		this.endTurn();
	}

	/**
	 * Place la carte de la main passée en paramètre dans la défausse
	 * On admet qu'elle existe dans la main du joueur (vérifié au préalable)
	 * 
	 * @param c carte à defausser
	 */
	public void defausse(Card c) {
		// Ajout dans la défausse
		this.discard.add(c);
		// Retrait de la main du joueur
		this.hand.remove(c);
	}

	/**
	 * Defausse une carte dont le nom est donné
	 * 
	 * @param cardName nom de carte à defausser
	 * @return true si la carte est bien defaussé, false si elle n'est pas dans la main
	 */
	public boolean defausse(String cardName) {
		// On vérifie que la carte est dans la main du joueur
		if (this.hand.getCard(cardName) != null) {
			// Si oui, on utilise la méthode {@code defausse(Card c)}
			this.defausse(this.hand.getCard(cardName));
			// et on renvoie vrai
			return true;
		}
		// Sinon, on renvoie faux
		return false;	
	}

	/**
	 * Met au rebut {@code trashedCards} la carte passée en paramètre
	 * On admet qu'elle existe dans la main du joueur (vérifié au préalable)
	 *  
	 * @param c carte à mettre au rebut
	 */
	public void ecarter(Card c) {
		// Ajout au Rebut
		this.game.trash(c);
		// Retrait de la main du joueur
		this.hand.remove(c);
	}

	/**
	 * Met au rebut une carte
	 * 
	 * @param cardName nom de la carte à mettre au rebut
	 * @return true si la carte à été mise au rebut, false si elle n'est pas dans la main
	 */
	public boolean ecarter(String cardName) {
		// On vérifie que la carte est dans la main du joueur
		if (this.hand.getCard(cardName) != null) {
			// Si oui, on utilise la méthode {@code ecarter(Card c)}
			this.ecarter(this.hand.getCard(cardName));
			// et on renvoie vrai
			return true;
		}
		// Sinon, on renvoie faux
		return false;	
	}

	/**
	 *  On met tout le deck dans la défausse
	 */
	public void discardDraw() {
		// On parcours la pioche
		for(Card c : this.draw) {
			// On ajoute la carte courante a la defausse
			this.discard.add(c);
			// On retire la carte de la pioche
			this.draw.remove(c);
		}
	}

	/**
	 * Met une carte en haut de la pioche du joueur
	 * 
	 * @param carte à mettre en haut du deck
	 */
	public void putOnTopDraw (Card c) {
		// On retire la carte de la main
		this.hand.remove(c);
		// On prend la carte et on l'ajoute en premier de {@code this.draw}
		this.draw.add(0, c);
	}

	/**
	 * Met une carte en haut de la pioche du joueur
	 * 
	 * @param nom de la carte à mettre en haut du deck
	 * @return carte mise en haut du deck, null si la carte est pas retiré
	 */
	public Card putOnTopDraw (String cardName) {
		// On vérifie que la carte est dans la main du joueur
		if (this.hand.getCard(cardName) != null) {
			// On initialise la carte à retirer
			Card tmpC = this.hand.getCard(cardName);
			// on utilise la méthode {@code putOnTopDraw(Card c)}
			this.putOnTopDraw(tmpC);
			return tmpC;
		} else {
			return null;
		}
	}
}
