import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TabelaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private Pessoa pessoa;
	private InterfaceDAO interfaceDao;
	private int index;
	private int tamanhoArray;

	private JTextField matriculaField;
	private JTextField nomeField;
	private JTextField emailField;
	private JTextField senhaField;
	private JTextField cargoField;
	private JTextField turmaField;
	private JTextField setorField;

	public TabelaGUI() {
		setTitle("Tabela");
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		interfaceDao = new ImplementadoDAO();
		pessoa = new Pessoa();
		index = 0;
		tamanhoArray = interfaceDao.getTamanhoArray();
		index = tamanhoArray - 1;

		setLayout(new FlowLayout(FlowLayout.LEFT));

		add(new JLabel("Matr�cula:"));
		matriculaField = new JTextField(50);
		add(matriculaField);

		add(new JLabel("Nome:"));
		nomeField = new JTextField(50);
		add(nomeField);

		add(new JLabel("Email:"));
		emailField = new JTextField(50);
		add(emailField);

		add(new JLabel("Senha:"));
		senhaField = new JTextField(50);
		add(senhaField);

		add(new JLabel("Cargo:"));
		cargoField = new JTextField(50);
		add(cargoField);

		add(new JLabel("Turma:"));
		turmaField = new JTextField(50);
		add(turmaField);

		add(new JLabel("Setor:"));
		setorField = new JTextField(50);
		add(setorField);

		JButton prevButton = new JButton("Anterior");
		prevButton.addActionListener(e -> mostrarAnterior());
		add(prevButton);

		JButton nextButton = new JButton("Pr�ximo");
		nextButton.addActionListener(e -> mostrarProximo());
		add(nextButton);

		JButton updateButton = new JButton("Atualizar");
		updateButton.addActionListener(e ->atualizarDado());
		add(updateButton);

		JButton deleteButton = new JButton("Deletar");
		deleteButton.addActionListener(e -> deletarDado());
		add(deleteButton);

		JButton saveButton = new JButton("Salvar");
		saveButton.addActionListener(e -> salvarDados());
		add(saveButton);

		mostrarDado();
	}

	private void mostrarDado() {
		pessoa = interfaceDao.getPessoa(index);
		matriculaField.setText(Integer.toString(pessoa.getMatricula()));
		nomeField.setText(pessoa.getNome());
		emailField.setText(pessoa.getEmail());
		senhaField.setText(pessoa.getSenha());
		cargoField.setText(pessoa.getCargo());
		turmaField.setText(pessoa.getTurma());
		setorField.setText(pessoa.getSetor());
	}

	private void mostrarAnterior() {
		if (index > 0) {
			index--;
		}
		if(index == -1){
			index = tamanhoArray;
		}
		mostrarDado();
	}

	private void mostrarProximo() {
		if (index < tamanhoArray - 1) {
			index++;
		}
		if(index == tamanhoArray){
			index = 0;
		}
		mostrarDado();
	}

	private void atualizarDado() {
		int matricula = Integer.parseInt(matriculaField.getText());
		pessoa.setNome(nomeField.getText());
		pessoa.setEmail(emailField.getText());
		pessoa.setSenha(senhaField.getText());
		pessoa.setCargo(cargoField.getText());
		pessoa.setTurma(turmaField.getText());
		pessoa.setSetor(setorField.getText()); 
		interfaceDao.atualizarPessoa(matricula, pessoa);
	}

	private void deletarDado() {
		int matricula = Integer.parseInt(matriculaField.getText());
		interfaceDao.deletarPessoa(matricula);
		mostrarDado();
	}

	private void salvarDados() {
		interfaceDao.salvarPessoas();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new TabelaGUI().setVisible(true);
		});
	}

}
