package br.com.estatistica.service;

import br.com.estatistica.daoHibernate.EstadoDAO;
import br.com.estatistica.daoHibernate.SimpleEntityManager;
import br.com.estatistica.modelo.cadastro.Estado;

public class EstadoService extends Service<Estado> {

	public EstadoService(SimpleEntityManager manager) {
		super(manager);
		dao = new EstadoDAO(manager.getEntityManager());
	}

	@Override
	protected boolean isNullId(Estado object) {
		return object.getId() == null;
	}

}
