public interface InterfaceDAO{

    public int getTamanhoArray();
    public Pessoa getPessoa(int matricula);
    public void atualizarPessoa(int matricula, Pessoa pessoa);
    public void deletarPessoa(int matricula);
    public void salvarPessoas();

}