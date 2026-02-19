import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Scanner;

public class ClienteForca {

    private String palavraIncompleta;
    private ArrayList<Character> letrasTentadas;
    private int tentativasRestantes;
    private boolean completouPalavra;
    private String resultadoFinal;

    public ClienteForca() {
        palavraIncompleta = new String();
        letrasTentadas = new ArrayList<>();
        tentativasRestantes = 6;
        completouPalavra = false;
        resultadoFinal = new String();
    }

    private char pegarLetraDoJogador(Scanner scanner) {
        System.out.print("Digite uma letra: ");
        String entrada = scanner.nextLine().toLowerCase();
        return entrada.charAt(0);
    }

    public void jogar(){
        InterfaceRetorno objeto;
        char letra;
        try{
            Scanner scanner = new Scanner(System.in);
            objeto = (InterfaceRetorno) Naming.lookup("rmi:///Servidor");
            while(tentativasRestantes > 0 && !completouPalavra){
                letra = pegarLetraDoJogador(scanner);

                if (letrasTentadas.contains(letra)) {
                    System.out.println("\nVocê já tentou essa letra.");
                    continue;
                }
                letrasTentadas.add(letra);

                palavraIncompleta = objeto.retornarPalavra(letra);
                System.out.print("\nPalavra: " + palavraIncompleta);

                tentativasRestantes = objeto.retornarTentativas();
                System.out.println("\nTentativas restantes: " + tentativasRestantes);
                System.out.println("Letras tentadas: " + letrasTentadas);

                resultadoFinal = objeto.retornarResultadoFinal();
                completouPalavra = objeto.retornarCompletouPalavra();
            }
            System.out.println(resultadoFinal);
        }catch (Exception e) {
            e.printStackTrace();
        }     
    }

    public static void main(String[] args) {
        ClienteForca cliente = new ClienteForca();
        cliente.jogar();
    }
    
}
