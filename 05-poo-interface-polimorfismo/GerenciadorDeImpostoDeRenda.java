public class GerenciadorDeImpostoDeRenda {
    private double total;

    public void adiciona(Tributavel tributo){
        System.out.println("Adicionando tributável: " + tributo);
        this.total = this.total + tributo.calculaTributos();
    }

    public double getTotal(){
        return total;
    }
}