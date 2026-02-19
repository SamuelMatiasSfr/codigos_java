import java.util.ArrayList;
import java.util.Scanner;

class Aluno{
    String matricula;
    String nome;
    String senha;
    int grupo;
    int subTurma;

    public Aluno(String matricula, String nome, String senha, int grupo, int subTurma){
        this.matricula = matricula;
        this.nome = nome;
        this.grupo = grupo;
        this.subTurma = subTurma;
        this.senha = senha;
        testaSenha();    
    }

    public void testaSenha(){
        int tam=0;

        for(int i=0; i<senha.length(); i++){
            tam++;
        }

        if(tam < 3 || tam > 6){
            System.out.println("\nSenha Inválida.\n"); 
            Scanner input = new Scanner(System.in);
            System.out.println("Digite uma senha entre 3 e 6 caracteres: ");
            senha = input.next();
            input.close();
            testaSenha();
        }
    }

    public Aluno(){
        this.matricula = "";
        this.nome = "";
        this.senha = "000";
        this.grupo = 0;
        this.subTurma = 0;
    }

    public void setMatricula(String matricula){
        this.matricula = matricula;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setSenha(String senha){
        this.senha = senha;
        testaSenha();
    }

    public void setGrupo(int grupo){
        this.grupo = grupo;
    }

    public void setSubTurma(int subTurma){
        this.subTurma = subTurma;
    }

    public String getMatricula(){
        return matricula;
    }

    public String getNome(){
        return nome;
    }

    public String getSenha(){
        return senha;
    }

    public int getGrupo(){
        return grupo;
    }

    public int getSubTurma(){
        return subTurma;
    }

    public String imprimeAsteriscos(){
        char[] asteriscos = new char[6];
        for(int i=0; i<senha.length(); i++){
            asteriscos[i] = '*';
        }
        String resultado = new String(asteriscos);
        return resultado;
    }

}

public class ClassesObjetos{
    public static void main(String[] args) {
        
        ArrayList<Aluno> alunos = new ArrayList<>(); 

        Scanner input = new Scanner(System.in);
        for(int i=0; i<3; i++){
            Aluno aluno = new Aluno();

            System.out.println("Digite a matricula: ");
            aluno.setMatricula(input.next());
            input.nextLine();

            System.out.println("Digite o nome: ");
            aluno.setNome(input.next());
            input.nextLine();

            System.out.println("Digite o grupo: ");
            aluno.setGrupo(input.nextInt());
            input.nextLine();

            System.out.println("Digite a subturma: ");
            aluno.setSubTurma(input.nextInt());
            input.nextLine();

            System.out.println("Digite a senha: ");
            aluno.setSenha(input.next());
            input.nextLine();

            System.out.println("\n");

            alunos.add(aluno);
        }
        input.close();

        

        for(Aluno aluno : alunos){
            System.out.println("\nMatricula: " + aluno.getMatricula());
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("Grupo: " + aluno.getGrupo());
            System.out.println("Subturma: " + aluno.getSubTurma());
            System.out.println("Senha: " + aluno.imprimeAsteriscos());
        }   
    }
}