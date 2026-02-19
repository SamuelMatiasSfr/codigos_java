import java.util.Random;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;


public class JogoAdivinhaNumero extends JFrame implements ActionListener {

    private JLabel label_comando;
    private JLabel label_numTentativas;
    private JLabel label_numMaiorOuMenor;
    private JTextField text;
    int palpite, sorteado, tentativas;

    public JogoAdivinhaNumero(String titulo) {
        super(titulo);
        setLayout(new FlowLayout());

        Random geradorDeAleatorios = new Random();
        sorteado = geradorDeAleatorios.nextInt(1000) + 1;
        palpite = 0;
        tentativas = 0;

        label_comando = new JLabel("Digite seu palpite:");
        label_numTentativas = new JLabel("Número de tentativas: 0");
        label_numMaiorOuMenor = new JLabel("");
        text = new JTextField("Digite um número entre 1 e 1000");
        
        text.addActionListener(this);

        add(label_comando);
        add(text);
        add(label_numTentativas);
        add(label_numMaiorOuMenor);
    }



    public void dica(int palpite, int sorteado, int tentativas) {
        if (palpite > sorteado) {
            label_numMaiorOuMenor.setText("Seu palpite é maior que o número sorteado.");
        } else if (palpite < sorteado) {
                label_numMaiorOuMenor.setText("Seu palpite é menor que o número sorteado.");
        } else if(palpite == sorteado){
            this.dispose();
            JOptionPane.showMessageDialog(null, "Parabéns, você acertou! O número era " + sorteado);
            JOptionPane.showMessageDialog(null, "Você tentou " + tentativas + " vezes antes de acertar!");
        }
        label_numTentativas.setText("Número de tentativas: " + tentativas);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
		if (event.getSource()==text) {
            try {
                palpite = Integer.parseInt(text.getText()); // Pega o palpite do usuário
                tentativas++; // Incrementa as tentativas
                dica(palpite, sorteado, tentativas); // Chama o método dica
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor, digite um número válido.");
            }
		}		
	}

    public static void main(String[] args) {
        JogoAdivinhaNumero jogo = new JogoAdivinhaNumero("Jogo Adivinha Número");
        jogo.setSize(300,150);
		jogo.setVisible(true);
    }

}