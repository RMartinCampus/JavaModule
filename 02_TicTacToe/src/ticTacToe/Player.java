package ticTacToe;

import java.util.Scanner;

public class Player {
    //Definie un joueur de TicTacToe


    //Attributs & Accesseurs

    private String[] listeRepresentation={"| X " , "| O "};
    
    public String[] getListeRepresentation() {
        return listeRepresentation;
    }

    private String representation;

    public void setRepresentation() {
        //Permet à l'utilisateur de choisir entre rond et croix
        String entree="";
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                System.out.println("Choisir entre les \"X\" et les \"O\" : ");
                entree=scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (entree.equals("X")==false && entree.equals("O")==false);
        //scanner.close();
        representation= entree.equals("X") ? listeRepresentation[0]: listeRepresentation[1]; 
    }

    public String getRepresentation() {
        return representation;
    }


    //Constructeurs
    Player(){
        this("| X ");
    }

    Player(String rpz){
        representation=rpz;
    }
    
    //Methodes

    


}
