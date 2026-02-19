import java.util.ArrayList;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Principal {

    private ArrayList<Pessoal> pessoas;

    public Principal(){
        pessoas = new ArrayList<Pessoal>();
    }
    
    public ArrayList<Pessoal> buscaDadosBD(){
        try{
            
            String nomeJDBC = "jdbc:mysql://localhost/bd_pessoal";
		    String configTimeZone = "?useTimezone=true&serverTimezone=UTC";
            String nomeUser = "root";
            String password = ""; 

            Class.forName("com.mysql.cj.jdbc.Driver"); 

            Connection conexao = DriverManager.getConnection(nomeJDBC+configTimeZone, nomeUser, password);

            Statement declaracao = conexao.createStatement();

            String pedido = "Select * From pessoal;";

            ResultSet resultados = declaracao.executeQuery(pedido);


            while (resultados.next()) {
				pessoas.add(new Pessoal(resultados.getString("matricula"), 
                resultados.getString("nome"),
                resultados.getString("email"),
                resultados.getString("senha"),
                resultados.getString("cargo"),
                resultados.getString("turma"),
                resultados.getString("setor")));
			}

            declaracao.close();
            conexao.close();

        } catch (ClassNotFoundException | SQLException  e) {
			e.printStackTrace();
		} 
        
        return pessoas;
    }

    public void registrarNoArquivo() {
		try {
            PrintWriter writer = new PrintWriter(new FileWriter("dados.csv"));
            for(int i=0; i<pessoas.size(); i++){
				writer.println(pessoas.get(i));
			}
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao processar a escrita no arquivo: " + e.getMessage());
        } 
    }

    public static void main(String[] args) {
        Principal principal = new Principal();

        ArrayList<Pessoal> pessoas = new ArrayList<>();

        pessoas = principal.buscaDadosBD();

        for(int i=0; i<pessoas.size(); i++){
            System.out.println(pessoas.get(i).toString());
        }

        principal.registrarNoArquivo();
    }

}
