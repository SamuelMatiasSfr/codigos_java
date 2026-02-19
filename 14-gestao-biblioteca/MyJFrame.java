import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class MyJFrame extends JFrame {
    
    private JLabel jlabelNumCatalago;
    private JLabel jlabelNumCopias;
    private JLabel jlabelTitulo;
    private JLabel jlabelMidia;
    private JLabel jlabelDiretor;
    private JLabel jlabelDataLancamento;
    private JLabel jlabelDistribuidor;
    private JLabel jlabelVersao;
    private JLabel jlabelPlataforma;
    private JTextField jtextNumCatalago;
    private JTextField jtextNumCopias;
    private JTextField jtextTitulo;
    private JTextField jtextMidia;
    private JTextField jtextDiretor;
    private JTextField jtextDataLancamento;
    private JTextField jtextDistribuidor;
    private JTextField jtextVersao;
    private JTextField jtextPlataforma;
    private JCheckBox jboxFilme;
    private JCheckBox jboxSoftware;

	private JButton jbuttonAdicionar;
	private JButton jbuttonLimpar;

    private ArrayList<Gravacao> gravacoes;
    private ArrayList<JTextField> textos;
    private Principal principal;
    private boolean filmeAtivado;
    private boolean softwareAtivado;
    
    public MyJFrame(String titulo) {
		super(titulo);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        //setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        gravacoes = new ArrayList<Gravacao>();
        textos = new ArrayList<>();
        principal = new Principal();

        jlabelNumCatalago = new JLabel("Numéro de Catálogo");
        jlabelNumCopias = new JLabel("Numéro de Cópias");
		jlabelTitulo = new JLabel("Título:");
		jlabelMidia = new JLabel("Mídia:");
        jlabelDiretor = new JLabel("Diretor:");
		jlabelDataLancamento = new JLabel("Data de lançamento:");
        jlabelDistribuidor = new JLabel("Distribuidor:");
		jlabelVersao = new JLabel("Versão:");
        jlabelPlataforma = new JLabel("Plataforma:");

        jtextNumCatalago = new JTextField(5);
        jtextNumCopias = new JTextField(5);
        jtextTitulo = new JTextField(30);
		jtextMidia = new JTextField(30);
        jtextDiretor = new JTextField(30);
		jtextDataLancamento = new JTextField(30);
        jtextDistribuidor = new JTextField(30);
		jtextVersao = new JTextField(30);
        jtextPlataforma = new JTextField(30);

        textos.add(jtextNumCatalago);
        textos.add(jtextNumCopias);
        textos.add(jtextTitulo);
        textos.add(jtextMidia);
        textos.add(jtextDiretor);
        textos.add(jtextDataLancamento);
        textos.add(jtextDistribuidor);
        textos.add(jtextVersao);
        textos.add(jtextPlataforma);

        jboxFilme = new JCheckBox("Filme");
        jboxSoftware = new JCheckBox("Software");

        jbuttonAdicionar = new JButton("Adicionar");
        jbuttonLimpar = new JButton("Limpar");

        filmeAtivado = false;
        softwareAtivado = false;

        add(jlabelNumCatalago);
        add(jtextNumCatalago);
        add(jlabelNumCopias);
        add(jtextNumCopias);
		add(jlabelTitulo);
		add(jtextTitulo);
        add(jlabelMidia);
		add(jtextMidia);
        add(jboxFilme);
        add(jboxSoftware);
        add(Box.createRigidArea(new Dimension(200, 10)));        
        add(jlabelDiretor);
		add(jtextDiretor);
        add(jlabelDataLancamento);
		add(jtextDataLancamento);
        add(jlabelDistribuidor);
		add(jtextDistribuidor);
        add(jlabelVersao);
		add(jtextVersao);
        add(jlabelPlataforma);
		add(jtextPlataforma);

		add(jbuttonAdicionar);
		add(jbuttonLimpar);

		TextFieldHandlerButtons handlerButtons = new TextFieldHandlerButtons();
        TextFieldHandlerBox handlerBox = new TextFieldHandlerBox();

		jbuttonAdicionar.addActionListener(handlerButtons);
		jbuttonLimpar.addActionListener(handlerButtons);
        jboxFilme.addActionListener(handlerBox);
        jboxSoftware.addActionListener(handlerBox);

        jtextDiretor.setEnabled(false);
        jtextDataLancamento.setEnabled(false);
        jtextDistribuidor.setEnabled(false);
        jtextVersao.setEnabled(false);
        jtextPlataforma.setEnabled(false);
	}

    private class TextFieldHandlerBox implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {			

            if(event.getSource()==jboxFilme && !filmeAtivado){
                filmeAtivado = true;
                softwareAtivado = false;

                jboxSoftware.setEnabled(false);
                jtextDiretor.setEnabled(true);

                jtextDataLancamento.setEnabled(true);
                jtextDistribuidor.setEnabled(true);
                jtextVersao.setEnabled(false);
                jtextPlataforma.setEnabled(false);
            }else if(event.getSource()==jboxSoftware && !softwareAtivado){
                filmeAtivado = false;
                softwareAtivado = true;

                jboxFilme.setEnabled(false);
                jtextDiretor.setEnabled(false);

                jtextDataLancamento.setEnabled(false);
                jtextDistribuidor.setEnabled(false);
                jtextVersao.setEnabled(true);
                jtextPlataforma.setEnabled(true);
            }else{
                filmeAtivado = false;
                softwareAtivado = false;

                jboxFilme.setEnabled(true);
                jboxSoftware.setEnabled(true);

                jtextDiretor.setEnabled(false);
                jtextDataLancamento.setEnabled(false);
                jtextDistribuidor.setEnabled(false);
                jtextVersao.setEnabled(false);
                jtextPlataforma.setEnabled(false);
            }

	    }
    }

    private class TextFieldHandlerButtons implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {			

			if(event.getSource()==jbuttonAdicionar && (filmeAtivado)) {
                int numCatalago = Integer.parseInt(jtextNumCatalago.getText());
                int numCopias = Integer.parseInt(jtextNumCopias.getText());
                
                gravacoes.add(new Filme(numCatalago, numCopias, jtextTitulo.getText(), 
                jtextMidia.getText(), jtextDiretor.getText(), jtextDataLancamento.getText(), 
                jtextDistribuidor.getText())); 

                principal.registrarNoArquivo(gravacoes);

                for(JTextField text : textos){
                    text.setText("");
                }
			} 
			
			if(event.getSource()==jbuttonAdicionar && (softwareAtivado)){
                int numCatalago = Integer.parseInt(jtextNumCatalago.getText());
                int numCopias = Integer.parseInt(jtextNumCopias.getText());
                int versao = Integer.parseInt(jtextVersao.getText());
                
                gravacoes.add(new Software(numCatalago, numCopias, jtextTitulo.getText(), 
                jtextMidia.getText(), versao, jtextPlataforma.getText()));

                principal.registrarNoArquivo(gravacoes);
                
                for(JTextField text : textos){
                    text.setText("");
                }
			} 

            if(event.getSource()==jbuttonLimpar){
                for(JTextField text : textos){
                    text.setText("");
                } 
			} 

	    }
    }

    public ArrayList<Gravacao> getGravacoes() {
        return gravacoes;
    }

    public static void main(String[] args) {
        MyJFrame myJFrame = new MyJFrame("Livraria");		
        myJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myJFrame.setSize(400, 600);
        myJFrame.setVisible(true);
    }

}
