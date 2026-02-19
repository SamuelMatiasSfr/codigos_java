import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ConexaoMaisSimples{

	private class Musica{
		private String artista;
		private String codigo;
		private String titulo;

		private Musica(String codigo, String titulo){
			this.artista = "";
			this.codigo = codigo;
			this.titulo = titulo;
		}

		private void setArtista(String artista){
			this.artista = artista;
		}
	}

	private class Faixa{
		private String codigoMusica;
		private String codigoDisco;

		private Faixa(String codigo1, String codigo2){
			this.codigoMusica = codigo1;
			this.codigoDisco = codigo2;
		}
	}

	private class Disco{
		private String codigo;
		private String codigoArtista;

		private Disco(String codigo1, String codigo2){
			this.codigo = codigo1;
			this.codigoArtista = codigo2;
		}
	}

	private class Artista{
		private String codigo;
		private String nome;

		private Artista(String codigo, String nome){
			this.codigo = codigo;
			this.nome = nome;
		}
	}

	public ConexaoMaisSimples() {
		
		// Conexão com o driver JDBC
		String nomeJDBC = "jdbc:mysql://localhost/discdb";
		String configTimeZone = "?useTimezone=true&serverTimezone=UTC";
		String nomeUser = "root";
		String password = "";

		try {
		
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver"); 

			Connection conexao = DriverManager.getConnection(nomeJDBC+configTimeZone, nomeUser, password);

			// Acessa um banco de dados específico.
			Statement declaracaoMusica = conexao.createStatement();
			Statement declaracaoFaixa = conexao.createStatement();
			Statement declaracaoDisco = conexao.createStatement();
			Statement declaracaoArtista = conexao.createStatement();

			declaracaoMusica.executeUpdate("USE " + "discdb");
			declaracaoFaixa.executeUpdate("USE " + "discdb");
			declaracaoDisco.executeUpdate("USE " + "discdb");
			declaracaoArtista.executeUpdate("USE " + "discdb");

			// Faz a consulta SQL
			String pedidoMusica = "Select * From musica;";
			String pedidoFaixa = "Select * From faixa;";
			String pedidoDisco = "Select * From disco;";
			String pedidoArtista = "Select * From artista;";
			
			ResultSet resultadosMusica = declaracaoMusica.executeQuery(pedidoMusica);
			ResultSet resultadosFaixa = declaracaoFaixa.executeQuery(pedidoFaixa);	
			ResultSet resultadosDisco = declaracaoDisco.executeQuery(pedidoDisco);	
			ResultSet resultadosArtista = declaracaoArtista.executeQuery(pedidoArtista);
			
			ArrayList<Musica> musicas = new ArrayList<>();
			ArrayList<Faixa> faixas = new ArrayList<>();
			ArrayList<Disco> discos = new ArrayList<>();
			ArrayList<Artista> artistas = new ArrayList<>();

			while (resultadosMusica.next()) {
				musicas.add(new Musica(resultadosMusica.getString("CodMus"), 
				resultadosMusica.getString("Titulo")));
			}

			while(resultadosFaixa.next()){
				faixas.add(new Faixa(resultadosFaixa.getString("CodMus"), 
				resultadosFaixa.getString("CodDisco")));
			}

			while(resultadosDisco.next()){
				discos.add(new Disco(resultadosDisco.getString("CodDisco"), 
				resultadosDisco.getString("CodArt")));
			}

			while(resultadosArtista.next()){
				artistas.add(new Artista(resultadosArtista.getString("CodArt"), 
				resultadosArtista.getString("Nome")));
			}

			// Mostra os resultados da consulta
			int numeroLinhas = 1;

			for(Musica musica : musicas){
				String codigoDiscoLocal = new String();
				for(Faixa faixa : faixas){
					if(musica.codigo.equals(faixa.codigoMusica)){
						codigoDiscoLocal = faixa.codigoDisco;
					}
				}

				String codigoArtistaLocal = new String();
				for(Disco disco : discos){
					if(disco.codigo.equals(codigoDiscoLocal)){
						codigoArtistaLocal = disco.codigoArtista;
					}
				}

				String nomeArtista = new String();
				for(Artista artista : artistas){
					if(artista.codigo.equals(codigoArtistaLocal)){
						nomeArtista = artista.nome;
					}
				}

				musica.setArtista(nomeArtista);

				System.out.println("REGISTRO: " + numeroLinhas );

				System.out.print("CodMus: ");
				System.out.println(musica.codigo);
				
				System.out.print("Titulo: ");
				System.out.println(musica.titulo);	
				
				System.out.print("Autor: ");
				System.out.println(musica.artista);	
			
				numeroLinhas++;
			}		
			declaracaoMusica.close();
			declaracaoFaixa.close();
			declaracaoDisco.close();
			declaracaoArtista.close();
			conexao.close();

			escreverNoArquivo(musicas);
		} catch (ClassNotFoundException | SQLException  e) {
			e.printStackTrace();
		} finally{}
	}

	public static void escreverNoArquivo(ArrayList<Musica> musicas) {
        try {
            FileOutputStream fileOutArquivo = new FileOutputStream("arquivo.txt");       
            
			int numRegistros = 1;
			for(Musica musica : musicas){ 
				fileOutArquivo.write(("Registro: " + numRegistros + "\n").getBytes());
				fileOutArquivo.write((musica.codigo + "\n").getBytes());
				fileOutArquivo.write((musica.titulo + "\n").getBytes());
				fileOutArquivo.write((musica.artista + "\n").getBytes());
				fileOutArquivo.write(("\n").getBytes());
			}
            fileOutArquivo.flush();
            fileOutArquivo.close();
        } catch (IOException e) {
            System.out.println("Erro ao processar a escrita no arquivo: " + e.getMessage());
        } 
    }

	public static void main(String[] args) {
		new ConexaoMaisSimples();
	}

}
