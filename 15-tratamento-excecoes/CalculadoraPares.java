public class CalculadoraPares{

    public int somar(int x, int y) throws NaoParException{
        if(x % 2 != 0){
            throw new NaoParException(x);
        }else if(y % 2 != 0){
            throw new NaoParException(y);
        }else{
            return x + y;
        }
    }

    public int subtrair(int x, int y) throws NaoParException{
        if(x % 2 != 0){
            throw new NaoParException(x);
        }else if(y % 2 != 0){
            throw new NaoParException(y);
        }else{
            return x - y;
        }
    }

    public int multiplicar(int x, int y) throws NaoParException{
        if(x % 2 != 0){
            throw new NaoParException(x);
        }else if(y % 2 != 0){
            throw new NaoParException(y);
        }else{
            return x * y;
        }
    }

    public int dividir(int x, int y) throws NaoParException{
        if(x % 2 != 0){
            throw new NaoParException(x);
        }else if(y % 2 != 0){
            throw new NaoParException(y);
        }else{
            return x / y;
        }
    }

}