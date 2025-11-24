import java.util.ArrayList;

public interface InterfaceAlunosDao {

    public ArrayList<Aluno> getTodosAlunos(float notaMinima);
    public void atualizarAluno(int id, float nota1, float nota2, float nota3);

}