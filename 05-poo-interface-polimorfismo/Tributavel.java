interface Tributavel {
    double calculaTributos();
}

class ContaCorrente extends Conta implements Tributavel{
    public double calculaTributos(){
        return this.saldo * 0.01;
    }
}

class SeguroDeVida implements Tributavel{
    public double calculaTributos(){
        return 42;
    }
}

class TestaTributavel{
    public static void main(String[] args) {
        ContaCorrente conta = new ContaCorrente();
        conta.deposita(1000);
        Tributavel tributo =  conta;
        System.out.println(tributo.calculaTributos());
    }
}