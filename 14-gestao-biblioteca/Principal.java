import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Principal {

    public void registrarNoArquivo(ArrayList<Gravacao> gravacoes) {
		try {
            PrintWriter writer = new PrintWriter(new FileWriter("dados.csv", true));
            for(int i=0; i<gravacoes.size(); i++){
				writer.println(gravacoes.get(i));
			}
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao processar a escrita no arquivo: " + e.getMessage());
        } 
    }

    public static void itemInfo(Biblioteca biblioteca){
        System.out.println(biblioteca.toString());
    }

    public static void main(String[] args) {
        Software software = new Software(0, 0, "Software", "Vídeo", 0, "Mobile");
        Principal.itemInfo(software);
    }
    
}
