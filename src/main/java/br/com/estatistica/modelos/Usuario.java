package br.com.estatistica.modelos;


public class Usuario implements Identificator, Validador {

	private Integer id;
	private String login;
	private String senha;
	private String descricao;

	private PerfilAcesso perfilAcesso;

	public Usuario(int id, String login, String descricao, PerfilAcesso perfilAcesso) {
		this.id = id;
		this.login = login;
		this.descricao = descricao;
		this.perfilAcesso = perfilAcesso;
	}

	public Usuario(String login, PerfilAcesso perfilAcesso) {
		this.login = login;
		this.perfilAcesso = perfilAcesso;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public PerfilAcesso getPerfilAcesso() {
		return perfilAcesso;
	}

	public void setPerfilAcesso(PerfilAcesso perfilAcesso) {
		this.perfilAcesso = perfilAcesso;
	}

	@Override
	public void validate() {
		if (this.getId() == null || this.login == null || this.descricao == null) {
			throw new NullPointerException("ID, login, descrição ou perfil de acesso não podem ser nulo.");
		}
	}

}
