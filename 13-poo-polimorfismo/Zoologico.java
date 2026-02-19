package br.com.alissonrs.polimorfismo;

import java.util.ArrayList;

public class Zoologico {
	public static void main(String[] args) {
		ArrayList<Mamifero> mamiferos = new ArrayList<Mamifero>();

		mamiferos.add(new Cachorro());
		mamiferos.add(new Vaca());
		mamiferos.add(new Gato());

	
		for(int i=0; i<mamiferos.size(); i++){
			mamiferos.get(i).mamar();
			mamiferos.get(i).emitirSom();
			mamiferos.get(i).correr();
		}

	}

}