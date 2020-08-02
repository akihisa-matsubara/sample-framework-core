package dev.sample.framework.core.util;

import dev.sample.framework.core.exception.SystemException;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.reflect.FieldUtils;

/**
 * Reflectionユーティリティー.
 */
@UtilityClass
public class ReflectionUtils {

  /**
   * オブジェクトからアノテーションが定義されたインスタンス変数を取得します.
   *
   * @param <T> PrimaryKeyの型
   * @param annotation アノテーション
   * @param target オブジェクト
   * @return インスタンス変数、アノテーションが見つからない場合はnull
   */
  @SuppressWarnings("unchecked")
  public static <T> T getAnnotatedField(@NonNull Class<? extends Annotation> annotation, @NonNull Object target) {
    List<Field> fieldsListWithAnnotation = FieldUtils.getFieldsListWithAnnotation(target.getClass(), annotation);
    if (!fieldsListWithAnnotation.isEmpty()) {
      Field field = fieldsListWithAnnotation.get(0);

      try {
        PropertyDescriptor pd = new PropertyDescriptor(field.getName(), target.getClass());
        return (T) pd.getReadMethod().invoke(target);

      } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
        throw new SystemException(e);

      }
    }

    return null;
  }
}
