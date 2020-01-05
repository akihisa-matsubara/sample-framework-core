package jp.co.sample.framework.jsf.converter;

import jp.co.sample.common.code.DateFormat.DateFormatVo;
import jp.co.sample.common.util.LocalDateFormatUtils;
import java.time.LocalDate;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
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
   */
  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) {
    return LocalDateFormatUtils.parse(value, DateFormatVo.YYYYMMDD);
  }

  /**
   * 文字列に変換します.
   *
   * @param context {@link FacesContext}
   * @param component {@link UIComponent}
   * @param value {@link LocalDate}
   * @return 変換した文字列
   */
  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    return LocalDateFormatUtils.format((LocalDate) value, DateFormatVo.YYYYMMDD);
  }

}
