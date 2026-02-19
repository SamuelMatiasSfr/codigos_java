import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Questao3 {
  
  private ArrayList<Movel> moveis;
  private ArrayList<Esportivo> esportivos;
  private ArrayList<Carga> cargas;

  public void carregaDados(){
    this.moveis = new ArrayList<>();
    this.esportivos = new ArrayList<>();
    this.cargas = new ArrayList<>();

    try(BufferedReader buffer = new BufferedReader(new FileReader("moveis.txt"))){
        String linha;
        int cont = 0;
        while((linha = buffer.readLine()) != null){
            String result[] = linha.split(";");
            
            if(cont > 0){
                int numRodas = Integer.parseInt(result[0]);
                String fabricante = result[1];
                int anoFabricacao = Integer.parseInt(result[2]);
                int vel = Integer.parseInt(result[3]);
                
                if(result.length == 4){
                    Movel movel = new Movel(numRodas, fabricante, anoFabricacao);
                    movel.setVelocidade(vel);
                    moveis.add(movel);
                } else if(result.length == 6){
                    int cilindradas = Integer.parseInt(result[4]);
                    int numPassageiros = Integer.parseInt(result[5]);
                    Esportivo esportivo = new Esportivo(numRodas, fabricante, anoFabricacao);
                    esportivo.setVelocidade(vel);
                    esportivo.setCilindradas(cilindradas);
                    esportivo.setNumPassageiros(numPassageiros);
                    esportivos.add(esportivo);
                } else if(result.length == 8){
                    int volumeMax = Integer.parseInt(result[6]);
                    int pesoMax = Integer.parseInt(result[7]);
                    Carga carga = new Carga(numRodas, fabricante, anoFabricacao);
                    carga.setVelocidade(vel);
                    carga.setPesoMax(pesoMax);
                    carga.setVolumeMax(volumeMax);
                    cargas.add(carga);
                }
            }
            cont++;
        }
        buffer.close();  
    } catch (FileNotFoundException e) {
        System.out.println("Arquivo não existe.");
    } catch (IOException e) {
        System.out.println("Erro na leitura do arquivo");
    }

  }

  public void mostraMoveis(){
    System.out.println("numRodas; fabricante; anoFabricação; velocidade");
    for(Movel movel : moveis){
      System.out.println(movel);
    }
    System.out.println("\n");
  }

  public void mostraEsportivos(){
    System.out.println("numRodas; fabricante; anoFabricação; velocidade; cilindradas; numPassageiros");
    for(Esportivo esportivo : esportivos){
      System.out.println(esportivo);
    }
    System.out.println("\n");
  }

  public void mostraCargas(){
    System.out.println("numRodas; fabricante; anoFabricação; velocidade; volumeMaximo; pesoMaximo");
    for(Carga carga : cargas){
      System.out.println(carga);
    }
    System.out.println("\n");
  }

  public static void main(String[] args) {
    
    Questao3 questao = new Questao3();
    questao.carregaDados();

    questao.mostraMoveis();
    questao.mostraEsportivos();
    questao.mostraCargas();
    
  }

}
