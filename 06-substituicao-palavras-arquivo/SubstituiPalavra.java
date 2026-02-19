import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SubstituiPalavra {

    public static FileOutputStream abrirArquivoEscrita(String nome) throws IOException {
        return new FileOutputStream(nome);
    }

    public static BufferedReader abrirArquivoLeitura(String nome) throws IOException {
        return new BufferedReader(new FileReader(nome));        
    }

    public static void lerArquivo(String nomeArquivoOrigem, String nomeArquivoDestino, String palavra, String novaPalavra)  {
        try {
            BufferedReader bufferInArquivo = abrirArquivoLeitura(nomeArquivoOrigem);
            FileOutputStream fileOutAuxiliar = abrirArquivoEscrita(nomeArquivoDestino);        
            String linha;
            while((linha = bufferInArquivo.readLine()) != null) {
                String[] result = linha.split("\\s+");
                for (int i = 0; i < result.length; i++) {
                    if (palavra.equals(result[i])) {
                        result[i] = novaPalavra;
                    }
                }
                linha = String.join(" ", result);
                fileOutAuxiliar.write((linha + "\n").getBytes());
            }
            fileOutAuxiliar.flush();
            fileOutAuxiliar.close();
            bufferInArquivo.close();
        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo de leitura e escrita: " + e.getMessage());
        }    
    }

    public static void escreverNoArquivo(String nomeArquivoOrigem, String nomeArquivoDestino) {
        try {
            BufferedReader bufferInAuxiliar = abrirArquivoLeitura(nomeArquivoOrigem); 
            FileOutputStream fileOutArquivo = abrirArquivoEscrita(nomeArquivoDestino);       
            String linha;
            while((linha = bufferInAuxiliar.readLine()) != null) {
                fileOutArquivo.write((linha + "\n").getBytes());
            }
            fileOutArquivo.flush();
            fileOutArquivo.close();
            bufferInAuxiliar.close();
        } catch (IOException e) {
            System.out.println("Erro ao processar a escrita no arquivo: " + e.getMessage());
        } 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite uma palavra a ser pesquisada: ");
        String palavraPesquisada = scanner.nextLine();

        System.out.println("Digite uma palavra nova: ");
        String palavraNova = scanner.nextLine();
        scanner.close();
    
        lerArquivo(args[0], "auxiliar.txt", palavraPesquisada, palavraNova);
        escreverNoArquivo("auxiliar.txt", args[0]);

    }
}
