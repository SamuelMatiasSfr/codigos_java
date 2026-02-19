import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;


public class MyJFrame extends JFrame implements ActionListener{

	private JLabel label_celsius;
    private JLabel label_fahrenheit;
	private JTextField jtext;
	private JButton jbutton;

	public MyJFrame(String titulo) {
		super(titulo);
        setLayout(new FlowLayout());

        Container painel = this.getContentPane();
        GridLayout grid = new GridLayout(3, 2, 10, 20);

		label_celsius = new JLabel("Celsius");
        label_fahrenheit = new JLabel("Fahrenheit");
		jtext = new JTextField("Digite o grau");
		jbutton = new JButton("Convert");

        painel.add(jtext);
		painel.add(label_celsius);
        painel.add(jbutton);
        painel.add(label_fahrenheit);

		jtext.addActionListener(this);
		jbutton.addActionListener(this);

        painel.setLayout(grid);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource()==jbutton) {
            int fahrenheit = Integer.parseInt(jtext.getText());
            fahrenheit = ((fahrenheit*9/5) + 32);
            label_fahrenheit.setText(fahrenheit + " Fahrenheit");
		}		
	}

	public static void main(String[] args) {
		MyJFrame my = new MyJFrame("Celsius Convert");
		my.setSize(250,150);
		my.setVisible(true);
	}


}