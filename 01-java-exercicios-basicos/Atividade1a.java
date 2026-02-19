import java.util.Scanner;

public class Atividade1a {
    
    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        int numEntrada = 0;
        int numSaida[];
        numSaida = new int[5];

        System.out.println("Digite um numero: ");
        numEntrada = input.nextInt();
        input.nextLine();

        int divisor = 10000;
        for(int i=0; i<5; i++){
            numSaida[i] = numEntrada/divisor;
            numEntrada = numEntrada % divisor;
            divisor = divisor/10;
        }       
        
        System.out.println("\n");
        for(int i=0; i<5; i++){
            System.out.printf("%d   ", numSaida[i]);
        }

        input.close();
    }

}

