package br.com.estatistica.modelo.cadastro;

public class TipoReposta {
	private int id;
	private String nome;

	public TipoReposta(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public TipoReposta(String nome) {
		this.nome = nome;
	}

	public TipoReposta() {

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

}