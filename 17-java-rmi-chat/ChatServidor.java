import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ChatServidor extends UnicastRemoteObject implements InterfaceChat{
    
    public ChatServidor() throws RemoteException {}

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
            chat = new ChatServidor();
            LocateRegistry.createRegistry(1099);
			Naming.bind("rmi:///ChatServidor", chat);
        } catch (Exception e) {
            System.err.println("Exceção servidor: " + e.toString());
			e.printStackTrace();
		}	
    }
    
}