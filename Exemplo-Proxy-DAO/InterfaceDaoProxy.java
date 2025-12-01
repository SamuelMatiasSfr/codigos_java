public interface InterfaceDaoProxy{

    public int getTamanhoArray();
    public Pessoa getPessoa(String senha, int matricula);
    public void atualizarPessoa(String senha, int matricula, Pessoa pessoa);
    public void deletarPessoa(String senha, int matricula);
    public void salvarPessoas(String senha);

}