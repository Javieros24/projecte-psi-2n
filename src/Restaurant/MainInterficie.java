package Restaurant;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import Excepcions.DuplicatedNameException;
import Excepcions.NotFoundException;
import InterficieGrafica.*;
import Llistes.LlistaClients;
import Llistes.LlistaProductes;
import Productes.Producte;
import Productes.RestriccionsAlimentaries;

public class MainInterficie {
	
	private static LlistaProductes llistaProductes;
	private static LlistaClients llistaClients;

	public static void main(String[] args) {
		carregarProductes();
		carregarClients();
		carregarComandes();
		new Login("Login", llistaProductes, llistaClients);
	}
	
	//FITXERS
	
	/**
	 * Mètode que carrega els productes (plats i begudes) desde un fitxer 
	 */
	private static void carregarProductes(){
		
		llistaProductes = new LlistaProductes(100);
		try {
			BufferedReader lectura = new BufferedReader(new FileReader("src/Fitxers/Productes.txt"));
			String linia;
			StringTokenizer token;
			String tipus, nom;
			double preu;
			int ref, volum;
			RestriccionsAlimentaries[] r;
			boolean alcohol;

			linia = lectura.readLine();
			while (linia != null) {
				try {
					token = new StringTokenizer(linia, ",");
					tipus = token.nextToken();
					nom = token.nextToken();
					preu = Double.parseDouble(token.nextToken());
					ref = Integer.parseInt(token.nextToken());
					if (tipus.equalsIgnoreCase("PLAT")) {
						r = new RestriccionsAlimentaries[Integer.parseInt(token.nextToken())];
						for (int i = 0; i < r.length; i++) {
							r[i] = RestriccionsAlimentaries.valueOf(token.nextToken());
						}
						llistaProductes.afegirElement(nom, preu, r, ref);
					} else if (tipus.equalsIgnoreCase("BEGUDA")) {
						volum = Integer.parseInt(token.nextToken());
						alcohol = token.nextToken().equalsIgnoreCase("SI");
						llistaProductes.afegirElement(nom, preu, volum, alcohol, ref);
					}
					linia = lectura.readLine();
				} catch (IllegalArgumentException e) {
					/*No mostrem cap missatge per no molestar al usuari i no afegim aquell producte a la llista
					 * la següent vegada que carreguem els productes ja no existirà aquesta línia que causa error.
					 */
					linia=lectura.readLine();		//Llegim la seguent línia
				} catch (DuplicatedNameException e) {
					//En cas d'existir un producte amb aquell nom, no s'afegeix a la llista
					linia = lectura.readLine();//Llegim la seguent línia
				}
			}
			lectura.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("ERROR! La llista de productes és plena.");
		}
	}
	
	/**Carrega a la llista de clients totes les dades del fitxer de text dels clients.**/
	private static void carregarClients(){
		
		llistaClients = new LlistaClients(100);
		try {
			BufferedReader lectura = new BufferedReader(new FileReader("src/Fitxers/Clients.txt"));
			String linia;
			StringTokenizer token;
			String nom, adreca, nomUsuari, contrasenya;
			int codiClient, numTelefon, numRestriccions;
			RestriccionsAlimentaries[] r;
			
			linia = lectura.readLine();
			while(linia != null){
				token = new StringTokenizer(linia, ",");
				nom = token.nextToken();
				codiClient = Integer.parseInt(token.nextToken());
				adreca = token.nextToken();
				nomUsuari = token.nextToken();
				contrasenya = token.nextToken();
				numTelefon = Integer.parseInt(token.nextToken());
				numRestriccions = Integer.parseInt(token.nextToken());
				r = new RestriccionsAlimentaries[numRestriccions];
				for (int i=0; i < r.length; i++){
					r[i] = RestriccionsAlimentaries.valueOf(token.nextToken());
				}
				llistaClients.afegirElement(nom, adreca, nomUsuari, contrasenya, numTelefon, r, codiClient);
				linia = lectura.readLine();
			}
			lectura.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("ERROR! La llista de clients és plena.");
		}
		}                        

	private static void carregarComandes(){
	
	try {
		BufferedReader lectura = new BufferedReader(new FileReader("src/Fitxers/Comandes.txt"));
		String linia, horaComanda;
		StringTokenizer token;
		Client client=null;
		Comanda c;
		Producte p;
		int refClient, refComanda, refProducte,numProd;
		linia = lectura.readLine();
		while(linia != null){
			try {
				token = new StringTokenizer(linia, ",");
				refClient = Integer.parseInt(token.nextToken());
				client=llistaClients.consultarElement(refClient);
				refComanda = Integer.parseInt(token.nextToken());
				horaComanda = token.nextToken();
				numProd = Integer.parseInt(token.nextToken());
				c=new Comanda(numProd,refComanda, horaComanda);
				
				for(int i=0;i<numProd;i++)
				{
					refProducte = Integer.parseInt(token.nextToken());
					p=llistaProductes.consultarElement(refProducte);
					c.afegirProducte(p, 1);
				}
				client.afegirComanda(c);
				
				linia = lectura.readLine();
			} catch (NotFoundException e) {
				linia = lectura.readLine();
				//Si un producte de la llista ha estat eliminat, aquella comanda no s'afegeix
			}
		}
		lectura.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e){
		e.printStackTrace();
	} catch (ArrayIndexOutOfBoundsException e) {
		System.out.println("ERROR! La llista de productes és plena.");
	}
}
}
