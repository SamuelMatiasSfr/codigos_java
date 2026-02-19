public class Software extends Gravacao {
    
    int versao;
    String plataforma;

    public Software(int numCatalago, int numCopias, String titulo, String midia, int versao, String plataforma) {
        super(numCatalago, numCopias, titulo, midia);
        this.versao = versao;
        this.plataforma = plataforma;
    }

    public int getVersao() {
        return versao;
    }

    public void setVersao(int versao) {
        this.versao = versao;
    }
    
    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    @Override
    public String toString() {
        return super.toString() + ";" + versao + ";" + plataforma + ";";
    }

}
