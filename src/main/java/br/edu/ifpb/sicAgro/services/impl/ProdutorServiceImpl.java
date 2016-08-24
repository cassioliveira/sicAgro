package br.edu.ifpb.sicAgro.services.impl;

import javax.inject.Inject;

import br.edu.ifpb.sicAgro.dao.ProdutorDAO;
import br.edu.ifpb.sicAgro.model.Produtor;
import br.edu.ifpb.sicAgro.services.ProdutorService;

public class ProdutorServiceImpl extends GenericServiceImpl<Produtor, Long> implements ProdutorService{

	
	private static final long serialVersionUID = -9171041213373059450L;
	
	public ProdutorServiceImpl() {
		
	}

	@Inject
	public ProdutorServiceImpl(ProdutorDAO produtorDAO) {
		this.dao = produtorDAO;
	}

	@Override
	public Long getTotalProdutores() {
		ProdutorDAO produtorDAO = (ProdutorDAO) this.dao;
		return produtorDAO.getTotalProdutores();
	}

	@Override
	public Produtor findByCPF(String cpf) {
		ProdutorDAO produtorDAO = (ProdutorDAO) this.dao;
		return produtorDAO.findByCPF(cpf);
	}

	@Override
	public boolean isCPFExists(String cpf) {
		ProdutorDAO produtorDAO = (ProdutorDAO) this.dao;
		return produtorDAO.isCPFExists(cpf);
	}

}
