package Kit01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		//Mise en commentaire des premiers exercices pour ne pas "flood" la console.
		/*
		//2a1.1
		int i=1;
		while(i<101) {
			System.out.println(i);
			i++;
		}
		//2a1.2
		for(int j=1;j<101;j++){
			System.out.println(j);
		}

		//2a1.3
		for(int k=0;k<101;k+=2){
			System.out.println(k);
		}
		*/
		///			Test des differentes fonctions			///
		/*
		while100();											//test de while100 (2a1.4)
		for100();											//test de for100 (2a1.4)
		paire100();											//test de paire100 (2a1.4)
		petitCarreSup(0);									//test de petitCarreSup (2a2.1)
		petitCarreInf(0);									//test de petitCarreInf (2a2.2)
		System.out.println(Arrays.toString(fibo(10));		//test de fibo (2a3.1)
		System.out.println(Arrays.toString(approximation_fibo(0.001)));	//test de approximation_fibo (2a3.2)
		ArrayList<String> nom=new ArrayList<String>();		//Création d'une arrayList pour le test de describe, describe4 et to_upper_case
		nom.add("Baptiste");
		nom.add("Remy");
		nom.add("Lea");
		nom.add("George");
		describe(nom);										//test de describe (2a4.2)					
		describe4(nom);										//test de describe4 (2a4.3)
		to_upper_case(nom);									//test de to_upper_case (2a4.5)
		System.out.println(decrypt(msgCode));				//test de decrypt (2a5)
		*/
	}
	//2a1.4
	static void while100(){
		//Affiche dans la console les nombres de 1 à 100
		int i=1;
		while(i<101) {
			System.out.println(i);
			i++;
		}
	}

	static void for100(){
		//Affiche dans la console les nombres de 1 à 100
		for(int i=1;i<101;i++){
			System.out.println(i);
		}
	}

	static void paire100(){
		//Affiche dans la console les nombres paires de 1 à 100
		for( int i=0;i<101;i+=2){
			System.out.println(i);
		}
	}
	
	//2a2.1
	static void petitCarreSup(int n){
		//Affiche dans la console le plus petit carre superieur à n
		System.out.println((int) Math.pow(Math.ceil(Math.sqrt(n)), 2));
	}

	//2a2.2
	static void petitCarreInf(int n){
		//Affiche dans la console le plus petit carre inferieur à n
		System.out.println((int) Math.pow(Math.floor(Math.sqrt(n)), 2));
	}
	
	//2a3.1
	static Integer[] fibo(int n){
		//Retourne la liste des nième premier terme de la suite de fibonacci
		Integer[] res =new Integer[n];
		ArrayList<Integer> resArrayList=new ArrayList<Integer>();
		resArrayList.add(0);
		resArrayList.add(1);
		if (n>2){
			resArrayList=fiboRecur(n,resArrayList);
		}
		else{
			System.out.println("merci de choisir un nombre plus grand.");
		}
		res=resArrayList.toArray(res);
		return res;
	}
	static ArrayList<Integer> fiboRecur(int n,ArrayList<Integer> res){
		//Prend en parametre une liste contenant les 2 termes precedent et calcule les n termes suivant de la suite de fibonacci.
		Integer nieme;
		if(n>2){
			res=fiboRecur(n-1, res);
			nieme=res.get(res.size()-1)+res.get(res.size()-2);	//calcule du nieme terme (n-1eme)+(n-2eme)
			res.add(nieme);
			return res;
		}
		else{
			return res;
		}
	}

	//2a3.2
	static int[] approximation_fibo(double epsilon){
		//Cette fonction prend en paramètre d’entrée un nombre flottant epsilon qui correspond à l’erreur d’approximation maximale autorisée et 
		//retourne les deux derniers entiers de la suite de Fibonacci (a et b) qui respecte  (a/b)<=epsilon
		int[] u={2,1};
		double phi=(1+Math.sqrt(5))/2;
		while (Math.abs((double)u[0]/u[1]-phi)>epsilon) {
			u[0]+=u[1];
			u[1]=u[0]-u[1];
		}
		return u;
	}

	//2a4.1
	static void describe(ArrayList<String>lNom){
		//Affiche les nom de lNom et leur nombre de lettres
		for (String nom : lNom) {
			System.out.println('"'+nom+" : "+nom.length());
		}
	}

	//2a4.2
	static void describe4(ArrayList<String>lNom){
		//Affiche les nom de lNom et leur nombre de lettres si ils ont 4 lettres ou plus
		for (String nom : lNom) {
			if(nom.length()>=4){
				System.out.println('"'+nom+" : "+nom.length());
			}
		}
	}

	static void to_upper_case(ArrayList<String>lNom){
		//Affiche les nom de lNom en majuscules
		for (String nom : lNom) {
			System.out.println(nom.toUpperCase());
		}
	}

	//2a5
	static String decrypt(String msg){
		//Affiche le msg en supprimant les 0
		String res="";
		for (int i=0;i<msg.length();i++) {
			res+= (msg.charAt(i)=='0')? "" : msg.charAt(i);
		}
		return res;
	}
	static String msgCode="000T000000000000000000000000000000o0000u00000000000j0o0000000u0000000000r00s0000c00o0000d00000e000000000000r00000000000000000000000c0000000000o000m00000000000000m0e000 000000000s00i0000000000 0l000e00000000g000000000000a00000000000000000r0s0000000000000000000000000000000q00000u0000000000000000000000000i00000000000f000i0ni00000r0000000a 000p00000a00000r0000000000000000000000000000000m00000000000000a000in0t000000e00n000000000i00r000000000000000000000000000v00000000000o0000000t00r0000000e0000000000c000o00d000000000e00000000000e0000000000000000000000000000000000s0t0000000000000000000 0000000000000u0n000000000000000000000000000000000000ps00000000y000000000000000000c0h0000000op00000a0t0000h0e0000000000000000v00000000000i00000000000000000000000000000000000000o0000l0000en00t0000000q0u000000000i 00000000sa00000it00000 000où0000000000000000v0000000o0000000000u000000000000000s00000000000000000000000v00000000000000i00000000v0000000000000000000000000000e0000000000z000000000(000000c0i0000000000000000000ta0ti0o00000000n 000000d000000000000000000e0000J000o00000000000000h0n00 0W00o000000000000o00000000000d0000000s0000000000)0.";
}