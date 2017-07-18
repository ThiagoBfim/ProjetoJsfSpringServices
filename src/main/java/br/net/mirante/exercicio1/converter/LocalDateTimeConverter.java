package br.net.mirante.exercicio1.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;

import org.springframework.stereotype.Component;

@Component("conveterDate")
public class LocalDateTimeConverter extends DateTimeConverter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {

		Object o = super.getAsObject(facesContext, uiComponent, value);
		if (o == null) {
			return null;
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(value, formatter);
		return dateTime;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		if (value == null) {
			return super.getAsString(facesContext, uiComponent, value);
		}
		if (value instanceof LocalDateTime) {
			LocalDateTime lDate = (LocalDateTime) value;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			String formatDateTime = lDate.format(formatter);
			return formatDateTime;
		} else {
			throw new IllegalArgumentException(String.format("value=%s is not a instanceof LocalDate", value));
		}
	}

}
