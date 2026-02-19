import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ChatCliente implements InterfaceChat {

    public ChatCliente() throws RemoteException {}

    public String enviarMensagem() throws RemoteException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a mensagem: ");
        String mensagem = scanner.nextLine();
        scanner.close();
        return "Servidor: " + mensagem;
    }

    public static void main(String[] args) {
        InterfaceChat chat;
        try{
            chat = (InterfaceChat) Naming.lookup("rmi:///ChatServidor"); 
            System.out.println(chat.enviarMensagem());
        }catch (Exception e) {
            System.err.println("Exceção cliente: " + e.toString());
            e.printStackTrace();
        }     	
    }
    
}
