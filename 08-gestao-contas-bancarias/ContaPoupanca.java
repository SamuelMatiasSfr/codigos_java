public class ContaPoupanca extends Conta {
    
    private float rendimento;

    public ContaPoupanca(int idNovo, String nomeNovo, float saldoNovo, float rendimentoNovo){
        super(idNovo, nomeNovo, saldoNovo);
        this.rendimento = rendimentoNovo;
    }

    public float calculaRendimento(){
        return (saldo*rendimento)/100;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + rendimento;
    }

    public String mostraDadosMaisCalculo(){
        return super.toString() + ", " + calculaRendimento();
    }

    public float getRendimento() {
        return rendimento;
    }

    public void setRendimento(float rendimento) {
        this.rendimento = rendimento;
    }

}
