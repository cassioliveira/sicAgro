package br.edu.ifpb.sicAgro.beans.produtor;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.filter.ProdutorFilter;
import br.edu.ifpb.sicAgro.model.Produtor;
import br.edu.ifpb.sicAgro.services.ProdutorService;
import br.edu.ifpb.sicAgro.util.jsf.JSFUtils;
import br.edu.ifpb.sicAgro.util.messages.MessageUtils;

/**
 * Bean responsável por gerenciar listagem de produtores.
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Named
@RequestScoped
public class ProdutorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutorService produtorService;

	private Produtor selectedProdutor;

	private List<Produtor> produtores;

	private ProdutorFilter filter = ProdutorFilter.getInstance();

	public void preRenderView() {
		if (selectedProdutor == null) {
			selectedProdutor = new Produtor();
		}
	}

	@PostConstruct
	public void init() {
		this.filter();
	}

	/*
	 * 
	 */
	public void remove() throws SicAgroException {
		produtorService.remove(selectedProdutor);
		MessageUtils.messageSucess("Produtor removido com sucesso.");
		JSFUtils.rederTo("produtores.xhtml");
	}

	/**
	 * <pre>
	 * Utilizado como solução pra conseguir passar um paramtro apartir do 
	 * manager bean, devido a forma que a linha da datatable é selecionada,
	 * desta forma sem usar um componente que tenha um outcome, tem-se a necessidade
	 * de fazer o manager bean redireiconar para outra página, diante isso o parametro
	 * deve ser passado do manager bean.
	 * </pre>
	 */
	public void renderTo() {
		JSFUtils.rederTo("produtorView.xhtml");
		JSFUtils.setParam("produtorToDetail", selectedProdutor);
	}

	/**
	 * 
	 */
	public void filter() {
		produtores = produtorService.filter(filter);
	}
	
	public Produtor getSelectedProdutor() {
		return selectedProdutor;
	}

	public void setSelectedProdutor(Produtor selectedProdutor) {
		this.selectedProdutor = selectedProdutor;
	}

	public List<Produtor> getProdutores() {
		return produtores;
	}

	public void setProdutores(List<Produtor> produtores) {
		this.produtores = produtores;
	}

	public ProdutorFilter getFilter() {
		return filter;
	}

	public void setFilter(ProdutorFilter filter) {
		this.filter = filter;
	}
}
