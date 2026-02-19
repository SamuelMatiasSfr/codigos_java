package br.com.alissonrs.polimorfismo;

public class Gato extends Mamifero {
    
    @Override
    public void mamar(){
        System.out.println("Chuc");
    }

    @Override
    public void correr(){
        System.out.println("tum-tum");
    }

    @Override
	public void emitirSom() {		
		System.out.println("Miau, miau!");
	}
}
