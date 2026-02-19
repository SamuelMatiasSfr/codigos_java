class UmaClasse{

    private static UmaClasse umaClasse;
    private static int contador = 0;

    private UmaClasse(){
        contador++;
    }

    public static UmaClasse getInstancia(){
        if(umaClasse == null){
            umaClasse = new UmaClasse();
        }
        return umaClasse;
    }

    public static int getContador(){
        return contador;
    }
    
}

public class TestadorDeClasse{

    public static void main(String[] args) {
        UmaClasse umaClasse;
        for(int i=0; i<4; i++){
            umaClasse = UmaClasse.getInstancia();
        }
        System.err.println("Total de instâncias: " + UmaClasse.getContador());
    }

}
