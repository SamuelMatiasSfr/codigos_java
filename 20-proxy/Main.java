
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Pessoa {

    private int matricula;
    private String nome;
    private String email;
    private String senha;
    private String cargo;
    private String turma;
    private String setor;

    public Pessoa(int matricula, String nome, String email, String senha, String cargo, String turma, String setor) {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
        this.turma = turma;
        this.setor = setor;
    }

    public String getSenha() {
        return senha;
    }

    public int getMatricula() {
        return matricula;
    }

    @Override
    public String toString() {
        return "Pessoa [matricula=" + matricula + ", nome=" + nome + ", email=" + email + ", senha=" + senha
                + ", cargo=" + cargo + ", turma=" + turma + ", setor=" + setor + "]";
    }

}

class GerenciadorPessoas {

    ArrayList<Pessoa> pessoas = new ArrayList<>();

    public GerenciadorPessoas() {

        try (BufferedReader br = new BufferedReader(new FileReader("pessoal.csv"))) {
            String line;
            br.readLine(); // pula cabeçalho
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                int matricula = Integer.parseInt(data[0]);
                String nome = data[1];
                String email = data[2];
                String senha = data[3];
                String cargo = data[4];
                String setor = "";
                String turma = "";
                switch (cargo) {
                    case "Geral":
                        setor = data[6];
                        break;
                    case "Discente":
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

    }

    public void imprimePessoas() {
        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa);
        }
    }

}

interface InterfaceControle{
    public void imprimePessoas(String senha);
}

class InterfaceControleImplementada implements InterfaceControle{

    GerenciadorPessoas gerenciadorPessoas;

    public InterfaceControleImplementada(){
        gerenciadorPessoas = new GerenciadorPessoas();
    }

    public void imprimePessoas(String senha){
        gerenciadorPessoas.imprimePessoas();
    }

}

class ProxyGerenciador implements InterfaceControle{

    InterfaceControleImplementada servico;

    public ProxyGerenciador(InterfaceControleImplementada servico){
        this.servico = servico;
    }

    public boolean checkAccess(String senha){
        boolean acessoLiberado = false;
        for(int i=0; i<servico.gerenciadorPessoas.pessoas.size(); i++){
            if (senha.equals(servico.gerenciadorPessoas.pessoas.get(i).getSenha())) {
                int inicio = servico.gerenciadorPessoas.pessoas.get(i).toString().indexOf("setor=") + 6;
                int fim = servico.gerenciadorPessoas.pessoas.get(i).toString().indexOf("]", inicio);
                String setorExtraido = servico.gerenciadorPessoas.pessoas.get(i).toString().substring(inicio, fim).trim();

                if(setorExtraido.equals("Secretaria") || setorExtraido.equals("Biblioteca")){
                    acessoLiberado = true;
                }
            }
        }
        return acessoLiberado;
    }

    public void imprimePessoas(String senha){
        if(checkAccess(senha)){
            System.out.print("\nAcesso liberado\n");
            servico.imprimePessoas(senha);
        }else{
            System.out.print("\nAcesso não liberado\n");
        }
    }

}

public class Main {
    public static void main(String[] args) {
        InterfaceControleImplementada servicoReal = new InterfaceControleImplementada();
        ProxyGerenciador proxy = new ProxyGerenciador(servicoReal);
        proxy.imprimePessoas("5ySSLEMF");
    }

}