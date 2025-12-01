import java.util.ArrayList;

public class ProxyForDao implements InterfaceAlunosDao{
    
    ImplementadoNotasDao servicoReal;

    public ProxyForDao(ImplementadoNotasDao servicoReal) {
        this.servicoReal = servicoReal;
    }

    public ArrayList<Aluno> getTodosAlunos(float notaMinima){
        return servicoReal.getTodosAlunos(notaMinima);
    }

    public void atualizarAluno(int id, float nota1, float nota2, float nota3){
        servicoReal.atualizarAluno(id, nota1, nota2, nota3);
    }

}
