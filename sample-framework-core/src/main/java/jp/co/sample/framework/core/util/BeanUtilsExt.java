package jp.co.sample.framework.core.util;

import jp.co.sample.framework.core.exception.ApplicationException;
import jp.co.sample.framework.core.exception.dto.ErrorMessage;
import jp.co.sample.framework.core.message.CoreMessageId;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import lombok.experimental.UtilityClass;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.SerializationUtils;

/**
 * Beanユーティリティー.
 * ※Apache Commonsとクラス名が重複するため別名
 */
@UtilityClass
public class BeanUtilsExt {

  /**
   * プロパティ名が同じ場合はすべて、コピー元のBeanからコピー先のBeanにプロパティ値をコピー(shallow copy)します.
   *
   * @param <T> 引数無しのデフォルトコンストラクターを持つ任意のクラス
   * @param type コピー先のBeanクラス
   * @param orig コピー元のBean
   * @return コピー先のBeanインスタンス
   */
  public static <T> T copyProperties(Class<T> type, final Object orig) {
    try {
      T dest = type.getDeclaredConstructor().newInstance();
      copyProperties(dest, orig);
      return dest;
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
        | SecurityException e) {
      throw new ApplicationException(e, new ErrorMessage(CoreMessageId.F0001E, type.getSimpleName(), orig.getClass().getSimpleName()));
    }
  }

  /**
   * プロパティ名が同じ場合はすべて、コピー元のBeanからコピー先のBeanにプロパティ値をコピー(shallow copy)します.
   *
   * @param dest コピー先のBean
   * @param orig コピー元のBean
   */
  public static void copyProperties(final Object dest, final Object orig) {
    try {
      BeanUtils.copyProperties(dest, orig);
    } catch (IllegalAccessException | InvocationTargetException e) {
      throw new ApplicationException(e, new ErrorMessage(CoreMessageId.F0001E, dest.getClass().getSimpleName(), orig.getClass().getSimpleName()));
    }
  }

  /**
   * コピー元のBeanからプロパティ値をコピー(deep copy)した新しいBeanを返します.
   *
   * @param <T> Serializableをimplementsした任意のクラス
   * @param orig コピー元のBean
   * @return コピーしたBeanインスタンス
   */
  public static <T extends Serializable> T deepCopy(final T orig) {
      return SerializationUtils.clone(orig);
  }

}
