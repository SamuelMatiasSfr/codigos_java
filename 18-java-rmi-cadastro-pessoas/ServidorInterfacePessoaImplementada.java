import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ServidorInterfacePessoaImplementada extends UnicastRemoteObject 
implements InterfacePessoa {

    private ArrayList<Pessoa> pessoas;

    protected ServidorInterfacePessoaImplementada() throws RemoteException{
        super();
        pessoas = new ArrayList<>();
        pessoas = carregarArray();
    }

    ArrayList<Pessoa> carregarArray(){
        ArrayList<Pessoa> pessoas = new ArrayList<>();

        try {
            BufferedReader buffer = new BufferedReader(new FileReader("pessoal.csv"));
	            
            int aux = 0;

            String linha_pessoa;
            while((linha_pessoa = buffer.readLine()) != null) {
                if(aux > 0){
                    String[] result = linha_pessoa.split(";");

                    if(result[4].equals("Docente")){
                        int matricula = Integer.parseInt(result[0]);
                        String nome = result[1];
                        String email = result[2];
                        String senha = result[3]; 
                        String cargo = result[4];
                        pessoas.add(new Pessoa(matricula, nome, email, senha, cargo, "", ""));
                    }

                    if(result[4].equals("Discente")){
                        int matricula = Integer.parseInt(result[0]);
                        String nome = result[1];
                        String email = result[2];
                        String senha = result[3]; 
                        String cargo = result[4];
                        String turma = result[5];
                        pessoas.add(new Pessoa(matricula, nome, email, senha, cargo, turma, ""));
                    }

                    if(result[4].equals("Geral")){
                        int matricula = Integer.parseInt(result[0]);
                        String nome = result[1];
                        String email = result[2];
                        String senha = result[3]; 
                        String cargo = result[4];
                        String setor = result[5];
                        pessoas.add(new Pessoa(matricula, nome, email, senha, cargo, "", setor));
                    }
                    
                }
                aux++;
            }
			buffer.close();
        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo de leitura e escrita: " + e.getMessage());
        }

        return pessoas;
    }

    public String retornarPessoa(int indice) throws RemoteException{
        String string_pessoa = "";
        if(indice < 0){
            indice = pessoas.size()-1;
        }
        if(indice == pessoas.size()){
            indice = 0;
        }
        string_pessoa = pessoas.get(indice).toString();
        return string_pessoa;
    }
    public static void main(String[] args) {
        InterfacePessoa objeto;
        try {
            objeto = new ServidorInterfacePessoaImplementada();
            LocateRegistry.createRegistry(1099);
			Naming.bind("rmi:///ServidorInterfacePessoaImplementada", objeto);
        } catch (Exception e) {
            System.err.println("Exceção servidor: " + e.toString());
            e.printStackTrace();
        }
    }
    
}
