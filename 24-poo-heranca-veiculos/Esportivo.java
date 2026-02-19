import java.util.Scanner;

public class Esportivo extends Movel {
  
  private int cilindradas;
  private int numeroPassageiros;
  
  Esportivo(int numRodas, String fabricante, int anoFabricacao){
    super(numRodas, fabricante, anoFabricacao);
  }

  public int getCilindradas() {
    return cilindradas;
  }

  public void setCilindradas(int cilindradasNova) {
    if(cilindradasNova > 0){
      this.cilindradas = cilindradasNova;
    }else{
      System.out.println("\nVelocidade inválida");
      Scanner scanner = new Scanner(System.in);
      System.out.println("Digite uma cilindradada > 0: ");
      cilindradasNova = scanner.nextInt();
      setCilindradas(cilindradasNova);
      scanner.close();
    }
  }

  public int getNumPassageiros() {
    return numeroPassageiros;
  }

  public void setNumPassageiros(int numPassageiros) {
    if(numPassageiros >= 1 && numPassageiros <= 4){
      this.numeroPassageiros = numPassageiros;
    }else{
      Scanner scanner = new Scanner(System.in);
      System.out.println("Digite um número entre 1 e 4: ");
      numPassageiros = scanner.nextInt();
      setNumPassageiros(numPassageiros);
      scanner.close();
    }

  }

  @Override
  public String toString() {
    return super.toString() + ", " + cilindradas + ", " + numeroPassageiros;
  }
  
}
