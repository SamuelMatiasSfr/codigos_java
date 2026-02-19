import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfacePessoa extends Remote {

    public String retornarPessoa(int matricula) throws RemoteException;

}
