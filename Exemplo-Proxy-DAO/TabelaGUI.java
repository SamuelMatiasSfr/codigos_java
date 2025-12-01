import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TabelaGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private Pessoa pessoa;
    private InterfaceDaoProxy interfaceDaoProxy;
    private int index;
    private int tamanhoArray;
    private String senhaDigitada;

    private JTextField matriculaField;
    private JTextField nomeField;
    private JTextField emailField;
    private JTextField senhaField;
    private JTextField cargoField;
    private JTextField turmaField;
    private JTextField setorField;

    public TabelaGUI(String senha) {
        setTitle("Tabela");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        this.senhaDigitada = senha;

        ImplementadoDaoProxy servicoReal = new ImplementadoDaoProxy();
        interfaceDaoProxy = new Proxy(servicoReal);
        pessoa = new Pessoa();
        index = 0;
        tamanhoArray = interfaceDaoProxy.getTamanhoArray();

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(new JLabel("Matrícula:"));
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

        JButton nextButton = new JButton("Próximo");
        nextButton.addActionListener(e -> mostrarProximo());
        add(nextButton);

        JButton updateButton = new JButton("Atualizar");
        updateButton.addActionListener(e -> atualizarDado());
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
        pessoa = interfaceDaoProxy.getPessoa(senhaDigitada, index);
        if (pessoa != null) {
            matriculaField.setText(Integer.toString(pessoa.getMatricula()));
            nomeField.setText(pessoa.getNome());
            emailField.setText(pessoa.getEmail());
            senhaField.setText(pessoa.getSenha());
            cargoField.setText(pessoa.getCargo());
            turmaField.setText(pessoa.getTurma());
            setorField.setText(pessoa.getSetor());
        }
    }

    private void mostrarAnterior() {
        if (index > 0) {
            index--;
        } else {
            index = tamanhoArray - 1;
        }
        mostrarDado();
    }

    private void mostrarProximo() {
        if (index < tamanhoArray - 1) {
            index++;
        } else {
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
        interfaceDaoProxy.atualizarPessoa(senhaDigitada, matricula, pessoa);
    }

    private void deletarDado() {
        int matricula = Integer.parseInt(matriculaField.getText());
        interfaceDaoProxy.deletarPessoa(senhaDigitada, matricula);
        mostrarDado();
    }

    private void salvarDados() {
        interfaceDaoProxy.salvarPessoas(senhaDigitada);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String senha = JOptionPane.showInputDialog(null, "Digite sua senha de acesso:", "Login", JOptionPane.QUESTION_MESSAGE);

            if (senha != null && !senha.isBlank()) {
                new TabelaGUI(senha).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma senha digitada. Encerrando o programa.");
                System.exit(0);
            }
        });
    }
}
