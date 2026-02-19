public class Publicacao extends Biblioteca {
    
    String titulo;
    String editor;

    public Publicacao(int numCatalago, int numCopias, String titulo, String editor) {
        super(numCatalago, numCopias);
        this.titulo = titulo;
        this.editor = editor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    @Override
    public String toString() {
        return super.toString() + ";" + titulo + ";" + editor + ";";
    }

}
