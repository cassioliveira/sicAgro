package br.edu.ifpb.sicAgro.services.impl;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.sicAgro.dao.VeiculoDAO;
import br.edu.ifpb.sicAgro.model.Veiculo;
import br.edu.ifpb.sicAgro.services.VeiculoService;
import br.edu.ifpb.sicAgro.util.jpa.Transactional;

public class VeiculoServiceImpl extends GenericServiceImpl<Veiculo, Long>
		implements VeiculoService {

	private static final long serialVersionUID = 1L;

	public VeiculoServiceImpl() {
	}

	@Inject
	public VeiculoServiceImpl(VeiculoDAO veiculoDAO) {
		this.dao = veiculoDAO;
	}

	@Override
	public List<Veiculo> findByIdentification(String identification) {
		VeiculoDAO veiculoDAO = (VeiculoDAO) this.dao;
		List<Veiculo> list = veiculoDAO.findByIdentification(identification);
		return list;
	}

	@Transactional
	public void setHorimetroVeiculo(Veiculo veiculo, Integer timesWorked) {
		veiculo.setHorimetro(veiculo.getHorimetro() + timesWorked);
		this.update(veiculo);
	}
}