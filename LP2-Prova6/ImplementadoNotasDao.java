import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ImplementadoNotasDao implements InterfaceAlunosDao {

    private ArrayList<Aluno> todasNotas;
    private static ImplementadoNotasDao implementadoNotasDao;

    private ImplementadoNotasDao(){
        todasNotas = new ArrayList<>();
    }

    public static ImplementadoNotasDao getInstancia(){
        if(implementadoNotasDao == null){
            implementadoNotasDao = new ImplementadoNotasDao();
        }
        return implementadoNotasDao;
    }

    public ArrayList<Aluno> getTodosAlunos(float notaMinima){
        String nomeJDBC = "jdbc:mysql://localhost";
		String configTimeZone = "?useTimezone=true&serverTimezone=UTC";
		String nomeUser = "root";
		String password = "";
        
		try {

            Class.forName("com.mysql.cj.jdbc.Driver"); 

			Connection con = DriverManager.getConnection(nomeJDBC+configTimeZone, nomeUser, password);

			Statement st = con.createStatement();
			st.executeUpdate("USE " + "escola");

			String pedido = "Select * From notas;";

			ResultSet rs = st.executeQuery(pedido);

			while (rs.next()) {
                float nota1 = Float.parseFloat(rs.getString("nota1"));
                float nota2 = Float.parseFloat(rs.getString("nota2"));
                float nota3 = Float.parseFloat(rs.getString("nota3"));

                if(nota1 >= notaMinima && nota2 >= notaMinima && nota3 >= notaMinima){
                    int id = Integer.parseInt(rs.getString("id"));
                    String email = rs.getString("email");
                    todasNotas.add(new Aluno(id, email, nota1, nota2, nota3));
                }

			}
			st.close();
			con.close();

		} catch (ClassNotFoundException | SQLException e) {			
			e.printStackTrace();
		} finally {

		}

        return todasNotas;
    }

    //A lógica de atualização no banco não foi realizado
    public void atualizarAluno(int id, float nota1, float nota2, float nota3){
        /* 
        String nomeJDBC = "jdbc:mysql://localhost";
		String configTimeZone = "?useTimezone=true&serverTimezone=UTC";
		String nomeUser = "root";
		String password = "";
        
		try {
            
            

		} catch (ClassNotFoundException | SQLException e) {			
			e.printStackTrace();
		} finally {

		}
        */
    }
    
}
