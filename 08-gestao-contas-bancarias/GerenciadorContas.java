import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class GerenciadorContas{
    
    private ArrayList<Conta> contas;
    private ArrayList<ContaEspecial> contasEspeciais;
    private ArrayList<ContaPoupanca> contasPoupanca;

    public void carregaDados(){
        this.contas = new ArrayList<>();
        this.contasEspeciais = new ArrayList<>();
        this.contasPoupanca = new ArrayList<>();

        try(BufferedReader buffer = new BufferedReader(new FileReader("contas.csv"))){
            String linha;
            int cont = 0;
            while((linha = buffer.readLine()) != null){
                String result[] = linha.split(",");
                
                if(cont > 0){
                    int id = Integer.parseInt(result[0]);
                    float saldo = Float.parseFloat(result[2]);
                    String nome = result[1];
                    if(result.length == 3){
                        Conta conta = new Conta(id, nome, saldo);
                        contas.add(conta);
                    } else if(result.length == 4){
                        float limite = Float.parseFloat(result[3]);
                        ContaEspecial contaEspecial = new ContaEspecial(id, nome, saldo, limite);
                        contasEspeciais.add(contaEspecial);
                    } else if(result.length == 5){
                        float rendimento = Float.parseFloat(result[4]);
                        ContaPoupanca contaPoupanca = new ContaPoupanca(id, nome, saldo, rendimento); 
                        contasPoupanca.add(contaPoupanca);
                    }
                }
                cont++;
            }
            buffer.close();  
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não existe.");
        } catch (IOException e) {
            System.out.println("Erro na leitura do arquivo");
        }


       
    }

    public void mostraQuantContas(){
        System.out.println(contas.size() + " Conta");
        System.out.println(contasEspeciais.size() + " ContaEspecial");
        System.out.println(contasPoupanca.size() + " ContaPoupanca");
    }

    public void mostraContasEspeciais(){
        System.out.println("id, nome, saldo, limite");
        for(ContaEspecial conta : contasEspeciais){
            if(conta.getSaldo() < conta.getLimite()){
                System.out.println(conta);
            }
        }
    }

    public void mostraContasPoupanca(){
        System.out.println("id, nome, saldo, calculoRendimento");
        for(ContaPoupanca conta : contasPoupanca){
            System.out.println(conta.mostraDadosMaisCalculo());
        }
    }

    public ArrayList<Conta> getContas() {
        return contas;
    }

    public ArrayList<ContaEspecial> getContasEspeciais() {
        return contasEspeciais;
    }

    public ArrayList<ContaPoupanca> getContasPoupanca() {
        return contasPoupanca;
    }
}