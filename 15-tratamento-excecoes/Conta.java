public class Conta {
    
    private int saldo;

    public Conta(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    public void saca(int valor) throws ContaException {
        if (valor > saldo) {
            throw new ContaException(saldo - valor); // <<< aqui estava errado
        }
        saldo -= valor;
    }

    public int getSaldo() {
        return saldo;
    }

}
