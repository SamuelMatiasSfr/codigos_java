public class Zoologico {
	public static void main(String[] args) {

		Mamifero mama;
		Cachorro cachorro;
		Vaca vaca;
		
		mama = new Mamifero();
		cachorro = new Cachorro("Snoopy");
		vaca = new Vaca();

		System.out.println("Mamifero mama!");
		mama.mamar();
		System.out.println("Cachorro mama!");
		cachorro.mamar();
		System.out.println("Vaca mama!");
		vaca.mamar();

		System.out.println("Mamifero emite som!");
		mama.emitirSom();
		System.out.println("Cachorro emite som!");
		cachorro.emitirSom();
		System.out.println("Vaca emite som!");
		vaca.emitirSom();		
	}
}
