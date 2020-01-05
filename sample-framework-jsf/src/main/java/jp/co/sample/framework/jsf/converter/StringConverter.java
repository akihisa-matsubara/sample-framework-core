package jp.co.sample.framework.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputSecret;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.commons.lang3.StringUtils;

/**
 * String型のコンバーター.
 */
@FacesConverter(value = "stringConverter", forClass = String.class)
public class StringConverter implements Converter {

  /**
   * 指定された値を解析してStringを作成します.
   * 文字列前後の空白・制御文字を削除します.
   *
   * @param context {@link FacesContext}
   * @param component {@link UIComponent}
   * @param value 値
   * @return 変換した文字列
   */
  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) {
    if (component instanceof HtmlInputSecret) {
      return value;
    }
    return StringUtils.strip(value);
  }

  /**
   * 文字列に変換します.
   *
   * @param context {@link FacesContext}
   * @param component {@link UIComponent}
   * @param value 値
   * @return 変換した文字列
   */
  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    return value == null ? null : value.toString();
  }

}
