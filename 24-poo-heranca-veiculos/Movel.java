import java.util.Scanner;

public class Movel {
 
  protected int numeroRodas;
  protected String fabricante;
  protected int anoFabricacao;
  private int velocidade;
  
  Movel(int numRodas, String fabricanteNome, int ano){
    Scanner scanner = new Scanner(System.in);
    
    if(ano > 0){
      this.anoFabricacao = ano;
    }else{
      System.out.println("\nVelocidade inválida");
      System.out.println("Digite uma velocidade > 0: ");
      ano = scanner.nextInt();
      setAnoFabricacao(ano);
    }

    if(fabricanteNome != null){
      this.fabricante = fabricanteNome;
    }else{
      System.out.println("\nVelocidade inválida");
      System.out.println("Digite uma velocidade > 0: ");
      fabricanteNome = scanner.nextLine();
      setFabricante(fabricanteNome);
    }

    if(numRodas > 0){
      this.numeroRodas = numRodas;
    }else{
      System.out.println("\nVelocidade inválida");
      System.out.println("Digite uma velocidade > 0: ");
      numRodas = scanner.nextInt();
      setNumeroRodas(numRodas);
    }
    scanner.close();
  }

  public int getVelocidade() {
    return velocidade;
  }

  public void setVelocidade(int vel) {
    if(vel > 0){
      this.velocidade = vel;
    }else{
      Scanner scanner = new Scanner(System.in);
      System.out.println("\nVelocidade inválida");
      System.out.println("Digite uma velocidade > 0: ");
      vel = scanner.nextInt();
      setVelocidade(vel);
      scanner.close();
    }
  }

  public void setNumeroRodas(int numRodas) {
    if(numRodas > 0){
      this.numeroRodas = numRodas;
    }else{
      Scanner scanner = new Scanner(System.in);
      System.out.println("\nVelocidade inválida");
      System.out.println("Digite um número de rodas > 0: ");
      numRodas = scanner.nextInt();
      setNumeroRodas(numRodas);
      scanner.close();
    }
  }

  public void setFabricante(String fabricanteNome) {
    if(fabricanteNome != null){
      this.fabricante = fabricanteNome;
    }else{
      Scanner scanner = new Scanner(System.in);
      System.out.println("\nVelocidade inválida");
      System.out.println("Digite um fabricante válido: ");
      fabricanteNome = scanner.nextLine();
      setFabricante(fabricanteNome);
      scanner.close();
    }
  }

  public void setAnoFabricacao(int ano) {
    if(ano > 0){
      this.anoFabricacao = ano;
    }else{
      Scanner scanner = new Scanner(System.in);
      System.out.println("\nVelocidade inválida");
      System.out.println("Digite um ano de fabricação > 0: ");
      ano = scanner.nextInt();
      setAnoFabricacao(ano);
      scanner.close();
    }
  }

  @Override
  public String toString() {
    return numeroRodas + ", " + fabricante + ", " + anoFabricacao + ", " + velocidade;
  }

}
