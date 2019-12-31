package jp.co.sample.framework.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputSecret;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.commons.lang3.StringUtils;

@FacesConverter(value = "stringConverter", forClass = String.class)
public class StringConverter implements Converter {
  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) {
    if (component instanceof HtmlInputSecret) {
      return value;
    }
    return StringUtils.strip(value);
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    return value == null ? null : value.toString();
  }

}
