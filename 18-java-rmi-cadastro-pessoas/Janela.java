import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Janela extends JFrame {

    JButton botaoProximo, botaoVoltar;
    JTextField matriculaPessoa, nomePessoa, emailPessoa, senhaPessoa, cargoPessoa, turmaPessoa, setorPessoa;

    public Janela() {
        super("Pessoal cadastrado");
        setLayout(new BorderLayout());
        
        matriculaPessoa = new JTextField(25);
        nomePessoa = new JTextField(25);
        emailPessoa = new JTextField(25);
        senhaPessoa = new JTextField(25); 
        cargoPessoa  = new JTextField(25);
        turmaPessoa = new JTextField(25); 
        setorPessoa = new JTextField(25);
        
        botaoProximo = new JButton("Proximo");
        botaoVoltar = new JButton("Voltar");

        matriculaPessoa.setEditable(false);
        nomePessoa.setEditable(false);
        emailPessoa.setEditable(false);
        senhaPessoa.setEditable(false);
        cargoPessoa.setEditable(false);
        turmaPessoa.setEditable(false);
        setorPessoa.setEditable(false);

        adicionaItens();
        //configuraListeners();
        setSize(300, 460);
    }

    private void adicionaItens() {

        JPanel painelCampos = new JPanel(new FlowLayout());
        painelCampos.add(new JLabel("Matricula"));
        
        painelCampos.add(matriculaPessoa);
        painelCampos.add(new JLabel("Nome"));
        painelCampos.add(nomePessoa);
        painelCampos.add(new JLabel("Email:"));
        painelCampos.add(emailPessoa);
        painelCampos.add(new JLabel("Senha:"));
        painelCampos.add(senhaPessoa);
        painelCampos.add(new JLabel("Cargo:"));
        painelCampos.add(cargoPessoa);
        painelCampos.add(new JLabel("Turma:"));
        painelCampos.add(turmaPessoa);
        painelCampos.add(new JLabel("Setor:"));
        painelCampos.add(setorPessoa);

        JPanel painelBotoes = new JPanel(new GridLayout(3, 2, 5, 5));
        painelBotoes.add(botaoProximo);
        painelBotoes.add(botaoVoltar);
        

        add(painelCampos, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    /* 
    private void configuraListeners() {
        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(evt.getSource()==botaoVoltar) {
                    clicouVoltar = true;
                } 
            }
        });

        botaoProximo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(evt.getSource()==botaoProximo) {
                    clicouProximo = true;
                }
            }
        });
    }
    */

    public void addListenerBotaoVoltar(ActionListener listener) {
        botaoVoltar.addActionListener(listener);
    }

    public void addListenerBotaoProximo(ActionListener listener) {
        botaoProximo.addActionListener(listener);
    }

    public void mostraDados(Pessoa pessoa) {
        matriculaPessoa.setText(Integer.toString(pessoa.getMatricula()));
        nomePessoa.setText(pessoa.getNome());
        emailPessoa.setText(pessoa.getEmail());
        senhaPessoa.setText(pessoa.getSenha());
        cargoPessoa.setText(pessoa.getCargo());
        turmaPessoa.setText(pessoa.getTurma());
        setorPessoa.setText(pessoa.getSetor());         
    }

}
