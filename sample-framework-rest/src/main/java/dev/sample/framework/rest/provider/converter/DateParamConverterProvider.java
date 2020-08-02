package dev.sample.framework.rest.provider.converter;

import dev.sample.common.util.DateFormat.DateFormatVo;
import dev.sample.common.util.DateFormatUtilsExt;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Date;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

/**
 * Date型のパラメーターコンバーター.
 */
@Provider
public class DateParamConverterProvider implements ParamConverterProvider {

  /**
   * コンバーターを取得します.
   *
   * @param <T> {@link Date}
   * @param rawType 変換されるオブジェクトの型
   * @param genericType 変換されるオブジェクトの型
   * @param annotations annotations
   */
  @SuppressWarnings("unchecked")
  @Override
  public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
    if (rawType != Date.class) {
      return null;
    }
    return (ParamConverter<T>) new ParamConverter<Date>() {

      /**
       * 指定された値を解析してDateのインスタンスを作成します.
       *
       * @param value 値
       * @return Dateのインスタンス
       */
      @Override
      public Date fromString(String value) {
        return DateFormatUtilsExt.parse(value, DateFormatVo.YYYYMMDD);
      }

      /**
       * 文字列に変換します.
       *
       * @param date {@link Date}
       * @return 変換した文字列
       */
      @Override
      public String toString(Date date) {
        return DateFormatUtilsExt.format(date, DateFormatVo.YYYYMMDD);
      }
    };
  }

}
