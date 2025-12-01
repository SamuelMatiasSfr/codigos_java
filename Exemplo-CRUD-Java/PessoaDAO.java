import java.sql.*;
import java.util.ArrayList;

public class PessoaDAO {

    // CREATE
    public void inserir(Pessoa p){
        String sql = "INSERT INTO pessoa (nome, email) VALUES (?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNome());
            ps.setString(2, p.getEmail());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ (um registro)
    public Pessoa buscarPorId(int id){
        String sql = "SELECT * FROM pessoa WHERE id = ?";
        Pessoa pessoa = null;

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pessoa = new Pessoa(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoa;
    }

    // READ ALL
    public ArrayList<Pessoa> listarTodos(){
        ArrayList<Pessoa> lista = new ArrayList<>();
        String sql = "SELECT * FROM pessoa";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Pessoa(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // UPDATE
    public void atualizar(Pessoa p){
        String sql = "UPDATE pessoa SET nome = ?, email = ? WHERE id = ?";

        try (Connection con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNome());
            ps.setString(2, p.getEmail());
            ps.setInt(3, p.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deletar(int id){
        String sql = "DELETE FROM pessoa WHERE id = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}

