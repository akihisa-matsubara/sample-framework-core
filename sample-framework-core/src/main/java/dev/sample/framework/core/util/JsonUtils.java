package dev.sample.framework.core.util;

import dev.sample.framework.core.exception.SystemException;
import dev.sample.framework.core.mapper.JsonObjectMapper;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

/**
 * JSON ユーティリティー.
 */
@UtilityClass
public class JsonUtils {

  /** Object Mapper. */
  private static final ObjectMapper objectMapper;

  static {
    objectMapper = JsonObjectMapper.create();
  }

  /**
   * JSON文字列をBeanに読み込みます.
   *
   * @param <T> beanクラス
   * @param content JSON文字列
   * @param valueType beanの型
   * @return beanインスタンス
   */
  public static <T> T readValue(String content, Class<T> valueType) {
    try {
      return objectMapper.readValue(content, valueType);
    } catch (IOException e) {
      throw new SystemException(e);
    }
  }

  /**
   * BeanをJSON文字列に出力します.
   *
   * @param bean bean
   * @return JSON文字列
   */
  public static String writeValue(Object bean) {
    try {
      return objectMapper.writeValueAsString(bean);
    } catch (JsonProcessingException e) {
      throw new SystemException(e);
    }
  }

}
