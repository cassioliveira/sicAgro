package br.edu.ifpb.sicAgro.beans.pedidoSolicitacao;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.enumerations.PedidoStatus;
import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.model.PedidoSolicitacao;
import br.edu.ifpb.sicAgro.services.PedidoSolicitacaoService;
import br.edu.ifpb.sicAgro.util.jsf.JSFUtils;

/**
 * Manager bean responsável por gerenciar as páginas referentes a visualização
 * de detalhes de pedido de solicitação
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Named
@ViewScoped
public class PedidoSolicitacaoViewBean implements Serializable {

	private static final long serialVersionUID = -5325152793743099030L;

	private PedidoSolicitacao pedidoSolicitacao;

	@Inject
	private PedidoSolicitacaoService pedidoSolicitacaoService;

	/**
	 * É iniciando no inicio da renderização da pagina de pedidoSolicitacaoView,
	 * responsável por obter um pedido pelo contexto de aplicação.
	 */
	public void preRenderView() {
		pedidoSolicitacao = (PedidoSolicitacao) JSFUtils.getParam("pedidoSolicitacao");
	}

	/**
	 * Método necessário para auxiliar na rejeição 
	 * de um pedido de solicitação.
	 * 
	 * @throws SicAgroException
	 */
	public void rejeitar() throws SicAgroException {
		pedidoSolicitacao.setStatus(PedidoStatus.NOT_ACCEPTED);
		pedidoSolicitacaoService.update(pedidoSolicitacao);
		JSFUtils.rederTo("pedidosSolicitacao.xhtml");
	}

	public PedidoSolicitacao getPedidoSolicitacao() {
		return pedidoSolicitacao;
	}

	public void setPedidoSolicitacao(PedidoSolicitacao pedidoSolicitacao) {
		this.pedidoSolicitacao = pedidoSolicitacao;
	}
}
