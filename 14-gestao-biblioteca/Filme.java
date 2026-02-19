public class Filme extends Gravacao {
    
    String diretor;
    String data;
    String distribuidor;

    public Filme(int numCatalago, int numCopias, String titulo, String midia, String diretor, String data,
            String distribuidor) {
        super(numCatalago, numCopias, titulo, midia);
        this.diretor = diretor;
        this.data = data;
        this.distribuidor = distribuidor;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(String distribuidor) {
        this.distribuidor = distribuidor;
    }

    @Override
    public String toString() {
        return super.toString() + ";" + diretor + ";" + data + ";" + distribuidor + ";";
    }

}
