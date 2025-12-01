public class Proxy implements InterfaceDaoProxy{

    ImplementadoDaoProxy servico;

    public Proxy(ImplementadoDaoProxy servico){
        this.servico = servico;
    } 

    public boolean checkAccess(String senha) {
        boolean acessoLiberado = false;

        for (int i = 0; i < servico.getTamanhoArray(); i++) {
            Pessoa pessoa = servico.getPessoa(senha, i);

            if (pessoa != null && senha.equals(pessoa.getSenha())) {
                String setor = pessoa.getSetor();

                if ("Secretaria".equalsIgnoreCase(setor) || "Biblioteca".equalsIgnoreCase(setor)) {
                    acessoLiberado = true;
                    break; 
                }
            }
        }

        return acessoLiberado;
    }


    public int getTamanhoArray(){
		return servico.getTamanhoArray();
	}

    public Pessoa getPessoa(String senha, int index){
        Pessoa pessoa = null;
        if(checkAccess(senha)){
            pessoa = servico.getPessoa(senha, index);
        }
        return pessoa;
    }

    public void atualizarPessoa(String senha, int matricula, Pessoa pessoa){
        if(checkAccess(senha)){
            servico.atualizarPessoa(senha, matricula, pessoa);
        }
    }

    public void deletarPessoa(String senha, int matricula){
        if(checkAccess(senha)){
            servico.deletarPessoa(senha, matricula);
        }
    }

	public void salvarPessoas(String senha) {
        if(checkAccess(senha)){
            servico.salvarPessoas(senha);
        }
	}
    
}
