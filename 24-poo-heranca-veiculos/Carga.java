import java.util.Scanner;

public class Carga extends Movel{
  
  private int volumeMax;
  private int pesoMax;
  
  Carga(int numRodas, String fabricante, int anoFabricacao){
    super(numRodas, fabricante, anoFabricacao);
  }

  public int getVolumeMax() {
    return volumeMax;
  }

  public void setVolumeMax(int volMax) {
    if(volMax > 0){
      this.volumeMax = volMax;
    }else{
      System.out.println("\nVelocidade inválida");
      Scanner scanner = new Scanner(System.in);
      System.out.println("Digite uma volume > 0: ");
      volMax = scanner.nextInt();
      setVolumeMax(volMax);
      scanner.close();
    }
  }

  public int getPesoMax() {
    return pesoMax;
  }

  public void setPesoMax(int psMax) {
    if(psMax > 0){
      this.volumeMax = psMax;
    }else{
      System.out.println("\nVelocidade inválida");
      Scanner scanner = new Scanner(System.in);
      System.out.println("Digite um peso > 0: ");
      psMax = scanner.nextInt();
      setPesoMax(psMax);
      scanner.close();
    }
  }

  @Override
  public String toString() {
    return super.toString() + ", " + volumeMax + ", " + pesoMax;
  }
  
}
