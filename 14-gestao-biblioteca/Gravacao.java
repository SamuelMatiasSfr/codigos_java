public class Gravacao extends Biblioteca {
   
    String titulo;
    String midia;
    
    public Gravacao(int numCatalago, int numCopias, String titulo, String midia) {
        super(numCatalago, numCopias);
        this.titulo = titulo;
        this.midia = midia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMidia() {
        return midia;
    }

    public void setMidia(String midia) {
        this.midia = midia;
    }

    @Override
    public String toString() {
        return super.toString() + ";" + titulo + ";" + midia + ";";
    }

}
