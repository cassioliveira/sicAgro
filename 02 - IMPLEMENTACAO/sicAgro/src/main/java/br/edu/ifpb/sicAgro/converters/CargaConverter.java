package br.edu.ifpb.sicAgro.converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.model.Carga;
import br.edu.ifpb.sicAgro.services.CargaService;

/**
 * Faces converter responsável por converter os atributos da entidade @Carga para
 * string (visualização na pagina) e para objeto. 
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Named
@RequestScoped
@FacesConverter(forClass = Carga.class)
public class CargaConverter implements Converter {

	@Inject
	private CargaService cargaService;

	/**
	 * 
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}
		Long id = Long.parseLong(value);
			return cargaService.findById(id);
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
		Long id = ((Carga) value).getId();
		return (id != null) ? id.toString() : null;
	}

}
