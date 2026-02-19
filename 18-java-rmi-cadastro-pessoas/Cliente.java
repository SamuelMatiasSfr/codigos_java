import java.rmi.Naming;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class Cliente {

    Janela janela;
    int indice_atual;

    public static void main(String[] args) { 
        new Cliente();   
    }

    public Cliente() {  
        janela = new Janela();
        indice_atual = 0;
        fazerRequisicaoParaServidor();

        janela.addListenerBotaoVoltar(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                indice_atual--;
                fazerRequisicaoParaServidor();
            }
        });

        janela.addListenerBotaoProximo(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                indice_atual++;
                fazerRequisicaoParaServidor();
            }
        });

        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }
    
    private void fazerRequisicaoParaServidor(){
        InterfacePessoa objeto;
        try{
            objeto = (InterfacePessoa) Naming.lookup("rmi://192.168.18.94:1099/ServidorInterfacePessoaImplementada");
            String string_pessoa = objeto.retornarPessoa(indice_atual);
            Pessoa pessoa = new Pessoa(string_pessoa);
            janela.mostraDados(pessoa);  
        }catch (Exception e) {
            System.err.println("Exceção cliente: " + e.toString());
            e.printStackTrace();
        }     

    }   

}
