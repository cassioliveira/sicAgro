package br.edu.ifpb.sicAgro.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.model.Veiculo;
import br.edu.ifpb.sicAgro.services.VeiculoService;

/**
 * Faces converter responsável por converter os atributos da entidade @Veiculo para
 * string (visualização na pagina) e para objeto. 
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Named
@RequestScoped
@FacesConverter(forClass=Veiculo.class)
public class VeiculoConverter implements Converter{

	@Inject
	private VeiculoService veiculoService;

	/**
	 * 
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}
		Long id = Long.parseLong(value);
		try {
			return veiculoService.findById(id);
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(String.format(
					"%s é invalido para o veiculo", id)), e);
		}
	}

	/**
	 * 
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null) {
			return null;
		}
		Long id = ((Veiculo) value).getId();
		return (id != null) ? id.toString() : null;
	}

}
