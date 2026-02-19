import java.util.Scanner;

public class Atividade1d {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        float peso, altura;

        System.out.println("Digite seu peso: ");
        peso = input.nextFloat();
        input.nextLine();

        System.out.println("Digite sua altura: ");
        altura = input.nextFloat();
        input.nextLine();

        float imc = peso/(altura*altura);

        System.out.println("\nSeu IMC: " + imc);
        System.out.println("\nValores IMC: ");
        System.out.println("MAGREZA:\tMENOR QUE 18,5");
        System.out.println("NORMAL:\tENTRE 18,5 E 24,9");
        System.out.println("SOBREPESO:\tENTRE 25,0 E 29,9");
        System.out.println("OBESIDADE:\t30,0 OU MAIOR");

        input.close();
    }
    
}
