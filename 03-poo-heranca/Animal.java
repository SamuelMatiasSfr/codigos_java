public class Animal {
    
    protected int idade;
    protected String nome;

    public Animal(){
        nome = "Inominado";
    }

    public void emitirSom(){   
        System.out.println(nome + " diz: zzzzz");
    }
}
