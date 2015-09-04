package br.com.estatistica.hibernate;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.estatistica.modelo.cadastro.PerfilAcesso;

public class TestHibernate {

	public static void main(String[] args) {

		PerfilAcesso acesso = new PerfilAcesso("Administradores", "Perfil para os administradores do sistema");

		Map<String, String> properties = new HashMap<String, String>();
		properties.put("javax.persistence.jdbc.user", "root");
		properties.put("javax.persistence.jdbc.password", "delfos#123");

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("delfos", properties);
		EntityManager manager = factory.createEntityManager();

		manager.persist(acesso);

		manager.remove(acesso);
		manager.close();
	}

}
