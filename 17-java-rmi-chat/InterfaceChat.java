import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceChat extends Remote {
    
    public String enviarMensagem() throws RemoteException;

}