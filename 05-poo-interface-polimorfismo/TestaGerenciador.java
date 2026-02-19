public class TestaGerenciador{
    public static void main(String[] args) {
        GerenciadorDeImpostoDeRenda gerenciador = new GerenciadorDeImpostoDeRenda();
        SeguroDeVida seguro = new SeguroDeVida();
        gerenciador.adiciona(seguro);
        ContaCorrente conta = new ContaCorrente();
        conta.deposita(1000);
        gerenciador.adiciona(conta);

        System.out.println(gerenciador.getTotal());
        System.out.printf("O saldo é: %.2f", conta.getSaldo());
    }
}

