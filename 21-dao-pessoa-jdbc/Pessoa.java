class Pessoa {

    private int matricula;
    private String nome;
    private String email;
    private String senha;
    private String cargo;
    private String turma;
    private String setor;

    public Pessoa(int matricula, String nome, String email, String senha, String cargo, String turma, String setor) {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
        this.turma = turma;
        this.setor = setor;
    }

    @Override
    public String toString() {
        return matricula + "," + nome + "," + email + "," + senha
                + "," + cargo + "," + turma + "," + setor;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

}
