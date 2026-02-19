public class ContaEspecial extends Conta {
    
    private float limite;

    public ContaEspecial(int idNovo, String nomeNovo, float saldoNovo, float limiteNovo){
        super(idNovo, nomeNovo, saldoNovo);
        this.limite = limiteNovo;
    }

    @Override
    public boolean retira(float valor){
        if(valor <= (saldo+limite)){
            saldo = saldo - valor;
            return true;
        }else{
            return false;
        }
    } 

    @Override
    public float verificaSaldo(){
        return saldo+limite;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + limite;
    }

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }  

}
