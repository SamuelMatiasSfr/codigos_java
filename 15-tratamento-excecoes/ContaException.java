public class ContaException extends Exception {
   
    private int deficit;

    public ContaException(int deficit) {
        this.deficit = deficit;
    }

    @Override
    public String toString() {
        return "Erro: saldo insuficiente. Déficit de " + deficit;
    }
    
}
