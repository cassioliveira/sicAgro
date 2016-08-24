package br.edu.ifpb.sicAgro.beans.veiculo;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.model.Veiculo;
import br.edu.ifpb.sicAgro.services.VeiculoService;
import br.edu.ifpb.sicAgro.util.jsf.JSFUtils;
import br.edu.ifpb.sicAgro.util.messages.MessageUtils;

@Named
@RequestScoped
public class VeiculoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private VeiculoService veiculoService;

	private List<Veiculo> veiculos;

	private Veiculo selectedVeiculo;
	
	@PostConstruct
	public void init() {
		this.listVeiculos();
	}

	public void remove() {
		veiculoService.remove(selectedVeiculo);
		MessageUtils.messageSucess("Veículo removido com sucesso.");
		JSFUtils.rederTo("veiculos.xhtml");
	}

	public void renderTo() {
		JSFUtils.rederTo("veiculoView.xhtml");
		JSFUtils.setParam("veiculo", selectedVeiculo);
	}

	public void listVeiculos() {
		this.veiculos = veiculoService.findAll();
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	public Veiculo getSelectedVeiculo() {
		return selectedVeiculo;
	}

	public void setSelectedVeiculo(Veiculo selectedVeiculo) {
		this.selectedVeiculo = selectedVeiculo;
	}

}
