import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PesquisaArquivo {
    public static void main(String[] args){
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite uma palavra: ");
        String palavra = scanner.nextLine();
        scanner.close();

        ArrayList<Integer> linhas = new ArrayList<>();
        String nomeFile = args[0];

        try(BufferedReader in = new BufferedReader(new FileReader(nomeFile))){
            String linha;
            int cont = 1;
            while((linha = in.readLine()) != null){
                String result[] = linha.split("[ ,]");
                for(int i=0; i<result.length; i++){
                    if(palavra.equals(result[i])){
                        linhas.add(cont);
                    }
                }
                cont++;
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não existe.");
        } catch (IOException e) {
            System.out.println("Erro na leitura do arquivo");
        }

        System.out.println("A palavra " + palavra + " aparece nas linhas: ");
        for (int i = 0; i < linhas.size(); i++) {
            if (i > 0) System.out.print(", ");
            System.out.print(linhas.get(i));
        }
    }
}
