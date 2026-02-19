import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MyJFrame extends JFrame {
    
    private JLabel jlabelMatricula;
    private JLabel jlabelNome;
    private JLabel jlabelEmail;
    private JLabel jlabelSenha;
    private JLabel jlabelCargo;
    private JLabel jlabelTurma;
    private JLabel jlabelSetor;
    private JTextField jtextMatricula;
    private JTextField jtextNome;
    private JTextField jtextEmail;
    private JTextField jtextSenha;
    private JTextField jtextCargo;
    private JTextField jtextTurma;
    private JTextField jtextSetor;

	private JButton jbuttonAnterior;
	private JButton jbuttonProximo;

    private Principal principal;
    private ArrayList<Pessoal> pessoas;
    private int num;

    
    public MyJFrame(String titulo) {
		super(titulo);
		setLayout(new FlowLayout());

        principal = new Principal();
        pessoas = new ArrayList<Pessoal>();
        pessoas = principal.buscaDadosBD();
        num = 0;

		jlabelMatricula = new JLabel("Matrícula:");
		jlabelNome = new JLabel("Nome:");
        jlabelEmail = new JLabel("Email:");
		jlabelSenha = new JLabel("Senha:");
        jlabelCargo = new JLabel("Cargo:");
		jlabelTurma = new JLabel("Turma:");
        jlabelSetor = new JLabel("Setor:");

        jtextMatricula = new JTextField(pessoas.get(num).getMatricula(), 15);
		jtextNome = new JTextField(pessoas.get(num).getNome(), 15);
        jtextEmail = new JTextField(pessoas.get(num).getEmail(), 15);
		jtextSenha = new JTextField(pessoas.get(num).getSenha(), 15);
        jtextCargo = new JTextField(pessoas.get(num).getCargo(), 15);
		jtextTurma = new JTextField(pessoas.get(num).getTurma(), 15);
        jtextSetor = new JTextField(pessoas.get(num).getSetor(), 15);

        jbuttonAnterior = new JButton("Anterior");
        jbuttonProximo = new JButton("Próximo");

		add(jlabelMatricula);
		add(jtextMatricula);
        add(jlabelNome);
		add(jtextNome);
        add(jlabelEmail);
		add(jtextEmail);
        add(jlabelSenha);
		add(jtextSenha);
        add(jlabelCargo);
		add(jtextCargo);
        add(jlabelTurma);
		add(jtextTurma);
        add(jlabelSetor);
		add(jtextSetor);
		add(jbuttonAnterior);
		add(jbuttonProximo);

		TextFieldHandler handler = new TextFieldHandler();

		jbuttonAnterior.addActionListener(handler);
		jbuttonProximo.addActionListener(handler);
	}

    private class TextFieldHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {			
			
			if(event.getSource()==jbuttonAnterior) {
                num--;
                if(num == -1){
                    num = pessoas.size()-1;
                }
                jtextMatricula.setText(pessoas.get(num).getMatricula());
                jtextNome.setText(pessoas.get(num).getNome());
                jtextEmail.setText(pessoas.get(num).getEmail());
                jtextSenha.setText(pessoas.get(num).getSenha());
                jtextCargo.setText(pessoas.get(num).getCargo());
                jtextTurma.setText(pessoas.get(num).getTurma());
                jtextSetor.setText(pessoas.get(num).getSetor());
			} 
			
			else if(event.getSource()==jbuttonProximo){
                num++;
                if(num == pessoas.size()){
                    num = 0;
                }
                jtextMatricula.setText(pessoas.get(num).getMatricula());
                jtextNome.setText(pessoas.get(num).getNome());
                jtextEmail.setText(pessoas.get(num).getEmail());
                jtextSenha.setText(pessoas.get(num).getSenha());
                jtextCargo.setText(pessoas.get(num).getCargo());
                jtextTurma.setText(pessoas.get(num).getTurma());
                jtextSetor.setText(pessoas.get(num).getSetor());
			} 

	    }
    }
    public static void main(String[] args) {
        MyJFrame myJFrame = new MyJFrame("Cadastro de Pessoal");		
        myJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myJFrame.setSize(400, 400);
        myJFrame.setVisible(true);
    }

}
