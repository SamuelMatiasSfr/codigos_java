import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

public class Servidor extends UnicastRemoteObject 
implements InterfaceRetorno {
    
    private ArrayList<String> palavras;
    private String palavraSecreta;
    private ArrayList<Character> letrasCorretas;
    private int tentativasRestantes;
    private boolean completouPalavra;

    Servidor() throws RemoteException {
        super();
        palavras = new ArrayList<>();
        palavraSecreta = new String();
        letrasCorretas = new ArrayList<>();

        try{
            carregarArray();
        }catch(Exception e){
            e.printStackTrace();
        }

        palavraSecreta = sortearPalavra();
        tentativasRestantes = 6;
        completouPalavra = false;
    }

    private void carregarArray() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("palavras.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                palavras.add(linha.trim().toLowerCase());
            }
        }
    }

    private String sortearPalavra() {
        Random rand = new Random();
        return palavras.get(rand.nextInt(palavras.size()));
    }

    // INÍCIO FUNÇÕES INTERFACE
    public String retornarPalavra(char letra) throws RemoteException {
        if (palavraSecreta.indexOf(letra) >= 0) {
            letrasCorretas.add(letra);
        } else {
            tentativasRestantes--;
        }

        String palavraIncompleta = new String();

        for (char c : palavraSecreta.toCharArray()) {
            if (letrasCorretas.contains(c)) {
                palavraIncompleta = palavraIncompleta + c + " ";
            } else {
                palavraIncompleta = palavraIncompleta + "_";
            }
        }
        return palavraIncompleta;
    }

    public int retornarTentativas() throws RemoteException {
        return tentativasRestantes;
    }

    public String retornarResultadoFinal() throws RemoteException {
        String resultadoFinal = new String();
        if (palavraCompleta()) {
            completouPalavra = true;
            resultadoFinal = "\nParabéns! Você acertou a palavra: " + palavraSecreta;
        } else {
            completouPalavra = false;
            resultadoFinal = "\nFim de jogo! A palavra era: " + palavraSecreta;
        }
        //reiniciarJogo();
        return resultadoFinal;
    }

    public boolean retornarCompletouPalavra() throws RemoteException {
        return completouPalavra;
    }
    // FIM FUNÇÕES INTERFACE

    public void reiniciarJogo(){
        if(completouPalavra || tentativasRestantes == -1){
            sortearPalavra();
            tentativasRestantes = 6;
            letrasCorretas.clear();
            completouPalavra = false;
        }
    }

    private boolean palavraCompleta() {
        for (char c : palavraSecreta.toCharArray()) {
            if (!letrasCorretas.contains(c)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        InterfaceRetorno objeto;
         try{
            objeto = new Servidor();
            LocateRegistry.createRegistry(1099);
            Naming.bind("rmi:///Servidor", objeto);            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
