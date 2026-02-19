public class Conta {
    
    protected int id;
    protected String nome;
    protected float saldo;

    public Conta(int id, String nome, float saldo){
        this.id = id;
        this.nome = nome;
        this.saldo = saldo;
    }

    public float verificaSaldo(){
        return saldo;
    }

    public boolean retira(float valor){
        if(valor <= saldo){
            saldo = saldo - valor;
            return true;
        }else{
            return false;
        }
    }

    public void deposita(float valor){
        saldo = saldo + valor;
    }

    @Override
    public String toString() {
        return id + ", " + nome + ", " + saldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    
}

