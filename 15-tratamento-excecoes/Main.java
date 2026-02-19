public class Main{

    public static void main(String[] args) {
        CalculadoraPares calculadora = new CalculadoraPares();

        try{
            System.out.println(calculadora.dividir(4, 4));
        } catch (NaoParException e){
            System.out.println(e);
        }

    }

}