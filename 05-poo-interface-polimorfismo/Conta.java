public class Conta {
    protected int numero;
    protected double saldo;
    protected double limite;
    protected String nome;

    public boolean saca(double valor){
        saldo = saldo - valor;
        return true;
    }

    public void deposita(double valor){
        saldo = saldo + valor;
    }

    public void trasnfere(Conta destino, double valor){
        destino.setSaldo(valor);
        saldo = saldo - valor;
    } 

    public void setSaldo(double valor){
        this.saldo = valor;
    }

    public double getSaldo(){
        return this.saldo;
    }
}
