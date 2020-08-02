package dev.sample.framework.jsf.converter;

import dev.sample.common.util.DateFormat.DateFormatVo;
import dev.sample.common.util.LocalDateFormatUtils;
import dev.sample.framework.core.message.CoreMessageId;
import dev.sample.framework.core.util.MessageUtils;
import dev.sample.framework.jsf.constant.ComponentAttribute;
import java.time.LocalDate;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * LocalDate型のコンバーター.
 */
@FacesConverter(value = "localDateConverter", forClass = LocalDate.class)
public class LocalDateConverter implements Converter {

  /**
   * 指定された値を解析してLocalDateのインスタンスを作成します.
   *
   * @param context {@link FacesContext}
   * @param component {@link UIComponent}
   * @param value 値
   * @return LocalDateのインスタンス
   * @throws ConverterException 変換に失敗した場合
   */
  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) {
    LocalDate obj = null;
    try {
      obj = LocalDateFormatUtils.parse(value, DateFormatVo.YYYYMMDD);
    } catch (Exception e) {
      String label = (String) component.getAttributes().get(ComponentAttribute.LABEL);
      throw new ConverterException(new FacesMessage(MessageUtils.getMessage(CoreMessageId.F1004E, label)), e);
    }
    return obj;
  }

  /**
   * 文字列に変換します.
   *
   * @param context {@link FacesContext}
   * @param component {@link UIComponent}
   * @param value {@link LocalDate}
   * @return 変換した文字列
   * @throws ConverterException 変換に失敗した場合
   */
  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    String str = null;
    try {
      str = LocalDateFormatUtils.format((LocalDate) value, DateFormatVo.YYYYMMDD);
    } catch (Exception e) {
      throw new ConverterException(new FacesMessage(MessageUtils.getMessage(CoreMessageId.F1003E)), e);
    }
    return str;
  }

}
