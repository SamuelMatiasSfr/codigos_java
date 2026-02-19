public abstract class Biblioteca {
    
    int numCatalago;
    int numCopias;
    
    public Biblioteca(int numCatalago, int numCopias) {
        this.numCatalago = numCatalago;
        this.numCopias = numCopias;
    }

    public int getNumCatalago() {
        return numCatalago;
    }

    public void setNumCatalago(int numCatalago) {
        this.numCatalago = numCatalago;
    }

    public int getNumCopias() {
        return numCopias;
    }

    public void setNumCopias(int numCopias) {
        this.numCopias = numCopias;
    }

    @Override
    public String toString() {
        return numCatalago + ";" + numCopias + ";";
    }

    public void adquirir(){

    }

    public void retornar(){
        
    }

}
