import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        PessoaDAO dao = new PessoaDAO();

        int opc = -1;

        while(opc != 0){
            System.out.println("\n--- CRUD de Pessoa ---");
            System.out.println("1 - Inserir");
            System.out.println("2 - Buscar por ID");
            System.out.println("3 - Listar todos");
            System.out.println("4 - Atualizar");
            System.out.println("5 - Deletar");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opc = sc.nextInt();

            sc.nextLine(); // limpar buffer

            switch(opc){
                case 1:
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    dao.inserir(new Pessoa(nome, email));
                    break;

                case 2:
                    System.out.print("ID: ");
                    int idBusca = sc.nextInt();
                    Pessoa p = dao.buscarPorId(idBusca);
                    System.out.println(p != null ? p : "Não encontrado!");
                    break;

                case 3:
                    dao.listarTodos().forEach(System.out::println);
                    break;

                case 4:
                    System.out.print("ID para atualizar: ");
                    int idUp = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Novo nome: ");
                    String novoNome = sc.nextLine();
                    System.out.print("Novo email: ");
                    String novoEmail = sc.nextLine();

                    dao.atualizar(new Pessoa(idUp, novoNome, novoEmail));
                    break;

                case 5:
                    System.out.print("ID para deletar: ");
                    int idDel = sc.nextInt();
                    dao.deletar(idDel);
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;
            }
        }

        sc.close();
    }

}
