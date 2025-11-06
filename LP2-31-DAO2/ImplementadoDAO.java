import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class ImplementadoDAO implements InterfaceDAO{

    String caminho = "tabela.csv";
    private ArrayList<Pessoa> pessoas;

    public ImplementadoDAO(){
        pessoas = new ArrayList<>();
        pessoas = carregarDados(caminho);
    }

    private ArrayList<Pessoa> carregarDados(String caminho) {
		ArrayList<Pessoa> pessoas = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String line;
            br.readLine(); // pula cabeçalho
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int matricula = Integer.parseInt(data[0]);
                String nome = data[1];
                String email = data[2];
                String senha = data[3];
                String cargo = data[4];
                String setor = "";
                String turma = "";
                switch (cargo) {
                    case "Administrativo":
                        setor = data[6];
                        break;
                    case "Aluno":
                        turma = data[5];
                        break;
                    default:
                        break;
                }
                pessoas.add(new Pessoa(matricula, nome, email, senha, cargo, turma, setor));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return pessoas;
	}

	public int getTamanhoArray(){
		return pessoas.size();
	}

    public Pessoa getPessoa(int index){
        return pessoas.get(index);
    }

    public void atualizarPessoa(int matricula, Pessoa pessoa){
		for(int i=0; i<pessoas.size(); i++){
			if(matricula == pessoas.get(i).getMatricula()){
				pessoas.get(i).setNome(pessoa.getNome());
				pessoas.get(i).setEmail(pessoa.getEmail());
				pessoas.get(i).setSenha(pessoa.getSenha());
				pessoas.get(i).setCargo(pessoa.getCargo());
				pessoas.get(i).setTurma(pessoa.getTurma());
				pessoas.get(i).setSetor(pessoa.getSetor());
			}
		}
    }

    public void deletarPessoa(int matricula){
		for(int i=0; i<pessoas.size(); i++){
			if(matricula == pessoas.get(i).getMatricula()){
				pessoas.remove(i);
			}
		}
    }

	public void salvarPessoas() {
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(caminho)))) {
			bw.write("matr�cula;nome;email;senha;cargo;turma;setor\n");
			for(int i=0; i<pessoas.size(); i++){
				String[] dado = pessoas.get(i).toString().split(";");
				bw.write(String.join(";", dado));
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}