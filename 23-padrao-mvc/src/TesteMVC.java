import controller.AlunoController;
import model.Aluno;
import view.JanelaPrincipal;

public class TesteMVC {
	
	public static void main(String[] args) {
		Aluno model = new Aluno();
		JanelaPrincipal view = new JanelaPrincipal();
		AlunoController controller = new AlunoController(model, view);
	}
	
}
