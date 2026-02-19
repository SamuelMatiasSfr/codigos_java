public class Pessoa {
    private int matricula;
    private String nome, email, senha, cargo, turma, setor;

    public Pessoa() {
    }

    public Pessoa(int matricula, String nome, String email, String senha, String cargo, String turma, String setor) {
        this.nome = nome;
        this.senha = senha;
        this.cargo = cargo;
        this.turma = turma;
        this.setor = setor;
        this.email = email;
        this.matricula = matricula;
    }

    public Pessoa(String linha) throws IllegalArgumentException {

        String[] partes = linha.split(";", -1);

        try {
            this.setMatricula(Integer.parseInt(partes[0]));
            this.setNome(partes[1]);
            this.setEmail(partes[2]);
            this.setSenha(partes[3]);
            this.setCargo(partes[4]);
            this.setTurma(partes[5]);
            this.setSetor(partes[6]);
        } catch (Exception e) {
            throw new IllegalArgumentException("Linha com formato incorreto: " + linha);
        }

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return matricula + ";" + nome + ";" + email + ";" + senha
                + ";" + cargo + ";" + turma + ";" + setor;
    }

}
