import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class PessoaDaoImplementada implements PessoaDao {

    private ArrayList<Pessoa> pessoas;

    public PessoaDaoImplementada(){
        pessoas = new ArrayList<>();
    }

    public ArrayList<Pessoa> getTodasPessoas(){

		// Conexão com o driver JDBC
        String nomeJDBC = "jdbc:mysql://localhost";
		String configTimeZone = "?useTimezone=true&serverTimezone=UTC";
		String nomeUser = "root";
		String password = "";
        
		try {

            Class.forName("com.mysql.cj.jdbc.Driver"); 

			Connection con = DriverManager.getConnection(nomeJDBC+configTimeZone, nomeUser, password);

			// Acessa um banco de dados específico.
			Statement st = con.createStatement();
			st.executeUpdate("USE " + "bd_pessoal");

			// Faz a consulta SQL
			String pedido = "Select * From pessoal;";

			ResultSet rs = st.executeQuery(pedido);

			// Mostra os resultados da consulta
			while (rs.next()) {
                int matricula = Integer.parseInt(rs.getString("matricula"));
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String cargo = rs.getString("cargo");
                String setor = rs.getString("setor");
                String turma = rs.getString("turma");
                pessoas.add(new Pessoa(matricula, nome, email, senha, cargo, turma, setor));
			}
			st.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {			
			e.printStackTrace();
		} finally {

		}

        return pessoas;
    }
    
}
