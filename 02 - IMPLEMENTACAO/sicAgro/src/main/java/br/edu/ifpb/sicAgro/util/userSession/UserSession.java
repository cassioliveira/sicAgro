package br.edu.ifpb.sicAgro.util.userSession;

import java.io.Serializable;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.edu.ifpb.sicAgro.enumerations.UserRole;
import br.edu.ifpb.sicAgro.model.Conta;
import br.edu.ifpb.sicAgro.services.ContaService;
import br.edu.ifpb.sicAgro.services.FuncionarioService;
import br.edu.ifpb.sicAgro.services.ProdutorService;

/**
 * Classe utilária usada para recuperar usuário logado e algumas informações da
 * sessão como usuário e data de último acesso
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@ApplicationScoped
public class UserSession implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private ContaService contaService;

	@Inject
	private FuncionarioService funcionarioService;

	@Inject
	private ProdutorService produtorService;

	private Conta conta;

	private String lastAccess;

	/**
	 * recupera do BD a conta correpondente ao usuário logado
	 * 
	 * @return - conta correspondente
	 */
	@Produces
	@UserLogged
	@RequestScoped
	public Conta getAcount() {
		conta = contaService.findByUserName(recoverUserNameSession());

		if (conta != null) {
			return conta;
		} else {
			Conta conta = new Conta();
			conta.setUserName(" ");
			return conta;
		}
	}

	/**
	 * Recupera do contexto do JSF o nome de usuário que
	 * está inline.
	 * 
	 * @return
	 */
	public String recoverUserNameSession() {
		Principal principal = FacesContext.getCurrentInstance()
				.getExternalContext().getUserPrincipal();

		return principal != null ? principal.getName() : "";
	}

	/**
	 * Método utilizado para produxir o último acesso do usuário. 
	 * 
	 */
	@Produces
	@UserLastAccess
	public String getLastAccess() {
		this.getAcount();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy 'às' HH:mm a",
				new Locale("pt", "BR"));
		if (conta.getLastAcess() != null) {
			lastAccess = sdf.format(conta.getLastAcess());
			this.updateLastAccess();
		} else {
			this.updateLastAccess();
			lastAccess = sdf.format(new Date());
		}
		return lastAccess;
	}

	/**
	 * Método responsável por atualizar último 
	 * acesso de um usuário no Banco de dados.
	 */
	private void updateLastAccess() {
		conta.setLastAcess(new Date());
		if (conta.getUserRole().equals(UserRole.PRODUTOR)) {
			produtorService.update(conta.getProdutor());
		} else {
			funcionarioService.update(conta.getFuncionario());
		}
	}
}
