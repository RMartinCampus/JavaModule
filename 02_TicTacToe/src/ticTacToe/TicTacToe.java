package ticTacToe;

import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToe {
	//Represente un plateau de TicTacToe


	//Attributs & Accesseurs
	private int size;
	int getSize(){
		return size;
	}

	private Cell[][] tCells=new Cell[size][size];	//tableau des cellule du plateau
	protected Cell[][] getTCells(){
		//Renvoie le tableau de cellules
		return tCells;
	}
	protected Cell getCell(int[] coordonee){
		//Renvoie la Cellule corespondant au coordonee
		return tCells[coordonee[0]][coordonee[1]];
	}

	private char separateur='-'; 						//separateur de ligne
	private String lSepa="";								//contient size fois le separateur de ligne pour l'affichage du plateau
	private ArrayList <Player> tabPlayer=new ArrayList<Player>();
		

	//Constructeurs
	TicTacToe(){
		this(3);
	}

	TicTacToe(int size){
		this.size=size;
		tCells=new Cell[size][size];
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				tCells[i][j]=new Cell();
			}
		}
		iniLSepa();
	}

	void iniLSepa(){
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < tCells[0][0].getRepresentation().length(); j++) {
				lSepa+=separateur;
			}
		}
		lSepa+=separateur;	//Ajout d'un separateur pour la derniere colone de "|"
	}


	//Methodes

	public void play(){
		//Gere la logique generale d'une partie de Tictactoe

		//Initialisation
		tabPlayer.add(new Player());
		tabPlayer.get(0).setRepresentation();
		Player j2=(tabPlayer.get(0).getRepresentation().equals(tabPlayer.get(0).getListeRepresentation()[0]) //Cree le J2 et lui donne la representation restente);
		? new Player(tabPlayer.get(0).getListeRepresentation()[1])
		: new Player(tabPlayer.get(0).getListeRepresentation()[0]));
		tabPlayer.add(j2);	
		System.out.println("J1 joue les "+tabPlayer.get(0).getRepresentation()+" et J2 joue les "+tabPlayer.get(1).getRepresentation());
		int[] coordonee=new int[2];
		int numTours=-1;
		//Derouler de la partie
		do {
			numTours+=1;
			coordonee=nextMouv(numTours);
			display();
		} while (!isOver(coordonee,numTours));

	} 

	private int[] nextMouv(int numTours) {
		//Demande le prochain coup au joueur et retourne les coordonnee de la cellule.
		int numJoueur=numTours%tabPlayer.size();
		System.err.println("Au tour de J"+(numJoueur+1)+".");
		int[] coordonee=getMoveFromPlayer();
		setOwner(coordonee, tabPlayer.get(numJoueur));
		return coordonee;
	}

	private boolean isOver(int[] coordonee,int numTours){
		//Retourne un booléen valant True lorsque 3 pions sont alignés ou que le plateau est rempli.
		int numJoueur=numTours%tabPlayer.size();
		if ((numTours+1)==size*size) {
			System.out.println("Match nul! Merci d'avoir jouer.");
			return true;
		}
		else if(winDiago(tabPlayer.get(numJoueur))){
			System.out.println("Felicitation J"+(numJoueur+1)+" à gagné!!!");
			return true;
		}
		else if(winLigneColone(coordonee,tabPlayer.get(numJoueur))){
			System.out.println("Felicitation J"+(numJoueur+1)+" à gagné!!!");
			return true;
		}
		else{
			return false;
		}
	}

	private boolean winLigneColone(int[] coordoneeMouvJoueur, Player joueur){
		//Retourn vrai si le joueur à completer une ligne ou colone
		int countLigne=0;
		int countColone=0;
		for (int i = 0; i < size; i++) {
			countLigne+=(tCells[coordoneeMouvJoueur[0]][i].getRepresentation().equals(joueur.getRepresentation()))? 1 : 0;
			countColone+=(tCells[i][coordoneeMouvJoueur[1]].getRepresentation().equals(joueur.getRepresentation()))? 1 : 0;
		}
		return ((countLigne==size)||countColone==size);
	}

	private boolean winDiago(Player joueur){
		//Retourn true si une diagonale est totalement occupé par le joueur
		int midTCells=size/2;
		int countDiag1=0;
		int countDiag2=0;
		//on compte le nombre de pion du joueur par diagonal (compte la case du milieu en double)
		for (int i = size/2; i >= 0; i--) {
			countDiag1+=(tCells[midTCells-i][midTCells-i].getRepresentation().equals(joueur.getRepresentation()))? 1 : 0;
			countDiag1+=(tCells[midTCells+i][midTCells+i].getRepresentation().equals(joueur.getRepresentation()))? 1 : 0;
			countDiag2+=(tCells[midTCells+i][midTCells-i].getRepresentation().equals(joueur.getRepresentation()))? 1 : 0;
			countDiag2+=(tCells[midTCells-i][midTCells+i].getRepresentation().equals(joueur.getRepresentation()))? 1 : 0;
		}
		return ((countDiag1==size+1)||(countDiag2==size+1));
	}

	void display() {
		//Affiche le plateau de Tic Tac Toe dans la console
		for(int i=0;i<size;i++){
			String ligne="";
			for(int j=0;j<size;j++){
				ligne+=tCells[i][j].getRepresentation();
			}
			System.out.println(lSepa);
			System.out.println(ligne+"|");
		}
		System.out.println(lSepa);
	}

	int[] getMoveFromPlayer(){
       //Demande des coordonnees valide de la case qu'il souhaite capturee
       int[] result=new int[2];
	   do {
			do {
				System.out.println("Saisir la ligne :");
				result[0]=getIntput()-1;
			} while (!(dansPlateau(result[0],size)));
			do {
				System.out.println("Saisir la colone :");
				result[1]=getIntput()-1;
			} while (!dansPlateau(result[1],size));
	   } while (!estLibre(result));
        return result;
    }

	private int getIntput(){
		//Recupere un int de la part de l'utilisateur
		int result;
		Scanner scanner=new Scanner(System.in);
		try {
			result=scanner.nextInt();
		} catch (Exception e) {
			result=-1;
		}
		return result;
	}

    private boolean dansPlateau(int coordonnees, int size){
        //Retourne vrai si les coordonnee sont dans le plateau de taille size
		if(coordonnees>=0 && coordonnees<size){
			return true;
		}
		else{
			System.out.println("Les coordonées ne correspondent pas à une case du plateau.");
        	return false;
		}
    }
    
    private boolean estLibre(int[] coordonnee){
        //Retourn vrai si la cellule au coordonee specifier est libre
        Cell cellLibre= new Cell();
		boolean libre=this.getCell(coordonnee).getRepresentation().equals(cellLibre.getRepresentation());  //compare le contenu de la cellule au coordonee au contenu d'une cellule vierge
		if (!libre) {
			System.out.println("Les coordonées ne correspondent pas à une case  libre.");
		}
        return libre;
    }

	public void setOwner(int[] coordonee,Player j){
		//Modifie la representation de la cellule au coordonee specifier pour y mettre celle du joueur
		tCells[coordonee[0]][coordonee[1]].setRepresentation(j.getRepresentation());
	}

}
