import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main{

    InterfaceAlunosDao interfaceAlunosDao;
    ArrayList<Aluno> alunos;
    ArrayList<MediaNotas> medias;
    
    public Main() {
        ImplementadoNotasDao servicoReal = ImplementadoNotasDao.getInstancia(); 
		interfaceAlunosDao = new ProxyForDao(servicoReal);

        alunos = new ArrayList<>();
		alunos = interfaceAlunosDao.getTodosAlunos(30);
        medias = new ArrayList<>();

        calcularMedias();
        mostrarTodosDados();

        String stringId = JOptionPane.showInputDialog(null, "Digite um id: ", "Janela", JOptionPane.QUESTION_MESSAGE); 
        if(stringId != null & !stringId.isEmpty()){
            int id = Integer.parseInt(stringId);
            boolean alunoEncontrado = false;
            for(int i=0; i<alunos.size(); i++){
                if(alunos.get(i).getId() == id){
                    String stringNota1 = JOptionPane.showInputDialog(null, "Atualize a nota 1: ", "Atualização", JOptionPane.QUESTION_MESSAGE);
                    String stringNota2 = JOptionPane.showInputDialog(null, "Atualize a nota 2: ", "Atualização", JOptionPane.QUESTION_MESSAGE);
                    String stringNota3 = JOptionPane.showInputDialog(null, "Atualize a nota 3: ", "Atualização", JOptionPane.QUESTION_MESSAGE);
                    float novaNota1 = Float.parseFloat(stringNota1);
                    float novaNota2 = Float.parseFloat(stringNota2);
                    float novaNota3 = Float.parseFloat(stringNota3);

                    alunos.get(i).setNota1(novaNota1);
                    alunos.get(i).setNota2(novaNota2);
                    alunos.get(i).setNota3(novaNota3);
                    interfaceAlunosDao.atualizarAluno(id, novaNota1, novaNota2, novaNota3);
                    
                    alunoEncontrado = true;
                }
            }
            if(!alunoEncontrado){
                JOptionPane.showMessageDialog(null, "ID inválido.");
                System.exit(0);
            }
        } else{
            JOptionPane.showMessageDialog(null, "Nenhuma ID digitado.");
            System.exit(0);
        }
	}

    private void calcularMedias(){
        for(int i=0; i<alunos.size(); i++){
            float media = (alunos.get(i).getNota1() + alunos.get(i).getNota2() + alunos.get(i).getNota3())/3;
            medias.add(new MediaNotas(alunos.get(i).getId(), media));
        }
    }   

    private void mostrarTodosDados(){
        System.out.println("\nID - email - nota1 - nota2 - nota3 - media");
        for(int i=0; i<alunos.size(); i++){

            float media = 0;
            for(int j=0; j<medias.size(); j++){
                if(medias.get(j).getId() == alunos.get(i).getId()){
                    media = medias.get(j).getMedia();
                } 
            }

            System.out.println(
                alunos.get(i).getId() + " - " +
                alunos.get(i).getEmail() + " - " +
                alunos.get(i).getNota1() + " - " +
                alunos.get(i).getNota2() + " - " +
                alunos.get(i).getNota3() + " - " +
                media
            );
        }
    }

    public static void main(String[] args) {
        new Main();
    }

}