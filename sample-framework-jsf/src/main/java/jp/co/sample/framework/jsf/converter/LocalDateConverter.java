package jp.co.sample.framework.jsf.converter;

import jp.co.sample.common.code.DateFormat.DateFormatVo;
import jp.co.sample.common.util.LocalDateFormatUtils;
import java.time.LocalDate;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "localDateConverter", forClass = LocalDate.class)
public class LocalDateConverter implements Converter {
  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) {
    return LocalDateFormatUtils.parse(value, DateFormatVo.YYYYMMDD);
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    return LocalDateFormatUtils.format((LocalDate) value, DateFormatVo.YYYYMMDD);
  }

}
