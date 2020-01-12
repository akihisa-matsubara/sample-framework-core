package jp.co.sample.framework.core.util;

import jp.co.sample.framework.core.exception.SystemException;
import jp.co.sample.framework.core.exception.dto.ErrorMessage;
import jp.co.sample.framework.core.message.CoreMessageId;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import lombok.experimental.UtilityClass;

/**
 * CDI ユーティリティー.
 */
@UtilityClass
public class CdiUtils {

  /**
   * Beanを取得します.
   *
   * @param <T> beanクラス
   * @param clazz beanクラス
   * @return beanインスタンス
   */
  public static <T> T getBean(Class<T> clazz) {
    Instance<T> instance = CDI.current().select(clazz);
    if (instance.isUnsatisfied()) {
      throw new SystemException(new ErrorMessage(CoreMessageId.F0008E, clazz.getName()));

    } else if (instance.isAmbiguous()) {
      throw new SystemException(new ErrorMessage(CoreMessageId.F0009E, clazz.getName()));

    }

    return instance.get();
  }

}
