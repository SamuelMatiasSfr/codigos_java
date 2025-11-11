import java.util.ArrayList;

public class Main {

	PessoaDao interfacePessoaDao;

	public Main() {
		interfacePessoaDao = new PessoaDaoImplementada();

		ArrayList<Pessoa> pessoas = interfacePessoaDao.getTodasPessoas();

		System.out.println("Registro " + "Nome " + "Email ");
		for(int i=0; i<pessoas.size(); i++){
			System.out.println(i+1 + ", " + pessoas.get(i).getNome() + ", " + pessoas.get(i).getEmail());
		}
	}

	public static void main(String[] args) {
		new Main();
	}

}
