import java.rmi.RemoteException;
import java.rmi.Remote;

public interface InterfaceRetorno extends Remote {
    
    public String retornarPalavra(char letra) throws RemoteException;

    public int retornarTentativas() throws RemoteException;

    public String retornarResultadoFinal() throws RemoteException;

    public boolean retornarCompletouPalavra() throws RemoteException;

}
