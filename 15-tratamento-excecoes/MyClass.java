public class MyClass {
    
    public static void main(String[] args) {
        Conta conta = new Conta(100);

        try {
            conta.saca(50); 
        } catch (ContaException e) {
            System.out.println(e);
        }
        
    }

}