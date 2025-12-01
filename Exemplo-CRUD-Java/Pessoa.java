public class Pessoa {

    private int id;
    private String nome;
    private String email;

    public Pessoa(){}

    public Pessoa(int id, String nome, String email){
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public Pessoa(String nome, String email){
        this.nome = nome;
        this.email = email;
    }

    public int getId(){ return id; }
    public String getNome(){ return nome; }
    public String getEmail(){ return email; }

    public void setNome(String nome){ this.nome = nome; }
    public void setEmail(String email){ this.email = email; }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", Email: " + email;
    }
    
}
