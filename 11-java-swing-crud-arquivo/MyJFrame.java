import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MyJFrame extends JFrame {

	private String num, matricula, nome;
    private JLabel jlabelNumero;
    private JLabel jlabelMatricula;
    private JLabel jlabelNome;
    private JTextField jtextNumero;
    private JTextField jtextMatricula;
    private JTextField jtextNome;
	private JButton jbuttonCreate;
	private JButton jbuttonRead;
	private JButton jbuttonUpdate;
	private JButton jbuttonDelete;
	private JButton jbuttonSalvar;
	private Timer time;

	public MyJFrame(String titulo) {
		super(titulo);
		setLayout(new FlowLayout());

		time = new Timer();

		jlabelNumero = new JLabel("Número:");
		jlabelMatricula = new JLabel("Matrícula:");
		jlabelNome = new JLabel("Nome:");
		jtextNumero = new JTextField(5);
		jtextMatricula = new JTextField(15);
		jtextNome = new JTextField(25);
		jbuttonCreate = new JButton("Create");
		jbuttonRead = new JButton("Read");
		jbuttonUpdate = new JButton("Update");
		jbuttonDelete = new JButton("Delete");
		jbuttonSalvar = new JButton("Salvar");

		num = new String();
        matricula = new String();
        nome = new String();

		add(jlabelNumero);
		add(jtextNumero);
		add(jlabelMatricula);
		add(jtextMatricula);
		add(jlabelNome);
		add(jtextNome);
		add(jbuttonCreate);
		add(jbuttonRead);
		add(jbuttonUpdate);
		add(jbuttonDelete);
		add(jbuttonSalvar);

		TextFieldHandler handler = new TextFieldHandler();

		jbuttonCreate.addActionListener(handler);
		jbuttonRead.addActionListener(handler);
		jbuttonUpdate.addActionListener(handler);
		jbuttonDelete.addActionListener(handler);
		jbuttonSalvar.addActionListener(handler);
	}

	public void carregarArray(ArrayList<String> linhas){
		try {
            BufferedReader buffer = new BufferedReader(new FileReader("dados.csv"));
	            
            String linha;
            while((linha = buffer.readLine()) != null) {
                String[] result = linha.split(",");
                if (matricula.equals(result[1])) {
					linha = num + "," + matricula + "," + nome;
					linhas.add(linha);
				}else{
					linha = result[0] + "," + result[1] + "," + result[2];
					linhas.add(linha);
				}
            }
			buffer.close();
        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo de leitura e escrita: " + e.getMessage());
        }
	}

	public void determinarEditaveis(boolean bTexts){
		jtextNumero.setEditable(bTexts);
		jtextMatricula.setEditable(bTexts);
		jtextNome.setEditable(bTexts);
	}

	private class TextFieldHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {			
			
			if(event.getSource()==jbuttonCreate) {
				num = jtextNumero.getText();
                matricula = jtextMatricula.getText();
                nome = jtextNome.getText();
				registrarNoArquivo(num, matricula, nome);
			} 
			
			else if(event.getSource()==jbuttonRead){
                nome = jtextNome.getText();
				determinarEditaveis(false);
				jbuttonUpdate.setEnabled(false);
				jbuttonCreate.setEnabled(false);
				jbuttonDelete.setEnabled(false);
				jbuttonSalvar.setEnabled(false);

				time.schedule(new TimerTask(){
					@Override
					public void run(){
						pesquisarNoArquivo(nome);
						jbuttonUpdate.setEnabled(true);
						jbuttonDelete.setEnabled(true);
					}
				}, 2000);
			} 
			
			else if(event.getSource()==jbuttonUpdate){
				determinarEditaveis(true);
				jbuttonCreate.setEnabled(true);
				jbuttonSalvar.setEnabled(true);
			} 

			else if(event.getSource()==jbuttonSalvar){
				num = jtextNumero.getText();
                matricula = jtextMatricula.getText();
                nome = jtextNome.getText();
				editarNoArquivo(num, matricula, nome);
			}
			
			else if(event.getSource()==jbuttonDelete){
                matricula = jtextMatricula.getText();
				apagarNoArquivo(matricula);
			}
			
		}

	}

	public void ordenarRegistros(ArrayList<String> linhas){
		for (int j = 0; j < linhas.size() - 1; j++) {
			for (int i = 1; i < linhas.size() - 1 - j; i++) {
				String[] result1 = linhas.get(i).split(",");
				String[] result2 = linhas.get(i + 1).split(",");

				int num1 = Integer.parseInt(result1[0]);
				int num2 = Integer.parseInt(result2[0]);

				if (num1 > num2) {
					String aux = linhas.get(i);
					linhas.set(i, linhas.get(i + 1));
					linhas.set(i + 1, aux);
				}else if(num1 == num2){
					num2++;
					String aux = num2 + "," + result2[1] + "," + result2[2];
					linhas.set(i+1, aux);
				}
			}
		}
	}

	public void registrarNoArquivo(String num, String matricula, String nome) {
        ArrayList<String> linhas = new ArrayList<>();
		carregarArray(linhas);
		linhas.add(num + "," + matricula + "," + nome);
		ordenarRegistros(linhas);

		try {
            PrintWriter writer = new PrintWriter(new FileWriter("dados.csv"));
            for(int i=0; i<linhas.size(); i++){
				writer.println(linhas.get(i));
			}
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao processar a escrita no arquivo: " + e.getMessage());
        } 
    }

	public void editarNoArquivo(String num, String matricula, String nome) {
        try {
            BufferedReader buffer = new BufferedReader(new FileReader("dados.csv"));
	            
			ArrayList<String> linhas = new ArrayList<>();
            String linha;

            while((linha = buffer.readLine()) != null) {
                String[] result = linha.split(",");
                if (matricula.equals(result[1])) {
					linha = num + "," + matricula + "," + nome;
					linhas.add(linha);
				}else{
					linha = result[0] + "," + result[1] + "," + result[2];
					linhas.add(linha);
				}
            }
			buffer.close();

			ordenarRegistros(linhas);

			PrintWriter writer = new PrintWriter(new FileWriter("dados.csv"));
			for(int i=0; i<linhas.size(); i++){
				writer.println(linhas.get(i));
			}

            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo de leitura e escrita: " + e.getMessage());
        }    
    }

	public void apagarNoArquivo(String matricula) {
        try {
            BufferedReader buffer = new BufferedReader(new FileReader("dados.csv"));
	            
			ArrayList<String> linhas = new ArrayList<>();
            String linha;
            int num = 0;

            while((linha = buffer.readLine()) != null) {
				if(num == 0){
					linhas.add(linha);
				}else if(num > 0){
                    String[] result = linha.split(",");
            
                    if (!matricula.equals(result[1])) {
						linha = num + "," + result[1] + "," + result[2];
						linhas.add(linha);
					} 
                    if(matricula.equals(result[1])){
                        num = num-1;
                    }
                }
                num++;
            }
			buffer.close();

			ordenarRegistros(linhas);

			PrintWriter writer = new PrintWriter(new FileWriter("dados.csv"));
			for(int i=0; i<linhas.size(); i++){
				writer.println(linhas.get(i));
			}

            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo de leitura e escrita: " + e.getMessage());
        }    
    }

	public void pesquisarNoArquivo(String nome) {
        try {
            BufferedReader buffer = new BufferedReader(new FileReader("dados.csv"));
	            
            String linha;

            while((linha = buffer.readLine()) != null) {
                String[] result = linha.split(",");
            
				for(int i=0; i<result.length; i++){
					if(nome.equals(result[i])){
						jtextNumero.setText(result[0]);
						jtextMatricula.setText(result[1]);
						jtextNome.setText(result[2]);
					}
				}
            }
			buffer.close();
        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo de leitura e escrita: " + e.getMessage());
        }    
    }
	public static void main(String[] args) {
		MyJFrame myJFrame = new MyJFrame("CRUD");		
		myJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myJFrame.setSize(400, 200);
		myJFrame.setVisible(true);
	}

}