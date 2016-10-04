package br.model;

public class EAgenda {

	private int id;
	private String nome;
	private String telefone;

	public EAgenda(int id, String nome, String telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
	}
	
	public EAgenda(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
