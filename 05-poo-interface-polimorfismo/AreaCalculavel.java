public interface AreaCalculavel {
    double calcularArea();
}

class Quadrado implements AreaCalculavel {
    private int lado;

    public Quadrado(int lado){
        this.lado = lado;
    }

    public double calcularArea(){
        return this.lado * this.lado;
    }
}

class Retangulo implements AreaCalculavel{
    private int largura;
    private int altura;

    public Retangulo(int largura, int altura){
        this.largura = largura;
        this.altura = altura;
    }

    public double calcularArea(){
        return this.largura * this.altura;
    }
}

class Teste{
     public static void main(String[] args) {
        AreaCalculavel retangulo = new Retangulo(3, 2);
        System.out.println("Área do retângulo: " + retangulo.calcularArea());
        AreaCalculavel quadrado = new Quadrado(3);
        System.out.println("Área do quadrado: " + quadrado.calcularArea());
    }
}