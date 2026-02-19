public class NaoParException extends Exception{
    
    private int valor;

    public NaoParException(int valor){
        this.valor = valor;
    }

    @Override
    public String toString(){
        return "Erro: o valor " + valor + " não é par";
    }

}