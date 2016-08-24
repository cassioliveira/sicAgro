package br.edu.ifpb.sicAgro.beans.solicitacaoServicoMaquinas;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.model.SolicitacaoServico;
import br.edu.ifpb.sicAgro.services.PedidoSolicitacaoService;
import br.edu.ifpb.sicAgro.services.SolicitacaoServicoService;
import br.edu.ifpb.sicAgro.services.VeiculoService;
import br.edu.ifpb.sicAgro.util.jsf.JSFUtils;
import br.edu.ifpb.sicAgro.util.messages.MessageUtils;

/**
 * Manager bean responsável por gerenciar as páginas referentes a exibição de
 * detalhes de uma solicitação de serviço de veículo (máquina)
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Named
@ViewScoped
public class SolicitacaoServicoViewBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private SolicitacaoServico solicitacaoServico;
	
	@Inject
	private SolicitacaoServicoService service;
	
	@Inject
	private PedidoSolicitacaoService PedidoSolicitacaoService;
	
	@Inject
	private VeiculoService veiculoService;

	/**
	 * É iniciando no início da renderização da pagina de solicitacaoView,
	 * responsável por obter uma solicitacao pelo contexto de aplicação.
	 */
	public void preRenderView() {
		solicitacaoServico = (SolicitacaoServico) JSFUtils.getParam("solicitacao");
	}
	
	public void completarSolicitacao(){
		if(solicitacaoServico.getTimeWorkeds() != null){
			this.veiculoService.setHorimetroVeiculo(solicitacaoServico.getVeiculo(), solicitacaoServico.getTimeWorkeds());
		}
		
		if(solicitacaoServico.getCompleted()){
			PedidoSolicitacaoService.completarPedidoSolicitacao(solicitacaoServico.getPedidoSolicitacao());
		}
		
		service.update(solicitacaoServico);
		MessageUtils.messageSucess("Solicitação finalizada com sucesso.");
	}
	
	public void cancelConclusao(){
		solicitacaoServico.setCompleted(false);
		solicitacaoServico.setDateRealization(null);
		solicitacaoServico.setTimeWorkeds(null);
		service.update(solicitacaoServico);
	}

	public SolicitacaoServico getSolicitacaoServico() {
		return solicitacaoServico;
	}

	public void setSolicitacaoServico(SolicitacaoServico solicitacaoServico) {
		this.solicitacaoServico = solicitacaoServico;
	}
}
