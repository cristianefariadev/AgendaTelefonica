package br.entidade;

public class EContatos {

	private long id;
	private String nome;
	private String telefone;

	public EContatos(int id, String nome, String telefone) {
	
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
	}
	
	public EContatos(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
