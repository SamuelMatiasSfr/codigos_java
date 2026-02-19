public class Main {
    public static void main(String[] args) {
        GerenciadorContas gerenciadorContas = new GerenciadorContas();
        gerenciadorContas.carregaDados();


        gerenciadorContas.mostraQuantContas();
        System.err.println("\n");
        gerenciadorContas.mostraContasEspeciais();
        System.out.println("\n");
        gerenciadorContas.mostraContasPoupanca();
    }

}
